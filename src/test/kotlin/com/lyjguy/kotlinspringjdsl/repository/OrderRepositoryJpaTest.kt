package com.lyjguy.kotlinspringjdsl.repository

import org.junit.jupiter.api.Test

import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.context.TestConstructor

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
class OrderRepositoryJpaTest(
    private val orderRepository: OrderRepository
) {

    @Test
    fun findById() {
        val id: Long = 1
        val order = orderRepository.findById(id)
        println("order : $order")
    }
}