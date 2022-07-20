package com.lyjguy.kotlinspringjdsl.model.dto

import com.lyjguy.kotlinspringjdsl.model.entity.OrderReceiver
import java.time.LocalDateTime

data class ResOrderReceiverDto(
    val id: Long = 0,
    val name: String,
    val address1: String,
    val address2: String,
    val zipcode: String,
    val createAt: LocalDateTime,
)

fun OrderReceiver.toResOrderReceiverDto(): ResOrderReceiverDto {
    return ResOrderReceiverDto(
        id = id,
        name = name,
        address1 = address1,
        address2 = address2,
        zipcode = zipcode,
        createAt = createAt,
    )
}