package me.sivieri.aoc2020.day18

data class LongTrick(
    val value: Long
)

operator fun LongTrick.plus(other: LongTrick): LongTrick =
    LongTrick(this.value + other.value)

operator fun LongTrick.minus(other: LongTrick): LongTrick =
    LongTrick(this.value * other.value)
