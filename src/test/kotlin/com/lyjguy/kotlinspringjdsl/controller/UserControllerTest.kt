package com.lyjguy.kotlinspringjdsl.controller

import com.fasterxml.jackson.databind.ObjectMapper
import com.lyjguy.kotlinspringjdsl.service.UserService
import org.junit.jupiter.api.Test

import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.context.TestConstructor
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get
import org.springframework.web.servlet.function.RequestPredicates

@SpringBootTest
@AutoConfigureMockMvc
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
class UserControllerTest(
    private val mockMvc: MockMvc,
    private val objectMapper: ObjectMapper,
    private val userService: UserService,
) {

    private val context = "/api/v1/users"

    @Test
    fun getUser() {
        userService.save()
        val id = 1L
        val user = userService.findById(id)
        val userJson = objectMapper.writeValueAsString(user)

        mockMvc.get("$context/$id")
            .andDo { print() }
            .andExpect {
                status { isOk() }
                content { RequestPredicates.contentType(MediaType.APPLICATION_JSON) }
                content { json(userJson) }
            }
    }
}