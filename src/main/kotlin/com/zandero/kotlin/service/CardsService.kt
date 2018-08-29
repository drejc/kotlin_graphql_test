package com.zandero.kotlin.service

import com.zandero.kotlin.data.Card
import com.zandero.kotlin.data.CardHolder
import java.time.Instant
import java.time.temporal.ChronoUnit

/**
 *
 */
class CardsService {

    var storage = listOf(
            Card(CardHolder("Ata", "Smrk"), "123-123-123", Instant.now().plus(500, ChronoUnit.DAYS)),
            Card(CardHolder("Ivan", "Grozni"), "999-888-777", Instant.now().plus(500, ChronoUnit.DAYS)),
            Card(CardHolder("Mickey", "Mouse"), "666-555-444", Instant.now().plus(500, ChronoUnit.DAYS))
    )

    fun get(id: String): Card? {
        return storage.firstOrNull({ it.number == id })
    }

    fun list(): List<Card> {
        return storage
    }
}