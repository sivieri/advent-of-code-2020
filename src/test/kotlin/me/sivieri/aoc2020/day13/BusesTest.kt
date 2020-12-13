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

}