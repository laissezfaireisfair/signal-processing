package and.signal.integrate

private const val defaultBlocksCount = 1000

private fun rangeToBlocks(from: Double, to: Double, blockSize: Double) =
    generateSequence(Pair(from, from + blockSize)) { (_, right) ->
        if (right + blockSize > to) null else Pair(right, right + blockSize)
    }

/// Simpson creates trouble with undefined borders
//private fun evalBlock(a: Double, b: Double, f: (Double) -> Double) = ((b - a) / 6) * (f(a) + 4 * f((a + b) / 2) + f(b))
private fun evalBlock(a: Double, b: Double, f: (Double) -> Double) = (b - a) * f((a + b) / 2)

fun integrate(from: Double, to: Double, blocksCount: Int = defaultBlocksCount, func: (Double) -> Double) = when {
    to < from -> throw IllegalArgumentException("Backward integration is unsupported")

    from < to -> {
        val blockSize = (to - from) / blocksCount
        rangeToBlocks(from, to, blockSize).sumOf { (left, right) -> evalBlock(left, right, func) }
    }

    else -> 0.0
}