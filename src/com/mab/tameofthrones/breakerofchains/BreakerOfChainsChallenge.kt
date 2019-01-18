package com.mab.tameofthrones.breakerofchains

import com.mab.tameofthrones.Southeros
import com.mab.tameofthrones.common.KingdomNotFoundException

class BreakerOfChainsChallenge {
    private val southeros = Southeros()

    init {
        showResult()
        showMenu()
    }

    private fun showResult() {
        println("Who is the ruler of Southeros?")
        println("Output: ${southeros.getRulerByMajorityOfAlligence() ?: "None"}")
        println("Allies of Ruler?")
        println("Output: ${southeros.getRuler()?.getAllies()?.getHasAlliesAsString() ?: "None"}")
    }

    private fun showMenu() {
        print("Enter the kingdoms competing to be the ruler: ")
        val input = readLine()!!

        try {
            val strugglingKingdoms = input.split(" ")
            strugglingKingdoms.forEach {
                val kingdom = southeros.getKingdomByName(it)
            }
        } catch (exception: IndexOutOfBoundsException) {
            println("Please Enter valid input")
        } catch (exception: KingdomNotFoundException) {
            println(exception.message)
        }

        showResult()
    }
}