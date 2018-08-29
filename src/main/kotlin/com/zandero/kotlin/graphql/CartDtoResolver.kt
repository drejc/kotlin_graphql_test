package com.zandero.kotlin.graphql

import com.zandero.kotlin.rest.dto.CardDto
import graphql.schema.Coercing
import javax.smartcardio.Card

class CartDtoResolver : Coercing<Card, CardDto> {
    override fun parseValue(p0: Any?): Card {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun parseLiteral(p0: Any?): Card {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun serialize(p0: Any?): CardDto {
        return CardDto.ModelMapper.from(p0 as com.zandero.kotlin.data.Card)
    }
}
