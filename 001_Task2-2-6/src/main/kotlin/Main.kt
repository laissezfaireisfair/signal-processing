package and.signal

data class Acc(val sum: Double, val lastTerm: Double)

fun approxSin(x: Double, termsCount: Int) = (1..<termsCount).fold(Acc(x, x)) { acc, i ->
    (acc.lastTerm * -1 * x * x / 2 * i / (2 * i + 1)).let { newTerm -> Acc(acc.sum + newTerm, newTerm) }
}.sum

const val arg = 0.1
val termCounts = listOf(1, 3, 7)

fun main() = termCounts.forEach { println("sin($arg) approx with $it term ${approxSin(arg, it)}") }