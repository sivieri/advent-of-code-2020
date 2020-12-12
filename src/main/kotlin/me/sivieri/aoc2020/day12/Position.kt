package me.sivieri.aoc2020.day12

import java.lang.IllegalArgumentException

data class Position(
    var x: Int,
    var y: Int,
    var direction: Direction
) {

    fun move(value: Int, direction: Direction = this.direction) = when (direction) {
        Direction.North -> y += value
        Direction.East -> x += value
        Direction.South -> y -= value
        Direction.West -> x -= value
    }

    fun moveAround(value: Int) {
        val (newX, newY) = when (value % 360) {
            0 -> { Pair(x, y) }
            90, -270 -> Pair(y, -x)
            180, -180 -> Pair(-x, -y)
            270, -90 -> Pair(-y, x)
            else -> throw IllegalArgumentException("Only right angles")
        }
        x = newX
        y = newY
    }

    fun moveWith(value: Int, other: Position) {
        x += other.x * value
        y += other.y * value
    }

}
