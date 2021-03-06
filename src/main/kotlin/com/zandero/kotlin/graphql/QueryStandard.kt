package com.zandero.kotlin.graphql

import com.zandero.kotlin.service.CardsDataFetcher
import com.zandero.kotlin.service.CardsListDataFetcher
import com.zandero.kotlin.service.CardsService
import graphql.ExecutionResult
import graphql.GraphQL
import graphql.schema.GraphQLScalarType
import graphql.schema.GraphQLSchema
import graphql.schema.idl.RuntimeWiring
import graphql.schema.idl.SchemaGenerator
import graphql.schema.idl.SchemaParser
import graphql.schema.idl.TypeRuntimeWiring.newTypeWiring
import java.io.InputStreamReader
import java.io.Reader

/**
 * - a lot of code (ie. schema, data-fetchers, type resolvers, wiring ... etc)
 * - flattening nested fields not simple ...
 * - no simple way to transform objects from A -> B ... need additional resolvers see: DateOnlyResolver
 */
class QueryStandard(cards: CardsService) {

    val schemaParser = SchemaParser()
    val schemaGenerator = SchemaGenerator()

    val cardFetcher = CardsDataFetcher(cards)
    val flatCardFetcher = FlatCardsDataFetcher(cards)
    val cardListFetcher = CardsListDataFetcher(cards)

    private val schema : GraphQLSchema by lazy {

        val runtimeWiring = RuntimeWiring.newRuntimeWiring() // query and transformation registration
                .scalar(GraphQLScalarType("DateOnly", "Convert Instant to Date only", DateOnlyResolver()))
                .type(newTypeWiring("Query").dataFetcher("card", cardFetcher))
                .type(newTypeWiring("Query").dataFetcher("all", cardListFetcher))
                .type(newTypeWiring("Query").dataFetcher("flat", flatCardFetcher ))
                .build()

        val streamReader = loadSchemaFile("/graphql/cards.graphqls") // need to describe all classes and queries
        val typeRegistry = schemaParser.parse(streamReader)
        schemaGenerator.makeExecutableSchema(typeRegistry, runtimeWiring)
    }

    private fun loadSchemaFile(name: String): Reader {
        val stream = QueryStandard::class.java.getResourceAsStream(name)
        return InputStreamReader(stream)
    }

    fun query(query: String): ExecutionResult {
        val instance = GraphQL.newGraphQL(schema).build()
        return instance.execute(query);
    }
}