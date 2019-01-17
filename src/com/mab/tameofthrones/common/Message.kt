package com.mab.tameofthrones.common

import com.mab.tameofthrones.Kingdom

data class Message(
        val senderKingdom: Kingdom,
        val receiverKingdom: Kingdom,
        val message: String
)