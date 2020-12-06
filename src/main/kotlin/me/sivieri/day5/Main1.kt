package me.sivieri.day5

import me.sivieri.Utils

object Main1 {

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
        println(data.map { it.seatCode() }.maxOrNull())
    }

}