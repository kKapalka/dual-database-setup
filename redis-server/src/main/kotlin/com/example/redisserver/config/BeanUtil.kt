package com.example.redisserver.config

import jakarta.annotation.PostConstruct
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.ApplicationContext
import org.springframework.stereotype.Service

@Service
class BeanUtil {

    @Autowired
    private lateinit var applicationContext: ApplicationContext

    @PostConstruct
    fun init() {
        instance = this
    }

    companion object {
        var instance: BeanUtil? = null

        fun <T> getBean(beanClass: Class<T>): T? {
            return instance?.applicationContext?.getBean(beanClass)
        }
    }
}