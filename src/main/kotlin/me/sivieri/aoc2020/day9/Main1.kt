package me.sivieri.aoc2020.day9

import me.sivieri.aoc2020.Utils

object Main1 {

    private const val preambleSize = 25

    @JvmStatic
    fun main(args: Array<String>) {
        val data = Utils
            .readInput(9)
            .map { it.toLong() }
        val cypher = Cypher()
        val res = cypher.getFirstNonValid(data, preambleSize)
        println(res)
    }

}