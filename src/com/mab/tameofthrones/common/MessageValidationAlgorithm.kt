package com.mab.tameofthrones.common

class MessageValidationAlgorithm {

    fun isValidMessage(emblem: String, message: String): Boolean {
        var messageString = message
        emblem.forEach {
            with(messageString.replaceFirst(it, '.', true)) {
                if (this == messageString) {
                    return false
                } else {
                    messageString = this
                }
            }
        }
        return true
    }
}