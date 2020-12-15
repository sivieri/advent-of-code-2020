package me.sivieri.aoc2020.day14

import org.junit.Assert
import org.junit.FixMethodOrder
import org.junit.Test
import org.junit.runners.MethodSorters

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
class BitMaskTest {

    @Test
    fun `01 string representation`() {
        val mask = BitMask("X0XX1X0")
        val exp = """
            Mask: 0100101
            Value: 0000100
            ValueAndMask: 0000100
        """.trimIndent()
        Assert.assertEquals(exp, mask.toString())
    }

    @Test
    fun `02 apply mask`() {
        val mask = BitMask("X0XX1X0")
        val number = 101L
        val exp = 68L
        Assert.assertEquals(exp, mask.apply(number))
    }

}