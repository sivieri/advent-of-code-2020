package me.sivieri.aoc2020.day17

class HyperCube(input: List<CharArray>) {

    private var grid: Map<Coordinates4D, Char> = input
        .indices.flatMap { x ->
            input[x].indices.map { y ->
                Coordinates4D(x, y, 0, 0) to input[x][y]
            }
        }
        .toMap()
    private var minX = grid.keys.minByOrNull { it.x }!!.x
    private var maxX = grid.keys.maxByOrNull { it.x }!!.x
    private var minY = grid.keys.minByOrNull { it.y }!!.y
    private var maxY = grid.keys.maxByOrNull { it.y }!!.y
    private var minZ = grid.keys.minByOrNull { it.z }!!.z
    private var maxZ = grid.keys.maxByOrNull { it.z }!!.z
    private var minW = grid.keys.minByOrNull { it.w }!!.w
    private var maxW = grid.keys.maxByOrNull { it.w }!!.w

    fun performIterations(iterations: Int) {
        println(toGridString())
        (1..iterations).forEach { i ->
            println("Iteration $i")
            performIteration()
            println(toGridString())
        }
    }

    private fun performIteration() {
        val newGrid = mutableMapOf<Coordinates4D, Char>()
        minX--
        minY--
        minZ--
        minW--
        maxX++
        maxY++
        maxZ++
        maxW++
        (minX..maxX).flatMap { x ->
            (minY..maxY).flatMap { y ->
                (minZ..maxZ).map { z ->
                    (minW..maxW).map { w ->
                        val coordinates = Coordinates4D(x, y, z, w)
                        val value = grid.getOrDefault(coordinates, inactive)
                        applyAlgorithm(coordinates, value, newGrid)
                    }
                }
            }
        }
        grid = newGrid
    }

    private fun applyAlgorithm(
        coordinates: Coordinates4D,
        value: Char,
        newGrid: MutableMap<Coordinates4D, Char>
    ) {
        val neighbors = getNeighbors(Pair(coordinates, value))
        val activeNumber = neighbors.count { it.second == active }
        if (value == active && activeNumber >= 2 && activeNumber <= 3) newGrid[coordinates] = active
        else if (value == active && (activeNumber < 2 || activeNumber > 3)) newGrid[coordinates] = inactive
        else if (value == inactive && activeNumber == 3) newGrid[coordinates] = active
        else newGrid[coordinates] = inactive
    }

    private fun getNeighbors(cell: Pair<Coordinates4D, Char>): List<Pair<Coordinates4D, Char>> =
        cell
            .first
            .getNeighbors()
            .map { c ->
                Pair(c, grid.getOrDefault(c,inactive))
            }

    fun countActive(): Int =
        grid.count { it.value == active }

    fun toGridString(): String {
        return (minZ..maxZ)
            .zip(minW..maxW)
            .joinToString("\n\n") { (z, w) ->
                "z = $z, w = $w\n" + (minX..maxX).joinToString("\n") { x ->
                    (minY..maxY).joinToString("") { y ->
                        val coordinates = Coordinates4D(x, y, z, w)
                        grid[coordinates]!!.toString()
                    }
                }
            }
    }

    companion object {
        private const val inactive = '.'
        private const val active = '#'
    }

}