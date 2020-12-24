package me.sivieri.aoc2020.day21

data class Recipe(
    val ingredients: List<String>,
    val allergens: List<String>
) {

    companion object {

        fun parse(s: String): Recipe {
            val parts = s.split("(contains", limit = 2)
            val ingredients = parts[0]
                .trim()
                .split(" ")
            val allergens = if (parts.size == 1) emptyList()
            else parts[1]
                .trim()
                .trim(')')
                .split(", ")
            return Recipe(ingredients, allergens)
        }

    }

}