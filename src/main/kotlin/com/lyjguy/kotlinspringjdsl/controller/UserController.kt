package com.lyjguy.kotlinspringjdsl.controller

import com.lyjguy.kotlinspringjdsl.model.entity.User
import com.lyjguy.kotlinspringjdsl.service.UserService
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping


@Controller
@RequestMapping("/api/v1/users")
class UserController(
    private val userService: UserService,
) {

    @GetMapping("/{id}")
    fun getUser(@PathVariable id: Long): ResponseEntity<User?> {
        return ResponseEntity.ok(userService.findById(id))
    }
}