package me.sivieri.aoc2020.day23

import me.sivieri.aoc2020.safeSubList

class Cups(
    input: String,
    million: Boolean = false
) {

    private var cups: List<Int> = input
        .toCharArray()
        .map { it.toString().toInt() }
        .let {
            if (million) {
                it.plus(
                    (it.maxOrNull()!! + 1)..1_000_000
                )
            }
            else it
        }
    private var current = 0

    fun performIterations(iterations: Int) {
        for (i in 1..iterations) {
            if (i % 100 == 0) println("Iteration $i")
            val sz = cups.size
            val c = cups[current]
            val l = cups.toMutableList()
            val extracted = listOf(
                l[(current + 1) % sz],
                l[(current + 2) % sz],
                l[(current + 3) % sz],
            )
            l.removeAll(extracted)
            var d = c - 1
            while (!l.contains(d)) {
                d = if (d - 1 < 0) cups.maxOrNull()!!
                else d - 1
            }
            val idx = l.indexOf(d)
            val l2 = l
                .safeSubList(0, idx + 1)
                .plus(extracted)
                .plus(l.safeSubList(idx + 1, sz))
            val l3 = if (l2[current] != c) {
                val l2idx = l2.indexOf(c)
                l2
                    .safeSubList(l2idx - current, l2idx + 1)
                    .plus(l2.safeSubList(l2idx + 1, sz))
                    .plus(l2.safeSubList(0, l2idx - current))
            }
            else l2
            cups = l3
            current = (current + 1) % sz
        }
    }

    fun getFullString(): String {
        val idx = cups.indexOf(1)
        return cups
            .safeSubList(idx + 1, cups.size)
            .plus(cups.safeSubList(0, idx))
            .joinToString("")
    }

    fun getTwoCups(): Long {
        val idx = cups.indexOf(1)
        return cups[idx + 1].toLong() * cups[idx + 2].toLong()
    }

    override fun toString(): String {
        return cups.joinToString(" ") {
            if (cups[current] == it) "($it)"
            else it.toString()
        }
    }
}