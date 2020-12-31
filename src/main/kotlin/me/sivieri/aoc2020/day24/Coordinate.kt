package me.sivieri.aoc2020.day24

// Representation: https://www.redblobgames.com/grids/hexagons/
data class Coordinate(
    val x: Int,
    val y: Int,
    val z: Int
) {

    fun move(direction: Direction): Coordinate = when (direction) {
        Direction.e -> Coordinate(x + 1, y - 1, z)
        Direction.w -> Coordinate(x - 1, y + 1, z)
        Direction.se -> Coordinate(x, y - 1, z + 1)
        Direction.sw -> Coordinate(x - 1, y, z + 1)
        Direction.nw -> Coordinate(x, y + 1, z - 1)
        Direction.ne -> Coordinate(x + 1, y, z - 1)
    }

    fun adjacent(): List<Coordinate> = listOf(
        this.move(Direction.e),
        this.move(Direction.w),
        this.move(Direction.se),
        this.move(Direction.sw),
        this.move(Direction.ne),
        this.move(Direction.nw)
    )

    override fun toString(): String {
        return "($x,$y,$z)"
    }
}
