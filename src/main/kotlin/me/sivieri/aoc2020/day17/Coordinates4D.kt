package me.sivieri.aoc2020.day17

data class Coordinates4D(
    val x: Int,
    val y: Int,
    val z: Int,
    val w: Int
) {

    fun getNeighbors(): List<Coordinates4D> =
        generateCoordinates(
            x - 1,
            x + 1,
            y - 1,
            y + 1,
            z - 1,
            z + 1,
            w - 1,
            w + 1
        ).filter { it != this }

    companion object {

        fun generateCoordinates(
            minX: Int,
            maxX: Int,
            minY: Int,
            maxY: Int,
            minZ: Int,
            maxZ: Int,
            minW: Int,
            maxW: Int
        ): List<Coordinates4D> =
            (minX..maxX).flatMap { x ->
                (minY..maxY).flatMap { y ->
                    (minZ..maxZ).flatMap { z ->
                        (minW..maxW).map { w ->
                            Coordinates4D(x, y, z, w)
                        }
                    }
                }
            }

    }

}
