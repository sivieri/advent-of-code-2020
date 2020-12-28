package me.sivieri.aoc2020.day24

class Floor(
    private val instructions: List<String>
) {

    private val tiles: MutableMap<Coordinate, TileStatus> = mutableMapOf()

    fun performInstructions() {
        instructions
            .forEach { line ->
                val finalCoordinate = move(line, Coordinate(0, 0))
                val currentTile = tiles.getOrDefault(finalCoordinate, TileStatus.white)
                tiles[finalCoordinate] = currentTile.flip()
            }
    }

    private tailrec fun move(line: String, coordinate: Coordinate): Coordinate {
        if (line == "") return coordinate
        val (direction, rest) = if (line.substring(0, 1) == "e" || line.substring(0, 1) == "w") {
            Pair(
                line.substring(0, 1),
                line.substring(1)
            )
        }
        else {
            Pair(
                line.substring(0, 2),
                line.substring(2)
            )
        }
        val newCoordinate = coordinate.move(Direction.valueOf(direction))
        return move(rest, newCoordinate)
    }

    fun countBlackTiles(): Int = tiles
        .count { it.value == TileStatus.black }

}