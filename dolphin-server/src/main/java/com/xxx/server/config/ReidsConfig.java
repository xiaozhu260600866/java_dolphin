package com.xxx.server.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class ReidsConfig {
    @Bean
    public RedisTemplate<String,Object> redisTemplate(RedisConnectionFactory redisConnectionFactory){
        RedisTemplate<String,Object>redisTemplate  = new RedisTemplate<>();


        //String 类型key 序列器
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        //String 类型value 序列器
        redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        //Hasp 类型key 序列器
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        //Hasp 类型value 序列器
        redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        return redisTemplate;
    }

}
