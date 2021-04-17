package dev.mk2481.dentaku

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers
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
        "3 2 - 3 2 * +,     7",
        "1 2 /,             0.5"
    )
    fun answer(exp: String, actual: BigDecimal) {
        assertThat(ReversePolishNotationCalculator(exp).answer(), Matchers.comparesEqualTo(actual))
    }

    @Test
    fun answer() {
        assertThat(
            ReversePolishNotationCalculator(Expression("1*2+3").toRPN().exp).answer(),
            Matchers.comparesEqualTo(5.toBigDecimal())
        )
    }
}