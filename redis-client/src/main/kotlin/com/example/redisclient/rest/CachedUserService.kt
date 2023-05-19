package com.example.redisclient.rest

import org.example.UserResponse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.cache.CacheManager
import org.springframework.cache.annotation.Cacheable
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException
import org.springframework.data.redis.cache.RedisCacheWriter
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.stereotype.Component
import java.util.concurrent.ConcurrentHashMap


@Component
class CachedUserService(
    @Autowired private val redisTemplate: RedisTemplate<Any, Any>
) {

    @Cacheable(value= [UserResponse.cacheName], key="T(org.example.UserResponse).Companion.getKeyForOrgIdAndUserId(#orgId, #id)")
    fun getUserByIdAndOrgId(orgId: Long, id: Long): UserResponse {
        throw NotFoundException()
    }

    fun getUsers(): List<UserResponse> {
        val keys = redisTemplate.keys(UserResponse.getKeyPattern())
        redisTemplate.opsForValue().multiGet(keys)?.let {
            return (it as List<UserResponse>).sortedBy { it.id }
        } ?: return emptyList()
    }

    fun getUsersByOrgId(orgId: Long): List<UserResponse> {
        val keys = redisTemplate.keys(UserResponse.getKeyPatternForOrgId(orgId))
        redisTemplate.opsForValue().multiGet(keys)?.let {
            return (it as List<UserResponse>).sortedBy { it.id }
        } ?: return emptyList()
    }
}