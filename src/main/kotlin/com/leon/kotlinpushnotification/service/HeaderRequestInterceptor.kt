package com.leon.kotlinpushnotification.service

import org.springframework.http.HttpRequest
import org.springframework.http.client.ClientHttpRequestExecution
import org.springframework.http.client.ClientHttpRequestInterceptor
import org.springframework.http.client.ClientHttpResponse
import org.springframework.http.client.support.HttpRequestWrapper


class HeaderRequestInterceptor (private val headerName: String, private val headerValue: String): ClientHttpRequestInterceptor {
    override fun intercept(request: HttpRequest, body: ByteArray, execution: ClientHttpRequestExecution): ClientHttpResponse {
        val wrapper: HttpRequest = HttpRequestWrapper(request)
        wrapper.headers.set(headerName, headerValue)
        return execution.execute(wrapper, body)
    }
}