package integrate

/// From Task 8.1.1

private const val defaultBlocksCount = 1000

private fun rangeToBlocks(a: Double, b: Double, size: Double) =
    generateSequence(a to a + size) { (_, right) -> if (right + size > b) null else right to right + size }

private fun evalBlock(a: Double, b: Double, f: (Double) -> Double) = (b - a) * f((a + b) / 2)

fun integrate(a: Double, b: Double, blocksCount: Int = defaultBlocksCount, f: (Double) -> Double) = when {
    b < a -> throw IllegalArgumentException("Backward integration is unsupported")

    a < b -> {
        val blockSize = (b - a) / blocksCount
        rangeToBlocks(a, b, blockSize).sumOf { (left, right) -> evalBlock(left, right, f) }
    }

    else -> 0.0
}