package me.sivieri.aoc2020.day16

import me.sivieri.aoc2020.Utils

object Main2 {

    @JvmStatic
    fun main(args: Array<String>) {
        val data = Utils.readInput(16)
        val (scanner, myTicket, tickets) = Scanner.parse(data)
        val validTickets = tickets
            .filter { scanner.validate(it) }
        scanner.findFieldsPosition(validTickets)
        val res = scanner.findSpecificFieldsValues(myTicket, listOf("departure"))
        println(res)
    }

}