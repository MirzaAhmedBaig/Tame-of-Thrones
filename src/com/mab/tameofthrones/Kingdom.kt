package com.mab.tameofthrones

import com.mab.tameofthrones.common.Allies
import com.mab.tameofthrones.common.Ballot
import com.mab.tameofthrones.common.Message
import com.mab.tameofthrones.common.MessageValidationAlgorithm

class Kingdom(var name: String, var emblem: String, var rulerName: String = "") {

    private val allies = Allies()
    private val messageValidationAlgorithm = MessageValidationAlgorithm()
    var isCompeting = false


    fun sendMessageToKingdom(kingdom: Kingdom, message: String) {
        val kingdomMessage = Message(this, kingdom, message)
        kingdom.onReceiveMessageFromKingdom(kingdomMessage)
    }

    fun onReceiveMessageFromKingdom(message: Message) {
        val isValidMessage = messageValidationAlgorithm.isValidMessage(emblem, message.message)
        message.senderKingdom.onAlliesRepose(this, isValidMessage)
        if (isValidMessage)
            beAlliesToKingdom(message.senderKingdom.name)
    }

    fun onReceiveMessageFromKingdomIfNotCompetingAndFree(message: Message) {
        if (isCompeting || allies.alliances.size > 0)
            return
        val isValidMessage = messageValidationAlgorithm.isValidMessage(emblem, message.message)
        message.senderKingdom.onAlliesRepose(this, isValidMessage)
        if (isValidMessage)
            beAlliesToKingdom(message.senderKingdom.name)
    }

    private fun onAlliesRepose(kingdom: Kingdom, isAllies: Boolean) {
        if (isAllies)
            addAllies(kingdom.name)
    }


    fun getAllies(): Allies {
        return allies
    }


    private fun addAllies(kingdomName: String) {
        if (!allies.alliances.contains(kingdomName))
            allies.alliances.add(kingdomName)

    }

    private fun beAlliesToKingdom(kingdomName: String) {
        if (!allies.allegiances.contains(kingdomName))
            allies.allegiances.add(kingdomName)
    }

    fun putMessagesToBallot(ballot: Ballot, kingdoms: List<Kingdom>) {
        val messagesTobeSent = getAllegianceMessages(kingdoms)
        ballot.putMessages(messagesTobeSent)
    }

    private fun getAllegianceMessages(receivingKingdomList: List<Kingdom>): ArrayList<Message> {
        return ArrayList<Message>().apply {
            receivingKingdomList.forEach {
                add(Message(this@Kingdom, it, MessageTable().getRandomMessage()))
            }
        }
    }
}