package com.example.redisletucceplayground

import com.example.redisletucceplayground.comps.Task
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.redis.core.ListOperations
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
@SpringBootTest
@ActiveProfiles("test")
class RedisLettucePlaygroundApplicationTests {

    @Value("\${spring.redis.host}")
    private var redisHost: String = ""

    @Value("\${spring.redis.port}")
    private var redisPort: Int = 0

    @Autowired
    lateinit var redisListOperations: ListOperations<String, Task>

    @Test
    fun contextLoads() {
        println("ok")
    }
}
