package me.sivieri.aoc2020.day13

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
        var time = 0L
        while (true) {
            val test = busesIndexed.all {
                (time + it.first) % it.second == 0L
            }
            if (test) return time
            time++
            if (time % 100_000_000 == 0L) println(time)
        }
    }

}