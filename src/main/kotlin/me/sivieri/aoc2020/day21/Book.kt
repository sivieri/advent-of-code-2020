package me.sivieri.aoc2020.day21

import com.google.common.collect.Sets

@Suppress("UnstableApiUsage")
class Book(input: List<String>) {

    private val recipes: List<Recipe> = input
        .map { Recipe.parse(it) }

    fun findGoodIngredients(): List<String> {
        val allIngredients = recipes
            .flatMap { it.ingredients }
            .distinct()
        val allAllergens = recipes
            .flatMap { it.allergens }
            .distinct()
        val allPairs = Sets
            .combinations(recipes.toSet(), 2)
            .map {
                val l = it.toList()
                Pair(l[0], l[1])
            }
        val commonPairs = allPairs.map {
                val commonIngredients = it.first.ingredients.intersect(it.second.ingredients)
                val commonAllergens = it.first.allergens.intersect(it.second.allergens)
                Pair(commonIngredients, commonAllergens)
            }
        val res = mutableMapOf<String, String>()
        var newPairs = commonPairs
        while (newPairs.any { it.first.size == 1 && it.second.size == 1 }) {
            newPairs
                .filter { it.first.size == 1 && it.second.size == 1 }
                .forEach { res[it.first.first()] = it.second.first() }
            val removedPairs = newPairs
                .map {
                    val newCommonIngredients = it
                        .first
                        .minus(res.keys)
                    val newCommonAllergens = it
                        .second
                        .minus(res.values)
                    Pair(newCommonIngredients, newCommonAllergens)
                }
            newPairs = removedPairs
        }
        allAllergens
            .minus(res.values)
            .forEach { allergen ->
                val recipes = recipes
                    .filter { it.allergens.contains(allergen) }
                    .flatMap { it.ingredients }
                    .minus(res.keys)
                if (recipes.size != 1) println("Unable to establish something for $allergen (${recipes.size})")
                else res[recipes.first()] = allergen
            }
        return allIngredients.minus(res.keys)
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