package com.lyjguy.kotlinspringjdsl.controller

import com.lyjguy.kotlinspringjdsl.model.dto.ReqOrderDto
import com.lyjguy.kotlinspringjdsl.model.entity.Order
import com.lyjguy.kotlinspringjdsl.service.OrderService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/orders")
class OrderController(
    val orderService: OrderService,
) {

    @PostMapping
    fun savePost(@RequestBody reqOrderDto: ReqOrderDto) {
        orderService.save(reqOrderDto)
    }

    @GetMapping
    fun getOrder(@PathVariable orderId: Long): Order? {
        return orderService.findById(orderId)
    }
}