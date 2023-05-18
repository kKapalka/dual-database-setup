package com.example.redisclient

import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cache.annotation.EnableCaching
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories

@SpringBootApplication
@EnableJpaRepositories
@EnableCaching(proxyTargetClass = true)
class RedisClientApplication

fun main(args: Array<String>) {
    runApplication<RedisClientApplication>(*args)
}
