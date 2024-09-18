package and.signal.fourier

import and.signal.integrate.discretionFrequency
import org.math.plot.Plot2DPanel
import java.awt.Color
import javax.swing.JFrame
import kotlin.math.PI
import kotlin.math.cos

const val from: Double = -0.5
const val to: Double = 0.5
const val terms = 20
const val amplitude = 2.0
const val frequency = 3.0

const val period = 1.0 / frequency

fun x(t: Double) = (2 * PI * frequency).let { w -> amplitude * cos(w * t) }

val args = generateSequence(from) { if (it < to) it + discretionFrequency else null }.toList().toDoubleArray()

fun main() {
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