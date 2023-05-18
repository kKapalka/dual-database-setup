package com.example.redisserver.rest

import com.example.redisserver.data.repository.UserRepository
import org.example.UserResponse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.cache.annotation.CachePut
import org.springframework.cache.annotation.Cacheable
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class UserController(
    @Autowired private val userService: UserService
) {

    @PostMapping("/user")
    fun createUser(
        @RequestBody request: UserRequest
    ): UserResponse {
        return userService.save(request.name)
    }

    @PutMapping("/user/{id}")
    fun updateUser(
        @PathVariable id: Long,
        @RequestBody request: UserRequest
    ): UserResponse {
        return userService.update(id, request.name)
    }

    @GetMapping("/user/{id}")
    fun getUser(
        @PathVariable id: Long,
    ): UserResponse {
        return userService.get(id)
    }

    @GetMapping("/user")
    fun getUsers(): List<UserResponse> {
        return userService.get()
    }
}


data class UserRequest(
    val name: String
)