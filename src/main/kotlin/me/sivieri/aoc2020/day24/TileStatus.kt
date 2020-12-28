package me.sivieri.aoc2020.day24

enum class TileStatus {

    white, black;

    fun flip(): TileStatus =
        when (this) {
            white -> black
            black -> white
        }

}