package com.zandero.kotlin.service

import com.zandero.kotlin.data.CardHolder

/**
 *
 */
val Ata = CardHolder("Ata", "Smrk")
val Ivan = CardHolder("Ivan", "Grozni")
val Mickey = CardHolder("Mickey", "Mouse")

class UserService {

    var storage = listOf(
            Ata,
            Ivan,
            Mickey
    )

    fun get(name: String): CardHolder? {
        return storage.firstOrNull({ it.name == name })
    }

    fun list(): List<CardHolder> {
        return storage
    }
}