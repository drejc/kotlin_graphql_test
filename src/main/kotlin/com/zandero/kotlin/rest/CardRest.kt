package com.zandero.kotlin.rest

import com.zandero.kotlin.API_ROOT
import com.zandero.kotlin.data.Card
import com.zandero.kotlin.rest.dto.CardDto
import com.zandero.kotlin.service.CardsService
import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.PathParam
import javax.ws.rs.Produces
import javax.ws.rs.core.MediaType

/**
 *
 */
@Path(API_ROOT + "card")
@Produces(MediaType.APPLICATION_JSON)
class CardRest(val cards: CardsService) {

    @GET
    @Path("echo")
    fun echo(): String = "echo"

    @GET
    @Path("{id}")
    fun getCard(@PathParam("id") id: String): Card? {
        return cards.get(id)
    }

    @GET
    @Path("flat/{id}")
    fun getFlatCard(@PathParam("id") id: String): CardDto? {
        return CardDto.ModelMapper.from(cards.get(id)!!)
    }
}