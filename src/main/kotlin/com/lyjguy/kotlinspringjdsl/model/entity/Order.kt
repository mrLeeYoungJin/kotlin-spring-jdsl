package com.lyjguy.kotlinspringjdsl.model.entity

import com.fasterxml.jackson.annotation.JsonManagedReference
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@EntityListeners(AuditingEntityListener::class)
@Table(name="orders")
class Order(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    val userId: Long,
    var name: String,
    val status: String,
    val totalPrice: Int,
    @CreatedDate
    var createdAt: LocalDateTime = LocalDateTime.now(),
    @LastModifiedDate
    var updatedAt: LocalDateTime = LocalDateTime.now(),

) {
//    @OneToOne(mappedBy = "order", fetch = FetchType.LAZY, cascade = [CascadeType.PERSIST])
    @JoinColumn(insertable = false, updatable = false)
    @OneToOne(mappedBy = "order", optional = false, fetch = FetchType.LAZY)
    @JsonManagedReference
    lateinit var orderReceiver: OrderReceiver
}