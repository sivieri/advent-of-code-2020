package me.sivieri.aoc2020.day23

object Main2 {

    @JvmStatic
    fun main(args: Array<String>) {
        val data = "653427918"
        val cups = Cups(data, million = true)
        cups.performIterations(10_000_000)
        val res = cups.getTwoCups()
        println(res)
    }

}