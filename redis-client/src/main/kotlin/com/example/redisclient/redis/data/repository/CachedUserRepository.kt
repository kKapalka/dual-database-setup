package com.example.redisclient.redis.data.repository

import com.example.redisclient.redis.data.entity.CachedUserEntity
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface CachedUserRepository: CrudRepository<CachedUserEntity, Long> {
}