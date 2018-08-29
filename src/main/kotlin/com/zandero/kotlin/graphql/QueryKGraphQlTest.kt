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
        assertEquals("{\"card\":{\"number\":\"123-123-123\",\"expires\":\"11-01-2020\",\"holder\":{\"name\":\"Ata\"}}}",
                result)
    }

    @Test
    fun getAll() {

        val result = query.query("""{ all {number} } """)

        println(result)
        assertEquals("{\"all\":[{\"number\":\"123-123-123\"},{\"number\":\"999-888-777\"},{\"number\":\"666-555-444\"}]}",
                result)
    }
}