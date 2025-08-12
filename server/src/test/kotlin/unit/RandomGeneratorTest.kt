package unit

import kotlin.random.Random

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.DisplayName

import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertTrue

class RandomNumberGeneratorTest {

    @Test
    @DisplayName("Generate Value within bounds, min is zero")
    fun valueWithinBoundsZeroMin() {
        val seeded = Random(111)
        val value = RandomGenerator.generate(min = 0, maxExclusive = 100, random = seeded)
        assertTrue(value in 0 until 100, "Expected value in [0,100), got $value")
    }

    @Test
    @DisplayName("Generate Value within bounds, min is non-zero")
    fun valueWithinBoundsNonZeroMin() {
        val seeded = Random(222)
        val value = RandomGenerator.generate(min = 10, maxExclusive = 20, random = seeded)
        assertTrue(value in 10 until 20, "Expected value in [10,20), got $value")
    }

    @Test
    @DisplayName("Generate value within edge case, min equals max minus 1")
    fun edgeCase() {
        val seeded = Random(333)
        val value = RandomGenerator.generate(min = 5, maxExclusive = 6, random = seeded)
        assertEquals(5, value)
    }

    @Test
    @DisplayName("Generate value within invalid range, throws IllegalArgumentException")
    fun invalidRange() {
        assertFailsWith<IllegalArgumentException> {
            RandomGenerator.generate(min = 10, maxExclusive = 10)
        }
        assertFailsWith<IllegalArgumentException> {
            RandomGenerator.generate(min = 5, maxExclusive = 0)
        }
    }
}