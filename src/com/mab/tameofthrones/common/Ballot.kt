package com.mab.tameofthrones.common

import com.mab.tameofthrones.MessageTable
import java.util.*
import kotlin.collections.ArrayList

class BallotSystem {


    private val messageList = ArrayList<Message>()

    fun putMessages(message: ArrayList<Message>) {
        messageList.addAll(message)
    }

    fun getRandomMessages(messageCount:Int):ArrayList<Message>{
        return ArrayList<Message>().apply {
            (0 until messageCount).forEach {
                add(messageList[(Math.random() * messageList.size).toInt()])
            }
        }
    }
    fun clearBallot() {
        messageList.clear()
    }

}