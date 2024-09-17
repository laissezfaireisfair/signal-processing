package and.signal

import and.signal.fourier.approximate
import and.signal.fourier.getError
import and.signal.fourier.toFourier
import and.signal.integrate.discretionFrequency
import kotlin.math.floor
import org.math.plot.Plot2DPanel
import java.awt.Color
import javax.swing.JFrame

const val from: Double = -4.0
const val to: Double = 4.0
const val terms = 20
const val period = 2.0

fun x(t: Double) = when {
    floor(t).toInt().rem(2).let { it == 1 || it == -1 } -> 2.0
    else -> -2.0
}

val args = generateSequence(from) { if (it < to) it + discretionFrequency else null }.toList().toDoubleArray()

fun main() {
    val fourier = toFourier(terms, period) { x(it) }

    val panel = Plot2DPanel().apply {
        addLinePlot("Signal", Color.BLUE, args, args.map { x(it) }.toDoubleArray())
        addLinePlot("Approximation", Color.ORANGE, args, args.map { fourier.approximate(it) }.toDoubleArray())
        addLinePlot("Error", Color.RED, args, args.map { fourier.getError { x(it) }(it) }.toDoubleArray())
    }

    with(JFrame("Plots")) {
        contentPane = panel
        isVisible = true
    }
}