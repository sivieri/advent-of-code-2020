package me.sivieri.aoc2020.day15

import org.junit.Assert
import org.junit.FixMethodOrder
import org.junit.Test
import org.junit.runners.MethodSorters

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
class GameTest {

    @Test
    fun `01 test game`() {
        val input = "0,3,6"
            .split(",")
            .map { it.toInt() }
        val game = Game()
        val res = game.play(input)
        Assert.assertEquals(436, res)
    }

    @Test
    fun `02 test game`() {
        val input = "1,3,2"
            .split(",")
            .map { it.toInt() }
        val game = Game()
        val res = game.play(input)
        Assert.assertEquals(1, res)
    }

    @Test
    fun `03 test game`() {
        val input = "2,1,3"
            .split(",")
            .map { it.toInt() }
        val game = Game()
        val res = game.play(input)
        Assert.assertEquals(10, res)
    }

    @Test
    fun `04 test game`() {
        val input = "1,2,3"
            .split(",")
            .map { it.toInt() }
        val game = Game()
        val res = game.play(input)
        Assert.assertEquals(27, res)
    }

    @Test
    fun `05 test game`() {
        val input = "2,3,1"
            .split(",")
            .map { it.toInt() }
        val game = Game()
        val res = game.play(input)
        Assert.assertEquals(78, res)
    }

    @Test
    fun `06 test game`() {
        val input = "3,2,1"
            .split(",")
            .map { it.toInt() }
        val game = Game()
        val res = game.play(input)
        Assert.assertEquals(438, res)
    }

    @Test
    fun `07 test game`() {
        val input = "3,1,2"
            .split(",")
            .map { it.toInt() }
        val game = Game()
        val res = game.play(input)
        Assert.assertEquals(1836, res)
    }

}