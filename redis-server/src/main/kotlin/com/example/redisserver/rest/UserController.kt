package com.example.redisserver.rest

import com.example.redisserver.data.entity.UserEntity
import com.example.redisserver.data.repository.UserRepository
import com.example.redisserver.redis.data.entity.CachedUserEntity
import com.example.redisserver.redis.data.repository.CachedUserRepository
import org.hibernate.service.spi.ServiceException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.transaction.annotation.Isolation
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import java.time.Instant
import kotlin.concurrent.thread

@RestController
class UserController(
    @Autowired private val userRepository: UserRepository,
    @Autowired private val userService: UserService
) {

    @PostMapping("/user")
    fun createUser(
        @RequestBody request: UserRequest
    ): UserEntity {
        return userService.save(request.name)
    }

    @PutMapping("/user/{id}")
    fun updateUser(
        @PathVariable id: Long,
        @RequestBody request: UserRequest
    ): UserEntity {
        return userService.update(id, request.name)
    }

    @GetMapping("/user/{id}")
    fun getUser(
        @PathVariable id: Long,
    ): UserEntity {
        return userRepository.findById(id).get()
    }
}


data class UserRequest(
    val name: String
)