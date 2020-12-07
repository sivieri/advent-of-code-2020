package me.sivieri.aoc2020.day7

import java.lang.AssertionError

class Bag(
    val name: String,
    private val bags: MutableMap<Bag, Int>
): Comparable<Bag> {

    fun extend(otherBag: Bag): Boolean =
        extend(otherBag, false)

    private fun extend(otherBag: Bag, status: Boolean): Boolean {
        val statuses = bags.map { it.key.extend(otherBag, status) }
        return if (this == otherBag) {
            bags.putAll(otherBag.bags)
            true
        } else {
            status || statuses.any { it }
        }
    }

    fun searchParentsNumber(name: String): List<String> =
        searchParentsNumberHelper(name).first

    private fun searchParentsNumberHelper(name: String): Pair<List<String>, Boolean> {
        if (this.name == name) return Pair(emptyList(), true)
        if (bags.isEmpty()) return Pair(emptyList(), false)
        val searchResults = bags.map {
            it.key.searchParentsNumberHelper(name)
        }
        val searchNames = searchResults.flatMap { it.first }
        return if (searchResults.any { it.second }) Pair(searchNames.plus(this.name), true)
        else Pair(searchNames, false)
    }

    fun countBags(s: String): Int? =
        findBag(s)?.countBags()

    private fun findBag(s: String): Bag? {
        if (this.name == s) return this
        if (bags.isEmpty()) return null
        return bags
            .map { it.key.findBag(s) }
            .filterNotNull()
            .firstOrNull()
    }

    private fun countBags(): Int =
        if (bags.isEmpty()) 1
        else 1 + bags
            .entries
            .sumBy {
                it.value * it.key.countBags()
            }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Bag

        if (name != other.name) return false

        return true
    }

    override fun hashCode(): Int =
        name.hashCode()

    override fun compareTo(other: Bag): Int =
        name.compareTo(other.name)

    override fun toString(): String {
        return "Bag(name='$name', bags=$bags)"
    }

    companion object {

        private val subbagPattern = Regex(" ([0-9]+) ([a-z ]+) bag[s]?")

        fun createBag(s: String): Bag {
            val mainParts = s.split(" bags contain")
            val rootBag = mainParts[0]
            val childBags = if (mainParts[1] == " no other bags.") mutableMapOf()
            else {
                mainParts[1]
                    .substring(0, mainParts[1].length - 1)
                    .split(",")
                    .map {
                        val groups = subbagPattern
                            .matchEntire(it)
                            ?.groups ?: throw AssertionError("Wrong pattern: $it")
                        Bag(groups[2]!!.value, mutableMapOf()) to groups[1]!!.value.toInt()
                    }
                    .toMap()
                    .toMutableMap()
            }
            return Bag(rootBag, childBags)
        }

        fun mergeBags(bags: List<Bag>): List<Bag> =
            bags
                .map { bag ->
                    val contained = bags.map { otherBag ->
                        if (otherBag != bag) {
                            val res = otherBag.extend(bag)
                            res
                        }
                        else false
                    }
                    Pair(bag, contained.any { it })
                }
                .filter { !it.second }
                .map { it.first }

    }

}