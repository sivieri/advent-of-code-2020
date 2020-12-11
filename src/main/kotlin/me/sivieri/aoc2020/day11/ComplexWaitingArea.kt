package me.sivieri.aoc2020.day11

import kotlin.math.abs

class ComplexWaitingArea(
    private var seats: Array<Array<Char>>
) {

    private val rows = seats.size
    private val columns = seats.firstOrNull()?.size ?: 0
    private val pairs = (0 until rows).flatMap { r -> (0 until columns).map { c -> Pair(r, c) } }

    fun countOccupiedSeats(): Int =
        seats.sumBy { row -> row.count { seat -> seat == occupiedSeat } }

    fun stabilizeArea(): Int {
        var counter = 0
        while (turn()) {
            counter++
            println(counter)
        }
        return counter
    }

    private fun turn(): Boolean {
        var res = false
        val newSeats = Array(rows) { Array(columns) { floor } }
        (0 until rows).forEach { row ->
            (0 until columns).forEach { column ->
                val adjacents = getAdjacents(row, column)
                when {
                    seats[row][column] == emptySeat && adjacents.all { it != occupiedSeat } -> {
                        newSeats[row][column] = occupiedSeat
                        res = true
                    }
                    seats[row][column] == occupiedSeat && adjacents.count { it == occupiedSeat } >= 5 -> {
                        newSeats[row][column] = emptySeat
                        res = true
                    }
                    else -> { newSeats[row][column] = seats[row][column] }
                }
            }
        }
        seats = newSeats
        return res
    }

    private fun getAdjacents(row: Int, column: Int): List<Char> = listOfNotNull(
        getPosition(getSeats(pairs.filter { it.first == row && it.second < column }, true)),
        getPosition(getSeats(pairs.filter { it.first == row && it.second > column }, false)),
        getPosition(getSeats(pairs.filter { it.first < row && it.second == column }, true)),
        getPosition(getSeats(pairs.filter { it.first > row && it.second == column }, false)),
        getPosition(getSeats(pairs.filter { abs(it.first - it.second) == abs(row - column) && it.first < row && it.second < column }, true)),
        getPosition(getSeats(pairs.filter { abs(it.first - it.second) == abs(row - column) && it.first > row && it.second > column }, false)),
        getPosition(getSeats(pairs.filter { abs(it.first + it.second) == abs(row + column) && it.first < row && it.second > column }, true)),
        getPosition(getSeats(pairs.filter { abs(it.first + it.second) == abs(row + column) && it.first > row && it.second < column }, false))
    )

    private fun getPosition(row: Array<Char>): Char? =
        if (row.isEmpty()) null
        else row.firstOrNull { it != floor }

    private fun getSeats(pairs: List<Pair<Int, Int>>, invert: Boolean): Array<Char> =
        pairs
            .sortedWith { p1, p2 ->
                val res = p1.first.compareTo(p2.first)
                if (res == 0) p1.second.compareTo(p2.second)
                else res
            }
            .let { if (invert) it.reversed() else it }
            .map { seats[it.first][it.second] }
            .toTypedArray()

    override fun toString(): String =
        seats.joinToString("\n") { row ->
            row.joinToString("")
        }

    companion object {

        private const val emptySeat = 'L'
        private const val floor = '.'
        private const val occupiedSeat = '#'

    }

}