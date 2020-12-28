package me.sivieri.aoc2020.day24

// Representation: https://www.redblobgames.com/grids/hexagons/
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

    fun adjacent(): List<Coordinate> = listOf(
        this.move(Direction.e),
        this.move(Direction.w),
        this.move(Direction.se),
        this.move(Direction.sw),
        this.move(Direction.ne),
        this.move(Direction.nw)
    )

}
