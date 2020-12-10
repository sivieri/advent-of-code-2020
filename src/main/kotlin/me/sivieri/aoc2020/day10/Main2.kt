package me.sivieri.aoc2020.day10

import me.sivieri.aoc2020.Utils

object Main2 {

    @JvmStatic
    fun main(args: Array<String>) {
        val data = Utils
            .readInput(10)
            .map { it.toInt() }
        val bag = AdaptersBag(data)
        val res = bag.findCombinations()
        println(res)
    }

}