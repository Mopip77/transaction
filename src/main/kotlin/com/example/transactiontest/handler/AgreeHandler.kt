package com.example.transactiontest.handler

import com.example.transactiontest.mapper.UserMapper
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Propagation
import org.springframework.transaction.annotation.Transactional

@Component
class AgreeHandler(
    private val userMapper: UserMapper
): AbstractHandler() {
    override fun type(): Int {
        return 1
    }

    override fun handle() {
        userMapper.insert(1, "agree")
    }
}