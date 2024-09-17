package and.signal

import and.signal.fourier.Fourier.Companion.toFourier
import and.signal.integrate.discretionFrequency
import kotlin.math.floor
import org.math.plot.Plot2DPanel
import java.awt.Color
import java.awt.Dimension
import javax.swing.JFrame
import kotlin.math.PI

const val from: Double = -4.0
const val to: Double = 4.0
const val terms = 5
const val period = 2.0

fun x(t: Double) = when {
    floor(t).toInt().rem(2).let { it == 1 || it == -1 } -> 2.0
    else -> -2.0
}

val args = generateSequence(from) { if (it < to) it + discretionFrequency else null }.toList().toDoubleArray()

fun main() = ::x.toFourier(terms, 0.0, period).let { fourier ->
    println(fourier)
    Plot2DPanel().apply {
        addLinePlot("Signal", Color.BLUE, args, args.map { x(it) }.toDoubleArray())
        addLinePlot("Approximation", Color.YELLOW, args, args.map { fourier.approximate(it) }.toDoubleArray())
    }.let {
        with(JFrame("Plots")) {
            contentPane = it
            isVisible = true
        }
    }
}