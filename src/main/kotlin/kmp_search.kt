// kmp_search.kt
// This file holds the implementation for the Knuth-Morris-Pratt
// String search algorithm.

/*
Temporary literal string array for testing during implementation.
*/
val samples: Array<String> = arrayOf(
    "Hello this is a test",
    "ababaca",
    "acabacacd",
    "aaaaaaaaaaaaaaaaaaaaaaaaaaaab"
)

/*
Creates a partial match table for use in kmp.
The partial match table [PMT] helps skip repeat comparisons in the pattern, not the text.
The text never has to re-eval looked-at indices -- the PMT handles that.
    | Inputs: 'pattern' used to build partial match 'table'.
    | Outputs: 'table' filled with correct values.
*/
fun createPartials(pattern: String): Array<Int> {
    var table = Array(pattern.length) { _ -> 0}
    var i = 1
    var m = 0

    while (i < pattern.length) {
        when {
            (pattern[i] == pattern[m]) ->
                table[i++] = ++m // 'i' postfix increment intentional
            (m != 0) ->
                m = table[m-1]
            else ->
                table[i++] = 0
        }
    }
    return table
}

/*
String search implementation of the Knuth-Morris-Pratt algorithm.
Uses a partial match table to determine when we can skip comparisons.
    | Inputs: 'text' to be searched, 'pattern' to be searched for.
    | Outputs: Array of indices where matches are found,
               who's len == number of matches found.
*/
fun kmp(pattern: String, text: String): Array<Int> {
    val matches = MutableList<Int>(0) {_ -> 0}
    val pmt = createPartials(pattern) // Partial Match Table tells us where to continue
                                      // comparisons from (new pattern index to look at)
    var i = 0 // pattern counter
    var j = 0 // text counter

    if (pattern.isEmpty() || text.isEmpty())
        return matches.toTypedArray() // Return empty Array<Int>

    while (j < text.length) {
        if (pattern[i] == text[j]) {
            ++i
            ++j
            if (i == pattern.length) {
                matches.add(j-i) // Add index this match was found at to the list's end
                i = pmt[i-1] // -1 because we need the spot before incrementing
            }
        } else {
            if (i > 0)
                i = pmt[i-1]
            else // can't skip anything if no portion of the pattern has matched yet.
                ++j
        }
    }

    return matches.toTypedArray()
}

fun main(args: Array<String>) {
    val sample = samples[1]
    val table = createPartials(sample)

    println(sample)
    for (i in table)
        print(i)
}