package and.signal.fourier

import and.signal.integrate.integrate

data class Fourier(val a0: Double, val aAndBs: List<Pair<Double, Double>>) {
    companion object {
        private fun ((Double) -> Double).getA0(from: Double, period: Double) =
            2 / period * integrate(from, from + period, this)

        private fun ((Double) -> Double).getAAndBs(count: Int, from: Double, period: Double) =
            (1..count).map { Pair(TODO(), TODO()) }

        fun ((Double) -> Double).toFourier(count: Int, from: Double, period: Double): Fourier = when {
            count < 0 -> throw IllegalArgumentException(
                "Count of fourier coefficients shouldn't be negative (has $count)"
            )

            period <= 0.0 -> throw IllegalArgumentException("Period should be positive (has $period")

            else -> Fourier(a0 = this.getA0(from, period), aAndBs = this.getAAndBs(count, from, period))
        }
    }

    val maxTerms = aAndBs.size

    fun approximate(arg: Double, termsRequired: Int? = null): Double = when {
        termsRequired != null && maxTerms < termsRequired -> throw IllegalArgumentException(
            "Required more terms ($termsRequired) than we have coefficients ($maxTerms)"
        )

        else -> TODO()
    }
}