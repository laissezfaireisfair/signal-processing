package and.signal.integrate

import org.math.plot.Plot2DPanel
import javax.swing.JFrame

fun squareFunc(x: Double) = when {
    0.0 < x && x < 1.0 -> 2.0
    else -> 0.0
}

const val from = -0.5
const val to = 1.5

val args = generateSequence(from) { if (it < to) it + discretionFrequency else null }.toList().toDoubleArray()

fun main() = println(integrate(from, to) { squareFunc(it) }).also {
    Plot2DPanel().apply {
        addLinePlot("Signal", args, args.map { squareFunc(it) }.toDoubleArray())
    }.let {
        with(JFrame("Plot frame")) {
            contentPane = it
            isVisible = true
        }
    }
}