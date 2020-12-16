package me.sivieri.aoc2020.day14

import com.google.common.collect.Sets
import me.sivieri.aoc2020.LongRepresentation
import java.math.BigInteger

class MemoryHandlerV2: MemoryHandler() {

    @Suppress("UnstableApiUsage")
    override fun executeInput(input: MemoryInput) {
        val s = input
            .bitMask
            .maskString
            .toCharArray()
        val indexes = s
            .mapIndexed { index, c -> if (c == BitMask.floating) index else null }
            .filterNotNull()
            .toSet()
        (0..indexes.size)
            .flatMap { i ->
                Sets.combinations(indexes, i)
            }
            .forEach { set ->
                val actualSet = set
                    .map { Pair(it, '1') }
                    .union(indexes.minus(set).map { Pair(it, '0') })
                input
                    .values
                    .forEach { (key, value) ->
                        val newKey = input.bitMask.applyIgnoreZero(key)
                        val c = LongRepresentation
                            .toStringRepresentation(newKey)
                            .padStart(s.size, '0')
                            .toCharArray()
                        actualSet.forEach { c[it.first] = it.second }
                        val n = BigInteger(c.concatToString(), 2).longValueExact()
                        memory[n] = value
                    }
            }
    }

}