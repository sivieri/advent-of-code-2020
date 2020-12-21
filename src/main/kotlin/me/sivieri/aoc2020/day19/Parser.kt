package me.sivieri.aoc2020.day19

import org.snt.inmemantlr.GenericParser
import org.snt.inmemantlr.exceptions.ParsingException

class Parser(
    private val input: List<String>
) {

    private val antlrParser: GenericParser

    init {
        val grammarIndex = input.indexOfFirst { it == "" }
        val initialGrammarLines = input
            .subList(0, grammarIndex)
            .sortedBy {
                it.substring(0, it.indexOf(":")).toInt()
            }
            .map { "$it;" }
        val initialGrammar = "\n" + initialGrammarLines.joinToString("\n") + "\n"
        val grammar = initialGrammarLines
            .indices
            .fold(initialGrammar) { acc, index ->
                val token = if (initialGrammarLines[index].contains("\"")) "T"
                else "p"
                acc
                    .replace("\n$index:", "\n$token$index:")
                    .replace(" $index ", " $token$index ")
                    .replace(" $index ", " $token$index ")
                    .replace(" $index;", " $token$index;")
            }
            .replace("\"", "'")
            .split("\n")
            .filter { it != "" }
            .map { if (it.startsWith("p0:")) "${it.substring(0, it.length - 1)} EOF;" else it }
            .sortedDescending()
            .joinToString("\n")
        val grammarText = "grammar Program;\n\n$grammar"
        println(grammarText)
        antlrParser = GenericParser(grammarText)
        antlrParser.compile()
    }

    fun validateAll(): Int {
        val grammarIndex = input.indexOfFirst { it == "" }
        val data = input.subList(grammarIndex + 1, input.size)
        return data.count { validate(it) }
    }

    private fun validate(input: String): Boolean =
        try {
            antlrParser.parse(input, "p0", GenericParser.CaseSensitiveType.NONE)
            true
        }
        catch (e: ParsingException) {
            false
        }

}