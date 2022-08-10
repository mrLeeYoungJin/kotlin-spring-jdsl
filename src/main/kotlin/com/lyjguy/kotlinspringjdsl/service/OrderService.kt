package com.lyjguy.kotlinspringjdsl.service

import com.linecorp.kotlinjdsl.querydsl.expression.col
import com.linecorp.kotlinjdsl.spring.data.SpringDataQueryFactory
import com.linecorp.kotlinjdsl.spring.data.singleQuery
import com.lyjguy.kotlinspringjdsl.model.dto.ReqOrderDto
import com.lyjguy.kotlinspringjdsl.model.entity.Order
import com.lyjguy.kotlinspringjdsl.model.entity.OrderReceiver
import com.lyjguy.kotlinspringjdsl.model.entity.User
import com.lyjguy.kotlinspringjdsl.repository.OrderRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import javax.persistence.NoResultException

@Service
class OrderService(
    private val orderRepository: OrderRepository,
    private val orderReceiverService: OrderReceiverService,
    private val queryFactory: SpringDataQueryFactory,
) {
    fun findById(id: Long): Order? {
        return try {
            queryFactory.singleQuery {
                select(entity(Order::class))
                from(entity(Order::class))
                where(col(Order::id).equal(id))
            }
        } catch (nre: NoResultException) {
            null
        }
    }

    @Transactional
    fun save(reqOrderDto: ReqOrderDto) {
        val order = Order(
            userId = 1,
            name = reqOrderDto.name,
            status = "ORDER",
            totalPrice = reqOrderDto.totalPrice,
        )

        orderRepository.save(order).also {
            val orderReceiver = OrderReceiver(
                orderId = it.id,
                name = reqOrderDto.receiver.name,
                address1 = reqOrderDto.receiver.address1,
                address2 = reqOrderDto.receiver.address2,
                zipcode = reqOrderDto.receiver.zipcode,
                order = it,
            )
            val savedOrderReceiver = orderReceiverService.save(orderReceiver)
            order.orderReceiver = orderReceiver
        }
    }

}