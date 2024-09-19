package and.signal

import and.signal.fourier.approximate
import and.signal.fourier.toFourier
import org.math.plot.Plot2DPanel
import java.awt.Color
import javax.swing.JFrame
import kotlin.math.PI
import kotlin.math.cos

private const val from: Double = -0.5
private const val to: Double = 0.5
private const val terms = 10
private const val amplitude = 2.0
private const val frequency = 3.0
private const val step = 0.01

private const val period = 1.0 / frequency

private fun x(t: Double) = (2 * PI * frequency).let { w -> amplitude * cos(w * t) }

private val args = generateSequence(from) { if (it < to) it + step else null }.toList().toDoubleArray()

private fun main() {
    val fourier = toFourier(terms, period) { x(it) }

    val panel = Plot2DPanel().apply {
        addLinePlot("Signal", Color.BLUE, args, args.map { x(it) }.toDoubleArray())
        addLinePlot("Approximation", Color.ORANGE, args, args.map { fourier.approximate(it) }.toDoubleArray())
    }

    with(JFrame("Plots")) {
        contentPane = panel
        isVisible = true
    }
}