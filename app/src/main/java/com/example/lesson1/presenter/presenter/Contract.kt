package com.example.lesson1.presenter.presenter

import java.io.Serializable

abstract class Contract {
    interface View{
        fun setResult(result : String)
        fun updateExp(result: String)
        fun setError(error : Throwable)
    }

    interface Presenter : Serializable{
        fun attach(v : View)
        fun calculate(symbol: String)
        fun clear()
        fun getExp()
        fun detach()
    }
}