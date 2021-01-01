package me.sivieri.aoc2020.day20

object Matrix {

    fun rotate90(image: Array<Array<Char>>): Array<Array<Char>> {
        val newImage = Array(image.size) { Array(image.first().size) { ' ' } }
        val row = image.size

        for (i in 0 until row) {
            for (j in i until row) {
                val temp = image[i][j]
                newImage[i][j] = image[j][i]
                newImage[j][i] = temp
            }
        }

        for (i in 0 until row) {
            for (j in 0 until row / 2) {
                val temp = newImage[i][j]
                newImage[i][j] = newImage[i][row - 1 - j]
                newImage[i][row - 1 - j] = temp
            }
        }

        return newImage
    }

    fun mirrorVertical(image: Array<Array<Char>>): Array<Array<Char>> {
        val newImage = Array(image.size) { Array(image.first().size) { ' ' } }
        val row = image.size

        for (i in 0 until row) {
            for (j in 0 until row) {
                newImage[i][row - 1 - j] = image[i][j]
            }
        }

        return newImage
    }

    fun mirrorHorizontal(image: Array<Array<Char>>): Array<Array<Char>> {
        val newImage = Array(image.size) { Array(image.first().size) { ' ' } }
        val row = image.size

        for (i in 0 until row) {
            for (j in 0 until row) {
                newImage[row - 1 - i][j] = image[i][j]
            }
        }

        return newImage
    }

}