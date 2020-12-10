package me.sivieri.aoc2020.day10

import com.google.common.collect.Sets

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
    fun findCombinations(): Int {
        val sortedAdapters = adapters
            .plus(0)
            .plus(adapters.maxOrNull()!! + 3)
            .sorted()
        val candidates = findRemovableElements(1, sortedAdapters, emptyList())
            .toSet()
        println("DEBUG - Found ${candidates.size} candidates")
        var counter = 0
        (0..candidates.size)
            .forEach { i ->
                val combinations = Sets
                    .combinations(candidates, i)
                println("DEBUG - ${combinations.size} combinations for $i")
                val res = combinations
                    .filter { checkIfSound(sortedAdapters, it) }
                    .size
                println("DEBUG - $res good combinations for $i")
                counter += res
            }
        return counter
    }

    private fun checkIfSound(numbers: List<Int>, toBeRemoved: Set<Int>): Boolean {
        val prepared = numbers
            .filterNot { toBeRemoved.contains(it) }
        return prepared
            .subList(0, prepared.size - 1)
            .zip(prepared.subList(1, prepared.size))
            .all { it.second - it.first <= 3 }
    }

    companion object {

        private tailrec fun findRemovableElements(
            index: Int,
            list: List<Int>,
            acc: List<Int>
        ): List<Int> {
            if (index >= list.size - 1) return acc
            return if (list[index + 1] - list[index - 1] <= 3) {
                findRemovableElements(index + 1, list, acc.plus(list[index]))
            }
            else {
                findRemovableElements(index + 1, list, acc)
            }
        }

    }

}