package com.example.redisserver.rest

import com.example.redisserver.data.entity.UserEntity
import com.example.redisserver.data.repository.UserRepository
import org.example.UserResponse
import org.hibernate.service.spi.ServiceException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.cache.CacheManager
import org.springframework.cache.annotation.CachePut
import org.springframework.cache.annotation.Cacheable
import org.springframework.cache.interceptor.SimpleKey
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
class UserService(
    @Autowired private val userRepository: UserRepository,
    @Autowired private val cacheManager: CacheManager,
) {

    @Transactional
    @CachePut(value=["users"], key="#result.id")
    fun save(name: String): UserResponse {
        return userRepository.save(
            UserEntity(
            name = name
        )).toResponse()
    }

    @CachePut(value=["users"], key="#id", unless="#result == null")
    fun update(id: Long, name: String) : UserResponse {
        val user = userRepository.findById(id).orElseThrow {
            ServiceException("User doesn't exist!")
        }
        return userRepository.save(
            user.apply {
                this.name = name
            }
        ).toResponse()
    }

    @Cacheable(value= ["users"], key="#id")
    fun get(id: Long): UserResponse {
        val user = userRepository.findById(id).orElseThrow {
            ServiceException("User doesn't exist!")
        }
        return user.toResponse()
    }

    fun get(): List<UserResponse> {
        val usersCache = cacheManager.getCache("users")
            ?: throw IllegalStateException("Cannot find cache users")
        usersCache.invalidate()
        return userRepository.findAll().map {
            val resp = it.toResponse()
            usersCache.put(resp.id, resp)
            resp
        }.sortedBy { it.id }
    }
}