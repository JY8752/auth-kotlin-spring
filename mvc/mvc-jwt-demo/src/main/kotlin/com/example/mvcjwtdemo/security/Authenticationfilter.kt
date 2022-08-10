package com.example.mvcjwtdemo.security

import com.example.mvcjwtdemo.utils.JWTUtils
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.http.HttpHeaders
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/**
 * 実際に認証処理をするクラス
 */
class Authenticationfilter(
    authenticationManager: AuthenticationManager,
    private val objectMapper: ObjectMapper
) : UsernamePasswordAuthenticationFilter() {
    init {
        this.authenticationManager = authenticationManager
    }

    override fun attemptAuthentication(request: HttpServletRequest, response: HttpServletResponse?): Authentication {
        val authRequest = this.objectMapper.readValue(request.inputStream, AuthRequest::class.java)
        return this.authenticationManager.authenticate(
            UsernamePasswordAuthenticationToken(
                authRequest.username,
                authRequest.password,
                emptyList()
            )
        )
    }

    /**
     * 認証が成功したときの処理
     */
    override fun successfulAuthentication(
        request: HttpServletRequest,
        response: HttpServletResponse,
        chain: FilterChain,
        authResult: Authentication
    ) {
        val user = authResult.principal as UserDetails
        val token = JWTUtils.generateToken(user.username)

        response.addHeader(HttpHeaders.AUTHORIZATION, "Bearer $token")
    }
}

data class AuthRequest(
    val username: String,
    val password: String
)