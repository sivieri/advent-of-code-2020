package me.sivieri.aoc2020.day20

import org.junit.Assert
import org.junit.FixMethodOrder
import org.junit.Test
import org.junit.runners.MethodSorters

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
class TileTest {

    @Test
    fun `01 rotation test`() {
        val input = """
            ..##
            ##..
            #...
            ####
        """.trimIndent()
            .split("\n")
            .map { it.toCharArray().toTypedArray() }
            .toTypedArray()
        val tile = Tile(1, input)
        val rotated = tile.rotate90()
        val exp = """
            ###.
            #.#.
            #..#
            #..#
        """.trimIndent()
            .split("\n")
            .map { it.toCharArray().toTypedArray() }
            .toTypedArray()
        Assert.assertArrayEquals(exp, rotated.image)
    }

    @Test
    fun `02 vertical mirror test`() {
        val input = """
            ..##
            ##..
            #...
            ####
        """.trimIndent()
            .split("\n")
            .map { it.toCharArray().toTypedArray() }
            .toTypedArray()
        val tile = Tile(1, input)
        val rotated = tile.mirrorVertical()
        val exp = """
            ##..
            ..##
            ...#
            ####
        """.trimIndent()
            .split("\n")
            .map { it.toCharArray().toTypedArray() }
            .toTypedArray()
        Assert.assertArrayEquals(exp, rotated.image)
    }

    @Test
    fun `03 horizontal mirror test`() {
        val input = """
            ..##
            ##..
            #...
            ####
        """.trimIndent()
            .split("\n")
            .map { it.toCharArray().toTypedArray() }
            .toTypedArray()
        val tile = Tile(1, input)
        val rotated = tile.mirrorHorizontal()
        val exp = """
            ####
            #...
            ##..
            ..##
        """.trimIndent()
            .split("\n")
            .map { it.toCharArray().toTypedArray() }
            .toTypedArray()
        Assert.assertArrayEquals(exp, rotated.image)
    }

}