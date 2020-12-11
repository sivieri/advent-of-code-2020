package me.sivieri.aoc2020.day11

import me.sivieri.aoc2020.Utils

object Main2 {

    @JvmStatic
    fun main(args: Array<String>) {
        val data = Utils
            .readInput(11)
            .map { it.toCharArray().toTypedArray() }
            .toTypedArray()
        val waitingArea = ComplexWaitingArea(data)
        waitingArea.stabilizeArea()
        val occupiedSeats = waitingArea.countOccupiedSeats()
        println(occupiedSeats)
    }

}