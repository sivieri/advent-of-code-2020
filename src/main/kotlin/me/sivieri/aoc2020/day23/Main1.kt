package me.sivieri.aoc2020.day23

object Main1 {

    @JvmStatic
    fun main(args: Array<String>) {
        val data = "653427918"
        val cups = Cups(data, 9)
        cups.performIterations(100)
        val res = cups.getFullString()
        println(res)
    }

}