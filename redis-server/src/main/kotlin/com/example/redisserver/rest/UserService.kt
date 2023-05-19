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
    @CachePut(value=[UserResponse.cacheName], key="T(org.example.UserResponse).Companion.setKey(#result)", unless="#result == null")
    fun save(name: String, orgId: Long): UserResponse {
        return userRepository.save(
            UserEntity(
                name = name,
                organizationId = orgId
        )).toResponse()
    }

    @Transactional
    @CachePut(value=[UserResponse.cacheName], key="T(org.example.UserResponse).Companion.setKey(#result)", unless="#result == null")
    fun update(id: Long, name: String, orgId: Long) : UserResponse {
        val user = userRepository.findById(id).orElseThrow {
            ServiceException("User doesn't exist!")
        }
        return userRepository.save(
            user.apply {
                this.name = name
                this.organizationId = orgId
            }
        ).toResponse()
    }

    fun get(): List<UserResponse> {
        val usersCache = cacheManager.getCache(UserResponse.cacheName)
            ?: throw IllegalStateException("Cannot find cache users")
        usersCache.invalidate()
        return userRepository.findAll().map {
            val resp = it.toResponse()
            usersCache.put(UserResponse.setKey(resp), resp)
            resp
        }.sortedBy { it.id }
    }
}