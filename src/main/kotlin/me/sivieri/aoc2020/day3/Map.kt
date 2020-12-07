package me.sivieri.aoc2020.day3

import java.lang.AssertionError

@Suppress("ArrayInDataClass")
data class Map(
    private val map: Array<Array<Char>>
) {

    private var currentPosition = Position(0, 0)
    var counter = 0

    fun move(xValue: Int, yValue: Int) {
        if (currentPosition.y + yValue >= map.size)
            throw AssertionError("Trying to go beyond the bottom!")
        val newX = (currentPosition.x + xValue) % map[currentPosition.y].size
        val newY = currentPosition.y + yValue
        if (map[newY][newX] == tree) {
            counter++
        }
        currentPosition = Position(newX, newY)
    }

    fun isBottom(): Boolean =
        currentPosition.y >= map.size - 1

    fun reset() {
        counter = 0
        currentPosition = Position(0, 0)
    }

    override fun toString(): String {
        return map.joinToString(
            separator = "\n"
        ) {
            it.joinToString(separator = "")
        }
    }

    companion object {
        private const val tree = '#'
    }

}