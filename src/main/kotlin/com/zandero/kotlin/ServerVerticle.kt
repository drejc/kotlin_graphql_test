package com.zandero.kotlin

import com.zandero.kotlin.rest.CardRest
import com.zandero.kotlin.rest.ExecutionResultWriter
import com.zandero.kotlin.rest.QueryRest
import com.zandero.kotlin.service.CardsService
import com.zandero.kotlin.service.UserService
import com.zandero.rest.RestBuilder
import graphql.ExecutionResult
import io.vertx.core.AbstractVerticle
import org.slf4j.LoggerFactory

/**
 * This is a basic server set up to get REST.vertx going ...
 */

const val API_ROOT = "/"

class ServerVerticle(val port: Int, val poolSize: Int) : AbstractVerticle() {


    override fun start() {

        val cardsService = CardsService()
        val userService = UserService()

        // Create router ...
        val router = RestBuilder(vertx)

                // register RESTs
                .register(CardRest(cardsService))
                .register(QueryRest(cardsService, userService))
                .writer(ExecutionResult::class.java, ExecutionResultWriter::class.java)

                /*SlowRest::class.java,
                PagesRest::class.java)*/

                // handle REST / page not found requests
                //            .notFound(API_ROOT, RestNotFoundHandler::class.java) // rest not found info - all under /api/*

                // add general purpose REST error handler
                /*          .errorHandler(NotFoundErrorHandler::class.java)
                          .errorHandler(RestErrorHandler::class.java)*/
                .build()


        // use port
        log.info("Listening on port: " + port + " - vert.x thread pool size: " + poolSize)

        // start up server
        vertx.createHttpServer()
                .requestHandler({ router.accept(it) })
                .listen(port)
    }

    companion object {
        private val log = LoggerFactory.getLogger(ServerVerticle::class.java)
    }
}
