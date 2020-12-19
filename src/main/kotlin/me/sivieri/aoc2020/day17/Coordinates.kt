package me.sivieri.aoc2020.day17

data class Coordinates(
    val x: Int,
    val y: Int,
    val z: Int
) {

    fun getNeighbors(): List<Coordinates> =
        generateCoordinates(
            x - 1,
            x + 1,
            y - 1,
            y + 1,
            z - 1,
            z + 1
        ).filter { it != this }

    companion object {

        fun generateCoordinates(
            minX: Int,
            maxX: Int,
            minY: Int,
            maxY: Int,
            minZ: Int,
            maxZ: Int
        ): List<Coordinates> =
            (minX..maxX).flatMap { x ->
                (minY..maxY).flatMap { y ->
                    (minZ..maxZ).map { z ->
                        Coordinates(x, y, z)
                    }
                }
            }

    }

}
