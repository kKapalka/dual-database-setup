package com.example.redisclient.rest

import com.example.redisclient.redis.data.entity.CachedUserEntity
import com.example.redisclient.redis.data.service.CachedUserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class CachedUserController(
    @Autowired private val cachedUserService: CachedUserService
) {
    @GetMapping("/user/{id}")
    fun getUser(
        @PathVariable id: Long,
    ): CachedUserEntity {
        return cachedUserService.findById(id).get()
    }
}