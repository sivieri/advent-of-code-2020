package me.sivieri.aoc2020.day22

import java.util.*

class Game(input: List<String>) {

    private val player1: Player
    private val player2: Player

    init {
        val idx = input.indexOf("Player 2:")
        val player1Cards = input
            .subList(1, idx - 1)
            .map { it.toInt() }
        val player2Cards = input
            .subList(idx + 1, input.size)
            .map { it.toInt() }
        player1 = Player(1, LinkedList(player1Cards))
        player2 = Player(2, LinkedList(player2Cards))
    }

    fun play() :Int {
        var v1 = player1.play()
        var v2 = player2.play()
        while (v1 != null && v2 != null) {
            if (v1 > v2) {
                player1.win(v1, v2)
                player2.lose()
            }
            else {
                player1.lose()
                player2.win(v2, v1)
            }
            v1 = player1.play()
            v2 = player2.play()
        }
        return if (v1 == null) 2
        else 1
    }

    fun winnerPoints(winner: Int): Long {
        val player = if (player1.id == winner) player1
        else player2
        return player.calculatePoints()
    }

}