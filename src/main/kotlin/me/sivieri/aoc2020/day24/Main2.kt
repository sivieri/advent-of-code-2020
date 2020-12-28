package me.sivieri.aoc2020.day24

import me.sivieri.aoc2020.Utils

object Main2 {

    @JvmStatic
    fun main(args: Array<String>) {
        val data = Utils.readInput(24)
        val floor = Floor(data)
        floor.performInstructions()
        floor.iterate(100)
        val res = floor.countBlackTiles()
        println(res)
    }

}