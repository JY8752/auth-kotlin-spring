package com.example.mvcjwtdemo.security

import com.example.mvcjwtdemo.utils.JWTUtils
import org.springframework.http.HttpHeaders
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/**
 * 認可の処理
 */
class AuthorizationFilter(authenticationManager: AuthenticationManager) :
    BasicAuthenticationFilter(authenticationManager) {
    override fun doFilterInternal(request: HttpServletRequest, response: HttpServletResponse, chain: FilterChain) {
        val token = request.getHeader(HttpHeaders.AUTHORIZATION).let {
            if (it == null || !it.startsWith("Bearer ")) {
                chain.doFilter(request, response)
                return
            }
            it.substring(7)
        }

        if (!JWTUtils.validateToken(token)) {
            chain.doFilter(request, response)
            return
        }

        val username = JWTUtils.getUsernameFromToken(token)
        //principalをuserDetailsにすればSecurityContextHolderから取得できるかな??
        val authentication =
            UsernamePasswordAuthenticationToken(HelloUserDetails(username, "password"), null, emptyList())
        SecurityContextHolder.getContext().authentication = authentication

        chain.doFilter(request, response)
    }
}