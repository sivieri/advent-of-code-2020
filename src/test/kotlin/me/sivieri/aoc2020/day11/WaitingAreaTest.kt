package me.sivieri.aoc2020.day11

import org.junit.Assert
import org.junit.Test

class WaitingAreaTest {

    @Test
    fun `01 test stabilization`() {
        val input = """
            L.LL.LL.LL
            LLLLLLL.LL
            L.L.L..L..
            LLLL.LL.LL
            L.LL.LL.LL
            L.LLLLL.LL
            ..L.L.....
            LLLLLLLLLL
            L.LLLLLL.L
            L.LLLLL.LL
        """.trimIndent()
            .split("\n")
            .map { it.toCharArray().toTypedArray() }
            .toTypedArray()
        val waitingArea = WaitingArea(input)
        val iterations = waitingArea.stabilizeArea()
        val occupiedSeats = waitingArea.countOccupiedSeats()
        Assert.assertEquals(5, iterations)
        Assert.assertEquals(37, occupiedSeats)
    }

}