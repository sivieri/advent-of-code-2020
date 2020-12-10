package me.sivieri.aoc2020.day10

import com.google.common.collect.Sets
import me.sivieri.aoc2020.multiplyBy

class AdaptersBag(
    private val adapters: List<Int>
) {

    fun findDifferenceProduct(): Int {
        val sortedAdapters = adapters
            .plus(0)
            .plus(adapters.maxOrNull()!! + 3)
            .sorted()
        val diffs = sortedAdapters
            .subList(0, sortedAdapters.size - 1)
            .zip(sortedAdapters.subList(1, sortedAdapters.size))
            .map { it.second - it.first }
        return diffs.filter { it == 1 }.size * diffs.filter { it == 3 }.size
    }

    @Suppress("UnstableApiUsage")
    fun findCombinations(): Long {
        val sortedAdapters = adapters
            .sorted()
        val subsequences = mutableListOf<List<Int>>()
        val latest = (0 until sortedAdapters.size - 1)
            .fold(0) { latest, i ->
                if (sortedAdapters[i + 1] - sortedAdapters[i] >= 3) {
                    subsequences.add(sortedAdapters.subList(latest, i + 1))
                    i + 1
                }
                else latest
            }
        subsequences.add(sortedAdapters.subList(latest, sortedAdapters.size))
        val combinations = subsequences
            .map {
                if (it.size < 3) 1
                else {
                    val candidates = it.subList(1, it.size - 1).toSet()
                    var counter: Long = 0
                    (0..candidates.size)
                        .forEach { i ->
                            Sets
                                .combinations(candidates, i)
                                .forEach { if (checkIfSound(sortedAdapters, it)) counter++ }
                        }
                    counter
                }
            }
        return combinations.multiplyBy { it }
    }

    private fun checkIfSound(numbers: List<Int>, toBeRemoved: Set<Int>): Boolean {
        val prepared = numbers
            .filterNot { toBeRemoved.contains(it) }
        return (0 until prepared.size - 1)
            .all { prepared[it + 1] - prepared[it] <= 3 }
    }

}