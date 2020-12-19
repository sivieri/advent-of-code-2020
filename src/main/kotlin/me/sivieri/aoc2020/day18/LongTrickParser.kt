package me.sivieri.aoc2020.day18

object LongTrickParser {

    fun eval(str: String): LongTrick {
        return object : Any() {
            var pos = -1
            var ch = 0

            fun nextChar() {
                ch = if (++pos < str.length) str[pos].toInt() else -1
            }

            fun eat(charToEat: Int): Boolean {
                while (ch == ' '.toInt()) nextChar()
                if (ch == charToEat) {
                    nextChar()
                    return true
                }
                return false
            }

            fun parse(): LongTrick {
                nextChar()
                val x = parseExpression()
                if (pos < str.length) throw RuntimeException("Unexpected: " + ch.toChar())
                return x
            }

            @Suppress("CascadeIf")
            fun parseExpression(): LongTrick {
                var x = parseFactor()
                while (true) {
                    if (eat('+'.toInt())) x += parseFactor() // addition
                    else if (eat('-'.toInt())) x -= parseFactor() // subtraction
                    else return x
                }
            }

            fun parseFactor(): LongTrick {
                val x: LongTrick
                val startPos = pos
                if (eat('('.toInt())) { // parentheses
                    x = parseExpression()
                    eat(')'.toInt())
                } else if (ch >= '0'.toInt() && ch <= '9'.toInt()) { // numbers
                    while (ch >= '0'.toInt() && ch <= '9'.toInt()) nextChar()
                    x = LongTrick(str.substring(startPos, pos).trim().toLong())
                } else {
                    throw RuntimeException("Unexpected: " + ch.toChar())
                }
                return x
            }
        }.parse()
    }

}