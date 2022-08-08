package com.lyjguy.kotlinspringjdsl.repository

import com.linecorp.kotlinjdsl.querydsl.expression.col
import com.linecorp.kotlinjdsl.spring.data.SpringDataQueryFactory
import com.linecorp.kotlinjdsl.spring.data.singleQuery
import com.lyjguy.kotlinspringjdsl.model.entity.Order
import org.junit.jupiter.api.Test

import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.context.annotation.ComponentScan
import org.springframework.test.context.TestConstructor
import javax.persistence.NoResultException

@DataJpaTest
@ComponentScan("com.linecorp.kotlinjdsl")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
class OrderRepositoryTest(
    private val queryFactory: SpringDataQueryFactory,
) {

    @Test
    fun findById() {
        try {
            val id: Long = 1
            val order: Order? = queryFactory.singleQuery {
                select(entity(Order::class))
                from(entity(Order::class))
                where(col(Order::id).equal(id))
            }
            println("order : ${order}")
        } catch (nre: NoResultException) {
            println(0)
        }
    }
}