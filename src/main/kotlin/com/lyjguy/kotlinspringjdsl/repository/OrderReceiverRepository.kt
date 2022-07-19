package com.lyjguy.kotlinspringjdsl.repository

import com.lyjguy.kotlinspringjdsl.model.entity.OrderReceiver
import org.springframework.data.jpa.repository.JpaRepository

interface OrderReceiverRepository: JpaRepository<OrderReceiver, Long> {
}