package me.sivieri.aoc2020.day2

import java.lang.AssertionError

data class Password(
    val min: Int,
    val max: Int,
    val letter: Char,
    val password: String
) {

    init {
        if (min < 0 || max < 0) throw AssertionError("Invalid $min or $max")
    }

    fun isCountValid(): Boolean =
        password.count {
            it == letter
        } in min..max

    fun isPositionValid(): Boolean =
        password.length >= max &&
        ((password[min - 1] == letter) xor
         (password[max - 1] == letter))

    companion object {
        val regex = Regex("([0-9]+)-([0-9]+) ([a-zA-Z]): ([a-zA-Z]*)")
    }

}