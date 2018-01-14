package com.example.redisletucceplayground

import com.example.redisletucceplayground.comps.JsonRedisSerializer
import com.example.redisletucceplayground.comps.Task
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.data.redis.connection.RedisStandaloneConfiguration
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory
import org.springframework.data.redis.core.ListOperations
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.serializer.StringRedisSerializer



@SpringBootApplication
class RedisLetuccePlaygroundApplication {

    @Value("\${spring.redis.host}")
    private var redisHost: String = ""

    @Value("\${spring.redis.port}")
    private var redisPort: Int = 0



    @Bean
    fun redisTemplate(lettuceConnectionFactory: LettuceConnectionFactory): RedisTemplate<String, Task> {
        return RedisTemplate<String, Task>().apply {
            this.connectionFactory = lettuceConnectionFactory
            this.keySerializer = StringRedisSerializer()
            this.valueSerializer = JsonRedisSerializer()
            this.setEnableTransactionSupport(true)
        }.also { it.afterPropertiesSet() }
    }

    @Bean
    fun redisListOperations(redisTemplate: RedisTemplate<String, Task>): ListOperations<String, Task>? {
        return redisTemplate.opsForList()
    }

    @Bean
    fun lettuceConnectionFactory(): LettuceConnectionFactory {
        return LettuceConnectionFactory(RedisStandaloneConfiguration().apply {
            this.hostName = redisHost
            this.port = redisPort
        })
    }
}

fun main(args: Array<String>) {
    runApplication<RedisLetuccePlaygroundApplication>(*args)
}
