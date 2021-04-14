package dev.mk2481.dentaku

/**
 * 式
 *
 * @param exp 中置記法の式
 */
class Expression(private val exp: String) {

    /**
     * 逆ポーランド記法に変換する
     */
    fun toRPN(): ReversePolishNotation {
        val operators = ArrayDeque<Operator>()
        var currentNumber = ""
        val resultStringList = mutableListOf<String>()

        exp
            .replace("÷", "/")
            .replace("×", "*")
            .forEach {
                val str = it.toString()

                when (val num = str.toIntOrNull()) {
                    is Int -> currentNumber += num
                    else -> {
                        resultStringList.add(currentNumber)
                        currentNumber = ""
                        val operator = Operator.fromString(str)
                        if (operators.lastOrNull()?.rank ?: 0 > operator.rank) {
                            resultStringList.add(operators.removeLast().symbol)
                            operators.addLast(Operator.fromString(str))
                        } else {
                            operators.addLast(Operator.fromString(str))
                        }

                    }
                }
            }
        resultStringList.add(currentNumber)
        resultStringList.addAll(operators.sortedByDescending { it.rank }.map { it.symbol })
        return ReversePolishNotation(resultStringList.joinToString(" "))
    }

}

/**
 * 演算子
 *
 * @param symbol 表示文字列
 * @param rank 優先度
 */
enum class Operator(val symbol: String, val rank: Int) {
    PLUS("+", 1),
    MINUS("-", 1),
    MULTIPLY("*", 2),
    DIVIDER("/", 2);

    companion object {
        fun fromString(str: String): Operator = values().first { it.symbol == str }
    }
}

/**
 * 逆ポーランド記法
 *
 * @param exp 式
 */
class ReversePolishNotation(val exp: String) {
    override fun equals(other: Any?): Boolean = other == exp
    override fun toString(): String = exp
    override fun hashCode(): Int = exp.hashCode()
}