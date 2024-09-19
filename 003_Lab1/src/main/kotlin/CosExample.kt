package and.signal

import and.signal.fourier.ShowOptions
import and.signal.fourier.approximate
import and.signal.fourier.show
import and.signal.fourier.toFourier
import org.math.plot.Plot2DPanel
import java.awt.Color
import javax.swing.JFrame
import kotlin.math.PI
import kotlin.math.cos

private const val amplitude = 0.05
private const val frequency = 100.0

private val options = ShowOptions(
    from = -0.05,
    to = 0.05,
    terms = 10,
    period = 1.0 / frequency,
    f = { t -> (2 * PI * frequency).let { w -> amplitude * cos(w * t) } },
    step = 1e-4
)

private fun main() = show(options)