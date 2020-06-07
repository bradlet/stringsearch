import kotlin.random.Random
import kotlin.system.measureNanoTime

var verbose: Boolean = false

fun runTest(searchFn: (String, String) -> Array<Int>, pattern: String, text: String): Long {
    var matches = Array(0) {_ -> 0}

    val runtime = measureNanoTime {
        matches = searchFn(pattern, text)
    }

    if (verbose) {
        println("Runtime: " + runtime/1000000 + "(ms), " + runtime/1000 + "(us), " +
                runtime + "(ns)")

        // TOO MUCH VERBOSE
        //  println("Matches: ")
        //  for (match in matches) print("$match, ")
        //  println() // Extra space for formatting
    }
    return runtime
}

fun printRunTimes(runTimeSum: Long, numTestRuns: Int) {
    var runTimeAvg: Long = runTimeSum / numTestRuns
    println("Avg. runtime over $numTestRuns: " + runTimeAvg/1000000 + "(ms), " +
            runTimeAvg/1000 + "(us), " + runTimeAvg + "(ns).")
}

// Hard-coding only in this function basically... Just a place for me to try and force
// runtime situations
fun specificTestAttemptWorstCase() {
    val pattern = "aaaaaaaaab"
    val size = 10000000

    var textList = Array<Char>(size) {_ -> 'a'}
    textList[size-1] = 'b'
    var text = textList.joinToString(separator = "")

    println("\nCRAFTED TEST CASE")

    println("Runtime for KMP in specific case:")
    printRunTimes(runTest(::kmp, pattern, text), 1)

    println("Runtime for BM in specific case:")
    printRunTimes(runTest(::bm, pattern, text), 1)
}

fun main(args: Array<String>) {
    val textSetSize: Int = Integer.parseInt(System.getenv("N") ?: "100000")
    val numTestRuns: Int = Integer.parseInt(System.getenv("x") ?: "1")
    val pattern: String = System.getenv("PATTERN") ?: "aba"
    verbose = (System.getenv("verbose") ?: "false").toBoolean()

    var runTimeSum: Long = 0

    var textList = Array<Char>(textSetSize)
        {_ -> Random.nextInt(alphabet_start, alphabet_end).toChar()}
    var text = textList.joinToString(separator = "")

    println("Running Knuth-Morris-Pratt search (target = $pattern) $numTestRuns times " +
            "on set of size $textSetSize.")
    for (i in 0 until numTestRuns) runTimeSum += runTest(::kmp, pattern, text)
    printRunTimes(runTimeSum, numTestRuns)
    runTimeSum = 0

    println("Running Boyer-Moore search (target = $pattern) $numTestRuns times " +
            "on set of size $textSetSize.")
    for (i in 0 until numTestRuns) runTimeSum += runTest(::bm, pattern, text)
    printRunTimes(runTimeSum, numTestRuns)

    specificTestAttemptWorstCase()
}