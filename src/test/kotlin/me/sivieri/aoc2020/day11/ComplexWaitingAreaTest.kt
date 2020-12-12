package me.sivieri.aoc2020.day11

import org.junit.Assert
import org.junit.FixMethodOrder
import org.junit.Test
import org.junit.runners.MethodSorters

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
class ComplexWaitingAreaTest {

    private val input = """
            #.#L.LL.LL
            LLLLLLL.LL
            #.L.L..L..
            LLLL.L#.LL
            L.LL.LL.LL
            L.LLLLL.L#
            ..L.L.....
            LLLLLLLLLL
            L.LLLLLL.L
            L.LLLLL.##
        """.trimIndent()
        .split("\n")
        .map { it.toCharArray().toTypedArray() }
        .toTypedArray()
    private val waitingArea = ComplexWaitingArea(input)

    @Test
    fun `01 get all directions of a central cell`() {
        val seat = Seat(Seat.emptySeat, 3, 6)
        val upperSeat = waitingArea.getFirstNonFloor(seat, Direction.Upper)
        Assert.assertEquals(1, upperSeat!!.row)
        Assert.assertEquals(6, upperSeat!!.col)
        val upperRightSeat = waitingArea.getFirstNonFloor(seat, Direction.UpperRight)
        Assert.assertEquals(2, upperRightSeat!!.row)
        Assert.assertEquals(7, upperRightSeat!!.col)
        val rightSeat = waitingArea.getFirstNonFloor(seat, Direction.Right)
        Assert.assertEquals(3, rightSeat!!.row)
        Assert.assertEquals(8, rightSeat!!.col)
        val lowerRightSeat = waitingArea.getFirstNonFloor(seat, Direction.LowerRight)
        Assert.assertEquals(5, lowerRightSeat!!.row)
        Assert.assertEquals(8, lowerRightSeat!!.col)
        val lowerSeat = waitingArea.getFirstNonFloor(seat, Direction.Lower)
        Assert.assertEquals(4, lowerSeat!!.row)
        Assert.assertEquals(6, lowerSeat!!.col)
        val lowerLeftSeat = waitingArea.getFirstNonFloor(seat, Direction.LowerLeft)
        Assert.assertEquals(4, lowerLeftSeat!!.row)
        Assert.assertEquals(5, lowerLeftSeat!!.col)
        val leftSeat = waitingArea.getFirstNonFloor(seat, Direction.Left)
        Assert.assertEquals(3, leftSeat!!.row)
        Assert.assertEquals(5, leftSeat!!.col)
        val upperLeftSeat = waitingArea.getFirstNonFloor(seat, Direction.UpperLeft)
        Assert.assertEquals(1, upperLeftSeat!!.row)
        Assert.assertEquals(4, upperLeftSeat!!.col)
    }

    @Test
    fun `02 get all directions of a first column cell`() {
        val seat = Seat(Seat.emptySeat, 2, 0)
        val upperSeat = waitingArea.getFirstNonFloor(seat, Direction.Upper)
        Assert.assertEquals(1, upperSeat!!.row)
        Assert.assertEquals(0, upperSeat!!.col)
        val upperRightSeat = waitingArea.getFirstNonFloor(seat, Direction.UpperRight)
        Assert.assertEquals(1, upperRightSeat!!.row)
        Assert.assertEquals(1, upperRightSeat!!.col)
        val rightSeat = waitingArea.getFirstNonFloor(seat, Direction.Right)
        Assert.assertEquals(2, rightSeat!!.row)
        Assert.assertEquals(2, rightSeat!!.col)
        val lowerRightSeat = waitingArea.getFirstNonFloor(seat, Direction.LowerRight)
        Assert.assertEquals(3, lowerRightSeat!!.row)
        Assert.assertEquals(1, lowerRightSeat!!.col)
        val lowerSeat = waitingArea.getFirstNonFloor(seat, Direction.Lower)
        Assert.assertEquals(3, lowerSeat!!.row)
        Assert.assertEquals(0, lowerSeat!!.col)
        val lowerLeftSeat = waitingArea.getFirstNonFloor(seat, Direction.LowerLeft)
        Assert.assertNull(lowerLeftSeat)
        val leftSeat = waitingArea.getFirstNonFloor(seat, Direction.Left)
        Assert.assertNull(leftSeat)
        val upperLeftSeat = waitingArea.getFirstNonFloor(seat, Direction.UpperLeft)
        Assert.assertNull(upperLeftSeat)
    }

