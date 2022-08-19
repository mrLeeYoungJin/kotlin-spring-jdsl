package com.lyjguy.kotlinspringjdsl.service

import com.linecorp.kotlinjdsl.spring.data.SpringDataQueryFactory
import com.linecorp.kotlinjdsl.spring.data.singleQuery
import com.lyjguy.kotlinspringjdsl.model.entity.OrderReceiver
import com.lyjguy.kotlinspringjdsl.model.entity.User
import com.lyjguy.kotlinspringjdsl.repository.OrderReceiverRepository
import com.lyjguy.kotlinspringjdsl.repository.UserRepository
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.junit5.MockKExtension
import io.mockk.mockk
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
}