package me.sivieri.aoc2020.day19

import org.junit.Assert
import org.junit.FixMethodOrder
import org.junit.Test
import org.junit.runners.MethodSorters

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
class ParserTest {

    @Test
    fun `01 test parser`() {
        val input = """
            0: 4 1 5
            1: 2 3 | 3 2
            2: 4 4 | 5 5
            3: 4 5 | 5 4
            4: "a"
            5: "b"

            ababbb
            bababa
            abbbab
            aaabbb
            aaaabbb
        """.trimIndent()
            .split("\n")
        val parser = Parser(input)
        val res = parser.validateAll()
        Assert.assertEquals(2, res)
    }

}