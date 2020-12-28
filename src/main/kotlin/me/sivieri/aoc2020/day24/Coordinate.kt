package me.sivieri.aoc2020.day24

data class Coordinate(
    val x: Int,
    val y: Int
) {

    fun move(direction: Direction): Coordinate = when (direction) {
        Direction.e -> this.copy(x = x + 1)
        Direction.se -> this.copy(x = x + 1, y = y - 1)
        Direction.sw -> this.copy(x = x - 1, y = y - 1)
        Direction.w -> this.copy(x = x - 1)
        Direction.nw -> this.copy(x = x - 1, y = y + 1)
        Direction.ne -> this.copy(x = x + 1, y = y + 1)
    }

}
