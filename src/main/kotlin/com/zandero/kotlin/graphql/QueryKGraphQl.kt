package com.zandero.kotlin.graphql

import com.github.pgutkowski.kgraphql.KGraphQL
import com.zandero.kotlin.data.Card
import com.zandero.kotlin.data.CardHolder
import com.zandero.kotlin.service.CardsService
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter

/**
 *
 */
class QueryKGraphQl(cards: CardsService) {

    val schema = KGraphQL.schema {

        configure { useDefaultPrettyPrinter = true }

        query("card") {
            resolver { id: String -> cards.get(id) }
        }

        query("all") {
            resolver { -> cards.list() }
        }

        stringScalar<Instant> {
            serialize = { time: Instant ->
                val datetime = LocalDateTime.ofInstant(time, ZoneOffset.UTC)
                DateTimeFormatter.ofPattern("dd-MM-yyyy").format(datetime)
            }
            deserialize = { value: String -> Instant.parse(value) }
        }

        //kotlin classes need to be registered with "type" method
        //to be included in created schema type system
        //class Character is automatically included,
        //as it is return type of both created queries
        type<Card>()
        type<CardHolder>()
    }

    fun query(query: String): String {
        return schema.execute(query);
    }
}