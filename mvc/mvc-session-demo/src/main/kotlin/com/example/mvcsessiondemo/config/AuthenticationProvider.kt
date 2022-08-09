package com.example.mvcsessiondemo.config

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Component

/**
 * 実際の認証処理を行うクラス
 */
@Component
class AuthenticationProvider : org.springframework.security.authentication.AuthenticationProvider {
    override fun authenticate(authentication: Authentication): Authentication {
        val email = authentication.principal as String
        val password = authentication.credentials as String

        /** ユーザー情報の検証 */

        val user = HelloUserDetails(email, password)
        return UsernamePasswordAuthenticationToken(user, password, emptyList())
    }

    override fun supports(authentication: Class<*>?): Boolean {
        return UsernamePasswordAuthenticationToken::class.java.isAssignableFrom(authentication)
    }
}