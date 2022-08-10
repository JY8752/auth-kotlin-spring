package com.example.mvcjwtdemo.security

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserDetailsService(
    private val passwordEncoder: BCryptPasswordEncoder
) : UserDetailsService {
    override fun loadUserByUsername(username: String): UserDetails {
        //        //本当はここでちゃんとDBからユーザー情報を取得する
        val pass = this.passwordEncoder.encode("password")
        return (HelloUserDetails(username, pass))
    }
}

data class HelloUserDetails(val email: String, val pass: String) : UserDetails {
    override fun getAuthorities(): MutableCollection<out GrantedAuthority>? {
        return null
    }

    override fun getPassword(): String {
        return this.pass
    }

    override fun getUsername(): String {
        return this.email
    }

    //アカウントの有効期限
    override fun isAccountNonExpired(): Boolean {
        return true
    }

    //アカウントロック状態かどうか
    override fun isAccountNonLocked(): Boolean {
        return true
    }

    //権限に有効期限の設定があるかどうか
    override fun isCredentialsNonExpired(): Boolean {
        return true
    }

    //有効なユーザーかどうか
    override fun isEnabled(): Boolean {
        return true
    }
}