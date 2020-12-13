package me.sivieri.aoc2020

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
