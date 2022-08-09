package com.example.auth.jwt.demo.security

import com.example.auth.jwt.demo.HelloUserDetailsService
import com.example.auth.jwt.demo.utils.JWTUtils
import org.springframework.security.authentication.UserDetailsRepositoryReactiveAuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Component
import reactor.core.publisher.Mono

@Component
class AuthenticationManager(
    private val userDetailsService: HelloUserDetailsService,
) : UserDetailsRepositoryReactiveAuthenticationManager(userDetailsService) {
    override fun authenticate(authentication: Authentication): Mono<Authentication> {
        val authToken = authentication.credentials.toString()
        val username = JWTUtils.getUsernameFromToken(authToken)

        return Mono.just(JWTUtils.validateToken(authToken))
            .filter { it }
            .switchIfEmpty(Mono.empty())
            .map {
                UsernamePasswordAuthenticationToken(
                    username,
                    null,
                    emptyList() //必要であればロール設定を入れる
                )
            }
    }
}