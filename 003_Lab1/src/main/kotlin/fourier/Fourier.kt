package and.signal.fourier

import and.signal.integrate.integrate
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin

data class Fourier(val a0: Double, val period: Double, val aAndBs: List<Pair<Double, Double>>) {
    companion object {
        private fun toW(period: Double) = 2 * PI / period

        fun ((Double) -> Double).toFourier(terms: Int, from: Double, period: Double): Fourier = when {
            terms < 0 -> throw IllegalArgumentException(
                "Count of fourier coefficients shouldn't be negative (has $terms)"
            )

            period <= 0.0 -> throw IllegalArgumentException("Period should be positive (has $period")

            else -> Fourier(
                a0 = 2 / period * integrate(from, from + period, this),
                period = period,
                aAndBs = (1..terms).map { n ->
                    Pair(2 / period * integrate(from, from + period) { this(it) * cos(n * toW(period) * it) },
                        2 / period * integrate(from, from + period) { this(it) * sin(n * toW(period) * it) })
                })
        }
    }

    fun approximate(arg: Double, termsRequired: Int? = null): Double = when {
        termsRequired != null && aAndBs.size < termsRequired -> throw IllegalArgumentException(
            "Required more terms ($termsRequired) than we have coefficients (${aAndBs.size})"
        )

        else -> (termsRequired ?: aAndBs.size).let { terms ->
            a0 / 2 + (1..terms).sumOf { n ->
                aAndBs[n - 1].let { (an, bn) -> an * cos(n * toW(period) * arg) + bn * cos(n * toW(period) * arg) }
            }
        }
    }
}