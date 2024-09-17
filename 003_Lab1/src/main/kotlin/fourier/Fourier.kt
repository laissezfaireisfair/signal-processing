package and.signal.fourier

import and.signal.integrate.integrate
import kotlin.math.cos
import kotlin.math.sin

data class Fourier(val a0: Double, val frequency: Double, val aAndBs: List<Pair<Double, Double>>) {
    companion object {
        private fun ((Double) -> Double).getA0(from: Double, period: Double) =
            2 / period * integrate(from, from + period, this)

        private fun ((Double) -> Double).getAAndBs(count: Int, from: Double, period: Double, frequency: Double) =
            (1..count).map { n ->
                Pair(2 / period * integrate(from, from + period) { this(it) * cos(n * frequency * it) },
                    2 / period * integrate(from, from + period) { this(it) * sin(n * frequency * it) })
            }

        fun ((Double) -> Double).toFourier(terms: Int, from: Double, period: Double, frequency: Double): Fourier =
            when {
                terms < 0 -> throw IllegalArgumentException(
                    "Count of fourier coefficients shouldn't be negative (has $terms)"
                )

                period <= 0.0 -> throw IllegalArgumentException("Period should be positive (has $period")

                else -> Fourier(
                    a0 = this.getA0(from, period),
                    frequency = frequency,
                    aAndBs = this.getAAndBs(terms, from, period, frequency)
                )
            }
    }

    fun approximate(arg: Double, termsRequired: Int? = null): Double = when {
        termsRequired != null && aAndBs.size < termsRequired -> throw IllegalArgumentException(
            "Required more terms ($termsRequired) than we have coefficients (${aAndBs.size})"
        )

        else -> (termsRequired ?: aAndBs.size).let { terms ->
            a0 / 2 + (1..terms).sumOf { n ->
                aAndBs[n - 1].let { (an, bn) -> an * cos(n * frequency * arg) + bn * cos(n * frequency * arg) }
            }
        }
    }
}