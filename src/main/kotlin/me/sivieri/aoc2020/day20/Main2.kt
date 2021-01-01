package me.sivieri.aoc2020.day20

import me.sivieri.aoc2020.Utils

object Main2 {

    @JvmStatic
    fun main(args: Array<String>) {
        val data = Utils.readInput(20)
        val tiles = Tiles(data)
        val res = tiles.solvePuzzle()
        println(res)
    }

}