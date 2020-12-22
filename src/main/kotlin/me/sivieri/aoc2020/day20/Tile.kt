package me.sivieri.aoc2020.day20

class Tile(
    val id: Int,
    image: Array<Array<Char>>
) {

    private val borders: List<Int> = listOf(
        image[0].joinToString("").hashCode(),
        image[size - 1].joinToString("").hashCode(),
        image.map { it[0] }.joinToString("").hashCode(),
        image.map { it[size - 1] }.joinToString("").hashCode()
    )
    private val mirrorBorders: List<Int> = listOf(
        image[0].reversedArray().joinToString("").hashCode(),
        image[size - 1].reversedArray().joinToString("").hashCode(),
        image.map { it[0] }.reversed().joinToString("").hashCode(),
        image.map { it[size - 1] }.reversed().joinToString("").hashCode()
    )
    private var bordersCount: Array<Int> = Array(4) { 0 }

    fun clearCount() {
        bordersCount = Array(4) { 0 }
    }

    fun countIfAny(other: Tile) {
        borders
            .indices
            .forEach { i ->
                if (other.borders.contains(borders[i]) || other.mirrorBorders.contains(borders[i])) bordersCount[i]++
            }
    }

    fun getEmptyCount(): Int = bordersCount.count { it == 0 }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Tile

        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        return id
    }

    override fun toString(): String {
        return "Tile(id=$id, borders=$borders, mirrorBorders=$mirrorBorders, counts=${bordersCount.joinToString(" ")})"
    }

    companion object {
        const val size = 10
    }

}
