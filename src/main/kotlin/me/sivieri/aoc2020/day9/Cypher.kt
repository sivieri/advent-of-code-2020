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

    fun findMinMaxRangeSum(
        input: List<Long>,
        nonValid: Long
    ): Long {
        for (i in input.indices) {
            val res = findRange(input.subList(i, input.size - i), 2, nonValid)
            if (res.isNotEmpty()) return res.minOrNull()!! + res.maxOrNull()!!
        }
        return -1
    }

    private tailrec fun findRange(
        input: List<Long>,
        size: Int,
        sum: Long
    ): List<Long> {
        if (size > input.size) return emptyList()
        val currentSum = input.subList(0, size).sum()
        if (currentSum == sum) return input.subList(0, size)
        if (currentSum > sum) return emptyList()
        return findRange(input, size + 1, sum)
    }

}