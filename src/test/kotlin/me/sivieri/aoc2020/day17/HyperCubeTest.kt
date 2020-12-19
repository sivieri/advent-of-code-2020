package me.sivieri.aoc2020.day17

import org.junit.Assert
import org.junit.FixMethodOrder
import org.junit.Test
import org.junit.runners.MethodSorters

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
class HyperCubeTest {

    @Test
    fun `01 string representation`() {
        val input = """
            .#.
            ..#
            ###
        """.trimIndent()
            .split("\n")
            .map { it.toCharArray() }
        val cubes = HyperCube(input)
        val exp = """
            z = 0, w = 0
            .#.
            ..#
            ###
        """.trimIndent()
        Assert.assertEquals(exp, cubes.toGridString())
    }

    @Test
    fun `02 active cubes test`() {
        val input = """
            .#.
            ..#
            ###
        """.trimIndent()
            .split("\n")
            .map { it.toCharArray() }
        val cubes = HyperCube(input)
        cubes.performIterations(6)
        val res = cubes.countActive()
        Assert.assertEquals(848, res)
    }

}