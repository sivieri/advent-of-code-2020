package me.sivieri.day1

import me.sivieri.toList
import java.nio.file.Files
import java.nio.file.Path
import kotlin.system.exitProcess

object Main2 {

    private const val result = 2020

    @JvmStatic
    fun main(args: Array<String>) {
        val numbers = Files
            .lines(Path.of(this::class.java.getResource("/day1/input.txt").toURI()))
            .map { it.toInt() }
            .toList()
        numbers.forEach { i ->
            if (i < result) {
                numbers.forEach { j ->
                    if (i + j < result) {
                        numbers.forEach { k ->
                            if (i + j + k == result) {
                                println("$i * $j * $k = ${i * j * k}")
                                exitProcess(0)
                            }
                        }
                    }
                }
            }
        }
    }

}