package com.example.transactiontest.controller

import com.example.transactiontest.handler.AgreeHandler
import com.example.transactiontest.handler.Handlers
import com.example.transactiontest.handler.InjectedHandlers
import com.example.transactiontest.handler.RejectHandler
import com.example.transactiontest.mapper.UserMapper
import org.springframework.context.annotation.Lazy
import org.springframework.transaction.annotation.Propagation
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.lang.RuntimeException

@RestController
@RequestMapping("/")
class MainController(
    private val userMapper: UserMapper,
    @Lazy private val self: MainController,
    private val agreeHandler: AgreeHandler,
    private val rejectHandler: RejectHandler,
    private val injectedHandlers: InjectedHandlers
) {

    @GetMapping
    fun test(@RequestParam("type") type: Int,
             @RequestParam("id") id: Int,
             @RequestParam("name") name: String) {
        when (type) {
            1 -> self.test1(id, name)
            2 -> test2(id, name)
            3 -> self.test3(id, name)
            4 -> self.test4(id, name)
            5 -> self.test6(id, name)

            6 -> Handlers.handle(1)
            7 -> Handlers.handle(2)
            8 -> Handlers.handleTest()

            9 -> agreeHandler.handle()
            10 -> rejectHandler.handle()

            11 -> injectedHandlers.handle(1)
            12 -> injectedHandlers.handle(2)
            13 -> injectedHandlers.handleTest()
        }
    }

    @Transactional(readOnly = true)
    fun test1(id: Int, name: String) {
        userMapper.insert(id, name)
    }

    @Transactional
    fun test2(id: Int, name: String) {
        userMapper.insert(id + 1, name + "test2")
        throw RuntimeException("haha")
    }

    @Transactional
    fun test3(id: Int, name: String) {
        self.test1(id, name)
        self.test2(id, name)
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    fun test4(id: Int, name: String) {
        userMapper.insert(id, name)
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    fun test5(id: Int, name: String) {
        userMapper.insert(id + 1, name + "test5")
        throw RuntimeException("haha")
    }

    @Transactional
    fun test6(id: Int, name: String) {
        self.test4(id, name)
        self.test5(id, name)
    }


}