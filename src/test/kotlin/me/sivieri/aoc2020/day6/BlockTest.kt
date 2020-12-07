package me.sivieri.aoc2020.day6

import org.junit.Assert
import org.junit.Test

class BlockTest {

    @Test
    fun `00 test occurences`() {
        val s = """
            abcx
            abcy
            abcz
        """.trimIndent()
        val res = Block.countOccurrences(s)
        Assert.assertEquals(3, res)
    }

    @Test
    fun `01 test occurences`() {
        val s = """
            abc
        """.trimIndent()
        val res = Block.countOccurrences(s)
        Assert.assertEquals(3, res)
    }

    @Test
    fun `02 test occurences`() {
        val s = """
            a
            b
            c
        """.trimIndent()
        val res = Block.countOccurrences(s)
        Assert.assertEquals(0, res)
    }

    @Test
    fun `03 test occurences`() {
        val s = """
            ab
            ac
        """.trimIndent()
        val res = Block.countOccurrences(s)
        Assert.assertEquals(1, res)
    }

    @Test
    fun `04 test occurences`() {
        val s = """
            a
            a
            a
            a
        """.trimIndent()
        val res = Block.countOccurrences(s)
        Assert.assertEquals(1, res)
    }

    @Test
    fun `05 test occurences`() {
        val s = """
            b
        """.trimIndent()
        val res = Block.countOccurrences(s)
        Assert.assertEquals(1, res)
    }

}