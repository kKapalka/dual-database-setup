package com.example.redisclient.rest

import org.example.UserResponse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class CachedUserController(
    @Autowired private val userService: CachedUserService
) {
    @GetMapping("/user/{id}")
    fun getUser(
        @PathVariable id: Long,
    ): UserResponse {
        return userService.getUser(id)
    }
}