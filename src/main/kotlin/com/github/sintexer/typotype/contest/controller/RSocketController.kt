package com.github.sintexer.typotype.contest.controller

import kotlinx.coroutines.flow.*
import mu.KotlinLogging
import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.messaging.handler.annotation.Payload
import org.springframework.messaging.rsocket.RSocketRequester
import org.springframework.stereotype.Controller

@Controller
class RSocketController {
    private val log = KotlinLogging.logger {}

    @MessageMapping("message.post")
    suspend fun postMessage(message: String): String {
        log.error { message }
        return message
    }

    @MessageMapping("stream")
    suspend fun messageStream(requester: RSocketRequester): Flow<Int> {
        return flow {
            for (i in 1..10) {
                kotlinx.coroutines.delay(2000)
                emit(i)
            }
        }

    }

    @MessageMapping("channel")
    suspend fun channel(@Payload ints: Flow<Int>): Flow<Int> {
        return ints
            .onEach { log.info{it} }
            .map { it+1000 }
    }

}