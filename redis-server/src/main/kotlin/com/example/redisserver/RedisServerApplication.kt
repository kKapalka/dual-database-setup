package com.example.redisserver

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cache.annotation.EnableCaching
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories

@SpringBootApplication
@EnableJpaRepositories
@EnableCaching(proxyTargetClass = true)
class RedisServerApplication

fun main(args: Array<String>) {
    runApplication<RedisServerApplication>(*args)
}
