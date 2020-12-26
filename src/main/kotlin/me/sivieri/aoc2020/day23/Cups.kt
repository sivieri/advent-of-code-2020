package me.sivieri.aoc2020.day23

import me.sivieri.aoc2020.safeArrayCopy
import me.sivieri.aoc2020.safeSubList
import me.sivieri.aoc2020.shift

class Cups(
    input: String,
    million: Boolean = false
) {

    private val cups: Array<Int> = input
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
        .toTypedArray()
    private val support: Array<Int> = Array(cups.size) { 0 }
    private val support2: Array<Int> = Array(cups.size) { 0 }
    private val zeros: Array<Int> = Array(cups.size) { 0 }
    private val max = cups.maxOrNull()!!
    private var current = 0

    fun performIterations(iterations: Int) {
        for (i in 1..iterations) {
            if (i % 100 == 0) println("Iteration $i")
            val sz = cups.size
            cups.shift(support2, 0 - current)
            val c = support2[0]
            val v1 = support2[(1) % sz]
            val v2 = support2[(2) % sz]
            val v3 = support2[(3) % sz]
            safeArrayCopy(
                support2,
                0,
                support,
                0,
                1
            )
            safeArrayCopy(
                support2,
                4,
                support,
                1,
                sz - 4
            )
            safeArrayCopy(
                zeros,
                0,
                support,
                sz - 3,
                3
            )
            var d = c - 1
            while (d <= 0 || d == v1 || d == v2 || d == v3) {
                d = if (d - 1 <= 0) max
                else d - 1
            }
            val idx = support.indexOf(d)
            safeArrayCopy(
                support,
                0,
                support2,
                0,
                idx + 1
            )
            support2[idx + 1] = v1
            support2[idx + 2] = v2
            support2[idx + 3] = v3
            safeArrayCopy(
                support,
                idx + 1,
                support2,
                idx + 4,
                sz - idx - 4
            )
            val idx2 = support2.indexOf(c)
            support2.shift(cups, current - idx2)
            current = (current + 1) % sz
        }
    }

    fun getFullString(): String {
        val idx = cups.indexOf(1)
        return cups
            .toList()
            .safeSubList(idx + 1, cups.size)
            .plus(cups.toList().safeSubList(0, idx))
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