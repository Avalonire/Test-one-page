import kotlin.random.Random

/**
 * Generating random integers within a range (default range: 0..1,000,000)
 */
object RandomGenerator {

    fun generate(
        min: Int = 0,
        maxExclusive: Int = 1000000,
        random: Random = Random
    ): Int {
        require(min < maxExclusive) { "Value 'min' must be less than 'maxExclusive'! Was min=$min, maxExclusive=$maxExclusive" }
        return random.nextInt(from = min, until = maxExclusive)
    }
}