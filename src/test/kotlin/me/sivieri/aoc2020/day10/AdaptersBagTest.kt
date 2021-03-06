package me.sivieri.aoc2020.day10

import org.junit.Assert
import org.junit.Test

class AdaptersBagTest {

    @Test
    fun `01 bag test complete usage`() {
        val input = """
            16
            10
            15
            5
            1
            11
            7
            19
            6
            12
            4
        """.trimIndent()
            .split("\n")
            .map { it.toInt() }
        val bag = AdaptersBag(input)
        val res = bag.findDifferenceProduct()
        Assert.assertEquals(35, res)
    }

    @Test
    fun `02 bag test complete usage`() {
        val input = """
            28
            33
            18
            42
            31
            14
            46
            20
            48
            47
            24
            23
            49
            45
            19
            38
            39
            11
            1
            32
            25
            35
            8
            17
            7
            9
            4
            2
            34
            10
            3
        """.trimIndent()
            .split("\n")
            .map { it.toInt() }
        val bag = AdaptersBag(input)
        val res = bag.findDifferenceProduct()
        Assert.assertEquals(220, res)
    }

    @Test
    fun `03 bag test combination`() {
        val input = """
            16
            10
            15
            5
            1
            11
            7
            19
            6
            12
            4
        """.trimIndent()
            .split("\n")
            .map { it.toInt() }
        val bag = AdaptersBag(input)
        val res = bag.findCombinations()
        Assert.assertEquals(8L, res)
    }

    @Test
    fun `04 bag test combination`() {
        val input = """
            28
            33
            18
            42
            31
            14
            46
            20
            48
            47
            24
            23
            49
            45
            19
            38
            39
            11
            1
            32
            25
            35
            8
            17
            7
            9
            4
            2
            34
            10
            3
        """.trimIndent()
            .split("\n")
            .map { it.toInt() }
        val bag = AdaptersBag(input)
        val res = bag.findCombinations()
        Assert.assertEquals(19208L, res)
    }

}