package me.sivieri.aoc2020.day19

import me.sivieri.aoc2020.Utils

object Main1 {

    @JvmStatic
    fun main(args: Array<String>) {
        val data = Utils.readInput(19)
        val parser = Parser(data)
        val res = parser.validateAll()
        println(res)
    }

}