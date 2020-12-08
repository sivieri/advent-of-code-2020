package me.sivieri.aoc2020.day8

import me.sivieri.aoc2020.Utils

object Main2 {

    @JvmStatic
    fun main(args: Array<String>) {
        val data = Interpreter
            .parseCode(Utils.readInput(8))
        val interpreter = Interpreter()
        val result = interpreter.executeWithCorrection(data)
        println(result)
    }

}