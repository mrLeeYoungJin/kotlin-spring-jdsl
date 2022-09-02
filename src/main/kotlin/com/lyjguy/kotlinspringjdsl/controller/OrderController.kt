package com.lyjguy.kotlinspringjdsl.controller

import com.lyjguy.kotlinspringjdsl.model.dto.ReqOrderDto
import com.lyjguy.kotlinspringjdsl.model.entity.Order
import com.lyjguy.kotlinspringjdsl.service.OrderService
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@Api(description = "주문 API", tags = ["주문api"])
@RestController
@RequestMapping("/api/v1/orders")
class OrderController(
    val orderService: OrderService,
) {

    @ApiOperation(value = "주문 등록")
    @PostMapping
    fun savePost(@RequestBody reqOrderDto: ReqOrderDto): ResponseEntity<Order>{
        return ResponseEntity<Order>(orderService.save(reqOrderDto), HttpStatus.OK)
    }
    @ApiOperation(value = "주문 조회")
    @GetMapping
    fun getOrder(@PathVariable orderId: Long): ResponseEntity<Order> {
        return ResponseEntity<Order>(orderService.findById(orderId), HttpStatus.OK)
    }
}