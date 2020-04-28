import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import kotlin.system.measureTimeMillis


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
        var matches: Array<Int> = arrayOf(1)

        //Printing out runtime for my KMP algorithm implementation
        println("Runtime(ms) emptyText: " + measureTimeMillis {
            matches = kmp(pattern, text)
        })

        assert(matches.isEmpty())
    }

    @Test
    fun emptyPatternTest() {
        val pattern = ""
        val text = "Hello world"
        var matches: Array<Int> = arrayOf(1)

        println("Runtime(ms) emptyPattern: " + measureTimeMillis {
            matches = kmp(pattern, text)
        })

        assert(matches.isEmpty())
    }

    @Test
    fun smallPatternLargeTextTest() {
        val pattern = "aba"
        val text = "abbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb" +
                "bbbbbbbbbbbbbbbababbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb"
        var matches = Array(0) {_ -> 0}
        val correctMatches: Array<Int> = arrayOf(80)

        println("Runtime(ms) smallPatternLargeText: " + measureTimeMillis {
            matches = kmp(pattern, text)
        })

        Assertions.assertArrayEquals(matches, correctMatches)
    }

    @Test
    fun multiplePatternsTest() {
        val pattern = "aba"
        val text = "abababababababababa"
        var matches = Array(0) {_ -> 0}
        val correctMatches: Array<Int> = arrayOf(0,2,4,6,8,10,12,14,16)

        println("Runtime(ms) multiplePatterns: " + measureTimeMillis {
            matches = kmp(pattern, text)
        })

        Assertions.assertArrayEquals(matches, correctMatches)
    }

    @Test
    fun closeSizedPatternAndTextTest() {
        val pattern = "123456789a"
        val text = "a123456789a"
        var matches = Array(0) {_ -> 0}
        val correctMatches: Array<Int> = arrayOf(1)

        println("Runtime(ms) closeSizedPatternAndText: " + measureTimeMillis {
            matches = kmp(pattern, text)
        })

        Assertions.assertArrayEquals(matches, correctMatches)
    }

    @Test
    fun samePatternAsTextTest() {
        val pattern = "123456789"
        val text = "123456789"
        var matches = Array(0) {_ -> 0}
        val correctMatches: Array<Int> = arrayOf(0)

        println("Runtime(ms) samePatternAsText: " + measureTimeMillis {
            matches = kmp(pattern, text)
        })

        Assertions.assertArrayEquals(matches, correctMatches)
    }

    @Test
    fun patternLargerThanTextTest() {
        val pattern = "aaaaaaaaaaaaaaaaaaaaa"
        val text = "aaaaaaaaa"
        var matches = Array(1) {_ -> 0}

        println("Runtime(ms) patternLargerThanText: " + measureTimeMillis {
            matches = kmp(pattern, text)
        })

        assert(matches.isEmpty())
    }

    @Test
    fun patternNotInTextTest() {
        val pattern = "bbb"
        val text = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
        var matches = Array(1) {_ -> 0}

        println("Runtime(ms) patternNotInText: " + measureTimeMillis {
            matches = kmp(pattern, text)
        })

        assert(matches.isEmpty())
    }

    /* Boyer-Moore Algorithm Tests */
}