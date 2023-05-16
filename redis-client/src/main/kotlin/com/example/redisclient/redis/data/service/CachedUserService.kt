package com.example.redisclient.redis.data.service

import com.example.redisclient.redis.data.entity.CachedUserEntity
import com.example.redisclient.redis.data.repository.ICachedUserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.core.script.DefaultRedisScript
import org.springframework.stereotype.Service
import java.util.Optional

@Service
class CachedUserService(
    @Autowired private val userRepository: ICachedUserRepository,
){
    fun existsById(userId: Long): Boolean {
        return userRepository.existsById(userId)
    }

    fun findById(userId: Long): Optional<CachedUserEntity> {
        return userRepository.findById(userId)
    }
}
