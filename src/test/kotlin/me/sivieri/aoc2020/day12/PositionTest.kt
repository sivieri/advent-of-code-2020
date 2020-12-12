package me.sivieri.aoc2020.day12

import org.junit.Assert
import org.junit.FixMethodOrder
import org.junit.Test
import org.junit.runners.MethodSorters

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
class PositionTest {

    @Test
    fun `01 rotate 90`() {
        val position = Position(10, 4, Direction.North)
        position.moveAround(90)
        Assert.assertEquals(4, position.x)
        Assert.assertEquals(-10, position.y)
    }

    @Test
    fun `02 rotate -90`() {
        val position = Position(10, 4, Direction.North)
        position.moveAround(-90)
        Assert.assertEquals(-4, position.x)
        Assert.assertEquals(10, position.y)
    }

    @Test
    fun `03 move with`() {
        val position = Position(100, 10, Direction.East)
        val other = Position(10, 4, Direction.East)
        position.moveWith(7, other)
        Assert.assertEquals(170, position.x)
        Assert.assertEquals(38, position.y)
    }

}