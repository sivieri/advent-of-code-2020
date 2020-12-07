package me.sivieri.aoc2020.day3

import me.sivieri.aoc2020.toList
import java.nio.file.Files
import java.nio.file.Path

object Main1 {
    private const val lateral = 3
    private const val downward = 1

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
        while (!map.isBottom()) {
            map.move(lateral, downward)
        }
        println(map.counter)
    }

}