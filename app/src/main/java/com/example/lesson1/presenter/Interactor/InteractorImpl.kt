package com.example.lesson1.presenter.Interactor

import com.example.lesson1.model.Calculator
import com.example.lesson1.model.CalculatorConstants
import com.example.lesson1.presenter.repository.Repository
import java.util.regex.Pattern

class InteractorImpl(private val repository: Repository) : Interactor {


    override fun calculate(symbol: String) : String{
        var isSymbol: Boolean = Pattern.matches(CalculatorConstants.REGEX_IS_SYMBOL_OPERATION, symbol)
        val operation = repository.getOperation()
        if (isSymbol) {
            val len = repository.getExp().length
            if (len == 0) {
                repository.setExp("0")
            } else {
                val exp = repository.getExp()
                val last = exp[len - 1].toString()
                isSymbol = Pattern.matches(CalculatorConstants.REGEX_IS_SYMBOL_OPERATION, last)
                if (isSymbol) {
                    repository.initExp(StringBuilder(exp.replace(operation, symbol)))
                    repository.setOperation(symbol)
                }
            }
            validate()
            if (symbol != CalculatorConstants.EQUAL) {
                repository.setOperation(symbol)
            }
        }
        if (symbol != CalculatorConstants.EQUAL) {
            repository.setExp(symbol)
        }

        return repository.getResult()
    }

    override fun getExp(): String {
        return repository.getExp()
    }

    override fun getResult(): String {
        return repository.getResult()
    }

    override fun clear() {
        repository.setResult("")
        repository.clearExp()
    }

    private fun validate() {
        val textEXP = repository.getExp()
        val isSymbol = isSymbol(textEXP);
        val isNumber = isNumber(textEXP)
        val operation = repository.getOperation()
        //состоит ли строка только из цифр
        if (isNumber) {
            repository.setResult(textEXP)
        } else if (isSymbol) {
            repository.setResult(CalculatorConstants.INPUT_NUMBER)
        } else {
            val partsEXPArray = textEXP.split(operation).toTypedArray()
            if (partsEXPArray.size == 1) {
                repository.setResult(partsEXPArray[0])
            } else {
                val result = repository.getResult()
                val operand1 = partsEXPArray[0].replace(',', '.').toDouble()
                val operand2 = partsEXPArray[1].replace(',', '.').toDouble()
                //расчитываем результат выражения
                calculate(operation, operand1, operand2)
//              //очищаем ткущее выражение
                repository.clearExp()
                //добавляем результат предудщего
                repository.setExp(if (result == CalculatorConstants.DIVIDE_ZERO) "" else result)
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
            CalculatorConstants.PLUS -> repository.setResult("${Calculator.plus(operand1, operand2)}")
            CalculatorConstants.MINUS -> repository.setResult("${Calculator.minus(operand1, operand2)}")
            CalculatorConstants.DIVIDE -> {
                val tmp = Calculator.divide(operand1, operand2)
                repository.setResult(if (tmp == -1.0) CalculatorConstants.DIVIDE_ZERO else "$tmp")

            }
            CalculatorConstants.MULTIPLY -> repository.setResult("${Calculator.multiply(operand1, operand2)}")
            CalculatorConstants.EQUAL -> calculate(operation, operand1, operand2)
        }
    }
    private fun isSymbol(symbol : String) = Pattern.matches(CalculatorConstants.REGEX_IS_SYMBOL_OPERATION, symbol)
    private fun isNumber(symbol : String) = Pattern.matches(CalculatorConstants.REGEX_IS_NUMBER, symbol)
}