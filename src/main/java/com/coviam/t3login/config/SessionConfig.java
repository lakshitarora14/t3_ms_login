package com.coviam.t3login.config;

import com.coviam.t3login.dto.SignupDto1;
import com.coviam.t3login.entity.Login;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;


@Configuration
public class SessionConfig {

    @Bean
    public JedisConnectionFactory connectionFactory() {
        return new JedisConnectionFactory();
    }

    @Bean
    public RedisTemplate<SignupDto1, String> redisTemplate() {
        RedisTemplate<SignupDto1, String> template = new RedisTemplate<>();
        template.setConnectionFactory(connectionFactory());
        boolean isDefault = template.isEnableDefaultSerializer();
        System.out.println(isDefault);
        return template;
    }
}
