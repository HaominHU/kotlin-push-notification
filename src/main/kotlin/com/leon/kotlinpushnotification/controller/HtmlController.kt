package com.leon.kotlinpushnotification.controller

import com.leon.kotlinpushnotification.model.Greeting
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.util.concurrent.atomic.AtomicLong

@RestController
class HtmlController {
    val counter = AtomicLong()
    @GetMapping("/hello")
    fun printOnWeb(@RequestParam(value = "name", defaultValue = "World") name: String): Greeting {
        return Greeting(counter.incrementAndGet(), "Hello, $name")
    }
}