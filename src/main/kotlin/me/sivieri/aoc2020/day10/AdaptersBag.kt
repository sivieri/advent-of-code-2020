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
    fun findCombinations(): Long {
        val sortedAdapters = adapters
            .plus(0)
            .plus(adapters.maxOrNull()!! + 3)
            .sorted()
        val candidates = findRemovableElements(1, sortedAdapters, emptyList())
            .toSet()
        println("DEBUG - Found ${candidates.size} candidates")
        var counter: Long = 0
        (0..candidates.size)
            .forEach { i ->
                println("DEBUG - $i")
                Sets
                    .combinations(candidates, i)
                    .filter { checkLength(it) }
                    .forEach { if (checkIfSound(sortedAdapters, it)) counter++ }
            }
        return counter
    }

    private fun checkLength(set: Set<Int>): Boolean =
        set.isEmpty() || set.size != 3 || set.maxOrNull()!! - set.minOrNull()!! != 2

    private fun checkIfSound(numbers: List<Int>, toBeRemoved: Set<Int>): Boolean {
        val prepared = numbers
            .filterNot { toBeRemoved.contains(it) }
        return (0 until prepared.size - 1)
            .all { prepared[it + 1] - prepared[it] <= 3 }
    }

    companion object {

        private tailrec fun findRemovableElements(
            index: Int,
            list: List<Int>,
            acc: List<Int>
        ): List<Int> {
            if (index >= list.size - 1) return acc
            return if (list[index + 1] - list[index - 1] < 3) {
                findRemovableElements(index + 1, list, acc.plus(list[index]))
            }
            else {
                findRemovableElements(index + 1, list, acc)
            }
        }

    }

}