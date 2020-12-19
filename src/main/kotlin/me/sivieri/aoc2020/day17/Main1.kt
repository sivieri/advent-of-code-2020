package me.sivieri.aoc2020.day17

import me.sivieri.aoc2020.Utils

object Main1 {

    @JvmStatic
    fun main(args: Array<String>) {
        val data = Utils
            .readInput(17)
            .map { it.toCharArray() }
        val cubes = Cubes(data)
        cubes.performIterations(6)
        val res = cubes.countActive()
        println(res)
    }

}