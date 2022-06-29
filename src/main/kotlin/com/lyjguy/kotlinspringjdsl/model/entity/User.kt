package com.lyjguy.kotlinspringjdsl.model.entity

import com.lyjguy.kotlinspringjdsl.model.enum.UserType
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@EntityListeners(AuditingEntityListener::class)
@Table(name="users")
class User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    var name: String,
    var email: String,
    var password: String,
    @Enumerated(EnumType.STRING)
    val userType: UserType,
    @CreatedDate
    var createdAt: LocalDateTime = LocalDateTime.now(),
    @LastModifiedDate
    var updatedAt: LocalDateTime = LocalDateTime.now(),
    @LastModifiedDate
    var lastLoginAt: LocalDateTime = LocalDateTime.now(),
)