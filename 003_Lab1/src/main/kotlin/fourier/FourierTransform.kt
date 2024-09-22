package and.signal.fourier

data class DiscreteSignal(val body: List<Double>) {
    val size = body.size

    fun get(t: Int) = when {
        t in 0..<body.size -> body[t]
        else -> throw IndexOutOfBoundsException("Signal for t=$t is undefined")
    }
}

data class DiscreteSpectrum(val body: List<Double>) {
    val size = body.size

    fun get(w: Int) = when {
        w in 0..<body.size -> body[w]
        else -> 0.0
    }
}

fun DiscreteSignal.fourierTransform(): DiscreteSpectrum = TODO("Implement")

fun DiscreteSignal.fastFourierTransform(): DiscreteSpectrum = TODO("Implement")

fun DiscreteSpectrum.inverseFourierTransform(): DiscreteSignal = TODO("Implement")

fun DiscreteSpectrum.inverseFastFourierTransform(): DiscreteSignal = TODO("Implement")