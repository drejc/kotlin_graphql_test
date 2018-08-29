package com.zandero.kotlin.rest.dto

import com.zandero.kotlin.data.Card
import java.time.Instant

data class CardDto(val number: String, val firstName: String, val lastName: String, val expires: Instant) {

    object ModelMapper {
        fun from(c: Card) = CardDto(c.number, c.holder.name, c.holder.surName, c.expires)
    }
}