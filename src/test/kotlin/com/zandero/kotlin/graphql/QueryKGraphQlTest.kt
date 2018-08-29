package com.zandero.kotlin.graphql

import com.zandero.kotlin.service.CardsService
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

/**
 *
 */
internal class QueryKGraphQlTest {

    private val query = QueryKGraphQl(CardsService())

    @Test
    fun getById() {

        val result = query.query("""{ card (id:"123-123-123") { number, expires, holder { name } } } """)

        println(result)
        assertEquals("{\n" +
                "  \"data\" : {\n" +
                "    \"card\" : {\n" +
                "      \"number\" : \"123-123-123\",\n" +
                "      \"expires\" : \"11-01-2020\",\n" +
                "      \"holder\" : {\n" +
                "        \"name\" : \"Ata\"\n" +
                "      }\n" +
                "    }\n" +
                "  }\n" +
                "}",
                result)
    }

    @Test
    fun getFlatById() {

        val result = query.query("""{ flat (id:"123-123-123") { number, expires, firstName, lastName } } """)

        println(result)
        assertEquals("{\n" +
                "  \"data\" : {\n" +
                "    \"flat\" : {\n" +
                "      \"number\" : \"123-123-123\",\n" +
                "      \"expires\" : \"11-01-2020\",\n" +
                "      \"firstName\" : \"Ata\",\n" +
                "      \"lastName\" : \"Smrk\"\n" +
                "    }\n" +
                "  }\n" +
                "}",
                result)
    }

    @Test
    fun getAll() {

        val result = query.query("""{ all {number} } """)

        println(result)
        assertEquals("{\n" +
                "  \"data\" : {\n" +
                "    \"all\" : [ {\n" +
                "      \"number\" : \"123-123-123\"\n" +
                "    }, {\n" +
                "      \"number\" : \"999-888-777\"\n" +
                "    }, {\n" +
                "      \"number\" : \"666-555-444\"\n" +
                "    } ]\n" +
                "  }\n" +
                "}",
                result)
    }
}