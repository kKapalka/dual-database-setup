package com.example.redisserver.redis.data.entity

import org.springframework.data.redis.core.RedisHash
import org.springframework.data.redis.core.index.Indexed

@RedisHash("CachedUserEntity")
class CachedUserEntity (
    @Indexed
    val id: Long,
    val name: String
)