package me.sivieri.aoc2020.day7

import me.sivieri.aoc2020.Utils

object Main2 {

    @JvmStatic
    fun main(args: Array<String>) {
        val data = Utils
            .readInput(7)
            .map { Bag.createBag(it) }
        val mergedBags = Bag.mergeBags(data)
        val sizes = mergedBags
            .map {
                it.countBags("shiny gold")
            }
            .distinct()
        println(sizes.first()!! - 1)
    }

}