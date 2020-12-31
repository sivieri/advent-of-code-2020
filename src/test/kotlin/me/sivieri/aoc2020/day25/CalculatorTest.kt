package me.sivieri.aoc2020.day25

import org.junit.Assert
import org.junit.FixMethodOrder
import org.junit.Test
import org.junit.runners.MethodSorters

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
class CalculatorTest {

    @Test
    fun `01 transform test`() {
        val calculator = Calculator()
        val res = calculator.transform(7L, 8)
        Assert.assertEquals(5764801L, res)
    }

    @Test
    fun `02 transform test`() {
        val calculator = Calculator()
        val res = calculator.transform(7L, 11)
        Assert.assertEquals(17807724L, res)
    }

    @Test
    fun `03 search test`() {
        val calculator = Calculator()
        val res = calculator.searchLoopSize(7L, 5764801L)
        Assert.assertEquals(8, res)
    }

    @Test
    fun `04 search test`() {
        val calculator = Calculator()
        val res = calculator.searchLoopSize(7L, 17807724L)
        Assert.assertEquals(11, res)
    }

    @Test
    fun `05 encryption key test`() {
        val calculator = Calculator()
        val res = calculator.calculateEncryptionKey(8, 17807724L)
        Assert.assertEquals(14897079L, res)
    }

    @Test
    fun `06 encryption key test`() {
        val calculator = Calculator()
        val res = calculator.calculateEncryptionKey(11, 5764801L)
        Assert.assertEquals(14897079L, res)
    }

}