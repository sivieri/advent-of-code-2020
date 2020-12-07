package me.sivieri.aoc2020.day5

import me.sivieri.aoc2020.Utils

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