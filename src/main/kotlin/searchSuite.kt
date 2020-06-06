import kotlin.random.Random
import kotlin.system.measureTimeMillis

fun runTest(searchFn: (String, String) -> Array<Int>, setSize: Int, pattern: String) {
    var matches = Array(0) {_ -> 0}
    var text = ""
    for (i in 1..setSize) {
        // fill with random val's ascii: a -> z
        text += Random.nextInt(97, 122).toChar()
    }

    println("Runtime(ms): " + measureTimeMillis {
        matches = searchFn(pattern, text)
    })

    println("Matches: ")
    for (match in matches) {
        print("$match, ")
    }
}

fun main(args: Array<String>) {
    val textSetSize: Int = Integer.parseInt(System.getenv("N") ?: "10000")
    val largeTextSetSize = 5 * textSetSize
    val veryLargeTextSetSize = 10 * textSetSize
    val pattern = System.getenv("PATTERN") ?: "ab"

    println("Running Knuth-Morris-Pratt search (target = $pattern) on set of size $textSetSize.")
    runTest(::kmp, textSetSize, pattern)

    println("Running Knuth-Morris-Pratt search (target = $pattern) on set of size $largeTextSetSize.")
    runTest(::kmp, largeTextSetSize, pattern)

    println("Running Knuth-Morris-Pratt search (target = $pattern) on set of size $veryLargeTextSetSize.")
    runTest(::kmp, veryLargeTextSetSize, pattern)

    println("Running Boyer-Moore search (target = $pattern) on set of size $textSetSize.")
    runTest(::bm, textSetSize, pattern)

    println("Running Boyer-Moore search (target = $pattern) on set of size $largeTextSetSize.")
    runTest(::bm, largeTextSetSize, pattern)

    println("Running Boyer-Moore search (target = $pattern) on set of size $veryLargeTextSetSize.")
    runTest(::bm, veryLargeTextSetSize, pattern)
}