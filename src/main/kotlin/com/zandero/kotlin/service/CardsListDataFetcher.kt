package com.zandero.kotlin.service

import com.zandero.kotlin.data.Card
import graphql.schema.DataFetcher
import graphql.schema.DataFetchingEnvironment

/**
 *
 */
class CardsListDataFetcher(val cards: CardsService) : DataFetcher<List<Card>> {

    override fun get(env: DataFetchingEnvironment?): List<Card> {
        return cards.list()
    }
}
