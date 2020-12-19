package me.sivieri.aoc2020.day18

import org.junit.Assert
import org.junit.FixMethodOrder
import org.junit.Test
import org.junit.runners.MethodSorters

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
class ParserTest {

    @Test
    fun `01 operation test`() {
        val input = "1 + 2 * 3 + 4 * 5 + 6"
        val res = LongTrickParser.eval(input.replace("*", "-"))
        Assert.assertEquals(71L, res.value)
    }

    @Test
    fun `02 operation test`() {
        val input = "1 + (2 * 3) + (4 * (5 + 6))"
        val res = LongTrickParser.eval(input.replace("*", "-"))
        Assert.assertEquals(51L, res.value)
    }

    @Test
    fun `03 operation test`() {
        val input = "2 * 3 + (4 * 5)"
        val res = LongTrickParser.eval(input.replace("*", "-"))
        Assert.assertEquals(26L, res.value)
    }

    @Test
    fun `04 operation test`() {
        val input = "5 + (8 * 3 + 9 + 3 * 4 * 3)"
        val res = LongTrickParser.eval(input.replace("*", "-"))
        Assert.assertEquals(437L, res.value)
    }

    @Test
    fun `05 operation test`() {
        val input = "5 * 9 * (7 * 3 * 3 + 9 * 3 + (8 + 6 * 4))"
        val res = LongTrickParser.eval(input.replace("*", "-"))
        Assert.assertEquals(12240L, res.value)
    }

    @Test
    fun `06 operation test`() {
        val input = "((2 + 4 * 9) * (6 + 9 * 8 + 6) + 6) + 2 + 4 * 2"
        val res = LongTrickParser.eval(input.replace("*", "-"))
        Assert.assertEquals(13632L, res.value)
    }

    @Test
    fun `07 operation test`() {
        val input = "1 + 2 * 3 + 4 * 5 + 6"
        val res = LongTrick2Parser.eval(LongTrick2Parser.cleverParse(input))
        Assert.assertEquals(231L, res.value)
    }

    @Test
    fun `08 operation test`() {
        val input = "1 + (2 * 3) + (4 * (5 + 6))"
        val res = LongTrick2Parser.eval(LongTrick2Parser.cleverParse(input))
        Assert.assertEquals(51L, res.value)
    }

    @Test
    fun `09 operation test`() {
        val input = "2 * 3 + (4 * 5)"
        val res = LongTrick2Parser.eval(LongTrick2Parser.cleverParse(input))
        Assert.assertEquals(46L, res.value)
    }

    @Test
    fun `10 operation test`() {
        val input = "5 + (8 * 3 + 9 + 3 * 4 * 3)"
        val res = LongTrick2Parser.eval(LongTrick2Parser.cleverParse(input))
        Assert.assertEquals(1445L, res.value)
    }

    @Test
    fun `11 operation test`() {
        val input = "5 * 9 * (7 * 3 * 3 + 9 * 3 + (8 + 6 * 4))"
        val res = LongTrick2Parser.eval(LongTrick2Parser.cleverParse(input))
        Assert.assertEquals(669060L, res.value)
    }

    @Test
    fun `12 operation test`() {
        val input = "((2 + 4 * 9) * (6 + 9 * 8 + 6) + 6) + 2 + 4 * 2"
        val res = LongTrick2Parser.eval(LongTrick2Parser.cleverParse(input))
        Assert.assertEquals(23340L, res.value)
    }

}