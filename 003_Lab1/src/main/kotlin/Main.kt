package and.signal

import and.signal.integrate.discretionFrequency
import kotlin.math.floor
import org.math.plot.Plot2DPanel
import javax.swing.JFrame

const val from: Double = -4.0
const val to: Double = 4.0

fun x(t: Double) = when {
    floor(t).toInt().rem(2).let { it == 1 || it == -1 } -> 2.0
    else -> -2.0
}

val args = generateSequence(from) { if (it < to) it + discretionFrequency else null }.toList().toDoubleArray()

fun main() = Plot2DPanel().apply {
    addLinePlot("Signal", args, args.map { x(it) }.toDoubleArray())
}.let {
    with(JFrame("Plots")) {
        contentPane = it
        isVisible = true
    }
}