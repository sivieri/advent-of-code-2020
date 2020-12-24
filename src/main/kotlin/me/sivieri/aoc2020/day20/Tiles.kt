package me.sivieri.aoc2020.day20

import me.sivieri.aoc2020.indexOfAll

class Tiles(input: List<String>) {

    private val tiles: List<Tile>

    init {
        val tilesIndices = input
            .indexOfAll { it.startsWith("Tile") }
        tiles = tilesIndices
            .map { i ->
                val sublist = input.subList(i, i + 11)
                val tileNumber = sublist[0]
                    .substring(0, sublist[0].length - 1)
                    .split(" ")[1]
                    .toInt()
                val image = sublist
                    .subList(1, sublist.size)
                    .map { it.toCharArray().toTypedArray() }
                    .toTypedArray()
                Tile(tileNumber, image)
            }
            .sortedBy { it.id }
    }

    fun findAngles(): List<Tile> {
        tiles
            .forEach { tile ->
                tile.clearCount()
                tiles.forEach { otherTile ->
                    if (tile != otherTile) {
                        tile.countIfAny(otherTile)
                    }
                }
            }
        return tiles.filter { it.getEmptyCount() == 2 }
    }

}