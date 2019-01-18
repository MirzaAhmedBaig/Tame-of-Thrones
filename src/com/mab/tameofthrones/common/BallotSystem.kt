package com.mab.tameofthrones.common

import com.mab.tameofthrones.MessageTable
import java.util.*

class BallotSystem {

    private val randomMessageCount = 6
    private val messageList = ArrayList<Message>()

    fun putMessages(message: Message) {
        messageList.add(message)
    }

    fun clearBallot() {
        messageList.clear()
    }

    fun getRandomMessageToSend(): ArrayList<Message> {
        return ArrayList<Message>().apply {
            (0 until randomMessageCount).forEach {
                add(messageList[Random().nextInt(messageList.size)])
            }
        }
    }

}