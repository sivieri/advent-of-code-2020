package me.sivieri.aoc2020.day2

import org.junit.Assert
import org.junit.Test

class PasswordTest {

    @Test
    fun `01 test valid count`() {
        val password = Password(
            1,
            3,
            'a',
            "abca"
        )
        Assert.assertTrue(password.isCountValid())
    }

    @Test
    fun `02 test invalid count`() {
        val password = Password(
            1,
            3,
            'd',
            "abca"
        )
        Assert.assertFalse(password.isCountValid())
    }

    @Test
    fun `03 test valid position`() {
        val password = Password(
            1,
            3,
            'a',
            "abca"
        )
        Assert.assertTrue(password.isPositionValid())
    }

    @Test
    fun `04 test invalid position due to letters`() {
        val password = Password(
            1,
            3,
            'a',
            "baca"
        )
        Assert.assertFalse(password.isPositionValid())
    }

    @Test
    fun `05 test invalid position due to length`() {
        val password = Password(
            1,
            6,
            'a',
            "abca"
        )
        Assert.assertFalse(password.isPositionValid())
    }

}