    @Test
    fun `03 get all directions of a last column cell`() {
        val seat = Seat(Seat.emptySeat, 5, 9)
        val upperSeat = waitingArea.getFirstNonFloor(seat, Direction.Upper)
        Assert.assertEquals(4, upperSeat!!.row)
        Assert.assertEquals(9, upperSeat!!.col)
        val upperRightSeat = waitingArea.getFirstNonFloor(seat, Direction.UpperRight)
        Assert.assertNull(upperRightSeat)
        val rightSeat = waitingArea.getFirstNonFloor(seat, Direction.Right)
        Assert.assertNull(rightSeat)
        val lowerRightSeat = waitingArea.getFirstNonFloor(seat, Direction.LowerRight)
        Assert.assertNull(lowerRightSeat)
        val lowerSeat = waitingArea.getFirstNonFloor(seat, Direction.Lower)
        Assert.assertEquals(7, lowerSeat!!.row)
        Assert.assertEquals(9, lowerSeat!!.col)
        val lowerLeftSeat = waitingArea.getFirstNonFloor(seat, Direction.LowerLeft)
        Assert.assertEquals(7, lowerLeftSeat!!.row)
        Assert.assertEquals(7, lowerLeftSeat!!.col)
        val leftSeat = waitingArea.getFirstNonFloor(seat, Direction.Left)
        Assert.assertEquals(5, leftSeat!!.row)
        Assert.assertEquals(8, leftSeat!!.col)
        val upperLeftSeat = waitingArea.getFirstNonFloor(seat, Direction.UpperLeft)
        Assert.assertEquals(4, upperLeftSeat!!.row)
        Assert.assertEquals(8, upperLeftSeat!!.col)
    }

    @Test
    fun `04 get all directions of a first row cell`() {
        val seat = Seat(Seat.emptySeat, 0, 2)
        val upperSeat = waitingArea.getFirstNonFloor(seat, Direction.Upper)
        Assert.assertNull(upperSeat)
        val upperRightSeat = waitingArea.getFirstNonFloor(seat, Direction.UpperRight)
        Assert.assertNull(upperRightSeat)
        val rightSeat = waitingArea.getFirstNonFloor(seat, Direction.Right)
        Assert.assertEquals(0, rightSeat!!.row)
        Assert.assertEquals(3, rightSeat!!.col)
        val lowerRightSeat = waitingArea.getFirstNonFloor(seat, Direction.LowerRight)
        Assert.assertEquals(1, lowerRightSeat!!.row)
        Assert.assertEquals(3, lowerRightSeat!!.col)
        val lowerSeat = waitingArea.getFirstNonFloor(seat, Direction.Lower)
        Assert.assertEquals(1, lowerSeat!!.row)
        Assert.assertEquals(2, lowerSeat!!.col)
        val lowerLeftSeat = waitingArea.getFirstNonFloor(seat, Direction.LowerLeft)
        Assert.assertEquals(1, lowerLeftSeat!!.row)
        Assert.assertEquals(1, lowerLeftSeat!!.col)
        val leftSeat = waitingArea.getFirstNonFloor(seat, Direction.Left)
        Assert.assertEquals(0, leftSeat!!.row)
        Assert.assertEquals(0, leftSeat!!.col)
        val upperLeftSeat = waitingArea.getFirstNonFloor(seat, Direction.UpperLeft)
        Assert.assertNull(upperLeftSeat)
    }

