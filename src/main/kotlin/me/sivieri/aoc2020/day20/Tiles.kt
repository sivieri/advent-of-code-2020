package me.sivieri.aoc2020.day20

import me.sivieri.aoc2020.*
import me.sivieri.aoc2020.arrayEquals
import me.sivieri.aoc2020.indexOfAll
import me.sivieri.aoc2020.indexOfArray
import me.sivieri.aoc2020.multiplyBy
import java.lang.IllegalArgumentException
import java.lang.IllegalStateException
import kotlin.math.sqrt

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
        val size = sqrt(tiles.size.toDouble()).toInt()
        for (i in 0 until size) {
            val prevRow = puzzle.getOrElse(puzzle.size - 1) { emptyList() }
            val row = mutableListOf<Tile>()
            for (j in 0 until size) {
                val tile = if (i == 0 && j == 0) {
                    tiles.first { it.bordersCount[0] == 0 && it.bordersCount[3] == 0 }
                }
                else {
                    val prev = if (j == 0) null
                    else row[j - 1]
                    val upper = if (prevRow.isEmpty()) null
                    else prevRow[j]
                    findTile(upper, prev)
                }
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
        val leftBorder = prev?.borders?.get(1)
        val upperBorder = upper?.borders?.get(2)
        val candidates = tiles
            .filter { tile ->
                if (prev != null && prev.id == tile.id) false
                else if (upper != null && upper.id == tile.id) false
                else {
                    val leftCondition =
                        if (leftBorder != null) tile.borders.any { leftBorder.arrayEquals(it) } ||
                                tile.mirrorBorders.any { leftBorder.arrayEquals(it) }
                        else true
                    val upperCondition =
                        if (upperBorder != null) tile.borders.any { upperBorder.arrayEquals(it) }||
                                tile.mirrorBorders.any { upperBorder.arrayEquals(it) }
                        else true
                    leftCondition && upperCondition
                }
            }
        if (candidates.size > 1) throw IllegalStateException("Found multiple tiles: ${candidates.size}")
        if (candidates.isEmpty()) throw IllegalStateException("Found no tiles")
        val cur = candidates.first()
        val final = when {
            upperBorder != null -> {
                val upperLocation = cur
                    .borders
                    .indexOfArray(upperBorder)
                val secondUpperLocation = cur
                    .mirrorBorders
                    .indexOfArray(upperBorder)
                when {
                    upperLocation == 0 -> cur
                    upperLocation == 1 -> cur.rotate90().rotate90().rotate90()
                    upperLocation == 2 -> cur.rotate90().rotate90().mirrorVertical()
                    upperLocation == 3 -> cur.rotate90().mirrorVertical()
                    secondUpperLocation == 0 -> cur.mirrorVertical()
                    secondUpperLocation == 1 -> cur.rotate90().rotate90().rotate90().mirrorVertical()
                    secondUpperLocation == 2 -> cur.rotate90().rotate90()
                    secondUpperLocation == 3 -> cur.rotate90()
                    else -> throw IllegalStateException("Non-existing state")
                }
            }
            leftBorder != null -> {
                val leftLocation = cur
                    .borders
                    .indexOfArray(leftBorder)
                val secondLeftLocation = cur
                    .mirrorBorders
                    .indexOfArray(leftBorder)
                when {
                    leftLocation == 0 -> cur.rotate90().rotate90().rotate90().mirrorHorizontal()
                    leftLocation == 1 -> cur.rotate90().rotate90().mirrorHorizontal()
                    leftLocation == 2 -> cur.rotate90()
                    leftLocation == 3 -> cur
                    secondLeftLocation == 0 -> cur.rotate90().rotate90().rotate90()
                    secondLeftLocation == 1 -> cur.rotate90().rotate90()
                    secondLeftLocation == 2 -> cur.rotate90().mirrorHorizontal()
                    secondLeftLocation == 3 -> cur.mirrorHorizontal()
                    else -> throw IllegalStateException("Non-existing state")
                }
            }
            else -> throw IllegalStateException("At least one border must exist")
        }
        if (
            upperBorder != null &&
            final.borders.indexOfArray(upperBorder) != 0 &&
            leftBorder != null &&
            final.borders.indexOfArray(leftBorder) != 3
        ) {
            throw IllegalStateException("Found tile ${final.id}, but borders are wrong")
        }
        return final
    }

    fun findRoughWaters(): Int {
        val image = prepareImage()
        val onlyWater = removeMonsters(image)
        return onlyWater
            .flatten()
            .count { it == Tile.roughWater }
    }

    private fun removeMonsters(image: Array<Array<Char>>): Array<Array<Char>> {
        val images = listOf(
            image,
            Matrix.flipVertical(image),
            Matrix.rotate90(image),
            Matrix.flipVertical(Matrix.rotate90(image)),
            Matrix.rotate90(Matrix.rotate90(image)),
            Matrix.flipVertical(Matrix.rotate90(Matrix.rotate90(image))),
            Matrix.rotate90(Matrix.rotate90(Matrix.rotate90(image))),
            Matrix.flipVertical(Matrix.rotate90(Matrix.rotate90(Matrix.rotate90(image)))),
        )
        images.forEach {
            val found = searchMonster(it)
            if (found > 0) return removeMonster(it)
        }
        throw IllegalStateException("Unable to find monsters!")
    }

    private fun removeMonster(image: Array<Array<Char>>): Array<Array<Char>> {
        val removedImage = image
            .map { it.toList().toTypedArray() }
            .toTypedArray()
        val monsterWidth = monster.first().size
        val monsterHeight = monster.size
        val mask = monster
            .map { it.map { if (it == Tile.roughWater) 1 else 0 }.toTypedArray() }
            .toTypedArray()
        val imageWidth = removedImage.first().size
        val imageHeight = removedImage.size
        for (i in 0..(imageHeight - monsterHeight)) {
            for (j in 0..(imageWidth - monsterWidth)) {
                val sub = image
                    .sliceArray(i until (i + monsterHeight))
                    .map { it.sliceArray(j until (j + monsterWidth)) }
                    .toTypedArray()
                if (compareWithMask(sub, mask)) {
                    removeWithMask(removedImage, mask, i, i + monsterHeight, j, j + monsterWidth)
                }
            }
        }
        return removedImage
    }

    @Suppress("ReplaceManualRangeWithIndicesCalls")
    private fun removeWithMask(
        image: Array<Array<Char>>,
        mask: Array<Array<Int>>,
        startRow: Int,
        endRow: Int,
        startCol: Int,
        endCol: Int
    ) {
        if (endRow - startRow != monster.size || endCol - startCol != monster.first().size) {
            throw IllegalArgumentException("Wrong sizes!")
        }
        for (i in startRow until endRow) {
            for (j in startCol until endCol) {
                if (mask[i - startRow][j - startCol] == 1) image[i][j] = Tile.calmWater
            }
        }
    }

    fun searchMonsters(): Int {
        val image = prepareImage()
        val images = listOf(
            image,
            Matrix.flipVertical(image),
            Matrix.rotate90(image),
            Matrix.flipVertical(Matrix.rotate90(image)),
            Matrix.rotate90(Matrix.rotate90(image)),
            Matrix.flipVertical(Matrix.rotate90(Matrix.rotate90(image))),
            Matrix.rotate90(Matrix.rotate90(Matrix.rotate90(image))),
            Matrix.flipVertical(Matrix.rotate90(Matrix.rotate90(Matrix.rotate90(image)))),
        )
        images.forEach {
            val found = searchMonster(it)
            if (found > 0) return found
        }
        return 0
    }

    private fun searchMonster(
        image: Array<Array<Char>>
    ): Int {
        val monsterWidth = monster.first().size
        val monsterHeight = monster.size
        val mask = monster
            .map { it.map { if (it == Tile.roughWater) 1 else 0 }.toTypedArray() }
            .toTypedArray()
        val imageWidth = image.first().size
        val imageHeight = image.size
        var counter = 0
        for (i in 0..(imageHeight - monsterHeight)) {
            for (j in 0..(imageWidth - monsterWidth)) {
                val sub = image
                    .sliceArray(i until (i + monsterHeight))
                    .map { it.sliceArray(j until (j + monsterWidth)) }
                    .toTypedArray()
                if (compareWithMask(sub, mask)) counter++
            }
        }
        return counter
    }

    @Suppress("ReplaceManualRangeWithIndicesCalls")
    private fun compareWithMask(
        image: Array<Array<Char>>,
        mask: Array<Array<Int>>
    ): Boolean {
        if (image.size != monster.size || image.first().size != monster.first().size) {
            throw IllegalArgumentException("Wrong sizes!")
        }
        for (i in 0 until image.size) {
            for (j in 0 until image.first().size) {
                if (mask[i][j] == 1 && image[i][j] != monster[i][j]) return false
            }
        }
        return true
    }

    fun prepareImage(): Array<Array<Char>> {
        val tileImages = puzzle
            .map {
                it.map { it.removeBorders() }
            }
        val buffer = StringBuffer()
        tileImages
            .forEach { row ->
                for (i in 0 until (tiles.first().image.size - 2)) {
                    if (i > 0) buffer.append("\n")
                    val line = row
                        .flatMap { it[i].toList() }
                        .joinToString("")
                    buffer.append(line)
                }
                buffer.append("\n")
            }
        return buffer
            .toString()
            .split("\n")
            .filter { it != "" }
            .map { it.toCharArray().toTypedArray() }
            .toTypedArray()
    }

    companion object {
        private val monster = listOf(
            "                  # ",
            "#    ##    ##    ###",
            " #  #  #  #  #  #   "
        )
            .map { it.toCharArray().toTypedArray() }
            .toTypedArray()
    }

}