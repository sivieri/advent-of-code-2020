package me.sivieri.day3

import me.sivieri.multiplyBy
import me.sivieri.toList
import java.nio.file.Files
import java.nio.file.Path

object Main2 {

    private val slopes = listOf(
        Pair(1, 1),
        Pair(3, 1),
        Pair(5, 1),
        Pair(7, 1),
        Pair(1, 2)
    )

    @JvmStatic
    fun main(args: Array<String>) {
        val data = Files
            .lines(Path.of(this::class.java.getResource("/day3/input.txt").toURI()))
            .toList()
            .map {
                it.toCharArray().toTypedArray()
            }
            .toTypedArray()
        val map = Map(data)
        val res = slopes.multiplyBy {
            map.reset()
            while (!map.isBottom()) {
                map.move(it.first, it.second)
            }
            map.counter
        }
        println(res)
    }

}