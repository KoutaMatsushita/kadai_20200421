package dev.mk2481.dentaku

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import dev.mk2481.dentaku.databinding.FragmentCalculatorBinding
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class CalculatorFragment : Fragment(R.layout.fragment_calculator) {

    private val viewModel by viewModels<CalculatorFragmentViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentCalculatorBinding.bind(view)
        val clickListener =
            View.OnClickListener { viewModel.onInput((it as Button).text.toString()) }
        listOf(
            binding.button0,
            binding.button1,
            binding.button2,
            binding.button3,
            binding.button4,
            binding.button5,
            binding.button6,
            binding.button7,
            binding.button8,
            binding.button9
        ).forEach { it.setOnClickListener(clickListener) }

        binding.buttonAdd.setOnClickListener { viewModel.onInput("+") }
        binding.buttonMinus.setOnClickListener { viewModel.onInput("-") }
        binding.buttonMultiply.setOnClickListener { viewModel.onInput("×") }
        binding.buttonDivider.setOnClickListener { viewModel.onInput("÷") }

        binding.buttonBack.setOnClickListener { viewModel.onBack() }
        binding.buttonEq.setOnClickListener { viewModel.onEnter() }

        lifecycleScope.launch {
            viewModel.expression.collect {
                binding.expression.text = it
                binding.answer.text = kotlin.runCatching {
                    ReversePolishNotationCalculator(Expression(it).toRPN().exp).answer()
                        .toPlainString()
                }.getOrNull() ?: ""
            }
        }
    }
}