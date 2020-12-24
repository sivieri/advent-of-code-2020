package me.sivieri.aoc2020.day21

import me.sivieri.aoc2020.Utils

object Main1 {

    @JvmStatic
    fun main(args: Array<String>) {
        val data = Utils.readInput(21)
        val book = Book(data)
        val ingredients = book.findGoodIngredients()
        val res = book.countIngredients(ingredients)
        println(res)
    }

}