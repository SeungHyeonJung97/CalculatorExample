package com.ashe.calculatorexample

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel


class MainViewModel : ViewModel() {

    companion object EXPRESSION {
        val PLUS = 0
        val MINUS = 1
        val MULTIPLY = 2
        val DIVIDE = 3
        val RESULT = 4
        val NONE = 5
    }
    //    var inputHistory = ""
    private var _inputHistory = MutableLiveData<String>()
    val inputHistory: LiveData<String>
        get() = _inputHistory

    //    var result = ""
    private var _result = MutableLiveData<String>()
    val result: LiveData<String>
        get() = _result

    init {
        _inputHistory.value = ""
        _result.value = ""
    }




    var num1 = ""
    var num2 = ""
    var expression = NONE

    fun inputNumber(number: String) {
        _inputHistory.value += number

        if(expression == NONE){
            num1 += number
        }else{
            num2 += number
        }

        if(num1.isNotEmpty() && num2.isNotEmpty()){
            when(expression){
                PLUS -> {
                    _result.postValue( (num1.toInt() + num2.toInt()).toString())
                }
                MINUS -> {
                    _result.postValue((num1.toInt() - num2.toInt()).toString())
                }
                MULTIPLY -> {
                    _result.postValue((num1.toInt() * num2.toInt()).toString())
                }
                DIVIDE -> {
                    _result.postValue((num1.toInt() / num2.toInt()).toString())
                }
            }
        }
    }

    fun inputExpression(expression: Int) {
        if(_result.value!!.isNotEmpty()){
            num1 = _result.value!!
            num2 = ""
        }
        when (expression) {
            PLUS -> {
                // inputHistory += " + "
                _inputHistory.value += " + "
            }
            MINUS -> {
                // inputHistory += " - "
                _inputHistory.value += " - "
            }
            MULTIPLY -> {
                // inputHistory += " * "
                _inputHistory.value += " * "
            }
            DIVIDE -> {
                // inputHistory += " / "
                _inputHistory.value += " / "
            }
            RESULT -> {
                // inputHistory += " = "
                _inputHistory.value += " = "
            }
        }
        this.expression = expression
    }
}