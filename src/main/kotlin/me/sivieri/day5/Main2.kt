package me.sivieri.day5

import me.sivieri.Utils

object Main2 {

    @JvmStatic
    fun main(args: Array<String>) {
        val data = Utils
            .readInput(5)
            .map {
                BoardingPass(
                    it.substring(0, 7),
                    it.substring(7)
                )
            }
        val ids = data
            .map { it.seatCode() }
            .sorted()
        val pairs = ids
            .subList(0, ids.size - 1)
            .zip(ids.subList(1, ids.size))
        val res = pairs.find { it.second - it.first == 2 }!!
        println(res.first + 1)
    }

}