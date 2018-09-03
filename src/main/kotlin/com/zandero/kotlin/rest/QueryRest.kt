package com.zandero.kotlin.rest

import com.zandero.kotlin.API_ROOT
import com.zandero.kotlin.graphql.QueryKGraphQl
import com.zandero.kotlin.graphql.QueryStandard
import com.zandero.kotlin.service.CardsService
import com.zandero.kotlin.service.UserService
import com.zandero.rest.annotation.ResponseWriter
import com.zandero.utils.extra.JsonUtils
import graphql.ExecutionInput.newExecutionInput
import graphql.ExecutionResult
import graphql.GraphQL
import graphql.execution.instrumentation.ChainedInstrumentation
import graphql.execution.instrumentation.Instrumentation
import graphql.execution.instrumentation.dataloader.DataLoaderDispatcherInstrumentation
import graphql.execution.instrumentation.dataloader.DataLoaderDispatcherInstrumentationOptions.newOptions
import graphql.execution.instrumentation.tracing.TracingInstrumentation
import java.net.URLDecoder
import java.util.Arrays.asList
import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.QueryParam
import javax.ws.rs.core.MediaType

/**
 *
 */
@Path(API_ROOT)
@Produces(MediaType.APPLICATION_JSON)
class QueryRest(cards : CardsService, users: UserService) {

    val standard = QueryStandard(cards)
    val kGraph = QueryKGraphQl(cards, users)

    @GET()
    @Path("standard")
    fun getCard(@QueryParam("query") query: String) : ExecutionResult {

       val decodedQuery = URLDecoder.decode(query, "UTF-8");
       return standard.query(decodedQuery)
    }

    @GET()
    @Path("kgraph")
    @Produces(MediaType.TEXT_PLAIN)
    fun getCardKGraph(@QueryParam("query") query: String) : String {

        val decodedQuery = URLDecoder.decode(query, "UTF-8");
        return kGraph.query(decodedQuery)
    }
}
