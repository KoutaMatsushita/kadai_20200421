package dev.mk2481.dentaku

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import dev.mk2481.dentaku.databinding.FragmentCalculatorBinding

class CalculatorFragment : Fragment(R.layout.fragment_calculator) {

    private val viewModel by activityViewModels<CalculatorFragmentViewModel>()

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

        viewModel.exp.observe(viewLifecycleOwner) {
            binding.expression.text = it
        }
        viewModel.ans.observe(viewLifecycleOwner) {
            binding.answer.text = it
        }
    }
}