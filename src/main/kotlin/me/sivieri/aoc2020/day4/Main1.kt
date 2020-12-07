package me.sivieri.aoc2020.day4

import java.nio.file.Files
import java.nio.file.Path

object Main1 {

    @JvmStatic
    fun main(args: Array<String>) {
        val lines = Files
            .lines(Path.of(this::class.java.getResource("/day4/input.txt").toURI()))
            .iterator()
        var buffer = StringBuffer()
        val data = mutableListOf<Passport>()
        while (lines.hasNext()) {
            val cur = "${lines.next()}\n"
            if (cur == "\n") {
                data.add(Passport.parse(buffer.toString()))
                buffer = StringBuffer()
            }
            else {
                buffer.append(cur)
            }
        }
        data.add(Passport.parse(buffer.toString()))
        println(data.count { it.isValid() })
    }

}