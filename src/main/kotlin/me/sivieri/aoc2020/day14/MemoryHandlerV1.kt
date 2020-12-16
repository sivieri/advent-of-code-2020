package me.sivieri.aoc2020.day14

class MemoryHandlerV1 : MemoryHandler() {

    private lateinit var bitMask: BitMask

    override fun executeInput(input: MemoryInput) {
        bitMask = input.bitMask
        input.values.forEach { (location, value) ->
            val valueAfterMask = bitMask.apply(value)
            memory[location] = valueAfterMask
        }
    }

}