package com.zandero.kotlin.graphql

import graphql.schema.Coercing
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter


class DateOnlyResolver : Coercing<Instant, String> {

    override fun parseValue(p0: Any?): Instant {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun parseLiteral(p0: Any?): Instant {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun serialize(time: Any?): String {

        if (time is Instant) {
            val datetime = LocalDateTime.ofInstant(time, ZoneOffset.UTC)
            return DateTimeFormatter.ofPattern("dd-MM-yyyy").format(datetime)
        }

        return time.toString()
    }
}
