package com.zandero.kotlin.graphql

import com.github.pgutkowski.kgraphql.KGraphQL
import com.zandero.kotlin.data.Card
import com.zandero.kotlin.data.CardHolder
import com.zandero.kotlin.rest.dto.CardDto
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

        query("flat") {
            resolver { id: String -> CardDto.ModelMapper.from(cards.get(id)!!) }
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

        type<Card>()
        type<CardDto>()
        type<CardHolder>()
    }

    fun query(query: String): String {
        return schema.execute(query);
    }
}