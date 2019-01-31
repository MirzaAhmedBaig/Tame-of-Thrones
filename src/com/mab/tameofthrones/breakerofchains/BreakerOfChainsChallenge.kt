package com.mab.tameofthrones.breakerofchains

import com.mab.tameofthrones.Southeros
import com.mab.tameofthrones.common.Ballot
import com.mab.tameofthrones.common.KingdomNotFoundException
import getCompetingKingdoms
import showResult
import showRoundResult

class BreakerOfChainsChallenge {
    private val southeros = Southeros()
    private val ballot = Ballot()


    fun startTameOfThrones() {
        southeros.showResult()
        loop@ while (true) {

            print("Enter the kingdoms competing to be the ruler: ")
            val input = readLine()!!

            try {
                val strugglingKingdoms = input.split(" ")
                if (strugglingKingdoms.size == 6) {
                    continue@loop
                }
                strugglingKingdoms.forEach {
                    southeros.getKingdomByName(it).isCompeting = true
                }
                startVotingProcess()
            } catch (exception: IndexOutOfBoundsException) {
                println("Please Enter valid input")
                continue@loop
            } catch (exception: KingdomNotFoundException) {
                println(exception.message)
                continue@loop
            }

            if (southeros.getRulerByMajorityOfAlliance() != null)
                break@loop
        }
        southeros.showResult()
    }

    private fun startVotingProcess() {
        var roundNumber = 0
        while (southeros.getRulerByMajorityOfAlliance() == null) {
            roundNumber++
            val priest = southeros.getPriest()
            southeros.getCompetingKingdoms().forEach { kingdom ->
                kingdom.putMessagesToBallot(ballot, southeros.getAllKingdoms().filter { it.name != kingdom.name })
            }
            priest.sendMessagesToKingdom(priest.getRandomMessageToSend(ballot))
            southeros.showRoundResult(roundNumber)
            evaluateCompetingKingdomForNextRound()
        }
    }

    private fun evaluateCompetingKingdomForNextRound() {
        val kingdoms = southeros.getAllKingdoms().filter { it.isCompeting }.sortedWith(compareBy { it.getAllies().alliances.size })
        val maxCount = kingdoms.last().getAllies().alliances.size
        kingdoms.forEach {
            if (it.getAllies().alliances.size < maxCount)
                it.isCompeting = false
        }
    }
}