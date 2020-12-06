package me.sivieri.day6

import me.sivieri.Utils

object Main2 {

    @JvmStatic
    fun main(args: Array<String>) {
        val data = Utils.readInputBlocks(6, Block::countOccurrences)
        println(data.sum())
    }

}