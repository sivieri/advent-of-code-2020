package me.sivieri.aoc2020.day22

import me.sivieri.aoc2020.Utils

object Main1 {

    @JvmStatic
    fun main(args: Array<String>) {
        val data = Utils.readInput(22)
        val game = Game(data)
        val winner = game.play()
        val res = game.winnerPoints(winner)
        println(res)
    }

}