package me.sivieri.aoc2020.day8

data class ExecutionResult(
    val terminatedCorrectly: Boolean,
    val lastValidLine: Int,
    val accumulator: Int,
    val executionSteps: List<ExecutionStep>
)
