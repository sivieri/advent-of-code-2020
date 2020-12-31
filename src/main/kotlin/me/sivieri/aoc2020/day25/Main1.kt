package me.sivieri.aoc2020.day25

import me.sivieri.aoc2020.Utils

object Main1 {

    @JvmStatic
    fun main(args: Array<String>) {
        val data = Utils.readInput(25)
        val card = data[0].toLong()
        val door = data[1].toLong()
        val calculator = Calculator()
        val cardLoop = calculator.searchLoopSize(7, card)
        val encryptionKey = calculator.calculateEncryptionKey(cardLoop, door)
        println(encryptionKey)
    }

}