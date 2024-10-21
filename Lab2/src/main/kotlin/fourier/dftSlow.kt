package fourier

import space.kscience.kmath.complex.Complex
import space.kscience.kmath.complex.toComplex
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin

operator fun Complex.times(other: Complex): Complex = TODO("Not yet implemented")

fun Iterable<Complex>.sum(): Complex = TODO("Not yet implemented")

fun dftSlow(values: List<Double>): Complex = values.mapIndexed { i, e ->
    e.toComplex() * Complex(re = cos(2 * PI * i / values.size), im = -sin(2 * PI * i / values.size))
}.sum()