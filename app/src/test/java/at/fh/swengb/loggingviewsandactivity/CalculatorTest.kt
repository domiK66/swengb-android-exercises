package at.fh.swengb.loggingviewsandactivity

import com.google.common.truth.ExpectFailure.assertThat
import org.junit.Test
import kotlin.test.assertEquals

class CalculatorTest {
    @Test
    fun testMulitply2By2() {
        val calculator = Calculator()
        val result = calculator.parse("2 * 2")
        assertEquals(4, result)
    }
    @Test
    fun testDivide2By2() {
        val calculator = Calculator()
        val result = calculator.parse("2 / 2")
        assertEquals(1, result)
    }
    @Test
    fun test2Plus2() {
        val calculator = Calculator()
        val result = calculator.parse("2 + 2")
        assertEquals(4, result)
    }
    @Test
    fun test2Minus2() {
        val calculator = Calculator()
        val result = calculator.parse("2 - 2")
        assertEquals(0, result)
    }
}