package dev.mk2481.dentaku

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class ExpressionTest {
    @ParameterizedTest
    @CsvSource(
        "3+2,     3 2 +",
        "3+2×2,   3 2 2 * +",
        "4÷2+1,   4 2 / 1 +",
        "3-2+3*2, 3 2 - 3 2 * +"
    )
    fun toRPN(exp: String, actual: String) {
        assertEquals(Expression(exp).toRPN(), ReversePolishNotation(actual))
    }
}