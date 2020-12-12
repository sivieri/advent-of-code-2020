package me.sivieri.aoc2020.day11

import kotlin.math.abs

class ComplexWaitingArea(
    seatsInput: Array<Array<Char>>
) {

    private val rows = seatsInput.size
    private val columns = seatsInput.firstOrNull()?.size ?: 0
    private var seats = (0 until rows)
        .map { r ->
            (0 until columns)
                .map { c ->
                    Seat(seatsInput[r][c], r, c)
                }
                .toTypedArray()
        }
        .toTypedArray()

    fun countOccupiedSeats(): Int =
        seats.sumBy { row -> row.count { seat -> seat.status == Seat.occupiedSeat } }

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
        val newSeats = Array(rows) { Array(columns) { Seat.zero } }
        (0 until rows).forEach { row ->
            (0 until columns).forEach { column ->
                val adjacents = getAdjacents(row, column)
                when {
                    seats[row][column].status == Seat.emptySeat && adjacents.all { it != Seat.occupiedSeat } -> {
                        newSeats[row][column] = Seat(Seat.occupiedSeat, row, column)
                        res = true
                    }
                    seats[row][column].status == Seat.occupiedSeat && adjacents.count { it == Seat.occupiedSeat } >= 5 -> {
                        newSeats[row][column] = Seat(Seat.emptySeat, row, column)
                        res = true
                    }
                    else -> { newSeats[row][column] = seats[row][column] }
                }
            }
        }
        seats = newSeats
        return res
    }

    private fun getAdjacents(row: Int, column: Int): List<Char> {
        val seat = seats[row][column]
        return Direction
            .values()
            .mapNotNull { getFirstNonFloor(seat, it)?.status }
    }

    fun getFirstNonFloor(
        seat: Seat,
        direction: Direction,
    ): Seat? = when (direction) {
        Direction.Upper -> move(seat.row - 1, seat.col, rows, columns, -1, 0)
        Direction.UpperRight -> move(seat.row - 1, seat.col + 1, rows, columns, -1, +1)
        Direction.Right -> move(seat.row, seat.col + 1, rows, columns, 0, +1)
        Direction.LowerRight -> move(seat.row + 1, seat.col + 1, rows, columns, +1, +1)
        Direction.Lower -> move(seat.row + 1, seat.col, rows, columns, +1, 0)
        Direction.LowerLeft -> move(seat.row + 1, seat.col - 1, rows, columns, +1, -1)
        Direction.Left -> move(seat.row, seat.col - 1, rows, columns, 0, -1)
        Direction.UpperLeft -> move(seat.row - 1, seat.col - 1, rows, columns, -1, -1)
    }

    private tailrec fun move(
        row: Int,
        col: Int,
        maxRow: Int,
        maxCol: Int,
        rowIncrement: Int,
        columnIncrement: Int
    ): Seat? {
        if (row < 0 || row >= maxRow) return null
        if (col < 0 || col >= maxCol) return null
        if (seats[row][col].status != Seat.floor) return seats[row][col]
        return move(row + rowIncrement, col + columnIncrement, maxRow, maxCol, rowIncrement, columnIncrement)
    }

    override fun toString(): String =
        seats.joinToString("\n") { row ->
            row.joinToString("") { seat -> seat.status.toString() }
        }

}