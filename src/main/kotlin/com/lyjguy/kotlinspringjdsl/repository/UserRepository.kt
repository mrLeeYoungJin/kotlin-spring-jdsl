package com.lyjguy.kotlinspringjdsl.repository

import com.lyjguy.kotlinspringjdsl.model.entity.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository: JpaRepository<User, Long>