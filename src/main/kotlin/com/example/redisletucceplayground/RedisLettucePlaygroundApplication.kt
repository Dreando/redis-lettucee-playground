package com.example.redisletucceplayground

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class RedisLettucePlaygroundApplication

fun main(args: Array<String>) {
    runApplication<RedisLettucePlaygroundApplication>(*args)
}
