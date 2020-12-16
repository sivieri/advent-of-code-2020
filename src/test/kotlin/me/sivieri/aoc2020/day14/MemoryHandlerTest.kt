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
        val memoryHandler = MemoryHandler()
        blocks.forEach { memoryHandler.executeInput(it) }
        val res = memoryHandler.sum()
        Assert.assertEquals(165, res)
    }

}