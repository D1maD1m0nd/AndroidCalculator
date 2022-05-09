package com.example.lesson1.presenter.repository

class RepositoryImpl : Repository {
    private var EXP: StringBuilder
    private var result: String
    private var operation: String


    init {
        EXP = java.lang.StringBuilder()
        operation = ""
        result = ""
    }
    override fun setOperation(symbol: String) {
        operation = symbol
    }

    override fun getExp() : String{
        return EXP.toString()
    }

    override fun initExp(builder: java.lang.StringBuilder) {
        EXP = builder
    }

    override fun clearExp() {
        EXP.setLength(0)
    }

    override fun getResult(): String {
        return result
    }

    override fun setResult(result: String){
        this.result = result
    }

    override fun getOperation(): String {
        return operation
    }

    override fun setExp(symbol : String) {
        EXP.append(symbol)
    }
}