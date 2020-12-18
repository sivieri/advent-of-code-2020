package me.sivieri.aoc2020.day17

class Cubes(input: List<CharArray>) {

    private var grid: Map<Coordinates, Char> = input
        .indices.flatMap { x ->
            input[x].indices.map { y ->
                Coordinates(x, y, 0) to input[x][y]
            }
        }
        .toMap()

    fun performIterations(iterations: Int) {
        (0 until iterations).forEach { _ -> performIteration() }
    }

    private fun performIteration() {
        val newGrid = mutableMapOf<Coordinates, Char>()
        grid.forEach { cell ->
            val neighbors = getNeighbors(cell)
            val activeNumber = neighbors.count { it.value == active }
            if (cell.value == active && activeNumber >= 2 && activeNumber <= 3) newGrid[cell.key] = active
            else if (cell.value == active && (activeNumber < 2 || activeNumber > 3)) newGrid[cell.key] = inactive
            else if (cell.value == inactive && activeNumber == 3) newGrid[cell.key] = active
            else newGrid[cell.key] = inactive
        }
        // TODO expand to neighbors not in list
        grid = newGrid
    }

    private fun getNeighbors(cell: Map.Entry<Coordinates, Char>): List<Map.Entry<Coordinates, Char>> {
        TODO()
    }

    fun countActive(): Int =
        grid.count { it.value == active }

    companion object {
        private const val inactive = '.'
        private const val active = '#'
    }

}