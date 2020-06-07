import kotlin.random.Random
import kotlin.system.measureTimeMillis

var verbose: Boolean = false

fun runTest(searchFn: (String, String) -> Array<Int>, pattern: String, text: String) {
    var matches = Array(0) {_ -> 0}

    println("Runtime(ms): " + measureTimeMillis {
        matches = searchFn(pattern, text)
    })

    if (verbose) {
        println("Matches: ")
        for (match in matches) print("$match, ")
        println() // Extra space for formatting
    }
}

fun main(args: Array<String>) {
    val textSetSize: Int = Integer.parseInt(System.getenv("N") ?: "100000")
    val numTestRuns: Int = Integer.parseInt(System.getenv("x") ?: "1")
    val pattern: String = System.getenv("PATTERN") ?: "aba"
    verbose = (System.getenv("verbose") ?: "false").toBoolean()

    var text = ""
    for (i in 0 until textSetSize) {
        // fill with random val's ascii: a -> z
        text += Random.nextInt(97, 122).toChar()
    }

    for (i in 0 until numTestRuns) {
        println("Running Knuth-Morris-Pratt search (target = $pattern) on set of size $textSetSize.")
        runTest(::kmp, pattern, text)
    }

    for (i in 0 until numTestRuns) {
        println("Running Boyer-Moore search (target = $pattern) on set of size $textSetSize.")
        runTest(::bm, pattern, text)
    }
}