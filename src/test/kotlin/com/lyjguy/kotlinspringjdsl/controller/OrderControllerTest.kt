package com.lyjguy.kotlinspringjdsl.controller

import com.fasterxml.jackson.databind.ObjectMapper
import com.lyjguy.kotlinspringjdsl.service.OrderService
import org.junit.jupiter.api.Test
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs

import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders
import org.springframework.test.context.TestConstructor
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureRestDocs(uriScheme = "https", uriHost = "localhost")
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
class OrderControllerTest(
    private val mockMvc: MockMvc,
    private val objectMapper: ObjectMapper,
    private val orderService: OrderService,
) {

    private val context = "/api/v1/orders"

    @Test
    fun getOrder() {
        val id = 1L
        val user = orderService.findById(id)
        val orderJson = objectMapper.writeValueAsString(user)

        mockMvc.perform(
            RestDocumentationRequestBuilders.get("$context/${id}", 1L)
        ).andDo(
            document(
                "getOrder",
            )
        ).andExpectAll(
            status().isOk,
            content().contentType(MediaType.APPLICATION_JSON),
            content().json(orderJson)
        )
    }
}