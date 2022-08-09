package com.example.auth.session.demo.security

import com.example.auth.session.demo.JsonBodyAuthenticationConverter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.http.codec.ServerCodecConfigurer
import org.springframework.security.authentication.ReactiveAuthenticationManager
import org.springframework.security.authentication.UserDetailsRepositoryReactiveAuthenticationManager
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity
import org.springframework.security.config.web.server.SecurityWebFiltersOrder
import org.springframework.security.config.web.server.ServerHttpSecurity
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.web.server.SecurityWebFilterChain
import org.springframework.security.web.server.authentication.AuthenticationWebFilter
import org.springframework.security.web.server.context.WebSessionServerSecurityContextRepository
import org.springframework.security.web.server.util.matcher.ServerWebExchangeMatcher
import org.springframework.security.web.server.util.matcher.ServerWebExchangeMatchers
import org.springframework.web.server.WebFilter

@Configuration
@EnableWebFluxSecurity //SpringSecurityの有効化
@EnableReactiveMethodSecurity //APIのエンドポイントごとの認可設定を有効化
class SecurityConfig {
    @Bean
    fun securityFilterChain(
        http: ServerHttpSecurity,
        authenticationManager: ReactiveAuthenticationManager,
        serverCodecConfigurer: ServerCodecConfigurer
    ): SecurityWebFilterChain {
        //認証設定
        val authenticationFilter = authenticationWebFilter(
            authenticationManager,
            serverCodecConfigurer,
            ServerWebExchangeMatchers.pathMatchers(HttpMethod.POST, "/login")
        )
        http.addFilterAt(authenticationFilter, SecurityWebFiltersOrder.AUTHENTICATION)

        //ログインのみ認証不要。その他は全て認証必要
        http.authorizeExchange()
            .pathMatchers("/login").permitAll()
            .anyExchange().authenticated()

        //ここは頑張ってCSRFトークンを渡すようなフィルタを自作したほうが本当はいい
        http.csrf().disable()

        //認可で拒否した場合の処理
        http.exceptionHandling().authenticationEntryPoint(AuthenticationEntryPoint())

        return http.build()
    }

    @Bean
    fun authenticationManager(
        passwordEncoder: BCryptPasswordEncoder,
        userDetailsService: HelloUserDetailsService
    ): UserDetailsRepositoryReactiveAuthenticationManager {
        return UserDetailsRepositoryReactiveAuthenticationManager(userDetailsService).also {
            it.setPasswordEncoder(passwordEncoder)
        }
    }

    @Bean
    fun passwordEncoder() = BCryptPasswordEncoder()

    private fun authenticationWebFilter(
        authenticationManager: ReactiveAuthenticationManager,
        serverCodecConfigurer: ServerCodecConfigurer,
        loginPath: ServerWebExchangeMatcher
    ): WebFilter {
        return AuthenticationWebFilter(authenticationManager).also {
            //認証処理を行うリクエスト
            it.setRequiresAuthenticationMatcher(loginPath)
            //認証情報の抽出方法
            it.setServerAuthenticationConverter(JsonBodyAuthenticationConverter(serverCodecConfigurer.readers))
            //認証成功・失敗時の処理
            it.setAuthenticationSuccessHandler(AuthenticationSuccessHandler())
            it.setAuthenticationFailureHandler(AuthenticationFailureHandler())
            //セキュリティコンテキストの保存方法
            it.setSecurityContextRepository(WebSessionServerSecurityContextRepository())
        }
    }
}

