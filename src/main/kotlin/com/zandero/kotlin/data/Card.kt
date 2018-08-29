package com.zandero.kotlin.data

import java.time.Instant

/**
 *
 */
data class Card(val holder: CardHolder, val number: String, val expires: Instant)
