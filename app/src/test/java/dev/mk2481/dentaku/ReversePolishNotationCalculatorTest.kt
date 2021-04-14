package dev.mk2481.dentaku

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import java.math.BigDecimal

class ReversePolishNotationCalculatorTest {

    @ParameterizedTest
    @CsvSource(
        "1 2 +,             3",
        "1 2 + 3 +,         6",
        "1 2 + 3 + 4 -,     2",
        "1 2 + 3 + 4 - 1 +, 3",
        "1 2 -,             -1",
        "1 2 *,             2",
        "1 2 * 3 +,         5",
        "6 2 /,             3",
        "10 4 +,            14",
        "3 2 - 3 2 * +,     7"
    )
    fun answer(exp: String, actual: BigDecimal) {
        assertEquals(ReversePolishNotationCalculator(exp).answer(), actual)
    }

    @Test
    fun answer() {
        assertEquals(
            ReversePolishNotationCalculator(Expression("1*2+3").toRPN().exp).answer(),
            5.toBigDecimal()
        )
    }
}