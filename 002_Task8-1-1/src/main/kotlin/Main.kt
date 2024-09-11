package and.signal

import kotlinx.coroutines.*
import kotlin.math.sqrt

const val discretionFrequency = 0.01
const val shiftFromBadBorder = 1e-6

fun internalFunc(x: Double, y: Double, z: Double) = (x + y + z) / sqrt(2 * x * x + 4 * y * y + 5 * z * z)

fun Double.discreteNext() = this + discretionFrequency
fun Double.shift() = this + shiftFromBadBorder

fun splitRange(from: Double, to: Double) = generateSequence(Pair(from, from.discreteNext())) { (_, right) ->
    if (right.discreteNext() > to) null else Pair(right, right.discreteNext())
}

suspend fun evalBlock(a: Double, b: Double, f: suspend (Double) -> Double) =
    ((b - a) / 6) * (f(a) + 4 * f((a + b) / 2) + f(b))

suspend fun <T> Sequence<T>.parallelSumOf(selector: suspend (T) -> Double) = this.toList().let { list ->
    coroutineScope { list.map { async(Dispatchers.Default) { selector(it) } }.awaitAll().sum() }
}

suspend fun integrate(from: Double, to: Double, func: suspend (Double) -> Double) = when {
    to < from -> throw IllegalArgumentException("Backward integration is unsupported")
    from < to -> splitRange(from, to).parallelSumOf { (left, right) -> evalBlock(left, right, func) }
    else -> 0.0
}

suspend fun main() = integrate(0.0.shift(), 1.0) { x ->
    integrate(0.0.shift(), sqrt(1 - x * x)) { y ->
        integrate(0.0.shift(), sqrt(1 - x * x - y * y)) { z -> internalFunc(x, y, z) }
    }
}.let { println(it) }