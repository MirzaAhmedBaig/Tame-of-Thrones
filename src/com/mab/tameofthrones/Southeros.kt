package com.mab.tameofthrones

import com.mab.tameofthrones.breakerofchains.Priest
import com.mab.tameofthrones.common.KingdomNotFoundException
import getCompetingKingdoms

class Southeros {

    private val kingdomList = arrayListOf(
            Kingdom("space", "gorilla", "King Shan"),
            Kingdom("land", "panda"),
            Kingdom("water", "octopus"),
            Kingdom("ice", "mammoth"),
            Kingdom("air", "owl"),
            Kingdom("fire", "dragon")
    )

    private val priest = Priest()

    fun getKingdomByName(name: String): Kingdom {
        try {
            return kingdomList.first { it.name == name.toLowerCase() }
        } catch (exception: NoSuchElementException) {
            throw KingdomNotFoundException("Kingdom with name:$name doesnot exists")
        }
    }

    fun getRuler(): Kingdom? {
        kingdomList.forEach {
            if (it.getAllies().alliances.size >= 3)
                return it
        }
        return null
    }

    fun getRulerByMajorityOfAlliance(): Kingdom? {
        with(getCompetingKingdoms()) {
            return if (this.size == 1)
                this.first()
            else
                null
        }
    }

    fun getPriest() = priest

    fun getAllKingdoms() = kingdomList

}