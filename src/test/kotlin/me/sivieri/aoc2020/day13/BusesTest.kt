package me.sivieri.aoc2020.day13

import org.junit.Assert
import org.junit.FixMethodOrder
import org.junit.Test
import org.junit.runners.MethodSorters

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
class BusesTest {

    @Test
    fun `01 test buses`() {
        val input = """
            939
            7,13,x,x,59,x,31,19
        """.trimIndent()
            .split("\n")
        val time = input[0].toInt()
        val buses = input[1]
            .split(",")
            .filter { it != "x" }
            .map { it.toInt() }
        val res = Buses.findFirstBus(time, buses)
        Assert.assertEquals(295, res)
    }

    @Test
    fun `02 test sequence`() {
        val input = """
            7,13,x,x,59,x,31,19
        """.trimIndent()
            .split("\n")
        val buses = input[0]
            .split(",")
        val res = Buses.findTimeForBusSequence(buses)
        Assert.assertEquals(1068781, res)
    }

    @Test
    fun `03 test sequence`() {
        val input = """
            17,x,13,19
        """.trimIndent()
            .split("\n")
        val buses = input[0]
            .split(",")
        val res = Buses.findTimeForBusSequence(buses)
        Assert.assertEquals(3417, res)
    }

    @Test
    fun `04 test sequence`() {
        val input = """
            67,7,59,61
        """.trimIndent()
            .split("\n")
        val buses = input[0]
            .split(",")
        val res = Buses.findTimeForBusSequence(buses)
        Assert.assertEquals(754018, res)
    }

    @Test
    fun `05 test sequence`() {
        val input = """
            67,x,7,59,61
        """.trimIndent()
            .split("\n")
        val buses = input[0]
            .split(",")
        val res = Buses.findTimeForBusSequence(buses)
        Assert.assertEquals(779210, res)
    }

    @Test
    fun `06 test sequence`() {
        val input = """
            67,7,x,59,61
        """.trimIndent()
            .split("\n")
        val buses = input[0]
            .split(",")
        val res = Buses.findTimeForBusSequence(buses)
        Assert.assertEquals(1261476, res)
    }

    @Test
    fun `07 test sequence`() {
        val input = """
            1789,37,47,1889
        """.trimIndent()
            .split("\n")
        val buses = input[0]
            .split(",")
        val res = Buses.findTimeForBusSequence(buses)
        Assert.assertEquals(1202161486, res)
    }

}