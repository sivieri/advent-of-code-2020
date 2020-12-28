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

    fun iterate(days: Int) {
        (1..days).forEach { day ->
            println("Day $day")
            val newTiles = tiles
                .map { tile ->
                    val adjacent = tile.key.adjacent()
                    val statuses = adjacent.map {
                        tiles.getOrDefault(it, TileStatus.white)
                    }
                    val blacks = statuses.count { it == TileStatus.black }
                    if (tile.value == TileStatus.black && (blacks == 0 || blacks > 2)) tile.key to TileStatus.white
                    else if (tile.value == TileStatus.white && blacks == 2) tile.key to TileStatus.black
                    else tile.key to tile.value
                }
                .toMap()
            val externalTiles = tiles
                .flatMap { tile ->
                    tile
                        .key
                        .adjacent()
                        .filter { !newTiles.containsKey(it) }
                        .map { it to TileStatus.white }
                }
            tiles.clear()
            tiles.putAll(newTiles)
            tiles.putAll(externalTiles)
        }
    }

}