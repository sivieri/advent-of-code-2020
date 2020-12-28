package me.sivieri.aoc2020.day24

import org.junit.Assert
import org.junit.FixMethodOrder
import org.junit.Test
import org.junit.runners.MethodSorters

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
class FloorTest {

    @Test
    fun `01 simple test`() {
        val input = """
            esenee
            esew
            nwwswee
        """.trimIndent()
            .split("\n")
        val floor = Floor(input)
        floor.performInstructions()
        val res = floor.countBlackTiles()
        Assert.assertEquals(3, res)
    }

    @Test
    fun `02 black count test`() {
        val input = """
            sesenwnenenewseeswwswswwnenewsewsw
            neeenesenwnwwswnenewnwwsewnenwseswesw
            seswneswswsenwwnwse
            nwnwneseeswswnenewneswwnewseswneseene
            swweswneswnenwsewnwneneseenw
            eesenwseswswnenwswnwnwsewwnwsene
            sewnenenenesenwsewnenwwwse
            wenwwweseeeweswwwnwwe
            wsweesenenewnwwnwsenewsenwwsesesenwne
            neeswseenwwswnwswswnw
            nenwswwsewswnenenewsenwsenwnesesenew
            enewnwewneswsewnwswenweswnenwsenwsw
            sweneswneswneneenwnewenewwneswswnese
            swwesenesewenwneswnwwneseswwne
            enesenwswwswneneswsenwnewswseenwsese
            wnwnesenesenenwwnenwsewesewsesesew
            nenewswnwewswnenesenwnesewesw
            eneswnwswnwsenenwnwnwwseeswneewsenese
            neswnwewnwnwseenwseesewsenwsweewe
            wseweeenwnesenwwwswnew
        """.trimIndent()
            .split("\n")
        val floor = Floor(input)
        floor.performInstructions()
        val res = floor.countBlackTiles()
        Assert.assertEquals(10, res)
    }

}