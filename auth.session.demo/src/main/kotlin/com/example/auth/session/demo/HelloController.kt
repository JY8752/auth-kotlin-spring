package com.example.auth.session.demo

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class HelloController {

    @GetMapping("/hello")
    suspend fun hello(): String {
        Thread.sleep(1000L)
        return "Hello"
    }
}