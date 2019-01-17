package com.mab.tameofthrones

import com.mab.tameofthrones.common.KingdomNotFoundException

class Southeros {

    private val kingdomList = arrayListOf(
            Kingdom("space", "gorilla", "King Shan"),
            Kingdom("land", "panda"),
            Kingdom("water", "octopus"),
            Kingdom("ice", "mammoth"),
            Kingdom("air", "owl"),
            Kingdom("fire", "dragon")
    )

    fun getKingdomByName(name: String): Kingdom {
        try {

            return kingdomList.first { it.name == name.toLowerCase() }
        } catch (exception: NoSuchElementException) {
            throw KingdomNotFoundException("Kingdom with name:$name doesnot exists")
        }
    }

    fun getRuler(): Kingdom? {

        kingdomList.forEach {
            if (it.getAllies().allegiances.size >= 3)
                return it
        }
        return null

    }

}