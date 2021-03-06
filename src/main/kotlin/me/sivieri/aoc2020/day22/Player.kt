package me.sivieri.aoc2020.day22

import java.util.*

data class Player(
    val id: Int,
    var cards: Queue<Int>
) {

    fun play(): Int? = cards.peek()

    fun win(myCard: Int, otherCard: Int) {
        cards.poll()
        cards.offer(myCard)
        cards.offer(otherCard)
    }

    fun lose() {
        cards.poll()
    }

    fun calculatePoints(): Int {
        val size = cards.size
        return (size downTo 1)
            .sumBy { i ->
                (cards.poll() * i)
            }
    }

}