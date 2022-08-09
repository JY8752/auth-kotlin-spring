package com.example.mvcsessiondemo.config

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.context.annotation.Bean
import org.springframework.http.HttpStatus
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.util.matcher.AntPathRequestMatcher


@EnableWebSecurity
class SecurityConfig(
    private val authenticationProvider: AuthenticationProvider,
    private val objectMapper: ObjectMapper
) {
    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
        http.csrf().disable()

        http.authorizeRequests()
            .antMatchers("/login").permitAll()
            .anyRequest().authenticated()


        return http.build()
    }

    @Bean
    fun authenticationFilter(
        authenticationManager: AuthenticationManager
    ): AuthenticationFilter {
        val authFilter = AuthenticationFilter(authenticationManager, this.objectMapper).also {
            it.setRequiresAuthenticationRequestMatcher(AntPathRequestMatcher("/login", "POST"))
            it.setAuthenticationSuccessHandler { _, response, _ -> response.status = HttpStatus.OK.value() }
        }
        return authFilter
    }

    @Bean
    fun authenticationManager(authenticationConfiguration: AuthenticationConfiguration): AuthenticationManager {
        return authenticationConfiguration.authenticationManager
    }

//    @Bean
//    fun authenticationManager(
//        passwordEncoder: BCryptPasswordEncoder,
//        userDetailsService: HelloUserDetailsService
//    ): Authentication {
//        return AuthenticationM
//        return UserDetailsRepositoryReactiveAuthenticationManager(userDetailsService).also {
//            it.setPasswordEncoder(passwordEncoder)
//        }
//    }

    @Bean
    fun passwordEncoder() = BCryptPasswordEncoder()
}