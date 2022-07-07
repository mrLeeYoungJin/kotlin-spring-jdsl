package com.lyjguy.kotlinspringjdsl.repository

import com.lyjguy.kotlinspringjdsl.model.entity.User
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport

class UserRepositoryCustomImpl : UserRepositoryCustom, QuerydslRepositorySupport(User::class.java)