package com.lyjguy.kotlinspringjdsl.service

import com.linecorp.kotlinjdsl.spring.data.SpringDataQueryFactory
import com.linecorp.kotlinjdsl.spring.data.singleQuery
import com.lyjguy.kotlinspringjdsl.model.entity.User
import com.lyjguy.kotlinspringjdsl.model.enum.UserType
import com.lyjguy.kotlinspringjdsl.repository.UserRepository
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.junit5.MockKExtension
import io.mockk.mockk
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MockKExtension::class)
class UserServiceTest {
    @MockK
    lateinit var queryFactory: SpringDataQueryFactory

    @RelaxedMockK
    lateinit var userRepository: UserRepository

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
        val user = User(
            name = "test",
            email = "test@test.com",
            password = "1234",
            userType = UserType.USER
        )

        every { userRepository.save(any()) } returns user

        userService.save()
    }
}