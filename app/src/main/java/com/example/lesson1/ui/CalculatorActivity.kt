package com.example.lesson1.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.lesson1.MainActivity
import com.example.lesson1.R
import com.example.lesson1.model.Calculator
import com.example.lesson1.model.CalculatorConstants.CALCULATOR_TAG
import com.example.lesson1.model.CalculatorConstants.CLEAR
import com.example.lesson1.model.CalculatorConstants.REGEX_IS_SYMBOL_OPERATION
import com.example.lesson1.model.CalculatorConstants.THEME_TAG
import com.example.lesson1.presenter.presenter.Contract
import com.example.lesson1.presenter.presenter.PresenterImpl
import java.util.regex.Pattern

class CalculatorActivity : AppCompatActivity(), View.OnClickListener, Contract.View{
    private var result: TextView? = null
    private var userInput: TextView? = null
    private var themeId = 0
    private var presenter : Contract.Presenter = PresenterImpl()
    override fun onCreate(savedInstanceState: Bundle?) {
        themeId = appTheme
        setTheme(themeId)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.calculator_layout)

        //init TextView
        result = findViewById(R.id.textViewResult)
        userInput = findViewById(R.id.textViewInput)
        initClickListenerButton()
        presenter.attach(this)
    }

    private val appTheme: Int
        get() = intent.extras!!.getInt(THEME_TAG)

    /**
     * метод инициализации слушателей клика для кнопок
     */
    private fun initClickListenerButton() {

        //appTheme button
        findViewById<View>(R.id.buttonBack).setOnClickListener { v: View? -> sendStateCalculatorAndTheme() }

        //values
        findViewById<View>(R.id.buttonOne).setOnClickListener(this)
        findViewById<View>(R.id.buttonTwo).setOnClickListener(this)
        findViewById<View>(R.id.buttonThree).setOnClickListener(this)
        findViewById<View>(R.id.buttonFour).setOnClickListener(this)
        findViewById<View>(R.id.buttonFive).setOnClickListener(this)
        findViewById<View>(R.id.buttonSix).setOnClickListener(this)
        findViewById<View>(R.id.buttonSeven).setOnClickListener(this)
        findViewById<View>(R.id.buttonEight).setOnClickListener(this)
        findViewById<View>(R.id.buttonNine).setOnClickListener(this)
        findViewById<View>(R.id.buttonZero).setOnClickListener(this)

        //actions
        findViewById<View>(R.id.buttonPlus).setOnClickListener(this)
        findViewById<View>(R.id.buttonMinus).setOnClickListener(this)
        findViewById<View>(R.id.buttonEqual).setOnClickListener(this)
        findViewById<View>(R.id.buttonDivide).setOnClickListener(this)
        findViewById<View>(R.id.buttonMulti).setOnClickListener(this)
        findViewById<View>(R.id.Clear).setOnClickListener(this)
    }

    private fun sendStateCalculatorAndTheme() {
        val intent = Intent(this, MainActivity::class.java)
        intent.putExtra(THEME_TAG, themeId)
        startActivity(intent)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        //сохраняем состояние
        outState.putSerializable(STATE_PRESENTER, presenter)
        presenter.detach()
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        savedInstanceState.getSerializable(STATE_PRESENTER)?.let {
            presenter = it as Contract.Presenter
            presenter.attach(this)
        }
    }

    /**
     * очищает поля у текст вью
     * и очищает стэйт у объекта калькулятора
     */
    private fun clearState() {
        presenter.clear()
        userInput?.text = null
        result?.text = null
    }

    override fun onClick(v: View) {
        val button = v as Button
        val text = button.text.toString()
        if (text == CLEAR) {
            clearState()
        } else {
            presenter.calculate(text)
            presenter.getExp()
        }
    }

    override fun setResult(result: String) {
            this.result?.text = result
    }

    override fun updateExp(result: String) {
        userInput?.text = result
    }

    override fun setError(error: Throwable) {

    }


    companion object {
        private const val STATE_PRESENTER = "STATE_PRESENTER"
    }

}