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

class Calculator : ICalculator, Serializable, IOperation {
    private var EXP: StringBuilder
    private lateinit var result: String
    private lateinit var operation: String

    init {
        EXP = StringBuilder()
    }

    override fun add(symbol: String) {

        var isSymbol: Boolean = Pattern.matches(REGEX_IS_SYMBOL_OPERATION, symbol)
        if (isSymbol) {
            val len = EXP.length
            if (len == 0) {
                EXP.append("0")
            } else {
                val exp = EXP.toString()
                val last = exp[len - 1].toString()
                isSymbol = Pattern.matches(REGEX_IS_SYMBOL_OPERATION, last)
                if (isSymbol) {
                    EXP = StringBuilder(exp.replace(operation, symbol))
                    operation = symbol
                    return
                }
            }
            calculate()
            if (symbol != EQUAL) {
                operation = symbol
            }
        }
        if (symbol != EQUAL) {
            EXP.append(symbol)
        }
    }

    override fun getResult(): String {
        return result
    }

    override fun minus(operand: Double, operand1: Double): Double {
        return operand - operand1
    }

    override fun plus(operand: Double, operand1: Double): Double {
        return operand + operand1
    }

    override fun divide(operand: Double, operand1: Double): Double {
        if (operand1 < 0.0000000000000001) {
            return -1.0
        }
        return operand / operand1
    }

    override fun multiply(operand: Double, operand1: Double): Double {
        return operand * operand1
    }

    override fun getEXP(): String {
        return EXP.toString()
    }

    fun clear() {
        result = ""
        EXP.setLength(0)
    }

    /**
     * Метод расчета
     * В данном методе назодятся основные проверки
     * Пользовательского ввода, далее производится расчет по введенным данным
     */
    private fun calculate() {
        val textEXP = EXP.toString()
        val isSymbol = Pattern.matches(REGEX_IS_SYMBOL_OPERATION, textEXP)
        val isNumber = Pattern.matches(REGEX_IS_NUMBER, textEXP)
        //состоит ли строка только из цифр
        if (isNumber) {
            result = textEXP
        } else if (isSymbol) {
            result = INPUT_NUMBER
        } else {
            val partsEXPArray = textEXP.split(operation).toTypedArray()
            if (partsEXPArray.size == 1) {
                result = partsEXPArray[0]
            } else {
                val operand1 = partsEXPArray[0].replace(',', '.').toDouble()
                val operand2 = partsEXPArray[1].replace(',', '.').toDouble()
                //расчитываем результат выражения
                calculate(operation, operand1, operand2)
//              //очищаем ткущее выражение
                EXP.setLength(0)
                //добавляем результат предудщего
                EXP.append(if (result == DIVIDE_ZERO) "" else result)
            }
        }
    }

    /**
     * Класс производит расчет значений двух переданных операндов, по операции
     *
     * @param operation - операция, которую необходимо произвести, сравнивается с константами
     * @param operand1  - первый операнд
     * @param operand2  - второй операнд
     */
    private fun calculate(operation: String, operand1: Double, operand2: Double) {
        when (operation) {
            PLUS -> result = "${plus(operand1, operand2)}"
            MINUS -> result = "${minus(operand1, operand2)}"
            DIVIDE -> {
                val tmp = divide(operand1, operand2)
                result = if (tmp == -1.0) DIVIDE_ZERO else
                    "$tmp"

            }
            MULTIPLY -> result =
                "${multiply(operand1, operand2)}"
            EQUAL -> calculate(operation, operand1, operand2)
        }
    }
}