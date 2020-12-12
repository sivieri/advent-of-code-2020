package me.sivieri.aoc2020.day12

import org.junit.Assert
import org.junit.FixMethodOrder
import org.junit.Test
import org.junit.runners.MethodSorters

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
class DirectionTest {

    @Test
    fun `01 test from representation`() {
        Assert.assertEquals(Direction.North, Direction.fromRepr('N'))
        Assert.assertEquals(Direction.East, Direction.fromRepr('E'))
        Assert.assertEquals(Direction.South, Direction.fromRepr('S'))
        Assert.assertEquals(Direction.West, Direction.fromRepr('W'))
    }

    @Test
    fun `02 rotations`() {
        val direction = Direction.North
        Assert.assertEquals(Direction.North, direction.rotate(0))
        Assert.assertEquals(Direction.East, direction.rotate(90))
        Assert.assertEquals(Direction.South, direction.rotate(180))
        Assert.assertEquals(Direction.West, direction.rotate(270))
        Assert.assertEquals(Direction.North, direction.rotate(360))
        Assert.assertEquals(Direction.West, direction.rotate(-90))
        Assert.assertEquals(Direction.South, direction.rotate(-180))
        Assert.assertEquals(Direction.East, direction.rotate(-270))
        Assert.assertEquals(Direction.North, direction.rotate(-360))
    }

}