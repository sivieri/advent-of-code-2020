package me.sivieri.aoc2020.day24

import me.sivieri.aoc2020.round
import kotlin.math.sqrt

data class Coordinate(
    val x: Double,
    val y: Double
) {

    fun move(direction: Direction): Coordinate = when (direction) {
        Direction.e -> this.copy(x = x + 2)
        Direction.se -> this.copy(x = x + 1, y = (y - sqrt(2.0)).round(2))
        Direction.sw -> this.copy(x = x - 1, y = (y - sqrt(2.0)).round(2))
        Direction.w -> this.copy(x = x - 2)
        Direction.nw -> this.copy(x = x - 1, y = (y + sqrt(2.0)).round(2))
        Direction.ne -> this.copy(x = x + 1, y = (y + sqrt(2.0)).round(2))
    }

}
