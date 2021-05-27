package com.example.transactiontest.handler

import com.example.transactiontest.mapper.UserMapper
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Propagation
import org.springframework.transaction.annotation.Transactional
import java.lang.RuntimeException

@Component
class RejectHandler(
    private val userMapper: UserMapper
): AbstractHandler() {
    override fun type(): Int {
        return 2
    }

    override fun handle() {
        userMapper.insert(2, "reject")
        throw RuntimeException("haha")
    }
}