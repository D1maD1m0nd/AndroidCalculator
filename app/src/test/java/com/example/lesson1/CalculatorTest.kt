package com.example.lesson1

import com.example.lesson1.model.Calculator
import com.example.lesson1.model.IOperation
import org.junit.Before




class CalculatorTest {
    lateinit var calculator : IOperation

    @Before
    fun initialize() {
        calculator = Calculator()
    }
}