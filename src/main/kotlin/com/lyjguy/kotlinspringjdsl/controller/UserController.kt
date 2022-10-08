package com.lyjguy.kotlinspringjdsl.controller

import com.lyjguy.kotlinspringjdsl.model.entity.User
import com.lyjguy.kotlinspringjdsl.service.UserService
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@Api(description = "사용자 API")
@RestController
@RequestMapping("/api/v1/users")
class UserController(
    private val userService: UserService,
) {

    @ApiOperation(value = "사용자 동록")
    @PostMapping
    fun createUser(): ResponseEntity<User> {
        return ResponseEntity.ok(userService.save())
    }

    @ApiOperation(value = "사용자 조회")
    @GetMapping("/{id}")
    fun getUser(@PathVariable id: Long): ResponseEntity<User?> {
        return ResponseEntity.ok(userService.findById(id))
    }
}