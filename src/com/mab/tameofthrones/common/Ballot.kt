package com.mab.tameofthrones.common

class Ballot {


    val messageList = ArrayList<Message>()

    fun putMessages(message: ArrayList<Message>) {
        messageList.addAll(message)
    }
    fun clearBallot() {
        messageList.clear()
    }

}