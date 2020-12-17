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
            .map { scanner.validateWithSum(it) }
            .sum()
        Assert.assertEquals(71, res)
    }

    @Test
    fun `02 scan fields position test`() {
        val input = """
            class: 0-1 or 4-19
            row: 0-5 or 8-19
            seat: 0-13 or 16-19
            
            your ticket:
            11,12,13
            
            nearby tickets:
            3,9,18
            15,1,5
            5,14,9
        """.trimIndent()
            .split("\n")
        val (scanner, myTicket, tickets) = Scanner.parse(input)
        val validTickets = tickets
            .filter { scanner.validate(it) }
        scanner.findFieldsPosition(validTickets)
        val res = scanner.findSpecificFieldsValues(myTicket, listOf("class", "row"))
        Assert.assertEquals(132L, res)
    }

    @Test
    fun `03 find specific fields sum`() {
        val scanner = Scanner(
            listOf(
                ValueClass("class", listOf(0..1, 4..19), 1),
                ValueClass("row", listOf(0..5, 8..19), 0),
                ValueClass("seat", listOf(0..13, 16..19), 2)
            )
        )
        val res = scanner.findSpecificFieldsValues(Ticket(listOf(11, 12, 13)), listOf("class", "row"))
        Assert.assertEquals(132L, res)
    }

}