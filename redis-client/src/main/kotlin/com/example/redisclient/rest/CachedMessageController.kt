package com.example.redisclient.rest

import com.example.redisclient.data.entity.MessageEntity
import com.example.redisclient.data.repository.MessageRepository
import org.hibernate.service.spi.ServiceException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class CachedMessageController(
    @Autowired private val messageRepository: MessageRepository,
    @Autowired private val userService: CachedUserService
) {
    @PostMapping("/message")
    fun createMessage(
        @RequestBody request: MessageRequest
    ): MessageEntity {
        if (userService.getUser(request.userId).id == 0L) throw ServiceException("User doesn't exist!")
        return messageRepository.save(
            MessageEntity(
                userId = request.userId,
                message = request.message
            )
        )
    }

    @GetMapping("/messages/{userId}")
    fun getMessagesForUser(
        @PathVariable userId: Long,
    ): List<MessageEntity> {
        if (userService.getUser(userId).id == 0L) throw ServiceException("User doesn't exist!")
        return messageRepository.findAllByUserId(userId)
    }
}

class MessageRequest(
    val userId: Long,
    val message: String
)