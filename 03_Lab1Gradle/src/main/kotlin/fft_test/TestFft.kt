package fft_test

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import org.apache.commons.math3.transform.TransformType
import space.kscience.kmath.commons.transform.fft
import space.kscience.kmath.streaming.asFlow
import space.kscience.kmath.structures.ListBuffer
import kotlin.math.sin
import kotlin.math.hypot

const val partsCount = 2048

fun main() {
    val from = 0.0
    val to = 100.0
    val step = (to - from) / partsCount

    val args = (0..<partsCount).map { from + step * it }

    val frequencies = (0..<partsCount).map { it / (to - from) }

    val valuesBuffer = ListBuffer(args.map { sin(it) })

    val fourierFlow = valuesBuffer.asFlow().fft(bufferSize = partsCount, direction = TransformType.FORWARD)

    val fourier = runBlocking(context = Dispatchers.Default) { fourierFlow.toList() }

    val amplitudes = fourier.map { hypot(it.re, it.im) / partsCount }

    val frequencyToAmplitudes = frequencies.zip(amplitudes)

    frequencyToAmplitudes.filter { (_, a) -> a > 0.1 }.forEach { (f, a) -> println("$f $a") }
}