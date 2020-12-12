package me.sivieri.aoc2020.day12

import kotlin.math.abs

class ShipWithWaypoint {

    private val ship = Position(0, 0, Direction.East)
    private val waypoint = Position(10, 1, Direction.East)

    fun manhattanDistance(): Int = abs(ship.x) + abs(ship.y)

    fun move(movements: List<String>) =
        movements.forEach { m ->
            val order = m.first()
            val value = m.substring(1).toInt()
            when (order) {
                'R' -> waypoint.moveAround(value)
                'L' -> waypoint.moveAround(-value)
                'F' -> ship.moveWith(value, waypoint)
                else -> waypoint.move(value, Direction.fromRepr(order))
            }
        }

    override fun toString(): String {
        return ship.toString()
    }
}