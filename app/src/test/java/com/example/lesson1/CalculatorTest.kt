package com.example.lesson1

import com.example.lesson1.model.Calculator
import com.example.lesson1.model.IOperation
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import org.junit.jupiter.params.provider.ValueSource
import java.util.stream.Stream
import kotlin.random.Random


class CalculatorTest {
    lateinit var calculator : IOperation

    @Before
    fun initialize() {
        calculator = Calculator()
    }
    @Test
    fun calculator_operation_minus_test_equals() {
        Assert.assertEquals(calculator.minus(Double.MAX_VALUE, Double.MIN_VALUE), 1.7976931348623157E308)
    }

    @Test
    fun calculator_operation_minus_test_not_equals() {
        Assert.assertNotEquals(calculator.minus(Double.MAX_VALUE , Double.MIN_VALUE), 0.0)
    }

    @Test
    fun calculator_operation_minus_test_not_null() {
        Assert.assertNotNull(calculator.minus(Double.MAX_VALUE , Double.MIN_VALUE))
    }

    @Test
    fun calculator_operation_plus_test_is_null() {
        Assert.assertNull(calculator.plus(Double.MAX_VALUE , Double.MIN_VALUE))
    }

    @Test
    fun calculator_operation_plus_test_equals() {
        Assert.assertEquals(calculator.plus(Double.MAX_VALUE, Double.MIN_VALUE), 1.7976931348623157E308)
    }

    @Test
    fun calculator_operation_plus_test_not_equals() {
        Assert.assertNotEquals(calculator.plus(Double.MAX_VALUE , Double.MIN_VALUE), 0.0)
    }

    @Test
    fun calculator_operation_multiply_test_not_null() {
        Assert.assertNotNull(calculator.multiply(Double.MAX_VALUE , Double.MIN_VALUE))
    }

    @Test
    fun calculator_operation_multiply_test_is_null() {
        Assert.assertNull(calculator.multiply(Double.MAX_VALUE , Double.MIN_VALUE))
    }

    @Test
    fun calculator_operation_multiply_test_equals() {
        Assert.assertEquals(calculator.multiply(Double.MAX_VALUE, Double.MIN_VALUE), 1.7976931348623157E308)
    }

    @Test
    fun calculator_operation_multiply_test_not_equals() {
        Assert.assertEquals(calculator.multiply(Double.MAX_VALUE, Double.MIN_VALUE), 0)
    }

    @Test
    fun calculator_operation_divide_test_not_equals() {
        Assert.assertNotEquals(calculator.divide(Double.MAX_VALUE , Double.MIN_VALUE), 0.0)
    }


    @Test
    fun calculator_operation_divide_test_not_null() {
        Assert.assertNotNull(calculator.divide(Double.MAX_VALUE , Double.MIN_VALUE))
    }

    @Test
    fun calculator_operation_divide_test_is_null() {
        Assert.assertNull(calculator.divide(Double.MAX_VALUE , Double.MIN_VALUE))
    }

    @Test
    fun calculator_operation_divide_test_is_null_first_operand() {
        Assert.assertNull(calculator.divide(null , Double.MIN_VALUE))
    }

    @Test
    fun calculator_operation_divide_test_is_null_second() {
        Assert.assertNull(calculator.divide(Double.MAX_VALUE , null))
    }

    @Test
    fun calculator_operation_divide_test_equals_zero_argument() {
        Assert.assertEquals(calculator.divide(Double.MAX_VALUE , 0.0),-1.0)
    }
}