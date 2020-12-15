package me.sivieri.aoc2020

import java.math.BigInteger
import java.util.*
import java.util.stream.Stream

internal fun <T> Stream<T>.toList(): List<T> =
    this
        .iterator()
        .asSequence()
        .toList()

internal fun <T> List<T>.multiplyBy(f: (T) -> Long): Long =
    this.fold(1) { acc, t ->
        acc * f(t)
    }

internal fun <T> List<T>.head(): T = this.first()

internal fun <T> List<T>.tail(): List<T> = this.subList(1, this.size)

internal fun <T> List<T>.zipWithIndex(): List<Pair<Int, T>> =
    this.mapIndexed { index, t -> Pair(index, t) }

internal fun Boolean.toInt() = if (this) 1 else 0

internal fun BitSet.toList(): List<Int> {
    val res = List(this.size()) { 0 }
    return res
        .mapIndexed { index, _ -> this.get(index).toInt() }
        .reversed()
}

internal fun BitSet.toBinaryString(length: Int): String {
    val list = this.toList()
    return list.subList(list.size - length, list.size).joinToString("")
}

internal fun BitSet.toLong(size: Int): Long {
    val buffer = StringBuffer()
    (size - 1 downTo 0).forEach { index ->
        if (this.get(index)) buffer.append("1")
        else buffer.append("0")
    }
    return BigInteger(buffer.toString(), 2).longValueExact()
}
