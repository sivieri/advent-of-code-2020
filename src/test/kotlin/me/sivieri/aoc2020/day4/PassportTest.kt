package me.sivieri.aoc2020.day4

import org.junit.Assert
import org.junit.Test

class PassportTest {

    @Test
    fun `01 parse correct passport`() {
        val passportString = """
            ecl:gry pid:860033327 eyr:2020 hcl:#fffffd
            byr:1937 iyr:2017 cid:147 hgt:183cm
        """.trimIndent()
        val passport = Passport.parse(passportString)
        Assert.assertTrue(passport.isValid())
    }

    @Test
    fun `02 parse correct passport`() {
        val passportString = """
            hcl:#ae17e1 iyr:2013
            eyr:2024
            ecl:brn pid:760753108 byr:1931
            hgt:179cm
        """.trimIndent()
        val passport = Passport.parse(passportString)
        Assert.assertTrue(passport.isValid())
    }

    @Test
    fun `03 parse incorrect passport`() {
        val passportString = """
            iyr:2013 ecl:amb cid:350 eyr:2023 pid:028048884
            hcl:#cfa07d byr:1929
        """.trimIndent()
        val passport = Passport.parse(passportString)
        Assert.assertFalse(passport.isValid())
    }

    @Test
    fun `04 parse incorrect passport`() {
        val passportString = """
            hcl:#cfa07d eyr:2025 pid:166559648
            iyr:2011 ecl:brn hgt:59in
        """.trimIndent()
        val passport = Passport.parse(passportString)
        Assert.assertFalse(passport.isValid())
    }

    @Test
    fun `05 parse correct passport`() {
        val passportString = "iyr:2015 eyr:2024 hgt:184cm\necl:blu hcl:#a97842 byr:1959 pid:932817398\n"
        val passport = Passport.parse(passportString)
        Assert.assertTrue(passport.isValid())
    }

}