import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test


// I clumped all tests for both algorithms into the same test suite so that
// they would all be located next to each other.
class searchTests {

    @Test
    fun contextLoadsTest() {}

    /* Knuth-Morris-Pratt Algorithm Tests */

    @Test
    // Checks that createPartials handles empty input
    fun emptyPatternPMTTest() {
        val pattern = ""

        val table: Array<Int> = createPartials(pattern)

        assert(table.isEmpty())
    }

    @Test
    // Checks that createPartials creates correct PMT based on hand calc'd val
    fun correctPMTTest() {
        val pattern = "ACABACACD"

        val table = createPartials(pattern)
        val correctTable: Array<Int> = arrayOf(0, 0, 1, 0, 1, 2, 3, 2, 0)

        Assertions.assertArrayEquals(table, correctTable)
    }

    @Test
    fun emptyTextTest() {
        val pattern = "ABA"
        val text = ""
        var matches: Array<Int> = kmp(pattern, text)

        assert(matches.isEmpty())
    }

    @Test
    fun emptyPatternTest() {
        val pattern = ""
        val text = "Hello world"
        var matches: Array<Int> = kmp(pattern, text)

        assert(matches.isEmpty())
    }

    @Test
    fun smallPatternLargeTextTest() {
        val pattern = "aba"
        val text = "abbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb" +
                "bbbbbbbbbbbbbbbababbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb"
        var matches = kmp(pattern, text)
        val correctMatches: Array<Int> = arrayOf(80)

        Assertions.assertArrayEquals(matches, correctMatches)
    }

    @Test
    fun multiplePatternsTest() {
        val pattern = "aba"
        val text = "abababababababababa"
        var matches = kmp(pattern, text)
        val correctMatches: Array<Int> = arrayOf(0,2,4,6,8,10,12,14,16)

        Assertions.assertArrayEquals(matches, correctMatches)
    }

    @Test
    fun closeSizedPatternAndTextTest() {
        val pattern = "123456789a"
        val text = "a123456789a"
        var matches = kmp(pattern, text)
        val correctMatches: Array<Int> = arrayOf(1)

        Assertions.assertArrayEquals(matches, correctMatches)
    }

    @Test
    fun samePatternAsTextTest() {
        val pattern = "123456789"
        val text = "123456789"
        var matches = kmp(pattern, text)
        val correctMatches: Array<Int> = arrayOf(0)

        Assertions.assertArrayEquals(matches, correctMatches)
    }

    @Test
    fun patternLargerThanTextTest() {
        val pattern = "aaaaaaaaaaaaaaaaaaaaa"
        val text = "aaaaaaaaa"
        var matches = kmp(pattern, text)

        assert(matches.isEmpty())
    }

    @Test
    fun patternNotInTextTest() {
        val pattern = "bbb"
        val text = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
        var matches = kmp(pattern, text)

        assert(matches.isEmpty())
    }

    /* Boyer-Moore Algorithm Tests */

    @Test
    fun buildTableCorrectInitTest() {
        val pattern = "abcabcaa"
        val table = buildTable(pattern)

        assert(table[0] == 7)
        assert(table[1] == 4)
        assert(table[2] == 5)
    }
    @Test
    fun emptyTextBMTest() {
        val pattern = "aba"
        val text = ""
        assert(bm(pattern, text).isEmpty())
    }

    @Test
    fun emptyPatternBMTest() {
        val pattern = ""
        val text = "Hello world"
        assert(bm(pattern, text).isEmpty())
    }

    @Test
    fun multiplePatternsBMTest() {
        val pattern = "aba"
        val text = "aaababababababababa"
        assert(bm(pattern, text)[0] == 2)
    }

    @Test
    fun patternLargerThanTextBMTest() {
        val pattern = "aaaaaaaaaaaaaaaaaaaaa"
        val text = "aaaaaaaaa"

        assert(bm(pattern ,text).isEmpty())
    }

    @Test
    fun patternNotInTextBMTest() {
        val pattern = "bbb"
        val text = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
        assert(bm(pattern ,text).isEmpty())
    }
}