package me.sivieri.aoc2020.day5

import me.sivieri.aoc2020.head
import me.sivieri.aoc2020.tail
import java.lang.AssertionError
import kotlin.math.ceil
import kotlin.math.floor

data class BoardingPass(
    val rowEncoded: String,
    val seatEncoded: String
) {

    init {
        if (rowEncoded.length != 7 && seatEncoded.length != 3) {
            throw AssertionError("Wrong $rowEncoded or $seatEncoded length")
        }
    }

    fun seatCode(): Int =
        row() * 8 + seat()

    fun row(): Int = position(rowEncoded.toCharArray().toList(), 0, rows - 1)

    fun seat(): Int = position(seatEncoded.toCharArray().toList(), 0, seats - 1)

    companion object {
        private const val rows = 128
        private const val seats = 8

        private tailrec fun position(
            c: List<Char>,
            min: Int,
            max: Int
        ): Int =
            when {
                c.isEmpty() -> min
                c.head() == 'F' || c.head() == 'L' -> position(c.tail(), min, min + floor((max - min) / 2.0).toInt())
                c.head() == 'B' || c.head() == 'R' -> position(c.tail(), min + ceil((max - min) / 2.0).toInt(), max)
                else -> throw AssertionError("Wrong letter ${c.head()}")
            }
    }

}