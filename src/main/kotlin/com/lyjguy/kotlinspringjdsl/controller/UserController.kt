package com.lyjguy.kotlinspringjdsl.controller

import com.lyjguy.kotlinspringjdsl.model.entity.User
import com.lyjguy.kotlinspringjdsl.service.UserService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/api/v1/users")
class UserController(
    private val userService: UserService,
) {

    @PostMapping
    fun createUser(): ResponseEntity<Unit> {
        return ResponseEntity.ok(userService.save())
    }

    @GetMapping("/{id}")
    fun getUser(@PathVariable id: Long): ResponseEntity<User?> {
        return ResponseEntity.ok(userService.findById(id))
    }
}