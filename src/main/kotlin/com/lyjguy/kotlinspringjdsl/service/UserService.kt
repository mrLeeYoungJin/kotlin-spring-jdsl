package com.lyjguy.kotlinspringjdsl.service

import com.linecorp.kotlinjdsl.querydsl.expression.col
import com.linecorp.kotlinjdsl.spring.data.*
import com.lyjguy.kotlinspringjdsl.model.entity.User
import com.lyjguy.kotlinspringjdsl.model.enum.UserType
import com.lyjguy.kotlinspringjdsl.repository.UserRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import javax.persistence.NoResultException
import javax.persistence.Query

@Service
class UserService(
    private val userRepository: UserRepository,
    private val queryFactory: SpringDataQueryFactory,
) {

    fun findById(id: Long): User? {
        return try {
            queryFactory.singleQuery {
                select(entity(User::class))
                from(entity(User::class))
                where(col(User::id).equal(id))
            }
        } catch (nre: NoResultException) {
            null
        }
    }

    @Transactional
    fun save(): User {
        val user = User(
            name = "test",
            email = "test@test.com",
            password = "1234",
            userType = UserType.USER
        )
        return userRepository.save(user)
    }

    @Transactional
    fun update() {
        val query: Query = queryFactory.updateQuery<User> {
            //where(col(User::id).`in`(1000, 2000))
            setParams(
                col(User::name) to "test",
                col(User::email) to "test@test.com",
                col(User::password) to "1234",
                col(User::userType) to UserType.USER,
            )
        }

        val updatedRowsCount: Int = query.executeUpdate()
        println("updatedRowsCount : $updatedRowsCount")
    }
}