package com.example.redisclient.data.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "messages")
class MessageEntity (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    val id: Long = 0,

    @Column(name = "user_id", updatable = false)
    val userId: Long,

    @Column(name = "message", updatable = false)
    val message: String = "",

)