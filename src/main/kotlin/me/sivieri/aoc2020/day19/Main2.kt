package me.sivieri.aoc2020.day19

import me.sivieri.aoc2020.Utils

object Main2 {

    @JvmStatic
    fun main(args: Array<String>) {
        val data = Utils.readInput(19)
        val parser = Parser(
            data,
            listOf(
                "p8: p30 | p30 p8;",
                "p11: p30 p29 | p30 p11 p29;"
            )
        )
        val res = parser.validateAll()
        println(res)
    }

}