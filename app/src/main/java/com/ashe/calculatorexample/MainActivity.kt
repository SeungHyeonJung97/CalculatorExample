package com.ashe.calculatorexample

import android.content.Context
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.CalendarContract
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import com.ashe.calculatorexample.MainViewModel.EXPRESSION.DIVIDE
import com.ashe.calculatorexample.MainViewModel.EXPRESSION.MINUS
import com.ashe.calculatorexample.MainViewModel.EXPRESSION.MULTIPLY
import com.ashe.calculatorexample.MainViewModel.EXPRESSION.PLUS
import com.ashe.calculatorexample.MainViewModel.EXPRESSION.RESULT
import com.ashe.calculatorexample.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        binding.viewModel = viewModel

        binding.etNumber.setTextIsSelectable(true)
        binding.etNumber.showSoftInputOnFocus = false

        viewModel.inputHistory.observe(this) {
            binding.etNumber.setText(it)
            if(viewModel.num1 != viewModel.inputHistory.value){
                binding.etNumber.setTextColor(Color.BLACK)
            }
        }

        viewModel.result.observe(this) {
            binding.tvResult.text = it
        }

        val buttonList = listOf(
            binding.btn0,
            binding.btn1,
            binding.btn2,
            binding.btn3,
            binding.btn4,
            binding.btn5,
            binding.btn6,
            binding.btn7,
            binding.btn8,
            binding.btn9
        )

        val buttonExpressions = listOf(
            binding.btnPlus,
            binding.btnMinus,
            binding.btnMultiply,
            binding.btnDivide,
            binding.btnResult
        )

        buttonList.forEachIndexed { index, number ->
            number.setOnClickListener {
                viewModel.inputNumber(index.toString())
            }
        }

        buttonExpressions.forEachIndexed { index, expression ->
            expression.setOnClickListener {
                when (index) {
                    PLUS -> {
                        viewModel.inputExpression(PLUS)
                    }
                    MINUS -> {
                        viewModel.inputExpression(MINUS)
                    }
                    MULTIPLY -> {
                        viewModel.inputExpression(MULTIPLY)
                    }
                    DIVIDE -> {
                        viewModel.inputExpression(DIVIDE)
                    }
                    RESULT -> {
                        viewModel.saveData()
                        binding.etNumber.setTextColor(Color.GREEN)
                    }
                }
            }
        }

        binding.btnHistory.setOnClickListener {
            viewModel.loadData()

        }
    }
}