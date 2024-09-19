package and.signal.fourier

import org.math.plot.Plot2DPanel
import java.awt.Color
import javax.swing.JFrame

data class ShowOptions(
    val from: Double, val to: Double, val terms: Int, val period: Double, val f: (Double) -> Double, val step: Double
)

fun show(options: ShowOptions) {
    val args = with(options) { generateSequence(from) { if (it < to) it + step else null }.toList().toDoubleArray() }

    val fourier = with(options) { toFourier(terms, period) { f(it) } }

    val panel = with(options) {
        Plot2DPanel().apply {
            addLinePlot("Signal", Color.BLUE, args, args.map { f(it) }.toDoubleArray())
            addLinePlot(
                "Approximation", Color.ORANGE, args, args.map { fourier.approximate(it) }.toDoubleArray()
            )
            addLinePlot(
                "Error", Color.RED, args, args.map { fourier.getError { f(it) }(it) }.toDoubleArray()
            )
        }
    }

    with(JFrame("Plots")) {
        contentPane = panel
        isVisible = true
    }
}