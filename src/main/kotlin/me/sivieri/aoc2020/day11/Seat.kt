package me.sivieri.aoc2020.day11

data class Seat(
    val status: Char,
    val row: Int,
    val col: Int
) {

    companion object {

        const val emptySeat = 'L'
        const val floor = '.'
        const val occupiedSeat = '#'

        val zero = Seat(floor, 0, 0)

    }

}
