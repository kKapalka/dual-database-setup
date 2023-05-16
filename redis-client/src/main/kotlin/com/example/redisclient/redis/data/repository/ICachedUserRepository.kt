package com.example.redisclient.redis.data.repository

import com.example.redisclient.redis.data.entity.CachedUserEntity
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.core.script.DefaultRedisScript
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Component
import org.springframework.stereotype.Repository
import java.lang.Long.parseLong
import java.util.Optional


@Repository
interface ICachedUserRepository: CrudRepository<CachedUserEntity, Long> {
}