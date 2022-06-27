package com.lyjguy.kotlinspringjdsl.repository

import com.linecorp.kotlinjdsl.querydsl.expression.col
import com.linecorp.kotlinjdsl.spring.data.SpringDataQueryFactory
import com.linecorp.kotlinjdsl.spring.data.singleQuery
import com.lyjguy.kotlinspringjdsl.model.entity.User
import org.junit.jupiter.api.Test

import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.context.TestConstructor

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
class UserRepositoryTest(
    private val queryFactory: SpringDataQueryFactory,
) {

    @Test
    fun findById() {
        val id: Long = 1
        val user: User = queryFactory.singleQuery {
            select(entity(User::class))
            from(entity(User::class))
            where(col(User::id).equal(id))
        }
    }
}