package me.sivieri.aoc2020.day23

import org.junit.Assert
import org.junit.FixMethodOrder
import org.junit.Test
import org.junit.runners.MethodSorters

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
class CupsTest {

    @Test
    fun `01 10 iterations`() {
        val input = "389125467"
        val cups = Cups(input)
        cups.performIterations(10)
        val res = cups.getFullString()
        Assert.assertEquals("92658374", res)
    }

    @Test
    fun `02 100 iterations`() {
        val input = "389125467"
        val cups = Cups(input)
        cups.performIterations(100)
        val res = cups.getFullString()
        Assert.assertEquals("67384529", res)
    }

}