package com.mab.tameofthrones.goldencrownchallenge

import com.mab.tameofthrones.Southeros
import com.mab.tameofthrones.common.KingdomNotFoundException

class GoldenCrownChallenge {

    private val southeros = Southeros()

    init {
        showResult()
        showMenu()
    }

    private fun showResult() {
        println("Who is the ruler of Southeros?")
        println("Output: ${southeros.getRuler()?.name ?: "None"}")
        println("Allies of Ruler?")
        println("Output: ${southeros.getRuler()?.getAllies()?.getHasAlliesAsString() ?: "None"}")
    }

    private fun showMenu() {
        val strugglingKingdom = "Space"
        val kingdom = southeros.getKingdomByName(strugglingKingdom)
        println("\nInput Messages to kingdoms from ${kingdom.rulerName}")
        while (southeros.getRuler() == null) {
            print("Input : ")
            val input = readLine()!!
            if (validateInput(input)) {
                val (kingdomName, message) = input.split(",")
                kingdom.sendMessageToKingdom(southeros.getKingdomByName(kingdomName), message)
            }
        }

        showResult()
    }


    private fun validateInput(input: String): Boolean {
        val regex = "([A-Z-a-z])\\w+, \".+\"".toRegex()
        if (!input.contains(regex)) {
            print("Please Enter your message in Kingdom, \"message\" format\n")
            return false
        }
        val kingdom = input.split(",")[0]
        return try {
            southeros.getKingdomByName(kingdom)
            true
        } catch (exception: KingdomNotFoundException) {
            println("${exception.message}")
            false
        }
    }

}