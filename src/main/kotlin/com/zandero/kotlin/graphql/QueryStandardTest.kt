package com.zandero.kotlin.graphql

import com.fasterxml.jackson.annotation.JsonUnwrapped
import com.zandero.kotlin.service.CardsService
import com.zandero.utils.extra.JsonUtils
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

/**
 *
 */
internal class QueryStandardTest {

    @Test
    fun getById() {

        val query = QueryStandard(CardsService())
        val result = query.query("""{ card (id:"123-123-123") { number, expires, holder { name } } } """)

        println(result.getData() as Any)
        assertEquals("{\"card\":{\"number\":\"123-123-123\",\"expires\":\"11-01-2020\",\"holder\":{\"name\":\"Ata\"}}}",
                JsonUtils.toJson(result.getData()))
    }

    @Test
    fun getAll() {

        val query = QueryStandard(CardsService())
        val result = query.query("""{ all {number} } """)

        println(result.getData() as Any)
        assertEquals("{\"all\":[{\"number\":\"123-123-123\"},{\"number\":\"999-888-777\"},{\"number\":\"666-555-444\"}]}",
                JsonUtils.toJson(result.getData()))
    }
}