package me.sivieri.aoc2020.day16

data class ValueClass(
    val name: String,
    val ranges: List<IntRange>,
    val positionInTicket: Int? = null
)
