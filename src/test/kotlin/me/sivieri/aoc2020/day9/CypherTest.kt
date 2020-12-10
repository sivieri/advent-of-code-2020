package me.sivieri.aoc2020.day9

import org.junit.Assert
import org.junit.Test

class CypherTest {

    @Test
    fun `01 test with small input`() {
        val input = """
            35
            20
            15
            25
            47
            40
            62
            55
            65
            95
            102
            117
            150
            182
            127
            219
            299
            277
            309
            576
        """.trimIndent()
            .split("\n")
            .map { it.toLong() }
        val cypher = Cypher()
        val res = cypher.getFirstNonValid(input, 5)
        Assert.assertEquals(127L, res)
    }

    @Test
    fun `02 find the wrong number min-max range sum`() {
        val input = """
            35
            20
            15
            25
            47
            40
            62
            55
            65
            95
            102
            117
            150
            182
            127
            219
            299
            277
            309
            576
        """.trimIndent()
            .split("\n")
            .map { it.toLong() }
        val cypher = Cypher()
        val preambleSize = 5
        val nonValid = cypher.getFirstNonValid(input, preambleSize)
        val res = cypher.findMinMaxRangeSum(input, nonValid)
        Assert.assertEquals(62L, res)
    }

}