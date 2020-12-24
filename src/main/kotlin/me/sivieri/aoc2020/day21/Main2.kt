package me.sivieri.aoc2020.day21

import me.sivieri.aoc2020.Utils

object Main2 {

    @JvmStatic
    fun main(args: Array<String>) {
        val data = Utils.readInput(21)
        val book = Book(data)
        val res = book.findBadIngredientsString()
        println(res)
    }

}