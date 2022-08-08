package com.example.auth.jwt.demo.controller

import com.example.auth.jwt.demo.HelloUserDetails
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class HelloController {

    @GetMapping("/hello")
    suspend fun hello(@AuthenticationPrincipal userDetails: HelloUserDetails): ResponseEntity<HelloUserDetails> {
        return ResponseEntity.ok(userDetails)
    }
}