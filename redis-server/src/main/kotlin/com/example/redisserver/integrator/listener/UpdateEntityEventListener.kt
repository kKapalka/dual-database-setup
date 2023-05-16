package com.example.redisserver.integrator.listener

import com.example.redisserver.config.BeanUtil
import com.example.redisserver.data.entity.UserEntity
import com.example.redisserver.redis.data.entity.CachedUserEntity
import com.example.redisserver.redis.data.repository.CachedUserRepository
import org.hibernate.event.spi.PostUpdateEvent
import org.hibernate.event.spi.PostUpdateEventListener
import org.hibernate.persister.entity.EntityPersister
import org.springframework.transaction.support.TransactionTemplate
import java.time.Instant

class UpdateEntityEventListener : PostUpdateEventListener {

    private var cachedUserRepository: CachedUserRepository? = null

    override fun requiresPostCommitHandling(persister: EntityPersister?): Boolean {
        return false
    }

    override fun onPostUpdate(event: PostUpdateEvent?) {
        if (cachedUserRepository == null)
            getBean()
        when (val entity = event?.entity) {
            is UserEntity -> {
                cachedUserRepository!!.save(
                    CachedUserEntity(
                        id = entity.id,
                        name = entity.name
                    )
                )
            }
        }
    }

    private fun getBean() {
        cachedUserRepository = BeanUtil.getBean(CachedUserRepository::class.java)
    }

}