package com.zandero.kotlin.rest

import com.zandero.rest.writer.HttpResponseWriter
import com.zandero.utils.extra.JsonUtils
import graphql.ExecutionResult
import io.vertx.core.http.HttpServerRequest
import io.vertx.core.http.HttpServerResponse

object ExecutionResultWriter : HttpResponseWriter<ExecutionResult> {

    override fun write(result: ExecutionResult?, request: HttpServerRequest?, response: HttpServerResponse) {

        val out = result!!.getData<Any>()
        response.end(JsonUtils.toJson(out))
    }
}
