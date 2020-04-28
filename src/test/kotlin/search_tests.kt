import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class searchTests {

    @Test
    fun contextLoadsTest() {}

    @Test
    //Checks that createPartials handles empty input
    fun emptyPatternPMTTest() {
        val pattern = ""

        val table: Array<Int> = createPartials(pattern)

        assert(table.isEmpty())
    }

    @Test
    //Checks that createPartials creates correct PMT based on hand calc'd val
    fun correctPMTTest() {
        val pattern = "ACABACACD"

        val table = createPartials(pattern)
        val correctTable: Array<Int> = arrayOf(0, 0, 1, 0, 1, 2, 3, 2, 0)

        Assertions.assertArrayEquals(table, correctTable)
    }
}