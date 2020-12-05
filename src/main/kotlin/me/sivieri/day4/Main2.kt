package me.sivieri.day4

import java.nio.file.Files
import java.nio.file.Path

object Main2 {

    @JvmStatic
    fun main(args: Array<String>) {
        val lines = Files
            .lines(Path.of(this::class.java.getResource("/day4/input.txt").toURI()))
            .iterator()
        var buffer = StringBuffer()
        val data = mutableListOf<PassportWithPrecision>()
        while (lines.hasNext()) {
            val cur = "${lines.next()}\n"
            if (cur == "\n") {
                val s = buffer.toString()
                val p = PassportWithPrecision.parse(s)
                data.add(p)
                if (p.isValid()) println(s)
                buffer = StringBuffer()
            }
            else {
                buffer.append(cur)
            }
        }
        data.add(PassportWithPrecision.parse(buffer.toString()))
        println(data.count { it.isValid() })
    }

}