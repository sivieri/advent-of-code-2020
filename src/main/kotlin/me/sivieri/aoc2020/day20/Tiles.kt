package me.sivieri.aoc2020.day20

import me.sivieri.aoc2020.indexOfAll
import me.sivieri.aoc2020.multiplyBy
import java.lang.IllegalStateException

class Tiles(input: List<String>) {

    private val tiles: List<Tile>
    private lateinit var puzzle: List<List<Tile>>

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

    private fun pairBorders() {
        tiles
            .forEach { tile ->
                tile.clearCount()
                tiles.forEach { otherTile ->
                    if (tile != otherTile) {
                        tile.countIfAny(otherTile)
                    }
                }
            }
    }

    fun getAnglesIdProduct(): Long {
        pairBorders()
        val angles = tiles.filter { it.getEmptyCount() == 2 }
        return angles.map { it.id }.multiplyBy { it.toLong() }
    }

    fun solvePuzzle(): List<List<Int>> {
        pairBorders()
        val puzzle = mutableListOf<MutableList<Tile>>()
        val size = tiles.size / 4
        for (i in 0 until size) {
            val prevRow = puzzle.getOrElse(puzzle.size - 1) { emptyList() }
            val row = mutableListOf<Tile>()
            for (j in 0 until size) {
                val prev = if (j == 0) null
                else row[j - 1]
                val upper = if (prevRow.isEmpty()) null
                else prevRow[j]
                val tile = findTile(upper, prev)
                row.add(tile)
            }
            puzzle.add(row)
        }
        this.puzzle = puzzle.map { it.toList() }
        return puzzle.map { it.map { it.id } }
    }

    private fun findTile(
        upper: Tile?,
        prev: Tile?
    ): Tile {
        val leftBorder = prev?.borders?.get(3)
        val upperBorder = upper?.borders?.get(2)
        val candidates = tiles
            .filter { tile ->
                val leftCondition = if (leftBorder != null) tile.borders.contains(leftBorder) || tile.mirrorBorders.contains(leftBorder)
                else true
                val upperCondition = if (upperBorder != null) tile.borders.contains(upperBorder) || tile.mirrorBorders.contains(upperBorder)
                else true
                leftCondition && upperCondition
            }
        if (candidates.size != 1) throw IllegalStateException("Found multiple tiles!")
        val cur = candidates.first()
        TODO("Not yet implemented")
    }

}