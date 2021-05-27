package com.example.transactiontest.handler

import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import javax.annotation.PostConstruct

@Component
class InjectedHandlers(
    private val abstractHandlers: List<AbstractHandler>
) {

    private val map = mutableMapOf<Int, AbstractHandler>()

    @PostConstruct
    private fun init() {
        abstractHandlers.forEach { map[it.type()] = it }
    }

    fun handle(type: Int) {
        map[type]?.handle()
    }

    @Transactional
    fun handleTest() {
        map[1]?.handle()
        map[2]?.handle()
    }

}