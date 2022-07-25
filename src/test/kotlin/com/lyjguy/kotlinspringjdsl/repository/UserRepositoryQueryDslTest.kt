package com.lyjguy.kotlinspringjdsl.repository

import com.lyjguy.kotlinspringjdsl.model.entity.QUser
import com.lyjguy.kotlinspringjdsl.model.entity.User
import com.lyjguy.kotlinspringjdsl.model.enum.UserType
import com.querydsl.jpa.impl.JPAQueryFactory
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager
import org.springframework.test.context.TestConstructor
import javax.persistence.EntityManager

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
class UserRepositoryQueryDslTest(
    private val entityManager: EntityManager,
    private val userRepository: UserRepository,
) {
    private lateinit var queryFactory: JPAQueryFactory

    @BeforeEach
    fun init() {
        queryFactory = JPAQueryFactory(entityManager)
    }

    @Test
    fun queryDslTest() {
        val userSaved = User(
            name = "test",
            email = "test@test.com",
            password = "1234",
            userType = UserType.USER
        )
        userRepository.save(userSaved)

        val qUser = QUser.user

        val user: User? = queryFactory
            .selectFrom(qUser)
            .where(qUser.id.eq(1L))
            .fetchOne()

        println("user : ${user?.id}")
    }
}