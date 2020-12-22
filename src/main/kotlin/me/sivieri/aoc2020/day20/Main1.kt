package me.sivieri.aoc2020.day20

import me.sivieri.aoc2020.Utils
import me.sivieri.aoc2020.multiplyBy

object Main1 {

    @JvmStatic
    fun main(args: Array<String>) {
        val data = Utils.readInput(20)
        val tiles = Tiles(data)
        val angles = tiles.findAngles()
        val res = angles.map { it.id }.multiplyBy { it.toLong() }
        println(res)
    }

}