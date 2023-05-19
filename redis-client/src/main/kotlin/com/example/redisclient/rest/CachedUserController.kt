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
    @GetMapping("/{orgId}/user/{id}")
    fun getUser(
        @PathVariable orgId: Long,
        @PathVariable id: Long,
    ): UserResponse {
        return userService.getUserByIdAndOrgId(orgId, id)
    }

    @GetMapping("/user")
    fun getUsers(): List<UserResponse> {
        return userService.getUsers()
    }

    @GetMapping("/{orgId}/user")
    fun getUsersByOrgId(
        @PathVariable orgId: Long): List<UserResponse> {
        return userService.getUsersByOrgId(orgId)
    }
}