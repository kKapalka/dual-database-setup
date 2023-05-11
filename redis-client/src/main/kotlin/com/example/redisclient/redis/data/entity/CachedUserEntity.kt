package com.example.redisclient.redis.data.entity

import org.springframework.data.redis.core.RedisHash
import org.springframework.data.redis.core.index.Indexed

@RedisHash("CachedUserEntity")
class CachedUserEntity(
    @Indexed
    var id: Long,
    var name: String
) {
    constructor() : this(0, "") {

    }
}