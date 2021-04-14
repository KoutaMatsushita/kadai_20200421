package dev.mk2481.dentaku

import java.math.BigDecimal

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
                    // 引き算とわり算は適当にやると 12/ が 2/1 になってしまうので注意すること。
                    when (it) {
                        "+" -> stack.addLast(stack.removeLast() + stack.removeLast())
                        "-" -> {
                            val op2 = stack.removeLast()
                            stack.addLast(stack.removeLast() - op2)
                        }
                        "*" -> stack.addLast(stack.removeLast() * stack.removeLast())
                        "/" -> {
                            val op2 = stack.removeLast()
                            stack.addLast(stack.removeLast() / op2)
                        }
                    }
                }
            }
        }
        return stack.removeFirst()
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