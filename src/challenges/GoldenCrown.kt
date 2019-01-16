package challenges

object GoldenCrown {
    private val kingdoms = arrayOf("LAND", "WATER", "ICE", "AIR", "FIRE")
    private val emblems = arrayOf("Panda", "  Octopus", "Mammoth", "Owl", "Dragon")
    private var rulerOfSoutheros = "None"
    private var alliesOfRuler = ""
    @JvmStatic
    fun main(args: Array<String>) {
        showResult()
        showMenu()
    }

    private fun showMenu() {
        println("Input Messages to kingdoms from King Shan")
        while (alliesOfRuler.split(",").size < 3) {
            print("Input : ")
            with(readLine()!!) {
                if (validateMessage(this.split(",")[0].toUpperCase())) {
                    sendMessageToKingdom(this)
                } else {
                    print("Please Enter your message in Kingdom, \"message\" format : ")
                    showMenu()
                }
            }
        }
        showResult()
    }

    private fun validateMessage(kingdom: String): Boolean {
        return kingdoms.contains(kingdom)
    }


    private fun sendMessageToKingdom(messageString: String) {
        val messageTokens = messageString.split(",")
        val kingdom = messageTokens[0]
        val emblem = emblems[kingdoms.indexOf(kingdom.toUpperCase())]

        var message = messageTokens[1]
        var willWin = true
        run loop@{
            emblem.forEach {
                with(message.replaceFirst(it, '.', true)) {
                    if (this == message) {
                        willWin = false
                        return@loop
                    } else {
                        message = this
                    }
                }
            }
            if (willWin)
                evaluateWinningChance(kingdom)
        }
    }

    private fun evaluateWinningChance(kingdom: String) {
        if (!alliesOfRuler.contains(kingdom)) {
            alliesOfRuler += if (alliesOfRuler == "")
                kingdom
            else
                ", $kingdom"
        }
        rulerOfSoutheros = if (alliesOfRuler.split(",").size >= 3)
            "King Shan"
        else
            "None"
    }

    private fun showResult() {
        println("Who is the ruler of Southeros?")
        println("Output: $rulerOfSoutheros")
        println("Allies of Ruler?")
        println("Output: ${if (alliesOfRuler == "") "None" else alliesOfRuler}")
    }
}


/*
Air, “oaaawaala”
Land, “a1d22n333a4444p”
Ice, “zmzmzmzaztzozh”


Input: Air, “Let’s swing the sword together”
Input: Land, “Die or play the tame of thrones”
Input: Ice, “Ahoy! Fight for me with men and money”
Input: Water, “Summer is coming”
Input: Fire, “Dragon Martin!”

*/
