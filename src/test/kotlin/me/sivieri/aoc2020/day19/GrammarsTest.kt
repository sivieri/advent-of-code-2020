package me.sivieri.aoc2020.day19

import me.sivieri.aoc2020.day19.grammars.Grammar1Lexer
import me.sivieri.aoc2020.day19.grammars.Grammar1Parser
import org.antlr.v4.runtime.CharStreams
import org.antlr.v4.runtime.CommonTokenStream
import org.junit.FixMethodOrder
import org.junit.Test
import org.junit.runners.MethodSorters
import java.lang.IllegalArgumentException

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
class GrammarsTest {

    @Test
    fun `01 first grammar test with correct input`() {
        val input = CharStreams.fromString("aab")
        val lexer = Grammar1Lexer(input)
        lexer.removeErrorListeners()
        lexer.addErrorListener(ThrowingErrorsListener())
        val parser = Grammar1Parser(CommonTokenStream(lexer))
        parser.removeErrorListeners()
        parser.addErrorListener(ThrowingErrorsListener())
        parser.p0()
    }

    @Test(expected = IllegalArgumentException::class)
    fun `02 first grammar test with wrong input`() {
        val input = CharStreams.fromString("abb")
        val lexer = Grammar1Lexer(input)
        lexer.removeErrorListeners()
        lexer.addErrorListener(ThrowingErrorsListener())
        val parser = Grammar1Parser(CommonTokenStream(lexer))
        parser.removeErrorListeners()
        parser.addErrorListener(ThrowingErrorsListener())
        parser.p0()
    }

    @Test
    fun `03 first grammar test with correct input`() {
        val input = CharStreams.fromString("aba")
        val lexer = Grammar1Lexer(input)
        lexer.removeErrorListeners()
        lexer.addErrorListener(ThrowingErrorsListener())
        val parser = Grammar1Parser(CommonTokenStream(lexer))
        parser.removeErrorListeners()
        parser.addErrorListener(ThrowingErrorsListener())
        parser.p0()
    }

}