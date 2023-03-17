package com.example.redisclient.rest

import com.example.redisclient.redis.data.entity.CachedUserEntity
import com.example.redisclient.redis.data.repository.CachedUserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class CachedUserController(
    @Autowired private val cachedUserRepository: CachedUserRepository
) {
    @GetMapping("/user/{id}")
    fun getUser(
        @PathVariable id: Long,
    ): CachedUserEntity {
        return cachedUserRepository.findById(id).get()
    }
}