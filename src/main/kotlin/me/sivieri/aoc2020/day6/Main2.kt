package me.sivieri.aoc2020.day6

import me.sivieri.aoc2020.Utils

object Main2 {

    @JvmStatic
    fun main(args: Array<String>) {
        val data = Utils.readInputBlocks(6, Block::countOccurrences)
        println(data.sum())
    }

}