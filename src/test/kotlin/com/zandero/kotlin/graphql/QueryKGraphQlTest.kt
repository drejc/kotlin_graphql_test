package com.zandero.kotlin.graphql

import com.zandero.kotlin.service.CardsService
import com.zandero.kotlin.service.UserService
import com.zandero.utils.extra.JsonUtils
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import kotlin.test.assertNotNull

/**
 *
 */
internal class QueryKGraphQlTest {

    private val query = QueryKGraphQl(CardsService(), UserService())

    @Test
    fun getById() {

        val result = query.query("""{ card (id:"123-123-123") { number, expires, holder { name } } } """)

        println(result)
        assertEquals("{\n" +
                "  \"data\" : {\n" +
                "    \"card\" : {\n" +
                "      \"number\" : \"123-123-123\",\n" +
                "      \"expires\" : \"16-01-2020\",\n" +
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
                "      \"expires\" : \"16-01-2020\",\n" +
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

    @Test
    fun getUsers() {

        val result = query.query("""{ user(name: "Ata") { name } } """)

        println(result)
        assertEquals("{\n" +
                "  \"data\" : {\n" +
                "    \"user\" : {\n" +
                "      \"name\" : \"Ata\"\n" +
                "    }\n" +
                "  }\n" +
                "}\n",
                result)
    }

    @Test
    fun combinedQuery() {

        val result = query.query("""{ card (id:"123-123-123") { number } user(name: "Ata") { name } } """)

        println(result)
        assertEquals("{\n" +
                "  \"data\" : {\n" +
                "    \"card\" : {\n" +
                "      \"number\" : \"123-123-123\"\n" +
                "    },\n" +
                "    \"user\" : {\n" +
                "      \"name\" : \"Ata\"\n" +
                "    }\n" +
                "  }\n" +
                "}\n",
                result)
    }

    @Test
    fun introspection() {

        val result = query.query("""{ __schema { types { name } } }""")

        assertNotNull(result)
        println(result)
        assertEquals("{\n" +
                "  \"data\" : {\n" +
                "    \"__schema\" : {\n" +
                "      \"types\" : [ {\n" +
                "        \"name\" : \"Card\"\n" +
                "      }, {\n" +
                "        \"name\" : \"CardHolder\"\n" +
                "      }, {\n" +
                "        \"name\" : \"CardDto\"\n" +
                "      }, {\n" +
                "        \"name\" : \"__Schema\"\n" +
                "      }, {\n" +
                "        \"name\" : \"__Directive\"\n" +
                "      }, {\n" +
                "        \"name\" : \"__InputValue\"\n" +
                "      }, {\n" +
                "        \"name\" : \"__Type\"\n" +
                "      }, {\n" +
                "        \"name\" : \"__EnumValue\"\n" +
                "      }, {\n" +
                "        \"name\" : \"__Field\"\n" +
                "      }, {\n" +
                "        \"name\" : \"__TypeKind\"\n" +
                "      }, {\n" +
                "        \"name\" : \"__DirectiveLocation\"\n" +
                "      }, {\n" +
                "        \"name\" : \"String\"\n" +
                "      }, {\n" +
                "        \"name\" : \"Boolean\"\n" +
                "      }, {\n" +
                "        \"name\" : \"Float\"\n" +
                "      }, {\n" +
                "        \"name\" : \"Int\"\n" +
                "      }, {\n" +
                "        \"name\" : \"Long\"\n" +
                "      }, {\n" +
                "        \"name\" : \"Instant\"\n" +
                "      }, {\n" +
                "        \"name\" : \"Mutation\"\n" +
                "      }, {\n" +
                "        \"name\" : \"Query\"\n" +
                "      } ]\n" +
                "    }\n" +
                "  }\n" +
                "}",
                result)
    }

    @Test
    fun introspectionType() {

        val result = query.query("""{ __type(name: "CardHolder") { name, fields { name type { name kind } } } }""")

        assertNotNull(result)
        println(result)
        assertEquals("{\"__type\":{\"name\":\"CardHolder\",\"fields\":[{\"name\":\"name\",\"type\":{\"name\":\"ID\",\"kind\":\"SCALAR\"}}," +
                "{\"name\":\"surName\",\"type\":{\"name\":\"String\",\"kind\":\"SCALAR\"}}]}}",
                JsonUtils.toJson(result))
    }
}
