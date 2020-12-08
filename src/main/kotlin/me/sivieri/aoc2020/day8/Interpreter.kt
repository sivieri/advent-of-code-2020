package me.sivieri.aoc2020.day8

import java.lang.IllegalStateException

class Interpreter {

    fun executeUntilLoop(instructions: List<Instruction>): Int =
        executeProgram(
            0,
            -1,
            0,
            0,
            emptyList(),
            instructions
        ).accumulator

    fun executeWithCorrection(instructions: List<Instruction>): Int {
        val originalExecutionResult = executeProgram(
            0,
            -1,
            0,
            0,
            emptyList(),
            instructions
        )
        var executionResult = originalExecutionResult.deepCopy()
        var lastChanged: Int = -1
        while (!executionResult.terminatedCorrectly) {
            val res = generateCorrection(lastChanged, instructions, executionResult)
            lastChanged = res.first
            executionResult = executeProgram(
                0,
                -1,
                0,
                0,
                emptyList(),
                res.second
            )
        }
        return executionResult.accumulator
    }

    private fun generateCorrection(
        lastChanged: Int,
        instructions: List<Instruction>,
        executionResult: ExecutionResult
    ): Pair<Int, List<Instruction>> {
        val newInstructions = mutableListOf<Instruction>()
        val actuallyLast = if (lastChanged != -1) {
            val oldInstruction = instructions.find { it.line == lastChanged }!!
            newInstructions.add(oldInstruction
                .copy(instructionType = InstructionType.invert(oldInstruction.instructionType)))
            lastChanged
        }
        else {
            executionResult.executionSteps.maxByOrNull { it.step }!!.instruction.line
        }
        val newChangedInstruction = searchInstructionToChange(
            executionResult.executionSteps,
            actuallyLast,
            lastChanged == -1
        )
        newInstructions.add(newChangedInstruction)
        instructions.forEach { i ->
            if (i.line != newChangedInstruction.line && i.line != lastChanged)
                newInstructions.add(i.copy())
        }
        return Pair(
            newChangedInstruction.line,
            newInstructions
            .toList()
            .sortedBy { it.line }
        )
    }

    private fun searchInstructionToChange(
        executionSteps: List<ExecutionStep>,
        lastChanged: Int,
        considerCurrent: Boolean
    ): Instruction {
        val index = executionSteps.indexOfFirst { it.instruction.line == lastChanged }
        val step = if (considerCurrent || index == 0) index
        else index - 1
        val instruction = executionSteps
            .subList(0, step + 1)
            .findLast { it.instruction.instructionType != InstructionType.acc }
            ?.instruction
            ?: throw IllegalStateException("Unable to find a non-acc instruction")
        return instruction.copy(instructionType = InstructionType.invert(instruction.instructionType))
    }

    private tailrec fun executeProgram(
        step: Int,
        previousLine: Int,
        currentLine: Int,
        accumulator: Int,
        executionSteps: List<ExecutionStep>,
        instructions: List<Instruction>
    ): ExecutionResult {
        if (currentLine >= instructions.size) return ExecutionResult(
            true,
            previousLine,
            accumulator,
            executionSteps
        )
        val currentInstruction = instructions[currentLine]
        if (executionSteps.alreadyExecuted(currentInstruction)) return ExecutionResult(
            false,
            previousLine,
            accumulator,
            executionSteps
        )
        val updatedExecutionSteps = executionSteps.plus(
            ExecutionStep(
                step + 1,
                currentInstruction
            )
        )
        return when (currentInstruction.instructionType) {
            InstructionType.nop -> executeProgram(
                step + 1,
                currentLine,
                currentLine + 1,
                accumulator,
                updatedExecutionSteps,
                instructions
            )
            InstructionType.acc -> executeProgram(
                step + 1,
                currentLine,
                currentLine + 1,
                accumulator + currentInstruction.argument,
                updatedExecutionSteps,
                instructions
            )
            InstructionType.jmp -> executeProgram(
                step + 1,
                currentLine,
                currentLine + currentInstruction.argument,
                accumulator,
                updatedExecutionSteps,
                instructions
            )
        }
    }

    companion object {

        fun parseCode(lines: List<String>): List<Instruction> =
            lines
                .mapIndexed { index, line ->
                    val parts = line.split(" ")
                    Instruction(
                        index,
                        InstructionType.valueOf(parts[0]),
                        parts[1].toInt()
                    )
                }
                .sortedBy { it.line }

        private fun List<ExecutionStep>.alreadyExecuted(instruction: Instruction): Boolean =
            this.find { it.instruction == instruction } != null

    }

}