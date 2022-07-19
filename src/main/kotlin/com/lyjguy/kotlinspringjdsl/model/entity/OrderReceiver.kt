package com.lyjguy.kotlinspringjdsl.model.entity

import com.fasterxml.jackson.annotation.JsonBackReference
import com.fasterxml.jackson.annotation.JsonManagedReference
import com.lyjguy.kotlinspringjdsl.model.entity.Order
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name="order_receivers")
class OrderReceiver(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
//    @Column(name="order_id", insertable = false, updatable = false, nullable = false)
//    var orderId: Long = 0,
    var name: String,
    var address1: String,
    var address2: String,
    var zipcode: String,
    val createAt: LocalDateTime = LocalDateTime.now(),
    val updatedAt: LocalDateTime? = null,

    @OneToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", insertable = false, updatable = false, nullable = false)
    @JsonBackReference
    var order: Order
) {
    //@MapsId
    //@OneToOne(fetch = FetchType.LAZY)
    //@JoinColumn(name = "orderId")
//    @OneToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "order_id")
//    lateinit var order: Order
}