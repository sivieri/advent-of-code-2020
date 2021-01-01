package me.sivieri.aoc2020.day20

object Matrix {

    fun rotate90(image: Array<Array<Char>>): Array<Array<Char>> {
        val row = image.size
        val col = image.first().size
        val newImage = Array(col) { Array(row) { ' ' } }

        for (i in 0 until row) {
            for (j in 0 until col) {
                newImage[j][row - 1 - i] = image[i][j]
            }
        }

        return newImage
    }

    fun flipVertical(image: Array<Array<Char>>): Array<Array<Char>> {
        val row = image.size
        val col = image.first().size
        val newImage = Array(row) { Array(col) { ' ' } }

        for (i in 0 until row) {
            for (j in 0 until col) {
                newImage[i][col - 1 - j] = image[i][j]
            }
        }

        return newImage
    }

    fun flipHorizontal(image: Array<Array<Char>>): Array<Array<Char>> {
        val row = image.size
        val col = image.first().size
        val newImage = Array(row) { Array(col) { ' ' } }

        for (i in 0 until row) {
            for (j in 0 until col) {
                newImage[row - 1 - i][j] = image[i][j]
            }
        }

        return newImage
    }

}