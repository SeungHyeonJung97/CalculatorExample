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

    var inputHistory = ""
    var result = ""
    var num1 = ""
    var num2 = ""
    var expression = NONE

    fun inputNumber(number: String) {
        inputHistory += number

        if(expression == NONE){
            num1 += number
        }else{
            num2 += number
        }

        if(num1.isNotEmpty() && num2.isNotEmpty()){
            when(expression){
                PLUS -> {
                    result = (num1.toInt() + num2.toInt()).toString()
                }
                MINUS -> {
                    result = (num1.toInt() - num2.toInt()).toString()
                }
                MULTIPLY -> {
                    result = (num1.toInt() * num2.toInt()).toString()
                }
                DIVIDE -> {
                    result = (num1.toInt() / num2.toInt()).toString()
                }
            }
        }
    }

    fun inputExpression(expression: Int) {
        if(result.isNotEmpty()){
            num1 = result
            num2 = ""
        }
        when (expression) {
            PLUS -> {
                inputHistory += " + "
            }
            MINUS -> {
                inputHistory += " - "
            }
            MULTIPLY -> {
                inputHistory += " * "
            }
            DIVIDE -> {
                inputHistory += " / "
            }
            RESULT -> {
                inputHistory += " = "
            }
        }
        this.expression = expression
    }
}