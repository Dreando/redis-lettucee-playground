package com.example.redisletucceplayground.comps

import org.springframework.data.redis.core.ListOperations
import org.springframework.stereotype.Component
import javax.annotation.PostConstruct

@Component
class RedisClientWrapper(private val redisListOperations: ListOperations<String, Task>) {

    @PostConstruct
    fun init() {
        println()
    }

    fun leftPush(key: String, task: Task): Long {
        return redisListOperations.leftPush(key, task)!!
    }

    fun rightPopAndLeftPush(srcKey: String, destKey: String) {
        redisListOperations.rightPopAndLeftPush(srcKey, destKey)
    }
    fun deleteByUser(user: String, key: String) {
        redisListOperations.range(key, 0, Long.MAX_VALUE)?.filterNotNull()?.filter {
            it.user == user
        }?.forEach {
            redisListOperations.remove(key, 1, it)
        }
    }
}