package com.example.mvcsessiondemo

import com.example.mvcsessiondemo.config.HelloUserDetails
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class HelloController {
    @GetMapping("/hello")
    fun hello(@AuthenticationPrincipal userDetails: HelloUserDetails): String {
        println("----------------------- userDetails: $userDetails -----------------")
        return "hello"
    }
}