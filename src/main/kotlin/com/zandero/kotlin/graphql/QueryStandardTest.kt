package com.zandero.kotlin.graphql

import com.zandero.kotlin.service.CardsService
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
    }

    @Test
    fun getAll() {

        val query = QueryStandard(CardsService())
        val result = query.query("""{ all {number, expires} } """)
        println(result.getData() as Any)
    }
}