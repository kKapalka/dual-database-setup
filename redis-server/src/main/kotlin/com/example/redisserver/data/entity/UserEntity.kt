package com.example.redisserver.data.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import org.example.UserResponse
import java.io.Serializable

@Entity
@Table(name = "users")
class UserEntity (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    val id: Long = 0,

    @Column(name = "organization_id")
    var organizationId: Long = 0,

    @Column(name = "name")
    var name: String = ""
) {
    fun toResponse() : UserResponse {
        return UserResponse(
            id = this.id,
            orgId = this.organizationId,
            name = this.name
        )
    }
}