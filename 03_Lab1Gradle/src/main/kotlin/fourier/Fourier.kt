package fourier

import integrate.integrate
import kotlin.math.cos
import kotlin.math.sin
import kotlin.math.PI

data class Fourier(val a0: Double, val period: Double, val aAndBs: List<Pair<Double, Double>>)

fun toFourier(terms: Int, period: Double, f: (Double) -> Double) = when {
    terms < 0 -> throw IllegalArgumentException(
        "Count of fourier coefficients shouldn't be negative (has $terms)"
    )

    period <= 0.0 -> throw IllegalArgumentException("Period should be positive (has $period")

    else -> {
        val w = (2 * PI / period)
        val from = -period / 2
        val to = period / 2

        Fourier(a0 = 2 / period * integrate(from, to) { f(it) }, period = period, aAndBs = (1..terms).map { n ->
            Pair(2 / period * integrate(from, to) { f(it) * cos(n * w * it) },
                2 / period * integrate(from, to) { f(it) * sin(n * w * it) })
        })
    }
}

fun Fourier.approximate(arg: Double): Double {
    val w = (2 * PI / period)

    return a0 / 2 + (1..aAndBs.size).sumOf { n ->
        aAndBs[n - 1].let { (an, bn) -> an * cos(n * w * arg) + bn * sin(n * w * arg) }
    }
}

fun Fourier.getError(f: (Double) -> Double) = { arg: Double -> f(arg) - approximate(arg) }