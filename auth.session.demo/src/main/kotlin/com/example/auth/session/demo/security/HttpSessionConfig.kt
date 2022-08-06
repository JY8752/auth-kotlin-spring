package com.example.auth.session.demo.security

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession
import org.springframework.session.data.redis.config.annotation.web.server.EnableRedisWebSession

@EnableRedisWebSession //WebFluxはこっち
//@EnableRedisHttpSession
@Configuration
class HttpSessionConfig {
    @Bean
    fun connectionFactory(): LettuceConnectionFactory {
        return LettuceConnectionFactory()
    }
}