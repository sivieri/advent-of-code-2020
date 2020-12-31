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

    private fun printTiles() {
        val minx = tiles.map { it.key.x }.minOrNull()!!
        val maxx = tiles.map { it.key.x }.maxOrNull()!!
        val miny = tiles.map { it.key.y }.minOrNull()!!
        val maxy = tiles.map { it.key.y }.maxOrNull()!!
        val buffer = StringBuffer("X: $minx - $maxx; Y: $miny - $maxy\n")
        for (j in miny..maxy) {
            if (j % 2 != 0) buffer.append(" ")
            for (i in minx..maxx) {
                val coord = Coordinate(i, j)
                buffer.append(coord.toString())
                val tile = tiles.getOrDefault(coord, TileStatus.white)
                buffer.append(tile.small)
                buffer.append(" ")
            }
            buffer.append("\n")
        }
        println(buffer.toString())
    }

    fun iterate(days: Int) {
        (1..days).forEach { day ->
            println("Day $day")
            printTiles()
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
                        .map { it to TileStatus.white }
                }
                .distinct()
            tiles.clear()
            tiles.putAll(externalTiles)
            tiles.putAll(newTiles)
            println(countBlackTiles())
        }
    }

}