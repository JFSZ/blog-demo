package com.example.blog.config.redis;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * @ClassName RedisConfig
 * @Author chenxue
 * @Description  redis配置类
 * @Date 2019/7/10 18:34
 **/

@Configuration
public class RedisConfig {
    @Bean
    @SuppressWarnings("ALL")
    public RedisTemplate<String,Object> redisTemplate(RedisConnectionFactory factory){
        RedisTemplate<String,Object> template = new RedisTemplate<>();
        template.setConnectionFactory(factory);
        return null;
    }
}
