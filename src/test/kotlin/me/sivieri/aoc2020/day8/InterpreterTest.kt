package me.sivieri.aoc2020.day8

import org.junit.Assert
import org.junit.Test

class InterpreterTest {

    @Test
    fun `01 interpreter test`() {
        val program = """
            nop +0
            acc +1
            jmp +4
            acc +3
            jmp -3
            acc -99
            acc +1
            jmp -4
            acc +6
        """.trimIndent()
        val data = Interpreter.parseCode(program.split("\n"))
        val interpreter = Interpreter()
        val result = interpreter.executeUntilLoop(data)
        Assert.assertEquals(5, result)
    }

    @Test
    fun `02 interpreter test with search for a correction`() {
        val program = """
            nop +0
            acc +1
            jmp +4
            acc +3
            jmp -3
            acc -99
            acc +1
            jmp -4
            acc +6
        """.trimIndent()
        val data = Interpreter.parseCode(program.split("\n"))
        val interpreter = Interpreter()
        val result = interpreter.executeWithCorrection(data)
        Assert.assertEquals(8, result)
    }

    @Test
    fun `03 interpreter test with an already corrected program`() {
        val program = """
            nop +0
            acc +1
            jmp +4
            acc +3
            jmp -3
            acc -99
            acc +1
            nop -4
            acc +6
        """.trimIndent()
        val data = Interpreter.parseCode(program.split("\n"))
        val interpreter = Interpreter()
        val result = interpreter.executeWithCorrection(data)
        Assert.assertEquals(8, result)
    }

}