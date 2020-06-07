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
    var runTimeAvg: Long = 0

    runTimeAvg = runTimeSum / numTestRuns
    println("Avg. runtime over $numTestRuns: " + runTimeAvg/1000000 + "(ms), " +
            runTimeAvg/1000 + "(us), " + runTimeAvg + "(ns).")
}

fun specificTestAttemptWorstCase() {
    val pattern = "aaaaaaaaab"
    val size = 10000000

    var textList = Array<Char>(size) {_ -> 'a'}
    textList[size-1] = 'b'
    var text = textList.joinToString(separator = "")

    /*
    Originally used string concatenation....
    "Trying loop unrolling because it takes so long to make giant strings"
    var a = "aaaaaaaaaaaaaaaaaaaaaaaaa" //25 a's
    for (i in 0 until 2) a += a //Should now have 100 a's

    for (i in 0 until size/100) text += a
    text += "b"
    */

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
/*
    var runTimeSum: Long = 0
    var text = ""

    // Create the string we'll search through
    for (i in 0 until textSetSize) {
        // fill with random val's ascii: a -> z
        text += Random.nextInt(97, 122).toChar()
    }

    println("Running Knuth-Morris-Pratt search (target = $pattern) $numTestRuns times " +
            "on set of size $textSetSize.")
    for (i in 0 until numTestRuns) runTimeSum += runTest(::kmp, pattern, text)
    printRunTimes(runTimeSum, numTestRuns)
    runTimeSum = 0

    println("Running Boyer-Moore search (target = $pattern) $numTestRuns times " +
            "on set of size $textSetSize.")
    for (i in 0 until numTestRuns) runTimeSum += runTest(::bm, pattern, text)
    printRunTimes(runTimeSum, numTestRuns)
*/
    specificTestAttemptWorstCase()
}