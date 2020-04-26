// kmp_search.kt
// This file holds the implementation for the Knuth-Morris-Pratt
// String search algorithm.

// Temporary literal string array for testing during implementation.
val samples: Array<String> = arrayOf(
    "Hello this is a test",
    "abcabcabcabc",
    "aaaaaaaaaaaaaaaaaaaaaaaaaaaab"
)


// Runs a supplied search function.
// Search functions should return number of matches found.
fun runSearch(function: ()->Number){
    function()
}


fun main(args: Array<String>) {
    for (sample in samples) {
        println(sample)
    }
}