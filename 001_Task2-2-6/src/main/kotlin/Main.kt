package and.signal

import org.math.plot.Plot2DPanel
import javax.swing.JFrame
import kotlin.math.sin

data class Acc(val sum: Double, val lastTerm: Double)

fun approxSin(x: Double, termsCount: Int) = (1..<termsCount).fold(Acc(x, x)) { acc, i ->
    (acc.lastTerm * -1 * x * x / 2 * i / (2 * i + 1)).let { newTerm -> Acc(acc.sum + newTerm, newTerm) }
}.sum

val args = generateSequence(-1.0) { if (it < 1) it + 0.001 else null }.toList().toDoubleArray()

fun main() = with(Plot2DPanel()) {
    listOf(1, 3, 7).forEach { termCount ->
        addLinePlot("Terms: $termCount", args, args.map { approxSin(it, termCount) }.toDoubleArray())
    }
    addLinePlot("Reference", args, args.map { sin(it) }.toDoubleArray())
    val frame = JFrame("Plot frame")
    frame.contentPane = this
    frame.isVisible = true
}