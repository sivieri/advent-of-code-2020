package me.sivieri.aoc2020.day14

import me.sivieri.aoc2020.Utils

object Main2 {

    @JvmStatic
    fun main(args: Array<String>) {
        val data = Utils.readInput(14)
        val blocks = MemoryHandler.parse(data)
        val memoryHandler = MemoryHandlerV2()
        blocks.forEach { memoryHandler.executeInput(it) }
        val res = memoryHandler.sum()
        println(res)
    }

}