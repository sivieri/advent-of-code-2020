package me.sivieri.aoc2020

import java.util.stream.Stream

internal fun <T> Stream<T>.toList(): List<T> =
    this
        .iterator()
        .asSequence()
        .toList()

internal fun <T> List<T>.multiplyBy(f: (T) -> Int): Long =
    this.fold(1) { acc, t ->
        acc * f(t)
    }

internal fun <T> List<T>.head(): T = this.first()

internal fun <T> List<T>.tail(): List<T> = this.subList(1, this.size)
