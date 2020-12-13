package me.sivieri.aoc2020.day13

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

}