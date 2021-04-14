package dev.mk2481.dentaku

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class CalculatorFragmentViewModel : ViewModel() {

    private val _expression: MutableStateFlow<String> = MutableStateFlow("")
    val expression: StateFlow<String>
        get() = _expression

    fun onInput(str: String) {
        _expression.tryEmit(expression.value + str)
    }

    fun onBack() {
        _expression.tryEmit(expression.value.dropLast(1))
    }

    fun onEnter() {
        val ans = ReversePolishNotationCalculator(Expression(_expression.value).toRPN().exp)
            .answer()
            .toPlainString()
        _expression.tryEmit(ans)
    }
}