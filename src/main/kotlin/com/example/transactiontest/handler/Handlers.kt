package com.example.transactiontest.handler

import org.springframework.stereotype.Component

class Handlers {

    companion object {
        private val map = mutableMapOf<Int, AbstractHandler>()

        fun register(handler: AbstractHandler) {
            map[handler.type()] = handler
        }

        fun handle(type: Int) {
            map[type]?.handle()
        }

        fun handleTest() {
            map[1]?.handle()
            map[2]?.handle()
        }
    }

}