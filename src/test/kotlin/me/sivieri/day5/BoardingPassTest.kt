package me.sivieri.day5

import org.junit.Assert
import org.junit.Test

class BoardingPassTest {

    @Test
    fun `01 test row function`() {
        val boardingPass = BoardingPass("FBFBBFF", "RLR")
        Assert.assertEquals(44, boardingPass.row())
        Assert.assertEquals(5, boardingPass.seat())
        Assert.assertEquals(357, boardingPass.seatCode())
    }

    @Test
    fun `02 test row function`() {
        val boardingPass = BoardingPass("BFFFBBF", "RRR")
        Assert.assertEquals(70, boardingPass.row())
        Assert.assertEquals(7, boardingPass.seat())
        Assert.assertEquals(567, boardingPass.seatCode())
    }

    @Test
    fun `03 test row function`() {
        val boardingPass = BoardingPass("FFFBBBF", "RRR")
        Assert.assertEquals(14, boardingPass.row())
        Assert.assertEquals(7, boardingPass.seat())
        Assert.assertEquals(119, boardingPass.seatCode())
    }

    @Test
    fun `04 test row function`() {
        val boardingPass = BoardingPass("BBFFBBF", "RLL")
        Assert.assertEquals(102, boardingPass.row())
        Assert.assertEquals(4, boardingPass.seat())
        Assert.assertEquals(820, boardingPass.seatCode())
    }

}