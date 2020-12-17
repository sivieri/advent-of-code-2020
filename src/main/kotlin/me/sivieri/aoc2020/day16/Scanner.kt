package me.sivieri.aoc2020.day16

class Scanner(private val classes: Map<String, List<IntRange>>) {

    private val ranges: List<IntRange> = classes.values.flatten()

    fun validate(ticket: Ticket): Int =
        ticket
            .values
            .filter { value ->
                !ranges
                    .any { range ->
                        range.contains(value)
                    }
            }
            .sum()

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
                    groups[1]!!.value to listOf(
                        groups[2]!!.value.toInt()..groups[3]!!.value.toInt(),
                        groups[4]!!.value.toInt()..groups[5]!!.value.toInt()
                    )
                }
                .toMap()
            val scanner = Scanner(classes)
            return Triple(scanner, myTicket, otherTickets)
        }

    }

}