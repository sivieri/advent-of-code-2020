package me.sivieri.aoc2020.day20

import me.sivieri.aoc2020.stringRepresentation
import me.sivieri.aoc2020.toMatrix
import org.junit.Assert
import org.junit.FixMethodOrder
import org.junit.Test
import org.junit.runners.MethodSorters

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
class MatrixTest {

    @Test
    fun `01 rotate square`() {
        val square = """
            #..#
            .#.#
            #...
            ....
        """.trimIndent()
            .toMatrix()
        val exp = """
            .#.#
            ..#.
            ....
            ..##
        """.trimIndent()
        val res = Matrix
            .rotate90(square)
            .stringRepresentation()
        Assert.assertEquals(exp, res)
    }

    @Test
    fun `02 rotate rectangle`() {
        val rect = """
            #..#...#
            .#.#.#..
        """.trimIndent()
            .toMatrix()
        val exp = """
            .#
            #.
            ..
            ##
            ..
            #.
            ..
            .#
        """.trimIndent()
        val res = Matrix
            .rotate90(rect)
            .stringRepresentation()
        Assert.assertEquals(exp, res)
    }

    @Test
    fun `03 flip vertical square`() {
        val square = """
            #..#
            .#.#
            #...
            ....
        """.trimIndent()
            .toMatrix()
        val exp = """
            #..#
            #.#.
            ...#
            ....
        """.trimIndent()
        val res = Matrix
            .flipVertical(square)
            .stringRepresentation()
        Assert.assertEquals(exp, res)
    }

    @Test
    fun `04 flip vertical rectangle`() {
        val rect = """
            #..#...#
            .#.#.#..
        """.trimIndent()
            .toMatrix()
        val exp = """
            #...#..#
            ..#.#.#.
        """.trimIndent()
        val res = Matrix
            .flipVertical(rect)
            .stringRepresentation()
        Assert.assertEquals(exp, res)
    }

    @Test
    fun `05 flip horizontal square`() {
        val square = """
            #..#
            .#.#
            #...
            ....
        """.trimIndent()
            .toMatrix()
        val exp = """
            ....
            #...
            .#.#
            #..#
        """.trimIndent()
        val res = Matrix
            .flipHorizontal(square)
            .stringRepresentation()
        Assert.assertEquals(exp, res)
    }

    @Test
    fun `06 flip horizontal rectangle`() {
        val rect = """
            #..#...#
            .#.#.#..
        """.trimIndent()
            .toMatrix()
        val exp = """
            .#.#.#..
            #..#...#
        """.trimIndent()
        val res = Matrix
            .flipHorizontal(rect)
            .stringRepresentation()
        Assert.assertEquals(exp, res)
    }

}