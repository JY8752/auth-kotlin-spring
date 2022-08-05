package com.example.auth.session.demo.security

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.ReactiveUserDetailsService
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

@Service
class HelloUserDetailsService : ReactiveUserDetailsService{
    override fun findByUsername(username: String): Mono<UserDetails> {
        //本当はここでちゃんとDBからユーザー情報を取得する
        return Mono.just(HelloUserDetails(username))
    }
}

data class HelloUserDetails(val email: String) : UserDetails {
    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        return mutableListOf()
    }

    override fun getPassword(): String {
        return "password"
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