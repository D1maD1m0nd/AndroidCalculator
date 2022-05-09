package com.example.lesson1.presenter.Interactor

interface Interactor {
    fun calculate(symbol: String) : String
    fun getExp() : String
    fun getResult() : String
    fun clear()
}