package me.sivieri.aoc2020.day6

import me.sivieri.aoc2020.Utils

object Main1 {

    @JvmStatic
    fun main(args: Array<String>) {
        val data = Utils.readInputBlocks(6) { block ->
            block
                .toCharArray()
                .toList()
                .filter { it.isLetter() }
                .distinct()
                .count()
        }
        println(data.sum())
    }

}