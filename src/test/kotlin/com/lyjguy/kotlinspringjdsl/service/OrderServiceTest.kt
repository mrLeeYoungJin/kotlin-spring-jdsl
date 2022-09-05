package com.lyjguy.kotlinspringjdsl.service

import com.linecorp.kotlinjdsl.spring.data.SpringDataQueryFactory
import com.linecorp.kotlinjdsl.spring.data.singleQuery
import com.lyjguy.kotlinspringjdsl.model.dto.ReqOrderDto
import com.lyjguy.kotlinspringjdsl.model.dto.ReqOrderReceiverDto
import com.lyjguy.kotlinspringjdsl.model.entity.Order
import com.lyjguy.kotlinspringjdsl.model.entity.OrderReceiver
import com.lyjguy.kotlinspringjdsl.model.entity.User
import com.lyjguy.kotlinspringjdsl.repository.OrderRepository
import com.lyjguy.kotlinspringjdsl.repository.UserRepository
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
class OrderServiceTest {
    @MockK
    lateinit var queryFactory: SpringDataQueryFactory

    @RelaxedMockK
    lateinit var orderRepository: OrderRepository

    @RelaxedMockK
    lateinit var orderReceiverService: OrderReceiverService

    @InjectMockKs
    lateinit var orderService: OrderService

    @Test
    fun findById() {
        val orderMock = mockk<Order>(relaxed = true) {
            every { id } returns 1
        }
        every { queryFactory.singleQuery<Order>(any()) } returns orderMock

        val user = orderService.findById(1)

        assertThat(user?.id).isEqualTo(1)
    }

    @Test
    fun save() {
        val reqOrderDto = ReqOrderDto(
            userId = 1,
            name = "test",
            totalPrice = 1000,
            receiver = ReqOrderReceiverDto(
                name = "test",
                address1 = "address1",
                address2 = "address2",
                zipcode = "12345",
            )
        )

        val order = Order(
            userId = 1,
            name = reqOrderDto.name,
            status = "ORDER",
            totalPrice = reqOrderDto.totalPrice,
        )

        every { orderRepository.save(any()) } returns order

        orderService.save(reqOrderDto)

        verify(exactly = 1) {
            orderRepository.save(any())
            orderReceiverService.save(any())
        }
    }
}