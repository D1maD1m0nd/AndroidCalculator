package com.example.lesson1.model


import com.example.lesson1.model.CalculatorConstants.DIVIDE
import com.example.lesson1.model.CalculatorConstants.DIVIDE_ZERO
import com.example.lesson1.model.CalculatorConstants.EQUAL
import com.example.lesson1.model.CalculatorConstants.INPUT_NUMBER
import com.example.lesson1.model.CalculatorConstants.MINUS
import com.example.lesson1.model.CalculatorConstants.MULTIPLY
import com.example.lesson1.model.CalculatorConstants.PLUS
import com.example.lesson1.model.CalculatorConstants.REGEX_IS_NUMBER
import com.example.lesson1.model.CalculatorConstants.REGEX_IS_SYMBOL_OPERATION
import java.io.Serializable
import java.util.regex.Pattern

class Calculator {
    companion object {
        fun minus(operand: Double?, operand1: Double?): Double? {
            if(operand1 == null || operand == null) {
                return null
            }
            return operand - operand1
        }

        fun plus(operand: Double?, operand1: Double?): Double? {
            if(operand1 == null || operand == null) {
                return null
            }
            return operand + operand1
        }

       fun divide(operand: Double?, operand1: Double?): Double? {
            if(operand1 == null || operand == null) {
                return null
            }

            if (operand1 < 0.0000000000000001) {
                return -1.0
            }
            return operand / operand1
        }

        fun multiply(operand: Double?, operand1: Double?): Double? {
            if(operand1 == null || operand == null) {
                return null
            }
            return operand * operand1
        }
    }
}