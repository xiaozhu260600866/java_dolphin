package com.xxx.server.config.security;

import com.xxx.server.config.security.component.*;
import com.xxx.server.pojo.User;
import com.xxx.server.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurtyConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private IUserService userService;

    @Autowired
    private RestAuthorizationEntryPoint restAuthenticationEntryPoint;
    @Autowired
    private RestfulAccessDeniedHandler restfulAccessDeniedHandler;

    @Autowired
    private CustomUrlDecisionManager customUrlDecisionManager;

    @Autowired
    private CustromFilter custromFilter;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService()).passwordEncoder(passwordEncoder());
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers(
                "/auth/login",
                "/auth/openid",
                "/*.txt",
                "/auth/updateUser",
                "/auth/logout",
                "/static/**",
                "/static/images/**",
                "/static/images/**/**",
                "favicon.ico",
                "/doc**",
                "/webjars/**",
                "/swagger-resources/**",
                "/v2/api-docs/**",
                "/captcha**",
                "/home/**",
                "/interceptor/**",
                "/wechat/**"


        );
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin().loginProcessingUrl("/auth/login");
        http.csrf()
                .disable()
                //??????token????????????session
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                 .anyRequest()
                .authenticated()
                //??????????????????
                .withObjectPostProcessor(new ObjectPostProcessor<FilterSecurityInterceptor>() {
                    @Override
                    public <O extends FilterSecurityInterceptor> O postProcess(O o) {
                        o.setAccessDecisionManager(customUrlDecisionManager);
                        o.setSecurityMetadataSource(custromFilter);
                        return o;
                    }
                })
                .and()
//????????????
                .headers()
                .cacheControl();
        //??????????????????????????????????????????

//??????jwt ?????????????????????
        http.addFilterBefore(jwtAuthencationTokenFilter(),
                UsernamePasswordAuthenticationFilter.class);
//????????????????????????????????????????????????
        http.exceptionHandling()
                .accessDeniedHandler(restfulAccessDeniedHandler)
                .authenticationEntryPoint(restAuthenticationEntryPoint);
    }

    @Override
    @Bean
    public UserDetailsService userDetailsService() {
//????????????????????????

        return userame -> {
            System.out.println("???????????????");
            User user = userService.getAdminByUserName(userame);
            if (null != user) {
                user.setRoles(userService.getRoles(user.getId()));
                return user;
            }
           throw  new UsernameNotFoundException("???????????????????????????");
        };
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Bean
    public JwtAuthencationTokenFilter jwtAuthencationTokenFilter(){
        return new JwtAuthencationTokenFilter();
    }
}
