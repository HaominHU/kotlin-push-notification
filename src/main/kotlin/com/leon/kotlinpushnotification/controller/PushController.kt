package com.leon.kotlinpushnotification.controller

import com.leon.kotlinpushnotification.model.Notifications
import com.leon.kotlinpushnotification.repository.NotificationsRepository
import com.leon.kotlinpushnotification.service.PushService
import org.json.JSONObject
import org.springframework.http.HttpEntity
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import java.util.concurrent.CompletableFuture
import java.util.concurrent.ExecutionException

@RestController
class PushController(private val pushService: PushService, private val notificationsRepository: NotificationsRepository) {
    private final val topic = "kotlin"

    @GetMapping("/send", produces = ["application/json"])
    fun send():ResponseEntity<String> {
//        val notification = mapOf("title" to "Kotlin Push", "body" to "Succeed")
//        val body = mapOf("to" to "/topics/$topic", "priority" to "high", "notification" to notification)

        val body = JSONObject()
        body.put("to", "/topics/$topic")
        body.put("priority", "high");

        val notification = JSONObject()
        notification.put("title", "Kotlin Push")
        notification.put("body", "Succeed")

        body.put("notification", notification)

        println("2.$body")
        val request = HttpEntity(body.toString())
        val push = pushService.send(request)
        println("3.$push")
        CompletableFuture.allOf(push).join()

        try {
            val response = push.get()
            var n = Notifications()
            n = notificationsRepository.saveAndFlush(n)
            return ResponseEntity(response, HttpStatus.OK)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        } catch (e: ExecutionException) {
            e.printStackTrace()
        }

        return ResponseEntity("Error", HttpStatus.BAD_REQUEST)

    }

}