    @Test
    fun `05 get all directions of a last row cell`() {
        val seat = Seat(Seat.emptySeat, 9, 8)
        val upperSeat = waitingArea.getFirstNonFloor(seat, Direction.Upper)
        Assert.assertEquals(7, upperSeat!!.row)
        Assert.assertEquals(8, upperSeat!!.col)
        val upperRightSeat = waitingArea.getFirstNonFloor(seat, Direction.UpperRight)
        Assert.assertEquals(8, upperRightSeat!!.row)
        Assert.assertEquals(9, upperRightSeat!!.col)
        val rightSeat = waitingArea.getFirstNonFloor(seat, Direction.Right)
        Assert.assertEquals(9, rightSeat!!.row)
        Assert.assertEquals(9, rightSeat!!.col)
        val lowerRightSeat = waitingArea.getFirstNonFloor(seat, Direction.LowerRight)
        Assert.assertNull(lowerRightSeat)
        val lowerSeat = waitingArea.getFirstNonFloor(seat, Direction.Lower)
        Assert.assertNull(lowerSeat)
        val lowerLeftSeat = waitingArea.getFirstNonFloor(seat, Direction.LowerLeft)
        Assert.assertNull(lowerLeftSeat)
        val leftSeat = waitingArea.getFirstNonFloor(seat, Direction.Left)
        Assert.assertEquals(9, leftSeat!!.row)
        Assert.assertEquals(6, leftSeat!!.col)
        val upperLeftSeat = waitingArea.getFirstNonFloor(seat, Direction.UpperLeft)
        Assert.assertEquals(8, upperLeftSeat!!.row)
        Assert.assertEquals(7, upperLeftSeat!!.col)
    }

    @Test
    fun `06 get all directions of the first cell`() {
        val seat = Seat(Seat.emptySeat, 0, 0)
        val upperSeat = waitingArea.getFirstNonFloor(seat, Direction.Upper)
        Assert.assertNull(upperSeat)
        val upperRightSeat = waitingArea.getFirstNonFloor(seat, Direction.UpperRight)
        Assert.assertNull(upperRightSeat)
        val rightSeat = waitingArea.getFirstNonFloor(seat, Direction.Right)
        Assert.assertEquals(0, rightSeat!!.row)
        Assert.assertEquals(2, rightSeat!!.col)
        val lowerRightSeat = waitingArea.getFirstNonFloor(seat, Direction.LowerRight)
        Assert.assertEquals(1, lowerRightSeat!!.row)
        Assert.assertEquals(1, lowerRightSeat!!.col)
        val lowerSeat = waitingArea.getFirstNonFloor(seat, Direction.Lower)
        Assert.assertEquals(1, lowerSeat!!.row)
        Assert.assertEquals(0, lowerSeat!!.col)
        val lowerLeftSeat = waitingArea.getFirstNonFloor(seat, Direction.LowerLeft)
        Assert.assertNull(lowerLeftSeat)
        val leftSeat = waitingArea.getFirstNonFloor(seat, Direction.Left)
        Assert.assertNull(leftSeat)
        val upperLeftSeat = waitingArea.getFirstNonFloor(seat, Direction.UpperLeft)
        Assert.assertNull(upperLeftSeat)
    }

    @Test
    fun `07 get all directions of the last cell`() {
        val seat = Seat(Seat.emptySeat, 9, 9)
        val upperSeat = waitingArea.getFirstNonFloor(seat, Direction.Upper)
        Assert.assertEquals(8, upperSeat!!.row)
        Assert.assertEquals(9, upperSeat!!.col)
        val upperRightSeat = waitingArea.getFirstNonFloor(seat, Direction.UpperRight)
        Assert.assertNull(upperRightSeat)
        val rightSeat = waitingArea.getFirstNonFloor(seat, Direction.Right)
        Assert.assertNull(rightSeat)
        val lowerRightSeat = waitingArea.getFirstNonFloor(seat, Direction.LowerRight)
        Assert.assertNull(lowerRightSeat)
        val lowerSeat = waitingArea.getFirstNonFloor(seat, Direction.Lower)
        Assert.assertNull(lowerSeat)
        val lowerLeftSeat = waitingArea.getFirstNonFloor(seat, Direction.LowerLeft)
        Assert.assertNull(lowerLeftSeat)
        val leftSeat = waitingArea.getFirstNonFloor(seat, Direction.Left)
        Assert.assertEquals(9, leftSeat!!.row)
        Assert.assertEquals(8, leftSeat!!.col)
        val upperLeftSeat = waitingArea.getFirstNonFloor(seat, Direction.UpperLeft)
        Assert.assertEquals(7, upperLeftSeat!!.row)
        Assert.assertEquals(7, upperLeftSeat!!.col)
    }

}