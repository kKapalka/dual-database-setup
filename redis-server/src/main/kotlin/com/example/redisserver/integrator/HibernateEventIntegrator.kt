package com.example.redisserver.integrator

import com.example.redisserver.integrator.listener.InsertEntityEventListener
import com.example.redisserver.integrator.listener.UpdateEntityEventListener
import org.hibernate.boot.Metadata
import org.hibernate.engine.spi.SessionFactoryImplementor
import org.hibernate.event.service.spi.EventListenerRegistry
import org.hibernate.event.spi.EventType
import org.hibernate.integrator.spi.Integrator
import org.hibernate.service.spi.SessionFactoryServiceRegistry

class HibernateEventIntegrator : Integrator {

    private val insertEntityEventListener = InsertEntityEventListener()
    private val updateEntityEventListener = UpdateEntityEventListener()

    override fun integrate(
        metadata: Metadata,
        sessionFactory: SessionFactoryImplementor,
        serviceRegistry: SessionFactoryServiceRegistry
    ) {
        val eventListenerRegistry = serviceRegistry.getService(EventListenerRegistry::class.java)
        eventListenerRegistry.appendListeners(
            EventType.POST_INSERT,
            insertEntityEventListener
        )
        eventListenerRegistry.appendListeners(
            EventType.POST_UPDATE,
            updateEntityEventListener
        )
    }

    override fun disintegrate(sessionFactory: SessionFactoryImplementor?, serviceRegistry: SessionFactoryServiceRegistry?) {}
}