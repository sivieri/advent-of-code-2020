package me.sivieri.aoc2020.day18

import me.sivieri.aoc2020.Utils

object Main1 {

    @JvmStatic
    fun main(args: Array<String>) {
        val data = Utils.readInput(18)
        val res = data
            .map { Parser.eval(it.replace("*", "-")).value }
            .sum()
        println(res)
    }

}