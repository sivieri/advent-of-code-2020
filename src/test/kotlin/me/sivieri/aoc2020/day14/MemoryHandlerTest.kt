package me.sivieri.aoc2020.day14

import org.junit.Assert
import org.junit.FixMethodOrder
import org.junit.Test
import org.junit.runners.MethodSorters

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
class MemoryHandlerTest {

    @Test
    fun `01 memory test`() {
        val input = """
            mask = XXXXXXXXXXXXXXXXXXXXXXXXXXXXX1XXXX0X
            mem[8] = 11
            mem[7] = 101
            mem[8] = 0
        """.trimIndent()
            .split("\n")
        val blocks = MemoryHandler.parse(input)
        val memoryHandler = MemoryHandlerV1()
        blocks.forEach { memoryHandler.executeInput(it) }
        val res = memoryHandler.sum()
        Assert.assertEquals(165, res)
    }

    @Test
    fun `02 memory test V2`() {
        val input = """
            mask = 000000000000000000000000000000X1001X
            mem[42] = 100
            mask = 00000000000000000000000000000000X0XX
            mem[26] = 1
        """.trimIndent()
            .split("\n")
        val blocks = MemoryHandler.parse(input)
        val memoryHandler = MemoryHandlerV2()
        blocks.forEach { memoryHandler.executeInput(it) }
        val res = memoryHandler.sum()
        Assert.assertEquals(208, res)
    }

}