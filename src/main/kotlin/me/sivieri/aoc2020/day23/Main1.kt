package me.sivieri.aoc2020.day23

object Main1 {

    @JvmStatic
    fun main(args: Array<String>) {
        val data = "653427918"
        val cups = Cups(data)
        val res = cups.performIterations(100)
        println(res)
    }

}