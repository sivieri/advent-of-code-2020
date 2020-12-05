package me.sivieri

import java.nio.file.Files
import java.nio.file.Path

object Utils {

    fun readInput(day: Int): List<String> =
        Files
            .lines(Path.of(this::class.java.getResource("/day$day/input.txt").toURI()))
            .toList()

}