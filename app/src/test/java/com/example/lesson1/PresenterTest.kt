package com.example.lesson1

import com.example.lesson1.presenter.Interactor.Interactor
import com.example.lesson1.presenter.Interactor.InteractorImpl
import com.example.lesson1.presenter.presenter.Contract
import com.example.lesson1.presenter.presenter.PresenterImpl
import com.example.lesson1.presenter.repository.Repository
import com.example.lesson1.presenter.repository.RepositoryImpl
import org.junit.Before
import org.junit.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class PresenterTest {
    private lateinit var presenter : Contract.Presenter
    @Mock
    private  lateinit var repository: RepositoryImpl

    @Mock
    private lateinit var Interactor : InteractorImpl

    @Mock
    private lateinit var viewContract: Contract.View


    @Before
    fun init() {
        MockitoAnnotations.initMocks(this)
        presenter = PresenterImpl()
        presenter.attach(viewContract)
    }

    @Test
    fun сalculate_call_test() {
        val calululateTest = "Calculate method call test"
        presenter.calculate(calululateTest)
        Mockito.verify(Interactor, Mockito.times(0)).calculate(calululateTest)
    }

    @Test
    fun view_contract_result_string_call_test() {
        val calululateTest = "Calculate method call test"
        presenter.calculate(calululateTest)
        Mockito.verify(viewContract, Mockito.times(0)).setResult(calululateTest)
    }

    @Test
    fun view_contract_result_symbol_test() {
        presenter.clear()
        presenter.calculate("1")
        presenter.calculate("+")
        presenter.calculate("1")
        presenter.calculate("=")


        Mockito.verify(viewContract, Mockito.times(1)).setResult("2.0")
    }


    @Test
    fun view_contract_result_exp_test() {
        presenter.clear()
        presenter.calculate("1")
        presenter.calculate("+")
        presenter.calculate("1")
        presenter.calculate("=")


        Mockito.verify(viewContract, Mockito.times(1)).setResult("2.0")
    }
}