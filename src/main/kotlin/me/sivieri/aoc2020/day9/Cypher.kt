package me.sivieri.aoc2020.day9

class Cypher {

    fun getFirstNonValid(
        numbers: List<Long>,
        preambleSize: Int
    ): Long {
        var res = -1L
        for (i in numbers.indices) {
            if (i < preambleSize) continue
            if (!searchSum(numbers, i, preambleSize)) {
                res = numbers[i]
                break
            }
        }
        return res
    }

    private fun searchSum(
        numbers: List<Long>,
        i: Int,
        preambleSize: Int
    ): Boolean {
        val current = numbers[i]
        val sublist = numbers.subList(i - preambleSize, i)
        sublist.forEach { x ->
            sublist.forEach { y ->
                if (x != y && x + y == current) return true
            }
        }
        return false
    }

}