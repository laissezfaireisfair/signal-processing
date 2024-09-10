package and.signal

import kotlin.math.sqrt

const val discretionFrequency = 0.001
fun Double.discreteNext() = this + discretionFrequency
fun Double.discretePrev() = this - discretionFrequency

fun internalFunc(x: Double, y: Double, z: Double) = (x + y + z) / sqrt(2 * x * x + 4 * y * y + 5 * z * z)

fun splitRange(from: Double, to: Double) = generateSequence(Pair(from, from.discreteNext())) { (_, right) ->
    if (right.discreteNext() > to) null else Pair(right, right.discreteNext())
}

//fun evalBlock(a: Double, b: Double, f: (Double) -> Double) = (b - a) * f((a + b) / 2)
fun evalBlock(a: Double, b: Double, f: (Double) -> Double) = ((b - a) / 6) * (f(a) + f((a + b) / 2) + f(b))

fun integrate(from: Double, to: Double, func: (Double) -> Double) = when {
    to < from -> throw IllegalArgumentException("Backward integration is unsupported")

    from < to -> splitRange(from.discreteNext(), to.discretePrev()).sumOf { (left, right) ->
        val evaluation = evalBlock(left, right, func)
        if (evaluation.isNaN() || evaluation.isInfinite())
            println("[$left, $right]: $evaluation")
        evaluation.takeUnless { (it.isNaN() || it.isInfinite()) } ?: 0.0
    }

    else -> 0.0
}

fun main() = integrate(0.0, 1.0) { x ->
    integrate(0.0, sqrt(1 - x * x)) { y ->
        integrate(0.0, sqrt(1 - x * x - y * y)) { z -> internalFunc(x, y, z) }
    }
}.let { println(it) }