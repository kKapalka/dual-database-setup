package com.example.redisserver

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories

@SpringBootApplication
@EnableRedisRepositories
@EnableJpaRepositories
class RedisServerApplication

fun main(args: Array<String>) {
    runApplication<RedisServerApplication>(*args)
}
