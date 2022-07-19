package com.lyjguy.kotlinspringjdsl.repository

import com.lyjguy.kotlinspringjdsl.model.entity.Order
import org.springframework.data.jpa.repository.JpaRepository

interface OrderRepository: JpaRepository<Order, Long>