package me.sivieri

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
