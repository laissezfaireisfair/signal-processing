package and.signal

import and.signal.fourier.Fourier.Companion.toFourier
import and.signal.integrate.discretionFrequency
import kotlin.math.floor
import org.math.plot.Plot2DPanel
import javax.swing.JFrame

const val from: Double = -4.0
const val to: Double = 4.0
const val terms = 5
const val frequency = 100.0

fun x(t: Double) = when {
    floor(t).toInt().rem(2).let { it == 1 || it == -1 } -> 2.0
    else -> -2.0
}

val args = generateSequence(from) { if (it < to) it + discretionFrequency else null }.toList().toDoubleArray()

fun main() = println(::x.toFourier(terms, from, 1.0, frequency)).also {
    Plot2DPanel().apply {
        addLinePlot("Signal", args, args.map { x(it) }.toDoubleArray())
    }.let {
        with(JFrame("Plots")) {
            contentPane = it
            isVisible = true
        }
    }
}