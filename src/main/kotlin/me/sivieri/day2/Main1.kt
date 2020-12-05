package me.sivieri.day2

import me.sivieri.toList
import java.nio.file.Files
import java.nio.file.Path

object Main1 {

    @JvmStatic
    fun main(args: Array<String>) {
        val data = Files
            .lines(Path.of(this::class.java.getResource("/day2/input.txt").toURI()))
            .toList()
            .mapNotNull {
                val groups = Password
                    .regex
                    .matchEntire(it)
                    ?.groups
                if (groups == null) null
                else Password(
                    groups[1]!!.value.toInt(),
                    groups[2]!!.value.toInt(),
                    groups[3]!!.value.first(),
                    groups[4]!!.value
                )
            }
        println(data.count { it.isCountValid() })
    }

}