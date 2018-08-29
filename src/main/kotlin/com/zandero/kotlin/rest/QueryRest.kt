package com.zandero.kotlin.rest

import com.zandero.kotlin.API_ROOT
import com.zandero.kotlin.service.CardsService
import com.zandero.utils.extra.JsonUtils
import graphql.ExecutionInput.newExecutionInput
import graphql.GraphQL
import graphql.execution.instrumentation.ChainedInstrumentation
import graphql.execution.instrumentation.Instrumentation
import graphql.execution.instrumentation.dataloader.DataLoaderDispatcherInstrumentation
import graphql.execution.instrumentation.dataloader.DataLoaderDispatcherInstrumentationOptions.newOptions
import graphql.execution.instrumentation.tracing.TracingInstrumentation
import java.util.Arrays.asList
import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.QueryParam
import javax.ws.rs.core.MediaType

/**
 *
 */
@Path(API_ROOT + "cards")
@Produces(MediaType.APPLICATION_JSON)
class QueryRest(val cards : CardsService) {

    @GET()
    @Path("cards")
    fun getCard(@QueryParam("query") query: String) : String {

       /* val executionInput = newExecutionInput()
                .query(query)
               *//* .operationName(parameters.getOperationName())
                .variables(parameters.getVariables())*//*

        val context = StarWarsWiring.Context()
        executionInput.context(context)

        //
        // you need a schema in order to execute queries
        val schema = buildStarWarsSchema()

        //
        // This example uses the DataLoader technique to ensure that the most efficient
        // loading of data (in this case StarWars characters) happens.  We pass that to data
        // fetchers via the graphql context object.
        //
        val dataLoaderRegistry = context.getDataLoaderRegistry()


        val dlInstrumentation = DataLoaderDispatcherInstrumentation(dataLoaderRegistry, newOptions().includeStatistics(true))

        val instrumentation = ChainedInstrumentation(
                asList<Instrumentation>(TracingInstrumentation(), dlInstrumentation)
        )

        // finally you build a runtime graphql object and execute the query
        val graphQL = GraphQL
                .newGraphQL(schema)
                // instrumentation is pluggable
                .instrumentation(instrumentation)
                .build()
        val executionResult = graphQL.execute(executionInput.build())

        return JsonUtils.toJson(executionResult);*/
        return "";
    }
}
