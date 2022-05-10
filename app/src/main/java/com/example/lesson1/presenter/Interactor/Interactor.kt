package com.example.lesson1.presenter.Interactor

import com.example.lesson1.presenter.repository.Repository

interface Interactor {
    fun calculate(symbol: String) : String
    fun getExp() : String
    fun getResult() : String
    fun clear()
}