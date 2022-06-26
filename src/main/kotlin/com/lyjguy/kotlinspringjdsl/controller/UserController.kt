package com.lyjguy.kotlinspringjdsl.controller

import com.lyjguy.kotlinspringjdsl.model.entity.User
import com.lyjguy.kotlinspringjdsl.service.UserService
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable


@Controller
class UserController(
    private val userService: UserService,
) {

    @GetMapping
    fun getUser(@PathVariable id: Long): User? {
        return userService.findById(id)
    }
}