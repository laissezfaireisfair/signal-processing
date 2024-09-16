package and.signal

import and.signal.integrate.integrate
import and.signal.integrate.shift
import kotlin.math.sqrt

fun internalFunc(x: Double, y: Double, z: Double) = (x + y + z) / sqrt(2 * x * x + 4 * y * y + 5 * z * z)

fun main() = integrate(0.0.shift(), 1.0) { x ->
    integrate(0.0.shift(), sqrt(1 - x * x)) { y ->
        integrate(0.0.shift(), sqrt(1 - x * x - y * y)) { z -> internalFunc(x, y, z) }
    }
}.let { println(it) }