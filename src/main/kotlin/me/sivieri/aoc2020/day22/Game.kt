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

    fun playRecursively(): Int =
        playRecursively(
            1,
            1,
            player1,
            player2,
            emptyList()
        )

    fun winnerPoints(winner: Int): Int {
        val player = if (player1.id == winner) player1
        else player2
        return player.calculatePoints()
    }

    companion object {

        private tailrec fun playRecursively(
            game: Int,
            round: Int,
            player1: Player,
            player2: Player,
            gameHistory: List<Pair<Queue<Int>, Queue<Int>>>
        ): Int {
            val v1 = player1.play()
            val v2 = player2.play()
            if (v1 == null) return 2
            if (v2 == null) return 1
            val currentStatus = Pair(player1.cards, player2.cards)
            if (gameHistory.contains(currentStatus)) return 1
            val newHistory = gameHistory.plus(Pair(LinkedList(player1.cards), LinkedList(player2.cards)))
            var winner = 0
            if (player1.cards.size - 1 >= v1 && player2.cards.size - 1 >= v2) {
                val player1cards = LinkedList(player1.cards).subList(1, v1 + 1)
                val player2cards = LinkedList(player2.cards).subList(1, v2 + 1)
                winner =  playRecursively(
                    game + 1,
                    1,
                    Player(player1.id, LinkedList(player1cards)),
                    Player(player2.id, LinkedList(player2cards)),
                    emptyList()
                )
            }
            when {
                winner == 1 -> {
                    player1.win(v1, v2)
                    player2.lose()
                }
                winner == 2 -> {
                    player1.lose()
                    player2.win(v2, v1)
                }
                v1 > v2 -> {
                    player1.win(v1, v2)
                    player2.lose()
                }
                v1 < v2 -> {
                    player1.lose()
                    player2.win(v2, v1)
                }
            }
            return playRecursively(
                game,
                round + 1,
                player1,
                player2,
                newHistory
            )
        }

    }

}