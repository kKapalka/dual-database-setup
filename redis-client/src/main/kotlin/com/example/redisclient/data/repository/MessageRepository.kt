package com.example.redisclient.data.repository

import com.example.redisclient.data.entity.MessageEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface MessageRepository: JpaRepository<MessageEntity, Long> {
    fun findAllByUserId(userId: Long): List<MessageEntity>
}