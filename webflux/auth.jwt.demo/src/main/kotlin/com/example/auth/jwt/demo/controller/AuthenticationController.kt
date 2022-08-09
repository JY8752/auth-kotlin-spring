package com.example.auth.jwt.demo.controller

import com.example.auth.jwt.demo.utils.JWTUtils
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono

@RestController
class AuthenticationController(
    private val passwordEncoder: BCryptPasswordEncoder
) {
    @PostMapping("/login")
    fun login(@RequestBody request: LoginRequest): Mono<ResponseEntity<String>> {
        if (request.password != "password") {
            return Mono.just(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build())
        }
        return Mono.just(ResponseEntity.ok(JWTUtils.generateToken(request.username)))
    }
}

data class LoginRequest(
    val username: String,
    val password: String
)