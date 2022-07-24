package com.lyjguy.kotlinspringjdsl.controller

import com.lyjguy.kotlinspringjdsl.model.dto.ResOrderReceiverDto
import com.lyjguy.kotlinspringjdsl.service.OrderReceiverService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/order_receivers")
class OrderReceiverController(
    val orderReceiverService: OrderReceiverService
) {

    @GetMapping("/{id}")
    fun getOrderReceiver(@PathVariable("id") id : Long): ResOrderReceiverDto? {
        return orderReceiverService.findById(id)
    }
}