package me.sivieri.aoc2020.day8

class Interpreter {

    fun executeUntilLoop(instructions: List<Instruction>): Int =
        executeProgram(
            0,
            -1,
            0,
            0,
            emptyList(),
            instructions
                .toMutableList()
        ).accumulator

    fun executeWithCorrection(instructions: List<Instruction>): Int {
        TODO()
    }

    private tailrec fun executeProgram(
        step: Int,
        previousLine: Int,
        currentLine: Int,
        accumulator: Int,
        executionSteps: List<ExecutionStep>,
        instructions: MutableList<Instruction>
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