package com.zandero.kotlin.service

import com.github.pgutkowski.kgraphql.schema.dsl.TypeID
import com.zandero.kotlin.data.Card
import com.zandero.kotlin.data.CardHolder
import org.omg.PortableInterceptor.USER_EXCEPTION
import java.time.Instant
import java.time.temporal.ChronoUnit

/**
 *
 */
class CardsService {

    val time = Instant.ofEpochSecond(1535932800L) // 3.9.2018

    var storage = listOf(
            Card(Ata, "123-123-123", time.plus(500, ChronoUnit.DAYS)),
            Card(Ivan, "999-888-777", time.plus(500, ChronoUnit.DAYS)),
            Card(Mickey, "666-555-44", time.plus(500, ChronoUnit.DAYS))
    )

    fun get(id: String): Card? {
        return storage.firstOrNull({ it.number == id })
    }

    fun list(): List<Card> {
        return storage
    }
}