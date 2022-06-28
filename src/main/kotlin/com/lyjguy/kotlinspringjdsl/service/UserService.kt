package com.lyjguy.kotlinspringjdsl.service

import com.linecorp.kotlinjdsl.querydsl.expression.col
import com.linecorp.kotlinjdsl.spring.data.SpringDataQueryFactory
import com.linecorp.kotlinjdsl.spring.data.singleQuery
import com.linecorp.kotlinjdsl.spring.data.updateQuery
import com.lyjguy.kotlinspringjdsl.model.entity.User
import com.lyjguy.kotlinspringjdsl.model.enum.UserType
import com.lyjguy.kotlinspringjdsl.repository.UserRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import javax.persistence.Query

@Service
class UserService(
    private val queryFactory: SpringDataQueryFactory,
) {

    fun findById(id: Long): User? {
        return queryFactory.singleQuery {
            select(entity(User::class))
            from(entity(User::class))
            where(col(User::id).equal(id))
        }
    }

    @Transactional
    fun save() {
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