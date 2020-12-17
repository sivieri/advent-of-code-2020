package me.sivieri.aoc2020.day16

import org.junit.Assert
import org.junit.FixMethodOrder
import org.junit.Test
import org.junit.runners.MethodSorters

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
class ScannerTest {

    @Test
    fun `01 scan errors test`() {
        val input = """
            class: 1-3 or 5-7
            row: 6-11 or 33-44
            seat: 13-40 or 45-50

            your ticket:
            7,1,14

            nearby tickets:
            7,3,47
            40,4,50
            55,2,20
            38,6,12
        """.trimIndent()
            .split("\n")
        val (scanner, _, tickets) = Scanner.parse(input)
        val res = tickets
            .map { scanner.validate(it) }
            .sum()
        Assert.assertEquals(71, res)
    }

}