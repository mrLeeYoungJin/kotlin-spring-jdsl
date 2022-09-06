package com.lyjguy.kotlinspringjdsl.service

import com.linecorp.kotlinjdsl.spring.data.SpringDataQueryFactory
import com.linecorp.kotlinjdsl.spring.data.singleQuery
import com.lyjguy.kotlinspringjdsl.model.entity.Order
import com.lyjguy.kotlinspringjdsl.model.entity.OrderReceiver
import com.lyjguy.kotlinspringjdsl.repository.OrderReceiverRepository
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.junit5.MockKExtension
import io.mockk.mockk
import io.mockk.verify
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MockKExtension::class)
class OrderReceiverServiceTest {
    @MockK
    lateinit var queryFactory: SpringDataQueryFactory

    @RelaxedMockK
    lateinit var orderReceiverRepository: OrderReceiverRepository

    @InjectMockKs
    lateinit var orderReceiverService: OrderReceiverService

    @Test
    fun findById() {
        val orderReceiverMock = mockk<OrderReceiver>(relaxed = true) {
            every { id } returns 1
        }
        every { queryFactory.singleQuery<OrderReceiver>(any()) } returns orderReceiverMock

        val orderReceiver = orderReceiverService.findById(1)

        assertThat(orderReceiver?.id).isEqualTo(1)
    }

    @Test
    fun save() {
        val order = Order(
            userId = 1,
            name = "test",
            status = "ORDER",
            totalPrice = 1000,
        )

        val orderReceiver = OrderReceiver(
            orderId = order.id,
            name = "test",
            address1 = "address1",
            address2 = "address2",
            zipcode = "12345",
            order = order
        )

        every { orderReceiverRepository.save(any()) } returns orderReceiver

        orderReceiverService.save(orderReceiver)

        verify(exactly = 1) {
            orderReceiverService.save(any())
        }
    }

    @Test
    fun findByOrderId() {
        val orderId = 1L

        val order = Order(
            id = orderId,
            userId = 1,
            name = "test",
            status = "ORDER",
            totalPrice = 1000,
        )

        val orderReceiver = OrderReceiver(
            id = 1,
            orderId = order.id,
            name = "test",
            address1 = "address1",
            address2 = "address2",
            zipcode = "12345",
            order = order
        )

        every { orderReceiverRepository.findByOrderId(orderId) } returns orderReceiver

        val resOrderReceiverDto = orderReceiverService.findByOrderId(orderId)

        assertThat(resOrderReceiverDto?.id).isEqualTo(1L)
    }
}