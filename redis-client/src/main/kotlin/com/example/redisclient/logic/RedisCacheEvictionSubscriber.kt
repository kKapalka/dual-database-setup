package com.example.redisclient.logic

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.cache.CacheManager
import org.springframework.data.redis.connection.Message
import org.springframework.data.redis.connection.MessageListener
import org.springframework.stereotype.Component
import java.time.Instant

@Component
class RedisCacheEvictionSubscriber(
    @Autowired private val cacheManager: CacheManager,
): MessageListener {

    override fun onMessage(message: Message, pattern: ByteArray?) {
        val (cacheName, key) = String(message.body).split("::")
        cacheManager.getCache(cacheName)?.evict(key)
    }
}