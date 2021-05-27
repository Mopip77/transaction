package com.example.transactiontest.handler

import org.springframework.transaction.annotation.Propagation
import org.springframework.transaction.annotation.Transactional

abstract class AbstractHandler {

    init {
        Handlers.register(this)
    }

    abstract fun type(): Int

//    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Transactional
    abstract fun handle()

}