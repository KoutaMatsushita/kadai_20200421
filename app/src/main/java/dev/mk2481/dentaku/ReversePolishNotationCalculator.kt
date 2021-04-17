package dev.mk2481.dentaku

import java.math.BigDecimal
import java.math.RoundingMode

/**
 * 逆ポーランド記法の式を計算する
 *
 * @param exp 計算式。
 * @param delimiter 区切り文字。デフォルトは半角スペースとする。
 */
class ReversePolishNotationCalculator(
    private val exp: String,
    private val delimiter: String = " "
) {

    /**
     * 計算を行う
     *
     * @return 計算結果
     */
    fun answer(): BigDecimal {
        val stack = ArrayDeque<BigDecimal>()

        exp.split(delimiter).forEach {
            when (val num = it.toBigDecimalOrNull()) {
                is BigDecimal -> {
                    stack.addLast(num)
                }
                else -> {
                    val op2 = stack.removeLast()
                    val op1 = stack.removeLast()
                    stack.addLast(calc(op1, op2, it))
                }
            }
        }
        return stack.removeFirst()
    }

    private fun calc(op1: BigDecimal, op2: BigDecimal, operator: String): BigDecimal =
        when (operator) {
            "+" -> op1 + op2
            "-" -> op1 - op2
            "*" -> op1 * op2
            "/" -> op1.divide(op2, 10, RoundingMode.HALF_EVEN).stripTrailingZeros()
            else -> throw RuntimeException("unknown operator: $operator")
        }

    override fun toString(): String {
        return "ReversePolishNotationCalculator(exp='$exp', delimiter='$delimiter')"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as ReversePolishNotationCalculator

        if (exp != other.exp) return false
        if (delimiter != other.delimiter) return false

        return true
    }

    override fun hashCode(): Int {
        var result = exp.hashCode()
        result = 31 * result + delimiter.hashCode()
        return result
    }

}