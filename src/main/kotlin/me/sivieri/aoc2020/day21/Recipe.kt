package me.sivieri.aoc2020.day21

data class Recipe(
    val ingredients: Set<String>,
    val allergens: Set<String>
) {

    companion object {

        fun parse(s: String): Recipe {
            val parts = s.split("(contains", limit = 2)
            val ingredients = parts[0]
                .trim()
                .split(" ")
                .toSet()
            val allergens = if (parts.size == 1) emptySet()
            else parts[1]
                .trim()
                .trim(')')
                .split(", ")
                .toSet()
            return Recipe(ingredients, allergens)
        }

    }

}