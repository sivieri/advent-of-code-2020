package me.sivieri.aoc2020.day16

import me.sivieri.aoc2020.find
import me.sivieri.aoc2020.multiplyBy

class Scanner(private var classes: List<ValueClass>) {

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
        val possibleClasses = assignPossibleClasses(validTickets)
        while (possibleClasses.any { it.value.size != 1 }) {
            val singlePositions = possibleClasses
                .filter { it.value.size == 1 }
                .map { it.value.first() }
            val keys = possibleClasses
                .keys
                .toSet()
            keys.forEach { key ->
                val currentValues = possibleClasses[key]!!
                if (currentValues.size != 1) {
                    possibleClasses[key] = currentValues
                        .subtract(singlePositions)
                        .toList()
                }
            }
        }
        possibleClasses
            .let { assigned ->
                classes = classes.map { c ->
                    val position = assigned
                        .find { it.name == c.name }!!
                        .first()
                    c.copy(positionInTicket = position)
                }
            }
    }

    private fun assignPossibleClasses(tickets: List<Ticket>): MutableMap<ValueClass, List<Int>> {
        val length = tickets.first().values.size
        return classes
            .map { c ->
                val positions = (0 until length).filter { position ->
                    tickets
                        .all { ticket ->
                            c.ranges.any { r -> r.contains(ticket.values[position]) }
                        }
                }
                c to positions
            }
            .toMap()
            .toMutableMap()
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