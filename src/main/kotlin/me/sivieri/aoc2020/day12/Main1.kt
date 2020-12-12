package me.sivieri.aoc2020.day12

import me.sivieri.aoc2020.Utils

object Main1 {

    @JvmStatic
    fun main(args: Array<String>) {
        val data = Utils
            .readInput(12)
        val ship = Ship()
        ship.move(data)
        val manhattanDistance = ship.manhattanDistance()
        println(manhattanDistance)
    }

}