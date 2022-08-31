package com.lyjguy.kotlinspringjdsl.controller

import com.fasterxml.jackson.databind.ObjectMapper
import com.lyjguy.kotlinspringjdsl.service.UserService
import org.junit.jupiter.api.Test
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs

import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders
import org.springframework.test.context.TestConstructor
import org.springframework.test.web.servlet.MockMvc

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureRestDocs(uriScheme = "https", uriHost = "localhost")
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
class OrderReceiverControllerTest(
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

        mockMvc.perform(
            RestDocumentationRequestBuilders.get("$context/${id}", 1L)
        ).andDo(
            document(
                "getUser",
//                pathParameters(
//                    parameterWithName("id").description("id")
//                ),
//                responseFields(
//                    fieldWithPath("id").description("이메일"),
//                )
            )
        )

//        mockMvc.get("$context/$id")
//            .andDo {
//                print()
//                handle(
//                    document(
//                        "find-developer",
//                        pathParameters(
//                            parameterWithName("id").description("id")
//                        ),
//                        responseFields(
//                            fieldWithPath("id").description("이메일"),
//                        )
//                    )
//                )
//            }
//            .andExpect {
//                status { isOk() }
//                content { RequestPredicates.contentType(MediaType.APPLICATION_JSON) }
//                content { json(userJson) }
//            }
    }
}