package com.lyjguy.kotlinspringjdsl.repository

import com.linecorp.kotlinjdsl.querydsl.expression.col
import com.linecorp.kotlinjdsl.spring.data.SpringDataQueryFactory
import com.linecorp.kotlinjdsl.spring.data.singleQuery
import com.lyjguy.kotlinspringjdsl.model.entity.Order
import com.lyjguy.kotlinspringjdsl.model.entity.OrderReceiver
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
class OrderReceiverRepositoryTest(
    private val queryFactory: SpringDataQueryFactory,
) {

    @Test
    fun findById() {
        try {
            val id: Long = 1
            val orderReceiver: OrderReceiver? = queryFactory.singleQuery {
                select(entity(OrderReceiver::class))
                from(entity(OrderReceiver::class))
                where(col(OrderReceiver::id).equal(id))
            }
            println("orderReceiver : ${orderReceiver}")
        } catch (nre: NoResultException) {
            println(0)
        }
    }
}