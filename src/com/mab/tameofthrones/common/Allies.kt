package com.mab.tameofthrones.common

class Allies {
    var alliances = ArrayList<String>()
    var allegiances = ArrayList<String>()

    fun getHasAlliesAsString(): String {
        return if (alliances.isNotEmpty()) {
            var allies = alliances[0].capitalize()
            (1 until alliances.size).forEach {
                allies += " ,${alliances[it].capitalize()}"
            }
            allies
        } else {
            "None"
        }
    }
}