package me.sivieri.aoc2020.day25

class Calculator {

    fun transform(subject: Long, loopSize: Int): Long {
        var value: Long = 1
        for (i in 1..loopSize) {
            value = (value * subject) % multiplier
        }
        return value
    }

    fun searchLoopSize(subject: Long, expected: Long): Int {
        var loopSize = 1
        var res = transform(subject, loopSize)
        while (res != expected) {
            loopSize++
            if (loopSize % 1000 == 0) println("Loop size $loopSize")
            res = transform(subject, loopSize)
        }
        return loopSize
    }

    fun calculateEncryptionKey(loopSize: Int, publicKey: Long): Long =
        transform(publicKey, loopSize)

    companion object {
        private const val multiplier = 20201227L
    }

}