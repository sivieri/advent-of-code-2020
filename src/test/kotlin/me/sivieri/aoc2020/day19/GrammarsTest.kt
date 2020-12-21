package me.sivieri.aoc2020.day19

import me.sivieri.aoc2020.day19.grammars.*
import org.antlr.v4.runtime.CharStreams
import org.antlr.v4.runtime.CommonTokenStream
import org.junit.Assert
import org.junit.FixMethodOrder
import org.junit.Test
import org.junit.runners.MethodSorters
import org.snt.inmemantlr.GenericParser
import org.snt.inmemantlr.exceptions.ParsingException
import java.lang.Exception
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

    @Test
    fun `04 second grammar tests`() {
        val input = """
            ababbb
            bababa
            abbbab
            aaabbb
            aaaabbb
        """.trimIndent()
            .split("\n")
        val res = input.count {
            try {
                val inputStream = CharStreams.fromString(it)
                val lexer = Grammar2Lexer(inputStream)
                lexer.removeErrorListeners()
                lexer.addErrorListener(ThrowingErrorsListener())
                val parser = Grammar2Parser(CommonTokenStream(lexer))
                parser.removeErrorListeners()
                parser.addErrorListener(ThrowingErrorsListener())
                parser.p0()
                true
            }
            catch (e: Exception) {
                false
            }
        }
        Assert.assertEquals(2, res)
    }

    @Test(expected = ParsingException::class)
    fun `05 first in-memory grammar test with wrong input`() {
        val grammar = """
            grammar Grammar1;

            p0: T1 p2 EOF;
            p2: T1 T3 | T3 T1;

            T1: 'a';
            T3: 'b';
        """.trimIndent()
        val genericParser = GenericParser(grammar)
        val input = "abb"
        genericParser.compile()
        genericParser.parse(input)
    }

    @Test
    fun `06 third grammar tests`() {
        val input = """
            ababbb
            bababa
            abbbab
            aaabbb
            aaaabbb
        """.trimIndent()
            .split("\n")
        val res = input.count {
            try {
                val inputStream = CharStreams.fromString(it)
                val lexer = ProgramLexer(inputStream)
                lexer.removeErrorListeners()
                lexer.addErrorListener(ThrowingErrorsListener())
                val parser = ProgramParser(CommonTokenStream(lexer))
                parser.removeErrorListeners()
                parser.addErrorListener(ThrowingErrorsListener())
                parser.p0()
                true
            }
            catch (e: Exception) {
                false
            }
        }
        Assert.assertEquals(2, res)
    }

}