package me.sivieri.aoc2020.day14

import me.sivieri.aoc2020.LongRepresentation
import me.sivieri.aoc2020.toBinaryString
import me.sivieri.aoc2020.toList
import me.sivieri.aoc2020.toLong
import java.math.BigInteger
import java.util.*

class BitMask(
    s: String
) {

    private val maskLength: Int = s.length
    private val mask: BitSet = BitSet(maskLength)
    private val value: BitSet = BitSet(maskLength)
    private val valueAndMask: BitSet

    init {
        parseString(s) { index, c ->
            if (c[index] != unused) mask.set(index)
            if (c[index] == '1') value.set(index)
        }
        valueAndMask = (value.clone() as BitSet)
        valueAndMask.and(mask)
    }

    fun apply(number: Long): Long {
        val numberString = LongRepresentation.toStringRepresentation(number)
        val numberBits = BitSet(numberString.length)
        parseString(numberString) { index, c ->
            if (c[index] == '1') numberBits.set(index)
        }
        numberBits.andNot(mask)
        numberBits.or(valueAndMask)
        return numberBits.toLong(numberString.length)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as BitMask

        if (mask != other.mask) return false
        if (value != other.value) return false

        return true
    }

    override fun hashCode(): Int {
        var result = mask.hashCode()
        result = 31 * result + value.hashCode()
        return result
    }

    override fun toString(): String {
        return """
            Mask: ${mask.toBinaryString(maskLength)}
            Value: ${value.toBinaryString(maskLength)}
            ValueAndMask: ${valueAndMask.toBinaryString(maskLength)}
        """.trimIndent()
    }

    companion object {
        private const val unused = 'X'

        fun parseString(
            s: String,
            f: (Int, List<Char>) -> Unit
        ) {
            val c = s
                .toCharArray()
                .reversed()
            (c.indices).forEach { f(it, c) }
        }
    }

}