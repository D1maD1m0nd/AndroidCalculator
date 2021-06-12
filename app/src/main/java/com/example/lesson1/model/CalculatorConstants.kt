package com.example.lesson1.model

object CalculatorConstants {
    //регулярное выражение ждя проверки, что строка состоит только из чисел
    const val REGEX_IS_NUMBER = "[0-9]+"

    //регулярное выражение ждя проверки, что строка состоит только из определенных символов
    //указанных в скобках
    const val REGEX_IS_SYMBOL_OPERATION = "[-x+%=]"

    const val DIVIDE_ZERO = "Деление на 0"

    const val INPUT_NUMBER = "Введите число"

    const val PLUS = "+"

    const val MINUS = "-"

    const val DIVIDE = "%"

    const val MULTIPLY = "x"

    const val EQUAL = "="

    const val CLEAR = "c"
    const val THEME_TAG = "THEME"
    const val CALCULATOR_TAG = "CALCULATOR"
}