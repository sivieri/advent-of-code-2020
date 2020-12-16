package me.sivieri.aoc2020.day14

import java.lang.IllegalArgumentException

class MemoryHandler {

    private lateinit var bitMask: BitMask
    private val memory: MutableMap<Int, Long> = mutableMapOf()

    fun executeInput(input: MemoryInput) {
        bitMask = input.bitMask
        input.values.forEach { (location, value) ->
            val valueAfterMask = bitMask.apply(value)
            memory[location] = valueAfterMask
        }
    }

    fun sum(): Long =
        memory.values.sum()

    companion object {

        @Suppress("RegExpRedundantEscape")
        private val memoryRegex = Regex("mem\\[([0-9]+)\\] = ([0-9]+)")

        fun parse(input: List<String>): List<MemoryInput> {
            val indexes = input
                .mapIndexed { index, s -> if (s.startsWith("mask")) index else null }
                .filterNotNull()
                .plus(input.size)
            return (0 until indexes.size - 1).map { index ->
                val block = input.subList(indexes[index], indexes[index + 1])
                val bitMask = BitMask(block.first().substring(7))
                val values = block
                    .subList(1, block.size)
                    .map {
                        val groups = memoryRegex
                            .matchEntire(it)
                            ?.groups ?: throw IllegalArgumentException("Wrong input: $it")
                        groups[1]!!.value.toInt() to groups[2]!!.value.toLong()
                    }
                MemoryInput(bitMask, values)
            }
        }

    }

}