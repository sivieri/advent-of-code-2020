package me.sivieri.aoc2020.day11

class WaitingArea(
    private var seats: Array<Array<Char>>
) {

    private val rows = seats.size
    private val columns = seats.firstOrNull()?.size ?: 0

    fun countOccupiedSeats(): Int =
        seats.sumBy { row -> row.count { seat -> seat == occupiedSeat } }

    fun stabilizeArea(): Int {
        var counter = 0
        while (turn()) {
            counter++
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
                    seats[row][column] == occupiedSeat && adjacents.count { it == occupiedSeat } >= 4 -> {
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
        getPosition(row - 1, column - 1),
        getPosition(row - 1, column),
        getPosition(row - 1, column + 1),
        getPosition(row, column - 1),
        getPosition(row, column + 1),
        getPosition(row + 1, column - 1),
        getPosition(row + 1, column),
        getPosition(row + 1, column + 1)
    )

    private fun getPosition(row: Int, column: Int): Char? = when {
        row < 0 -> null
        row >= rows -> null
        column < 0 -> null
        column >= columns -> null
        else -> seats[row][column]
    }

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