package fourier

import space.kscience.kmath.complex.Complex
import space.kscience.kmath.complex.toComplex
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin

operator fun Complex.times(other: Complex) = Complex(
    re = re * other.re - im * other.im,
    im = re * other.im + im * other.re,
)

operator fun Complex.plus(other: Complex) = Complex(re + other.re, im + other.im)

fun Iterable<Complex>.sum() = fold(Complex(0.0)) { e, acc -> acc + e }

fun dftSlow(values: List<Double>) = values.indices.map { k -> values.mapIndexed { i, e ->
    Complex(e) * Complex(cos(2 * PI * k * i / values.size), -sin(2 * PI * i / values.size))
}.sum() }