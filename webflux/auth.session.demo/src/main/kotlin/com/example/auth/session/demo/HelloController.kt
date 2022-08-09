package com.example.auth.session.demo

import kotlinx.coroutines.coroutineScope
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import kotlin.coroutines.coroutineContext

@RestController
class HelloController {

    @GetMapping("/hello")
    suspend fun hello(): String {
        return "Hello"
    }

    @GetMapping("/blocking/hello")
    suspend fun blockinHello(): String {
        coroutineScope {
            Thread.sleep(1000)
        }

        Thread.sleep(1000)
        println(Thread.currentThread())
        println("----------------------- call blocking hello ----------------------------")
        return "Blocking Hello"
    }
}