package com.example.lesson1.model

interface IOperation {
    fun minus(operand: Double, operand1: Double): Double

    fun plus(operand: Double, operand1: Double): Double

    fun divide(operand: Double, operand1: Double): Double

    fun multiply(operand: Double, operand1: Double): Double
}