package me.sivieri.aoc2020.day8

enum class InstructionType {

    nop, acc, jmp;

    companion object {

        fun invert(current: InstructionType): InstructionType = when (current) {
            nop -> jmp
            jmp -> nop
            acc -> acc
        }

    }

}