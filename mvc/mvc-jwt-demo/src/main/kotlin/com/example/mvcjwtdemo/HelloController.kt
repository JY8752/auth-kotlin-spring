package com.example.mvcjwtdemo

import com.example.mvcjwtdemo.security.HelloUserDetails
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class HelloController {
    @GetMapping("/hello")
    fun hello(@AuthenticationPrincipal userDetails: HelloUserDetails): String {
        val principal = SecurityContextHolder.getContext().authentication.principal
        println("------------------------------ userDetails $userDetails ----------------------------------")
        return "Hello"
    }
}