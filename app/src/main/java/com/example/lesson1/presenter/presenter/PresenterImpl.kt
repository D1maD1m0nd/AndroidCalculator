package com.example.lesson1.presenter.presenter

import com.example.lesson1.model.CalculatorConstants
import com.example.lesson1.presenter.Interactor.Interactor
import com.example.lesson1.presenter.Interactor.InteractorImpl
import com.example.lesson1.presenter.repository.RepositoryImpl
import java.io.Serializable
import java.util.regex.Pattern

class PresenterImpl(private val interactor: Interactor = InteractorImpl()) : Contract.Presenter, Serializable {
    private var view : Contract.View? = null
    override fun attach(v: Contract.View) {
        view = v
    }

    override fun calculate(symbol: String) {

        val result = interactor.calculate(symbol)
        val isSuccess = Pattern.matches(CalculatorConstants.REGEX_IS_SYMBOL_OPERATION, symbol)
        if(isSuccess) {
            view?.setResult(result)
        }
    }

    override fun clear() {
        interactor.clear()
    }

    override fun getExp() {
        val exp = interactor.getExp()
        view?.updateExp(exp)
    }


    override fun detach() {
        view = null
    }
}