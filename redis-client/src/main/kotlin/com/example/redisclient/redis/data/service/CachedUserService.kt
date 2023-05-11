package com.example.redisclient.redis.data.service

import com.example.redisclient.redis.data.entity.CachedUserEntity
import com.example.redisclient.redis.data.repository.CachedUserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.cache.annotation.Cacheable
import org.springframework.stereotype.Service
import java.util.Optional

@Service
class CachedUserService(@Autowired private val userRepository: CachedUserRepository){
    @Cacheable(value = ["redisEntityCache"], key = "#userId")
    fun existsById(userId: Long): Boolean {
        Thread.sleep(500)
        return userRepository.existsById(userId)
    }

    @Cacheable(value = ["redisEntityCache"], key = "#userId")
    fun findById(userId: Long): Optional<CachedUserEntity> {
        Thread.sleep(500)
        return userRepository.findById(userId)
    }
}