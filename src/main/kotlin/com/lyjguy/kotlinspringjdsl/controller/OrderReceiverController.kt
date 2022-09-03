package com.lyjguy.kotlinspringjdsl.controller

import com.lyjguy.kotlinspringjdsl.model.dto.ResOrderReceiverDto
import com.lyjguy.kotlinspringjdsl.service.OrderReceiverService
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/order_receivers")
@Api(description = "주문 수령자 API", tags = ["주문 수령자 api"])
class OrderReceiverController(
    val orderReceiverService: OrderReceiverService
) {
    @ApiOperation(value = "주문 수령자 조회")
    @GetMapping("/{id}")
    fun getOrderReceiver(@PathVariable("id") id : Long): ResOrderReceiverDto? {
        return orderReceiverService.findById(id)
    }
}
