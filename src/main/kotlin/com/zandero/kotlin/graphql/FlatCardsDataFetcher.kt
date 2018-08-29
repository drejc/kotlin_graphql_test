package com.zandero.kotlin.graphql

import com.zandero.kotlin.rest.dto.CardDto
import com.zandero.kotlin.service.CardsService
import graphql.schema.DataFetcher
import graphql.schema.DataFetchingEnvironment

class FlatCardsDataFetcher(val cards: CardsService) : DataFetcher<CardDto> {

    override fun get(env: DataFetchingEnvironment?): CardDto {
        val id = env!!.arguments["id"].toString()
        return CardDto.ModelMapper.from(cards.get(id)!!)
    }
}
