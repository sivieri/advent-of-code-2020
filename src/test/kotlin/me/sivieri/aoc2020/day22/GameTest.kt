package me.sivieri.aoc2020.day22

import org.junit.Assert
import org.junit.FixMethodOrder
import org.junit.Test
import org.junit.runners.MethodSorters

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
class GameTest {

    @Test
    fun `01 play test`() {
        val input = """
            Player 1:
            9
            2
            6
            3
            1

            Player 2:
            5
            8
            4
            7
            10
        """.trimIndent()
            .split("\n")
        val game = Game(input)
        val winner = game.play()
        val res = game.winnerPoints(winner)
        Assert.assertEquals(306, res)
    }

    @Test
    fun `02 play recursively test`() {
        val input = """
            Player 1:
            9
            2
            6
            3
            1

            Player 2:
            5
            8
            4
            7
            10
        """.trimIndent()
            .split("\n")
        val game = Game(input)
        val winner = game.playRecursively()
        val res = game.winnerPoints(winner)
        Assert.assertEquals(291, res)
    }

}