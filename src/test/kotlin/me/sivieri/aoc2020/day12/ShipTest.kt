package me.sivieri.aoc2020.day12

import org.junit.Assert
import org.junit.Test

class ShipTest {

    @Test
    fun `01 simple test`() {
        val input = """
            F10
            N3
            F7
            R90
            F11
        """.trimIndent()
            .split("\n")
        val ship = Ship()
        ship.move(input)
        val manhattanDistance = ship.manhattanDistance()
        Assert.assertEquals(25, manhattanDistance)
    }

    @Test
    fun `02 test with waypoint`() {
        val input = """
            F10
            N3
            F7
            R90
            F11
        """.trimIndent()
            .split("\n")
        val ship = ShipWithWaypoint()
        ship.move(input)
        val manhattanDistance = ship.manhattanDistance()
        Assert.assertEquals(286, manhattanDistance)
    }

}