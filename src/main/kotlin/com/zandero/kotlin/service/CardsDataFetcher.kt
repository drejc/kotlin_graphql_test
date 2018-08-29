package com.zandero.kotlin.service

import com.zandero.kotlin.data.Card
import graphql.schema.DataFetcher
import graphql.schema.DataFetchingEnvironment

/**
 *
 */
class CardsDataFetcher(val cards: CardsService) : DataFetcher<Card> {

    override fun get(env: DataFetchingEnvironment?): Card {
        val id = env!!.arguments["id"].toString()
        return cards.get(id)!!
    }
}
