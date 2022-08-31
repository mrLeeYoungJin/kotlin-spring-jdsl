package com.lyjguy.kotlinspringjdsl.service

import com.linecorp.kotlinjdsl.spring.data.SpringDataQueryFactory
import com.lyjguy.kotlinspringjdsl.model.dto.ResOrderReceiverDto
import com.lyjguy.kotlinspringjdsl.model.dto.toResOrderReceiverDto
import com.lyjguy.kotlinspringjdsl.model.entity.OrderReceiver
import com.lyjguy.kotlinspringjdsl.repository.OrderReceiverRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class OrderReceiverService(
    private val queryFactory: SpringDataQueryFactory,
    private val orderReceiverRepository: OrderReceiverRepository,
) {

    @Transactional
    fun findById(id: Long): ResOrderReceiverDto? {
        val orderReceiver = orderReceiverRepository.findByIdOrNull(id)
        return orderReceiver?.toResOrderReceiverDto()
    }

    @Transactional
    fun save(orderReceiver: OrderReceiver): OrderReceiver {
        return orderReceiverRepository.save(orderReceiver)
    }

    @Transactional
    fun findByOrderId(orderId: Long): ResOrderReceiverDto? {
        return orderReceiverRepository.findByOrderId(orderId)?.toResOrderReceiverDto()
    }
}