package me.sivieri.aoc2020.day17

import org.junit.Assert
import org.junit.FixMethodOrder
import org.junit.Test
import org.junit.runners.MethodSorters

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
class CubesTest {

    @Test
    fun `01 active cubes test`() {
        val input = """
            .#.
            ..#
            ###
        """.trimIndent()
            .split("\n")
            .map { it.toCharArray() }
        val cubes = Cubes(input)
        cubes.performIterations(6)
        val res = cubes.countActive()
        Assert.assertEquals(112, res)
    }

}