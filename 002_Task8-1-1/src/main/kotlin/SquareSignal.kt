package and.signal

import and.signal.integrate.integrate
import org.math.plot.Plot2DPanel
import javax.swing.JFrame

private fun squareSignal(x: Double) = when {
    0.0 < x && x < 1.0 -> 2.0
    else -> 0.0
}

private const val from = -0.5
private const val to = 1.5
private const val stepSize = 0.01

private val args = generateSequence(from) { if (it < to) it + stepSize else null }.toList().toDoubleArray()

private fun main() = println(integrate(from, to) { squareSignal(it) }).also {
    Plot2DPanel().apply {
        addLinePlot("Signal", args, args.map { squareSignal(it) }.toDoubleArray())
    }.let {
        with(JFrame("Plot frame")) {
            contentPane = it
            isVisible = true
        }
    }
}