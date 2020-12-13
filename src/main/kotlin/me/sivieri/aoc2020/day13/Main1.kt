package me.sivieri.aoc2020.day13

import me.sivieri.aoc2020.Utils

object Main1 {

    @JvmStatic
    fun main(args: Array<String>) {
        val data = Utils.readInput(13)
        val time = data[0].toInt()
        val buses = data[1]
            .split(",")
            .filter { it != "x" }
            .map { it.toInt() }
        val res = Buses.findFirstBus(time, buses)
        println(res)
    }

}