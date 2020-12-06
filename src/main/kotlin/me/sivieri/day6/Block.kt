package me.sivieri.day6

object Block {

    private val letters = listOf(
        'a', 'b', 'c', 'd', 'e', 'f',
        'g', 'h', 'i', 'j', 'k', 'l',
        'm', 'n', 'o', 'p', 'q', 'r',
        's', 't', 'u', 'v', 'w', 'x',
        'y', 'z'
    )

    internal fun countOccurrences(block: String): Int {
        val members = block
            .split("\n")
            .filter { it != "" }
        val presentLetters = letters
            .map { letter ->
                members.all { it.contains(letter) }
            }
        return presentLetters.count { it }
    }

}