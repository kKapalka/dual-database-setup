package com.example.redisclient.rest

import org.example.UserResponse
import org.springframework.cache.annotation.Cacheable
import org.springframework.stereotype.Component

@Component
class CachedUserService {

    @Cacheable(value= ["users"], key="#id")
    fun getUser(id: Long): UserResponse {
        println("Trying to get user which is not in the cache")
        return UserResponse()
    }
}