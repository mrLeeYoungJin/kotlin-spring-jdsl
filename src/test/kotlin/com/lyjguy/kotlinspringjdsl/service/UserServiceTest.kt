package com.lyjguy.kotlinspringjdsl.service

import com.linecorp.kotlinjdsl.spring.data.SpringDataQueryFactory
import com.linecorp.kotlinjdsl.spring.data.singleQuery
import com.lyjguy.kotlinspringjdsl.model.entity.User
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.mockk
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MockKExtension::class)
class UserServiceTest {
    @MockK
    lateinit var queryFactory: SpringDataQueryFactory

    @InjectMockKs
    lateinit var userService: UserService

    @Test
    fun findById() {
        val userMock = mockk<User>(relaxed = true) {
            every { id } returns 1
        }
        every { queryFactory.singleQuery<User>(any()) } returns userMock

        val user = userService.findById(1)

        assertThat(user?.id).isEqualTo(1)
    }

    @Test
    fun save() {
    }
}