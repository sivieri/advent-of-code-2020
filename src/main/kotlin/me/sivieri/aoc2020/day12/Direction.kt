package me.sivieri.aoc2020.day12

import kotlin.math.abs
import kotlin.math.sign

enum class Direction(
    private val repr: Char
) {

    North('N'), East('E'), South('S'), West('W');

    fun rotate(value: Int): Direction {
        val actualValue = (abs(value) / 90) % 4
        val sign = sign(value.toDouble())
        val currentPosition = values().indexOf(this)
        val newPosition = (currentPosition + (sign * actualValue).toInt())
            .let {
                if (it >= 0) it % 4
                else 4 + it
            }
        return values()[newPosition]
    }

    companion object {

        fun fromRepr(repr: Char): Direction =
            values().first { it.repr == repr }

    }

}