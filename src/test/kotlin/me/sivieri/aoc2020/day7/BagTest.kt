package me.sivieri.aoc2020.day7

import org.junit.Assert
import org.junit.Test

class BagTest {

    @Test
    fun `01 create the trees`() {
        val text = """
            light red bags contain 1 bright white bag, 2 muted yellow bags.
            dark orange bags contain 3 bright white bags, 4 muted yellow bags.
            bright white bags contain 1 shiny gold bag.
            muted yellow bags contain 2 shiny gold bags, 9 faded blue bags.
            shiny gold bags contain 1 dark olive bag, 2 vibrant plum bags.
            dark olive bags contain 3 faded blue bags, 4 dotted black bags.
            vibrant plum bags contain 5 faded blue bags, 6 dotted black bags.
            faded blue bags contain no other bags.
            dotted black bags contain no other bags.
        """.trimIndent()
        val bags = text
            .split("\n")
            .map { Bag.createBag(it.trim()) }
        val mergedBags = Bag.mergeBags(bags)
        Assert.assertEquals(2, mergedBags.size)
    }

    @Test
    fun `02 search the trees`() {
        val text = """
            light red bags contain 1 bright white bag, 2 muted yellow bags.
            dark orange bags contain 3 bright white bags, 4 muted yellow bags.
            bright white bags contain 1 shiny gold bag.
            muted yellow bags contain 2 shiny gold bags, 9 faded blue bags.
            shiny gold bags contain 1 dark olive bag, 2 vibrant plum bags.
            dark olive bags contain 3 faded blue bags, 4 dotted black bags.
            vibrant plum bags contain 5 faded blue bags, 6 dotted black bags.
            faded blue bags contain no other bags.
            dotted black bags contain no other bags.
        """.trimIndent()
        val bags = text
            .split("\n")
            .map { Bag.createBag(it.trim()) }
        val mergedBags = Bag.mergeBags(bags)
        val steps = mergedBags
            .flatMap { it.searchParentsNumber("shiny gold") }
            .distinct()
        Assert.assertEquals(4, steps.size)
    }

    @Test
    fun `03 search the number of bags`() {
        val text = """
            light red bags contain 1 bright white bag, 2 muted yellow bags.
            dark orange bags contain 3 bright white bags, 4 muted yellow bags.
            bright white bags contain 1 shiny gold bag.
            muted yellow bags contain 2 shiny gold bags, 9 faded blue bags.
            shiny gold bags contain 1 dark olive bag, 2 vibrant plum bags.
            dark olive bags contain 3 faded blue bags, 4 dotted black bags.
            vibrant plum bags contain 5 faded blue bags, 6 dotted black bags.
            faded blue bags contain no other bags.
            dotted black bags contain no other bags.
        """.trimIndent()
        val bags = text
            .split("\n")
            .map { Bag.createBag(it.trim()) }
        val mergedBags = Bag.mergeBags(bags)
        val sizes = mergedBags
            .map {
                it.countBags("shiny gold")
            }
            .distinct()
        Assert.assertEquals(1, sizes.size)
        Assert.assertEquals(32, sizes.first()!! - 1)
    }

    @Test
    fun `04 search another number of bags`() {
        val text = """
            shiny gold bags contain 2 dark red bags.
            dark red bags contain 2 dark orange bags.
            dark orange bags contain 2 dark yellow bags.
            dark yellow bags contain 2 dark green bags.
            dark green bags contain 2 dark blue bags.
            dark blue bags contain 2 dark violet bags.
            dark violet bags contain no other bags.
        """.trimIndent()
        val bags = text
            .split("\n")
            .map { Bag.createBag(it.trim()) }
        val mergedBags = Bag.mergeBags(bags)
        val sizes = mergedBags
            .map {
                it.countBags("shiny gold")
            }
            .distinct()
        Assert.assertEquals(1, sizes.size)
        Assert.assertEquals(126, sizes.first()!! - 1)
    }

}