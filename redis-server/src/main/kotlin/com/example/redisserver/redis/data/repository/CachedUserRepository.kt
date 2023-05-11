package com.example.redisserver.redis.data.repository

import com.example.redisserver.redis.data.entity.CachedUserEntity
import org.springframework.data.keyvalue.repository.KeyValueRepository
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface CachedUserRepository: CrudRepository<CachedUserEntity, Long>, KeyValueRepository<CachedUserEntity, Long> {
}