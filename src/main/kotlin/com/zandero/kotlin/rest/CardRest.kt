package com.zandero.kotlin.rest

import com.zandero.kotlin.API_ROOT
import com.zandero.kotlin.data.Card
import com.zandero.kotlin.service.CardsService
import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.PathParam
import javax.ws.rs.Produces

/**
 *
 */
@Path(API_ROOT + "cards")
@Produces("application/json")
class CardRest(val cards: CardsService) {

    @GET
    @Path("echo")
    fun echo(): String {
        return "echo"
    }

    @GET
    @Path("{id}")
    fun getCard(@PathParam("id") id: String): Card? {
        return cards.get(id)
    }
}