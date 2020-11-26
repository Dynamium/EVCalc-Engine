
inline fun <R> executeAndMeasureTimeInMillis(block: () -> R): Pair<R, Long> {
    val start = System.currentTimeMillis()
    val result = block()
    return result to (System.currentTimeMillis() - start)
}