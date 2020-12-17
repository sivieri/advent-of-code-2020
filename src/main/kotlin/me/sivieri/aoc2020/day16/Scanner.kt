package me.sivieri.aoc2020.day16

import me.sivieri.aoc2020.multiplyBy

class Scanner(private val classes: List<ValueClass>) {

    private val ranges: List<IntRange> = classes
        .flatMap { it.ranges }

    fun validateWithSum(ticket: Ticket): Int =
        filterTicketValues(ticket)
            .sum()

    fun validate(ticket: Ticket): Boolean =
        filterTicketValues(ticket).isEmpty()

    private fun filterTicketValues(ticket: Ticket) = ticket
        .values
        .filter { value ->
            !ranges
                .any { range ->
                    range.contains(value)
                }
        }

    fun findFieldsPosition(validTickets: List<Ticket>) {
        TODO("Not yet implemented")
    }

    fun findSpecificFieldsValues(myTicket: Ticket, classNames: List<String>): Long =
        classes
            .filter { c -> classNames.any { c.name.startsWith(it) } }
            .map { it.positionInTicket!! }
            .multiplyBy { myTicket.values[it].toLong() }

    companion object {
        private const val myTicketLabel = "your ticket:"
        private const val otherTicketsLabel = "nearby tickets:"

        private val classRegex = Regex("([a-z\\s]+): ([0-9]+)-([0-9]+) or ([0-9]+)-([0-9]+)")

        fun parse(data: List<String>): Triple<Scanner, Ticket, List<Ticket>> {
            val filtered = data
                .filter { it != "" }
            val myTicketIndex = filtered.indexOfFirst { it == myTicketLabel } + 1
            val myTicket = Ticket(filtered[myTicketIndex].split(",").map { it.toInt() })
            val otherTicketsIndex = filtered.indexOfFirst { it == otherTicketsLabel } + 1
            val otherTickets = filtered
                .subList(otherTicketsIndex, filtered.size)
                .map { Ticket(it.split(",").map { it.toInt() }) }
            val classes = filtered
                .subList(0, myTicketIndex - 1)
                .map {
                    val groups = classRegex
                        .matchEntire(it)
                        ?.groups ?: throw IllegalArgumentException("Wrong input: $it")
                    ValueClass(
                        groups[1]!!.value,
                        listOf(
                            groups[2]!!.value.toInt()..groups[3]!!.value.toInt(),
                            groups[4]!!.value.toInt()..groups[5]!!.value.toInt()
                        )
                    )
                }
            val scanner = Scanner(classes)
            return Triple(scanner, myTicket, otherTickets)
        }

    }

}