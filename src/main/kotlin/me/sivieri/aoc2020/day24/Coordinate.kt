package me.sivieri.aoc2020.day24

data class Coordinate(
    val x: Int,
    val y: Int
) {

    fun move(direction: Direction): Coordinate = when (direction) {
        Direction.e -> this.copy(x = x + 1)
        Direction.w -> this.copy(x = x - 1)
        Direction.se -> {
            if (y % 2 == 0) this.copy(y = y + 1)
            else this.copy(x = x + 1, y = y + 1)
        }
        Direction.sw -> {
            if (y % 2 == 0) this.copy(x = x - 1, y = y + 1)
            else this.copy(y = y + 1)
        }
        Direction.nw -> {
            if (y % 2 == 0) this.copy(x = x - 1, y = y - 1)
            else this.copy(y = y - 1)
        }
        Direction.ne -> {
            if (y % 2 == 0) this.copy(y = y - 1)
            else this.copy(x = x + 1, y = y - 1)
        }
    }

}
