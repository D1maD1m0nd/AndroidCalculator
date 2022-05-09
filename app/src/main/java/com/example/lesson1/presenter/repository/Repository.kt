package com.example.lesson1.presenter.repository

import java.lang.StringBuilder

interface Repository {
    fun getExp() : String
    fun initExp(builder : StringBuilder)
    fun clearExp()
    fun getResult() : String
    fun setResult(result : String)
    fun getOperation() : String
    fun setOperation(symbol : String)
    fun setExp(symbol : String)
}