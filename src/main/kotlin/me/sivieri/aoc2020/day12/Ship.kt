package me.sivieri.aoc2020.day12

import kotlin.math.abs

class Ship {

    private var direction = Direction.East
    private var x = 0
    private var y = 0

    fun manhattanDistance(): Int = abs(x) + abs(y)

    fun move(movements: List<String>) =
        movements.forEach { m ->
            val order = m.first()
            val value = m.substring(1).toInt()
            when (order) {
                'R' -> direction = direction.rotate(value)
                'L' -> direction = direction.rotate(-value)
                'F' -> move(direction, value)
                else -> move(Direction.fromRepr(order), value)
            }
        }

    private fun move(direction: Direction, value: Int) = when (direction) {
        Direction.North -> y += value
        Direction.East -> x += value
        Direction.South -> y -= value
        Direction.West -> x -= value
    }

    override fun toString(): String {
        return "Current position: ($x, $y)\nCurrent direction: $direction"
    }
}