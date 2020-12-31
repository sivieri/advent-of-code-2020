package me.sivieri.aoc2020.day24

enum class TileStatus(val small: String) {

    white("W"), black("B");

    fun flip(): TileStatus =
        when (this) {
            white -> black
            black -> white
        }

}