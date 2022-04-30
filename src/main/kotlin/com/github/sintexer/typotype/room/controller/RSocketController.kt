package com.github.sintexer.typotype.room.controller

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.flowOf
import mu.KotlinLogging
import org.springframework.messaging.handler.annotation.MessageMapping
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
    suspend fun messageStream(): Flow<Int> {
        return listOf(1,2,3,4,5)
            .asFlow()
    }

}