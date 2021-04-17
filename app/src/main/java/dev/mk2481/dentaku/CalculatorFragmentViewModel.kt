package dev.mk2481.dentaku

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel

class CalculatorFragmentViewModel(
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    companion object {
        private const val EXP_KEY = "exp_key"
    }

    private val _exp = savedStateHandle.getLiveData(EXP_KEY, "")
    val exp: LiveData<String>
        get() = _exp
    val ans = MediatorLiveData<String>()

    init {
        ans.addSource(exp) {
            val a = kotlin.runCatching {
                ReversePolishNotationCalculator(Expression(it).toRPN().exp).answer()
                    .toPlainString()
            }.getOrNull() ?: ""
            ans.postValue(a)
        }
    }

    fun onInput(str: String) {
        _exp.postValue(exp.value?.plus(str) ?: "")
    }

    fun onBack() {
        _exp.postValue(exp.value?.dropLast(1) ?: "")
    }

    fun onEnter() {
        _exp.postValue(ans.value ?: "")
    }
}