package me.sivieri.aoc2020.day23

import me.sivieri.aoc2020.safeSubList
import me.sivieri.aoc2020.shift

class Cups(
    input: String,
    private val inputSize: Int
) {

    private val cups: Array<Int> = Array(inputSize + 1) { 0 }
    private var current: Int = "${input.first()}".toInt()
    private var position: Int = 0

    init {
        var i = 1
        val numbers = input
            .toCharArray()
            .map { "$it".toInt() }
            .let {
                if (inputSize > it.size) {
                    val m = it.maxOrNull()!!
                    it.plus((m + 1)..inputSize)
                }
                else it
            }
        numbers.forEach { n ->
                if (i == inputSize) cups[n] = current
                else cups[n] = numbers[i++]
            }
    }

    fun performIterations(iterations: Int) {
        for (i in 1..iterations) {
            val v1 = cups[current]
            val v2 = cups[v1]
            val v3 = cups[v2]
            cups[current] = cups[v3]
            var d = current - 1
            while (d == 0 || d == v1 || d == v2 || d == v3) {
                if (d == 0) d = inputSize
                else d--
            }
            val next = cups[d]
            cups[d] = v1
            cups[v3] = next
            current = cups[current]
            position = (position + 1) % inputSize
        }
    }

    fun getFullString(): String {
        val numbers = Array(inputSize) { 0 }
        var counter = cups[1]
        (0 until inputSize).forEach { i ->
            numbers[i] = counter
            counter = cups[counter]
        }
        val numbers2 = Array(inputSize) { 0 }
        val idx = numbers.indexOf(1)
        numbers.shift(numbers2, 0 - idx)
        return numbers2.toList().subList(1, numbers2.size).joinToString("")
    }

    fun getTwoCups(): Long =
        cups[1].toLong() * cups[cups[1]].toLong()

    override fun toString(): String {
        return cups.joinToString(" ") + " - ($current)"
    }

}