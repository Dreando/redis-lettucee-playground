package com.example.redisletucceplayground.comps

import org.springframework.data.redis.core.ListOperations
import org.springframework.stereotype.Component
import java.util.*
import javax.annotation.PostConstruct

@Component
class RedisClientWrapper(private val redisListOperations: ListOperations<String, Task>) {

    val names = arrayOf("aaa1", "aaa2", "aaa3", "aaa4", "aaa5", "aaa6")

    @PostConstruct
    fun init() {
        println()
    }
//    fun init() {
//
//        names.forEach {
//            for(i in 1..5) {
//                redisListOperations.leftPush("dispatched:$it", randomTask())
//            }
//        }
//
//        names.forEach {
//            for(i in 1..3) {
//                redisListOperations.leftPush("awaiting:$it", randomTask())
//            }
//        }
//
//        for(i in 1..10) {
//            redisListOperations.leftPush("needsExpert", randomTask())
//        }
//
//        for(i in 1..6) {
//            redisListOperations.leftPush("needsImprovement", randomTask())
//        }
//
//        for(i in 1..50) {
//            redisListOperations.leftPush("done", randomTask())
//        }
//    }

    fun randomTask(): Task {
        val random = Random()
        return Task(
                taskId = UUID.randomUUID().toString()
        )
    }
}