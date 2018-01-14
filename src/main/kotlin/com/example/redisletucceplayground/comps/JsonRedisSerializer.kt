package com.example.redisletucceplayground.comps

import com.fasterxml.jackson.annotation.JsonTypeInfo
import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import org.springframework.data.redis.serializer.RedisSerializer
import org.springframework.data.redis.serializer.SerializationException

class JsonRedisSerializer : RedisSerializer<Task> {

    private val objectMapper = ObjectMapper().registerKotlinModule().enableDefaultTyping(
            ObjectMapper.DefaultTyping.NON_FINAL, JsonTypeInfo.As.PROPERTY
    )

    override fun serialize(t: Task): ByteArray {
        return try {
            objectMapper.writeValueAsBytes(t)
        } catch (e: JsonProcessingException) {
            throw SerializationException("Can't serialize $t", e)
        }
    }

    override fun deserialize(bytes: ByteArray): Task {
        return try {
            objectMapper.readValue<Task>(bytes, Task::class.java)
        } catch (e: Exception) {
            throw SerializationException("Can't derialize $bytes", e)
        }
    }
}