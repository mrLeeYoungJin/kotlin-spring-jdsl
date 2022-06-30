package com.lyjguy.kotlinspringjdsl.repository

import com.linecorp.kotlinjdsl.querydsl.expression.col
import com.linecorp.kotlinjdsl.spring.data.SpringDataQueryFactory
import com.linecorp.kotlinjdsl.spring.data.listQuery
import com.linecorp.kotlinjdsl.spring.data.singleQuery
import com.lyjguy.kotlinspringjdsl.model.entity.User
import org.junit.jupiter.api.Test

import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.context.annotation.ComponentScan
import org.springframework.test.context.TestConstructor
import javax.persistence.NoResultException

@DataJpaTest
@ComponentScan("com.linecorp.kotlinjdsl")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
class UserRepositoryTest(
    private val queryFactory: SpringDataQueryFactory,
    private val userRepository: UserRepository,
) {

    @Test
    fun jpaQueryTest() {
        userRepository.findById(1)
    }

    @Test
    fun findById() {
        try {
            val id: Long = 1
            val user: User? = queryFactory.singleQuery {
                select(entity(User::class))
                from(entity(User::class))
                where(col(User::id).equal(id))
            }
            println("user : ${user}")
        } catch (nre: NoResultException) {
            println(0)
        }
    }

    @Test
    fun findAllById() {
        val id: Long = 1
        val user = queryFactory.listQuery<User> {
            select(entity(User::class))
            from(entity(User::class))
            where(col(User::id).equal(id))
        }
        println("user : ${user}")
    }
}