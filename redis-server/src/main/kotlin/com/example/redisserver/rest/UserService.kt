package com.example.redisserver.rest

import com.example.redisserver.data.entity.UserEntity
import com.example.redisserver.data.repository.UserRepository
import org.hibernate.service.spi.ServiceException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import java.time.Instant

@Component
class UserService(
    @Autowired private val userRepository: UserRepository
) {


    @Transactional
    fun save(name: String): UserEntity {
        return userRepository.save(
            UserEntity(
            name = name
        ))
    }

    @Transactional
    fun update(id: Long, name: String) : UserEntity {
        val user = userRepository.findById(id).orElseThrow {
            ServiceException("User doesn't exist!")
        }
        return userRepository.save(
            user.apply {
                this.name = name
            }
        )
    }
}