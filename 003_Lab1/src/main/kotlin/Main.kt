package and.signal

import and.signal.fourier.*
import kotlin.math.floor
import org.math.plot.Plot2DPanel
import java.awt.Color
import javax.swing.JFrame

private val options = ShowOptions(from = -4.0, to = 4.0, terms = 20, period = 2.0, step = 0.01, f = { t ->
    when {
        floor(t).toInt().rem(2).let { it == 1 || it == -1 } -> 2.0
        else -> -2.0
    }
})

private fun main() = show(options)