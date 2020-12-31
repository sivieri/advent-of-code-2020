package me.sivieri.aoc2020.day25

class Calculator {

    fun transform(subject: Long, loopSize: Int): Long =
        transform(1L, subject, 1, loopSize)

    private fun transform(
        startingValue: Long,
        subject: Long,
        startingLoop: Int,
        loopSize: Int
    ): Long {
        var value = startingValue
        for (i in startingLoop..loopSize) {
            value = transformValue(value, subject)
        }
        return value
    }

    private fun transformValue(value: Long, subject: Long) = (value * subject) % multiplier

    fun searchLoopSize(subject: Long, expected: Long): Int {
        var loopSize = 1
        var res = 1L
        while (res != expected) {
            if (loopSize % 1000 == 0) println("Loop size $loopSize")
            res = transformValue(res, subject)
            loopSize++
        }
        return loopSize - 1
    }

    fun calculateEncryptionKey(loopSize: Int, publicKey: Long): Long =
        transform(publicKey, loopSize)

    companion object {
        private const val multiplier = 20201227L
    }

}