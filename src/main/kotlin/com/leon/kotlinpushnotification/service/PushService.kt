package com.leon.kotlinpushnotification.service

import org.springframework.core.ParameterizedTypeReference
import org.springframework.http.HttpEntity
import org.springframework.http.HttpMethod
import org.springframework.http.RequestEntity
import org.springframework.http.client.ClientHttpRequestInterceptor
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.client.RestTemplate
import org.springframework.web.client.postForEntity
import java.net.URI
import java.util.concurrent.CompletableFuture

@Service
class PushService {
    private val key: String= "AAAAZkZxXSo:APA91bEN16hgLVqpF3BCjKj90iW7Sy8ognPgHAwx1eeSYILFzTY_6G6Yns1XwERbLTlK38dIIZ_pRgB7Cj9tKDK4ntiAyuJn2oKgHiHgXFi0uW-gMYAoH9zs5oF4WhDzTvH3jPrk9jc3"
    private val url: String = "https://fcm.googleapis.com/fcm/send"

    @Async
    fun send(entity: HttpEntity<String>): CompletableFuture<String> {
        val restTemplate = RestTemplate();
        val interceptors = arrayListOf<ClientHttpRequestInterceptor>()

        interceptors.add(HeaderRequestInterceptor("Authorization", "key=$key"))
        interceptors.add(HeaderRequestInterceptor("Content-Type", "application/json"))
        restTemplate.interceptors = interceptors

        val response = restTemplate.exchange(url, HttpMethod.POST, entity, typeRef<String>())
//        val response = restTemplate.postForObject(url, entity, String::class.java)
        println("1.$response")
        return CompletableFuture.completedFuture(response.body)

//        inline fun <reified T: Any> typeRef(): ParameterizedTypeReference<T> = object: ParameterizedTypeReference<T>(){}
//        val response = restTemplate.exchange(request, typeRef<List<String>>())
    }

    private inline fun <reified T: Any> typeRef(): ParameterizedTypeReference<T> = object: ParameterizedTypeReference<T>(){}
}