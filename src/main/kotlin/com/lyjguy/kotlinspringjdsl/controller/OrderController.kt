package com.lyjguy.kotlinspringjdsl.controller

import com.lyjguy.kotlinspringjdsl.model.dto.ReqOrderDto
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
}