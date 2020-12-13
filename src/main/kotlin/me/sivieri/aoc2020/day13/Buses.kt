package me.sivieri.aoc2020.day13

import me.sivieri.aoc2020.multiplyBy
import me.sivieri.aoc2020.zipWithIndex

object Buses {

    fun findFirstBus(time: Int, buses: List<Int>): Int {
        var currentTime = time
        while (true) {
            buses.forEach { bus ->
                if (currentTime % bus == 0) return bus * (currentTime - time)
            }
            currentTime++
        }
    }

    fun findTimeForBusSequence(buses: List<String>): Long {
        val busesIndexed = buses
            .zipWithIndex()
            .filterNot { it.second == "x" }
            .map { Pair(it.first, it.second.toInt()) }
        return searchNewIncrement(2, 0, 1, busesIndexed)
    }

    private tailrec fun searchNewIncrement(
        lastIndex: Int,
        start: Long,
        increment: Long,
        buses: List<Pair<Int, Int>>
    ): Long {
        if (lastIndex > buses.size) return start
        val sublist = buses.subList(0, lastIndex)
        val res = findWithIncrement(sublist, start, increment)
        return searchNewIncrement(
            lastIndex + 1,
            res,
            sublist.multiplyBy { it.second.toLong() },
            buses
        )
    }

    private fun findWithIncrement(
        buses: List<Pair<Int, Int>>,
        start: Long,
        increment: Long
    ): Long {
        var time = start
        while (true) {
            val test = buses.all {
                (time + it.first) % it.second == 0L
            }
            if (test) return time
            time += increment
        }
    }

}