package com.example.redisserver.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.cache.annotation.CachingConfigurerSupport
import org.springframework.cache.annotation.EnableCaching
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.connection.RedisConnectionFactory
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.serializer.GenericToStringSerializer
import org.springframework.data.redis.serializer.StringRedisSerializer


@Configuration
class RedisConfig : CachingConfigurerSupport() {

    @Value("\${spring.data.redis.host}")
    private val redisHost: String = ""

    @Value("\${spring.data.redis.port}")
    private val redisPort = 0

    @Value("\${spring.data.redis.database}")
    private val redisDatabase = 0

    @Bean
    fun redisConnectionFactory(): RedisConnectionFactory {
        val lettuceConnectionFactory = LettuceConnectionFactory(redisHost, redisPort)
        lettuceConnectionFactory.database = redisDatabase
        return lettuceConnectionFactory
    }

    @Bean
    fun redisTemplate(): RedisTemplate<String, Any> {
        val redisTemplate = RedisTemplate<String, Any>()
        redisTemplate.setConnectionFactory(redisConnectionFactory())
        redisTemplate.keySerializer = StringRedisSerializer()
        redisTemplate.valueSerializer = GenericToStringSerializer(Any::class.java)
        redisTemplate.hashKeySerializer = StringRedisSerializer()
        redisTemplate.hashValueSerializer = GenericToStringSerializer(Any::class.java)
        redisTemplate.afterPropertiesSet()
        return redisTemplate
    }
}