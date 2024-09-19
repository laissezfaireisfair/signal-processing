package and.signal

import and.signal.integrate.integrate
import kotlin.math.sqrt

private const val blocksCountPerLevel = 300

private fun internalFunc(x: Double, y: Double, z: Double) = (x + y + z) / sqrt(2 * x * x + 4 * y * y + 5 * z * z)

private fun main() = integrate(0.0, 1.0, blocksCountPerLevel) { x ->
    integrate(0.0, sqrt(1 - x * x), blocksCountPerLevel) { y ->
        integrate(0.0, sqrt(1 - x * x - y * y), blocksCountPerLevel) { z -> internalFunc(x, y, z) }
    }
}.let { println(it) }