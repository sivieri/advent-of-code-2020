package me.sivieri.aoc2020.day21

class Book(input: List<String>) {

    private val recipes: List<Recipe> = input
        .map { Recipe.parse(it) }

    fun findGoodIngredients(): List<String> {
        val allIngredients = recipes
            .flatMap { it.ingredients }
            .distinct()
        val pairs = findPairs()
        return allIngredients.minus(pairs.values)
    }

    fun findBadIngredientsString(): String =
        findPairs()
            .toSortedMap()
            .values
            .joinToString(",")

    private fun findPairs(): Map<String, String> {
        val allAllergens = recipes
            .flatMap { it.allergens }
            .distinct()
        val res = mutableMapOf<String, String>()
        val allergensRecipes = allAllergens
            .map { allergen ->
                val lists = recipes
                    .filter { it.allergens.contains(allergen) }
                    .map { it.ingredients }
                val intersection = if (lists.size == 1) lists.first()
                else lists
                    .subList(1, lists.size)
                    .fold(lists.first()) { acc, list ->
                        acc.intersect(list)
                    }
                allergen to intersection
            }
            .toMap()
            .toMutableMap()
        while (res.keys.size != allAllergens.size) {
            allergensRecipes.forEach { (allergen, ingredients) ->
                if (ingredients.size == 1) res[allergen] = ingredients.first()
            }
            res.keys.forEach { allergensRecipes.remove(it) }
            res.values.forEach { ingredient ->
                val keys = allergensRecipes.filter { it.value.contains(ingredient) }.keys
                keys.forEach { allergensRecipes[it] = allergensRecipes[it]!!.minus(ingredient) }
            }
        }
        return res.toMap()
    }

    fun countIngredients(ingredients: List<String>): Int =
        ingredients
            .sumBy { ingredient ->
                recipes.sumBy { recipe ->
                    recipe.ingredients.count { it == ingredient }
                }
            }

    override fun toString(): String {
        return "Book(recipes=${recipes.joinToString("\n")})"
    }


}