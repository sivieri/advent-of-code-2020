package me.sivieri.aoc2020.day16

import me.sivieri.aoc2020.Utils

object Main1 {

    @JvmStatic
    fun main(args: Array<String>) {
        val data = Utils.readInput(16)
        val (scanner, _, tickets) = Scanner.parse(data)
        val res = tickets
            .map { scanner.validateWithSum(it) }
            .sum()
        println(res)
    }

}