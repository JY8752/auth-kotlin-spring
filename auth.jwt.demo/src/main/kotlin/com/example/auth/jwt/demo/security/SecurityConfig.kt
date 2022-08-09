package com.example.auth.jwt.demo.security

import org.springframework.context.annotation.Bean
import org.springframework.http.HttpStatus
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity
import org.springframework.security.config.web.server.ServerHttpSecurity
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.web.server.SecurityWebFilterChain
import reactor.core.publisher.Mono

@EnableWebFluxSecurity
class SecurityConfig(
    private val authenticationManager: AuthenticationManager,
    private val securityContextRepository: SecurityContextRepository,
) {

    @Bean
    fun securityFilterChain(
        http: ServerHttpSecurity,
    ): SecurityWebFilterChain {
        //csrf無効化
        http.csrf().disable()
            .formLogin().disable()
            .httpBasic().disable()

        //ログインのみ認証不要。そのほかは認証要
        http.authorizeExchange()
            .pathMatchers("/login").permitAll()
            .anyExchange().authenticated()

        //認証・認可失敗時の処理
        http.exceptionHandling()
            .authenticationEntryPoint { swe, _ ->
                Mono.fromRunnable { swe.response.statusCode = HttpStatus.UNAUTHORIZED }
            }
            .accessDeniedHandler { swe, _ ->
                Mono.fromRunnable { swe.response.statusCode = HttpStatus.FORBIDDEN }
            }

        //カスタムした認証処理を挟む
        http.authenticationManager(this.authenticationManager)
            .securityContextRepository(this.securityContextRepository)

        return http.build()
    }

    @Bean
    fun passwordEncoder() = BCryptPasswordEncoder()
}