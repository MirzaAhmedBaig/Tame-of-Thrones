package com.mab.tameofthrones.breakerofchains

import com.mab.tameofthrones.common.Ballot
import com.mab.tameofthrones.common.Message

class Priest {

    private val randomMessageCount = 6
    fun getRandomMessageToSend(ballot: Ballot): ArrayList<Message> {
        val messageList = ballot.messageList
        return ArrayList<Message>().apply {
            (0 until randomMessageCount).forEach {
                add(messageList[(Math.random() * messageList.size).toInt()])
            }
        }

    }

    fun sendMessagesToKingdom(messages: ArrayList<Message>) {
        messages.forEach {
            it.receiverKingdom.onReceiveMessageFromKingdomIfNotCompetingAndFree(it)
        }
    }
}