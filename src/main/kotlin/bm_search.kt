import java.lang.Integer.min

// bm_search.kt
// This file holds the implementation for the Boyer-Moore
// string search algorithm.

const val alphabet_start = 97 // 'a'
const val alphabet_end = 122 // 'z'

/*
Builds the table holding the shift val for each char in our alphabet.
    | Inputs: 'pattern' the string we determine shift values from.
    | Outputs: Table size of our alphabet, holding shift values.
 */
fun buildTable(pattern: String): Array<Int> {
    var table = Array(alphabet_end - alphabet_start + 1) { _ -> 0}

    for (i in pattern.indices) {
        // Get the int val for this char in the pattern,
        // subtract from the start of our alphabet to get the correct index in table.
        table[pattern[i].toInt() - alphabet_start] = i
    }

    return table
}

/*
Reads a table and returns the value at the index corresponding to a char
    | Inputs: 'table' to read, 'index' char to access
    | Output: the integer stored at that index.
*/
fun findShift(table: Array<Int>, index: Char): Int = table[index.toInt() - alphabet_start]

/*
String search implementation of the Boyer-Moore algorithm.
Uses what we know about the pattern to avoid unnecessary comparisons.
    | Inputs: 'text' to be searched, 'pattern' to be searched for.
    | Outputs: Index of the first substring of the text that matches the pattern.
*/
fun bm(pattern: String, text: String): Array<Int>{
    val table = buildTable(pattern)

    if (pattern.isEmpty())
        return Array(0) {_ -> 0}

    // Start comparisons at end of pattern, compare backwards.
    var i = pattern.length-1
    var j = i

    while (i < text.length) {
        if (text[i] == pattern[j]) {
            if (j == 0)     // We have compared all chars in pattern...
                return Array(1){_ -> i}    // So, this is a match.
            i -= 1
            j -= 1
        }
        else {
            i = i + pattern.length - min(j,1 + findShift(table, text[i]))
            j = pattern.length - 1
        }
    }

    return Array(0) {_ -> 0} // If we get here, no match was found.
}