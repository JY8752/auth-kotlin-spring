package com.example.mvcsessiondemo.config

import com.example.mvcsessiondemo.AuthenticationRequest
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/**
 * 認証リクエストで通る処理
 * requestから認証情報を取り出す
 * UsernamePasswordAuthenticationTokenを作成する
 */
class AuthenticationFilter(
    authenticationManager: AuthenticationManager,
    private val objectMapper: ObjectMapper,
) : UsernamePasswordAuthenticationFilter(authenticationManager) {
    override fun attemptAuthentication(request: HttpServletRequest, response: HttpServletResponse?): Authentication {
        val principal = this.objectMapper.readValue(request.inputStream, AuthenticationRequest::class.java)
        val authRequest = UsernamePasswordAuthenticationToken(principal.email, principal.password)
        this.setDetails(request, authRequest)
        return this.authenticationManager.authenticate((authRequest))
    }
}