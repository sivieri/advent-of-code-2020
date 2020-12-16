package me.sivieri.aoc2020.day15

object Main2 {

    @JvmStatic
    fun main(args: Array<String>) {
        val data = "0,1,4,13,15,12,16"
            .split(",")
            .map { it.toInt() }
        val game = Game()
        val res = game.play(data, 30_000_000)
        println(res)
    }

}