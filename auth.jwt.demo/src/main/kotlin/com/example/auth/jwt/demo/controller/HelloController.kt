package com.example.auth.jwt.demo.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class HelloController {

    @GetMapping("/hello")
    suspend fun hello(): String {
        return "Hello"
    }
}