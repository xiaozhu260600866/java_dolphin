package com.xxx.server.config;

import com.xxx.server.bean.MultiRequestBodyArgumentResolver;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.util.ResourceUtils;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.io.FileNotFoundException;
import java.nio.charset.Charset;
import java.util.List;

/**
 * Web Config Demo
 *
 * @author Wangyang Liu   liuwangyangedu@163.com
 * @date 2018/08/27
 */
@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {
    @Value("${file.uploadFolder}")
    private String realBasePath;
    @Value("${file.accessPath}")
    private String accessPath;
    @Value("${file.staticAccessPath}")
    private String staticAccessPath;

    @Value("${spring.profiles.active}")
    private String active;
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //设置允许静态资源;
        //registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
        registry.addResourceHandler("/swagger2/**").addResourceLocations("classpath:/swagger2/");
        registry.addResourceHandler("/*.txt").addResourceLocations("classpath:/a/");

        // 文件的真实路径
        System.out.println(active);
        if(active.equals("dev")){
            try {
                realBasePath = ResourceUtils.getFile("classpath:static").getPath() +"/";
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        registry.addResourceHandler(staticAccessPath).addResourceLocations("file:" + realBasePath);

    }
    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        // 添加MultiRequestBody参数解析器
        argumentResolvers.add(new MultiRequestBodyArgumentResolver());
    }
    @Bean
    public HttpMessageConverter<String> responseBodyConverter() {
        // 解决中文乱码问题
        return new StringHttpMessageConverter(Charset.forName("UTF-8"));
    }
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        super.configureMessageConverters(converters);
        converters.add(responseBodyConverter());
    }
}
