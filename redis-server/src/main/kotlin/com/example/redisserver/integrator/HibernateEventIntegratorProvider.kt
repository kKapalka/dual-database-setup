package com.example.redisserver.integrator

import org.hibernate.integrator.spi.Integrator
import org.hibernate.jpa.boot.spi.IntegratorProvider


class HibernateEventIntegratorProvider : IntegratorProvider {
    override fun getIntegrators(): MutableList<Integrator> {
        return mutableListOf(HibernateEventIntegrator())
    }
}