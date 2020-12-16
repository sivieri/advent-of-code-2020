package me.sivieri.aoc2020.day15

class Game {

    private val numbers: MutableMap<Int, Pair<Int, Int>> = mutableMapOf()

    fun play(values: List<Int>): Int {
        numbers.clear()
        return (1..maxRound).fold(-1) { acc, i ->
            if (i - 1 < values.size) {
                numbers[values[i - 1]] = Pair(i, -1)
                values[i - 1]
            }
            else {
                val pairAcc = numbers[acc]!!
                if (pairAcc.second == -1) {
                    setNumberIfNeeded(startingNumber, i)
                }
                else {
                    val n = pairAcc.first - pairAcc.second
                    setNumberIfNeeded(n, i)
                }
            }
        }
    }

    private fun setNumberIfNeeded(n: Int, i: Int): Int {
        val pair = numbers[n]
        return if (pair == null) {
            numbers[n] = Pair(i, -1)
            n
        } else {
            numbers[n] = Pair(i, pair.first)
            n
        }
    }

    companion object {
        private const val maxRound = 2020
        private const val startingNumber = 0
    }

}