package me.sivieri.aoc2020.day18

import me.sivieri.aoc2020.Utils

object Main2 {

    @JvmStatic
    fun main(args: Array<String>) {
        val data = Utils.readInput(18)
        val res = data
            .map { LongTrick2Parser.eval(LongTrick2Parser.cleverParse(it)).value }
            .sum()
        println(res)
    }

}