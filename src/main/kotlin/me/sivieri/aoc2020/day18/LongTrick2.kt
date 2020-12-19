package me.sivieri.aoc2020.day18

data class LongTrick2(
    val value: Long
)

operator fun LongTrick2.plus(other: LongTrick2): LongTrick2 =
    LongTrick2(this.value * other.value)

operator fun LongTrick2.times(other: LongTrick2): LongTrick2 =
    LongTrick2(this.value + other.value)
