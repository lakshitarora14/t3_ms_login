package com.coviam.t3login.config;

import com.coviam.t3login.dto.LoginDto;
import com.coviam.t3login.dto.SignupDto1;
import com.coviam.t3login.entity.Login;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericToStringSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;


@Configuration
@EnableAutoConfiguration
public class SessionConfig {

    @Bean
    public JedisConnectionFactory connectionFactory() {
        return new JedisConnectionFactory();
    }

    @Bean
    public RedisTemplate<SignupDto1, String> redisTemplate() {
        RedisTemplate<SignupDto1, String> template = new RedisTemplate<>();
        template.setConnectionFactory(connectionFactory());
        return template;
    }


//    @Bean
//    public RedisTemplate<LoginDto, String> redisTemplate1() {
//        RedisTemplate<LoginDto, String> template1 = new RedisTemplate<>();
//        template1.setConnectionFactory(connectionFactory());
//        return template1;
//    }
}
