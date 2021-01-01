package me.sivieri.aoc2020.day20

class Tile(
    val id: Int,
    private val image: Array<Array<Char>>
) {

    val borders: List<Array<Char>> = listOf(
        image[0],
        image.map { it[size - 1] }.toTypedArray(),
        image[size - 1],
        image.map { it[0] }.toTypedArray()
    )
    val mirrorBorders: List<Array<Char>> = listOf(
        image[0].reversedArray(),
        image.map { it[size - 1] }.reversed().toTypedArray(),
        image[size - 1].reversedArray(),
        image.map { it[0] }.reversed().toTypedArray()
    )
    private val bordersCode: List<Int> = listOf(
        image[0].joinToString("").hashCode(),
        image.map { it[size - 1] }.joinToString("").hashCode(),
        image[size - 1].joinToString("").hashCode(),
        image.map { it[0] }.joinToString("").hashCode()
    )
    private val mirrorBordersCode: List<Int> = listOf(
        image[0].reversedArray().joinToString("").hashCode(),
        image.map { it[size - 1] }.reversed().joinToString("").hashCode(),
        image[size - 1].reversedArray().joinToString("").hashCode(),
        image.map { it[0] }.reversed().joinToString("").hashCode()
    )
    var bordersCount: Array<Int> = Array(4) { 0 }

    fun clearCount() {
        bordersCount = Array(4) { 0 }
    }

    fun countIfAny(other: Tile) {
        bordersCode
            .indices
            .forEach { i ->
                if (other.bordersCode.contains(bordersCode[i]) || other.mirrorBordersCode.contains(bordersCode[i])) bordersCount[i]++
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
        return "Tile(id=$id, borders=$bordersCode, mirrorBorders=$mirrorBordersCode, counts=${bordersCount.joinToString(" ")})"
    }

    companion object {
        const val size = 10
    }

}
