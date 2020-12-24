package me.sivieri.aoc2020.day21

import org.junit.Assert
import org.junit.FixMethodOrder
import org.junit.Test
import org.junit.runners.MethodSorters

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
class BookTest {

    @Test
    fun `01 count ingredients test`() {
        val input = """
            mxmxvkd kfcds sqjhc nhms (contains dairy, fish)
            trh fvjkl sbzzf mxmxvkd (contains dairy)
            sqjhc fvjkl (contains soy)
            sqjhc mxmxvkd sbzzf (contains fish)
        """.trimIndent()
            .split("\n")
        val book = Book(input)
        val ingredients = listOf("mxmxvkd", "trh")
        val res = book.countIngredients(ingredients)
        Assert.assertEquals(4, res)
    }

    @Test
    fun `02 find ingredients test`() {
        val input = """
            mxmxvkd kfcds sqjhc nhms (contains dairy, fish)
            trh fvjkl sbzzf mxmxvkd (contains dairy)
            sqjhc fvjkl (contains soy)
            sqjhc mxmxvkd sbzzf (contains fish)
        """.trimIndent()
            .split("\n")
        val book = Book(input)
        val ingredients = book.findGoodIngredients()
        val res = book.countIngredients(ingredients)
        Assert.assertEquals(5, res)
    }

}