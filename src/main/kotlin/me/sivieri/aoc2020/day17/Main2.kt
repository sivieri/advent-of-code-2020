package me.sivieri.aoc2020.day17

import me.sivieri.aoc2020.Utils

object Main2 {

    @JvmStatic
    fun main(args: Array<String>) {
        val data = Utils
            .readInput(17)
            .map { it.toCharArray() }
        val cubes = HyperCube(data)
        cubes.performIterations(6)
        val res = cubes.countActive()
        println(res)
    }

}