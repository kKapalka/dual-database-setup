package com.example.redisclient.rest

import org.example.UserResponse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.cache.CacheManager
import org.springframework.cache.annotation.Cacheable
import org.springframework.data.redis.cache.RedisCacheWriter
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.stereotype.Component
import java.util.concurrent.ConcurrentHashMap


@Component
class CachedUserService(
    @Autowired private val redisTemplate: RedisTemplate<Any, Any>
) {

    @Cacheable(value= ["users"], key="#id")
    fun getUser(id: Long): UserResponse {
        println("Trying to get user which is not in the cache")
        return UserResponse()
    }

    fun getUsers(): List<UserResponse> {
        val keys = redisTemplate.keys("users:*")
        redisTemplate.opsForValue().multiGet(keys)?.let {
            return (it as List<UserResponse>).sortedBy { it.id }
        } ?: return emptyList()
    }
}