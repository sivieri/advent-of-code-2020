package me.sivieri.aoc2020.day10

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

    fun findCombinations(): Long {
        TODO("Not yet implemented")
    }

}