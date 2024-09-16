import and.signal.integrate.integrate
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import kotlin.math.PI
import kotlin.math.exp
import kotlin.math.sin

const val allowedDelta = 0.1

class MainKtTest {
    @Test
    fun testId() = assertEquals(0.5, integrate(0.0, 1.0, { it }), allowedDelta)

    @Test
    fun testSinFromZeroToPi() = assertEquals(2.0, integrate(0.0, PI) { sin(it) }, allowedDelta)

    @Test
    fun testSinSymmetry() = assertEquals(0.0, integrate(-PI, PI) { sin(it) }, allowedDelta)

    @Test
    fun testSinSymmetry2() = assertEquals(0.0, integrate(-1.0, 1.0) { sin(it) }, allowedDelta)

    @Test
    fun testSinSymmetry3() = assertEquals(0.0, integrate(-10.0, 10.0) { sin(it) }, allowedDelta)

    @Test
    fun testSinFullPeriod() = assertEquals(0.0, integrate(0.0, 2 * PI) { sin(it) }, allowedDelta)

    @Test
    fun testSquare() = assertEquals(0.3333, integrate(0.0, 1.0) { it * it }, allowedDelta)

    @Test
    fun testExp() = assertEquals(1.7183, integrate(0.0, 1.0, { exp(it) }), allowedDelta)
}