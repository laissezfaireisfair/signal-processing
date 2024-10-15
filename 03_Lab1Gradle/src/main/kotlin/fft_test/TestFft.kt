package fft_test

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import org.apache.commons.math3.transform.TransformType
import space.kscience.kmath.commons.transform.fft
import space.kscience.kmath.streaming.asFlow
import space.kscience.kmath.structures.ListBuffer
import kotlin.math.PI
import kotlin.math.cos

private data class FunctionRange(val from: Double, val to: Double, val period: Double, val func: (Double) -> Double)

fun main() {
    val range = FunctionRange(from = 0.0, to = 2 * PI, period = 2 * PI) { cos(it) }

    val step = 1e+0

    val args = with(range) { generateSequence(from) { if (it < to) it + step else null }.toList() }

    val valuesBuffer = ListBuffer(args.map { range.func(it) })

    val fourierFlow = valuesBuffer.asFlow().fft(direction = TransformType.FORWARD)

    val fourier = runBlocking(context = Dispatchers.Default) { fourierFlow.toList() }

    println(fourier)
}