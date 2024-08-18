import dev.idot.text.color.parseColorCodes
import dev.idot.text.color.compressColors
import dev.idot.text.color.parseHexColors
import kotlin.math.pow
import kotlin.math.roundToInt
import kotlin.random.Random
import kotlin.system.measureNanoTime
import kotlin.test.Test

class Benchmark {
/*
    @Test
    fun hexBenchmark0() {
        benchmark("ColorizerMin: Hex", 5_500L) {
            generateRandomHexTest().translateHex()
        }.printBenchmark()
    }

    @Test
    fun hexBenchmark1() {
        benchmark("ColorizerMin (Compressed): Hex", 20_000L) {
            generateRandomHexTest().translateHex().compressColors()
        }.printBenchmark()
    }

    @Test
    fun gradientBenchmark0() {
        benchmark("Colorizer: Gradient", 40_500L) {
            "{#000>}${generateGarbage()}{#fff<}".translateColors()
        }.printBenchmark()
    }

    @Test
    fun gradientBenchmark1() {
        benchmark("Colorizer (Compressed): Gradient", 83_000L) {
            val garbage = generateGarbage()
            "{#000>}$garbage{#fff<}".translateColors().compressColors()
        }.printBenchmark()
    }

    @Test
    fun gradientBenchmark() {
        val name = "Colorizer: Gradient"
        val targetTime = 80_000L
        val iterations = 100000
        println("Optimizing... '$name'")

        val preResults = mutableListOf<Double>()
        for (i in 1..iterations) {
            var time: Long
            val garbage = generateGarbage()
            do {
                time = measureNanoTime { "{#000>}$garbage{#fff<}".translateColors().compressColors() }
                System.out.flush()
            } while (time > targetTime)
            print("  \r" + getLoadingBar(i, iterations) + " | $time ns")
            preResults.add(time.toDouble())
        }
        println()
        println()

        val pmd = preResults.culledMeanAndDeviation()
        val newTarget = pmd.first.toLong() + pmd.second.toLong()
        println("Target: $newTarget ns")

        var totalTime = 0L
        print("Benchmarking... Iterations: ")
        println(iterations)
        val results = mutableListOf<Double>()
        for (i in 1..iterations) {
            var time: Long
            val garbage = generateGarbage()
            do {
                time = measureNanoTime { "{#000>}$garbage{#fff<}".translateColors().compressColors() }
            } while (time > newTarget)
            print("\r" + getLoadingBar(i, iterations))
            System.out.flush()
            totalTime += time
            results.add(time.toDouble())
        }
        println()
        val md = results.culledMeanAndDeviation()
        //println("Average: ${totalTime / iterations} ns / ${"%.2f".format(totalTime.toDouble() / (iterations * 1000000L))} ms")
        Triple(totalTime, md.first.toLong(), md.second.toLong()).printBenchmark()
    }

    @Test
    fun loadingBarTest() {
        val iterations = 420
        for (i in 0..iterations) {
            print(getLoadingBar(i, iterations) + " | $i")
            System.out.flush()
            Thread.sleep(10)
        }
        println()
    }

    companion object {
        private const val COLORS = "0123456789abcdef"
        private const val FORMATS = "klmnor"
        private val charPool: List<Char> = ('a'..'z') + ('A'..'Z') + ('0'..'9')

        private fun generateRandomHexTest(length: Int = 16): String {
            return lazy {
                buildString {
                    for (i in 1..length) {
                        append("&#")
                        for (i in 0..5) {
                            append(COLORS.random())
                        }
                        for (i in 0..Random.nextInt(0, 3)) {
                            append("&")
                            append(FORMATS.random())
                        }

                        append(generateGarbage(4))
                    }
                }
            }.value
        }

        private fun generateGarbage(chars: Int = 64): String {
            return buildString {
                repeat(chars) { append(charPool.random()) }
            }
        }

        private inline fun benchmark(name: String = "Benchmark", targetTime: Long = 1000000L, iterations: Int = 100000, block: () -> Unit): Triple<Long, Long, Long> {
            println("Optimizing... '$name'")

            val preResults = mutableListOf<Double>()
            for (i in 1..iterations) {
                var time: Long
                do {
                    time = measureNanoTime { block() }
                    System.out.flush()
                } while (time > targetTime)
                print("  \r" + getLoadingBar(i, iterations) + " | $time ns")
                preResults.add(time.toDouble())
            }
            println()
            println()

            val pmd = preResults.culledMeanAndDeviation()
            val newTarget = pmd.first.toLong() + pmd.second.toLong()
            println("Target: $newTarget ns")

            var totalTime = 0L
            print("Benchmarking... Iterations: ")
            println(iterations)
            val results = mutableListOf<Double>()
            for (i in 1..iterations) {
                var time: Long
                do {
                    time = measureNanoTime { block() }
                } while (time > newTarget)
                print("\r" + getLoadingBar(i, iterations))
                System.out.flush()
                totalTime += time
                results.add(time.toDouble())
            }
            println()
            val md = results.culledMeanAndDeviation()
            //println("Average: ${totalTime / iterations} ns / ${"%.2f".format(totalTime.toDouble() / (iterations * 1000000L))} ms")
            return Triple(totalTime, md.first.toLong(), md.second.toLong())
        }

        private fun Triple<Long, Long, Long>.printBenchmark() {
            println("Average: ${this.second} ns ± ${this.third}")
            println("Total: ${this.first} ns / ${(this.first.toDouble() / 1000000).roundToInt()} ms")
            println()
        }

        private fun List<Double>.culledMeanAndDeviation(): Pair<Double, Double> {
            val mean = this.average()
            val deviation = this.map { (it - mean).pow(2) }.average().pow(0.5)
            return Pair(mean, deviation)
        }

        private fun getLoadingBar(progress: Int, iterations: Int, barLength: Int = 50): String {
            val progress = progress.toDouble() / iterations.toDouble()
            val filledLength = (barLength * progress).roundToInt()
            return buildString {
                append("\r|")
                append("-".repeat(filledLength))
                append(" ".repeat(barLength - filledLength))
                append("| %.2f".format(progress * 100))
                append("%")
            }
        }
    }
 */
}