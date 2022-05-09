package com.example.lesson1

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.RadioButton
import androidx.appcompat.app.AppCompatActivity
import com.example.lesson1.model.Calculator
import com.example.lesson1.model.CalculatorConstants.CALCULATOR_TAG
import com.example.lesson1.model.CalculatorConstants.THEME_TAG
import com.example.lesson1.ui.CalculatorActivity


class MainActivity : AppCompatActivity() {
    private var bloodButton: RadioButton? = null
    private var extraButton: RadioButton? = null
    private var darkerButton: RadioButton? = null
    private var greenButton: RadioButton? = null
    private var sendButton: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var themeId = getAppTheme(R.style.Theme_MainContainer)
        val intent = intent
        val bundle = intent.extras
        if (bundle != null) {
            if (bundle.getInt(THEME_TAG) != 0) {
                themeId = intent.extras!!.getInt(THEME_TAG)
                intent.removeExtra(THEME_TAG)
            }
        }
        setTheme(themeId)
        setContentView(R.layout.main_activity)
        initView()
        bindingOnClickButton()
    }

    override fun onStart() {
        super.onStart()
        Log.d("On start", "onstart")
    }
    private fun initView() {
        bloodButton = findViewById(R.id.bloodThemeButton)
        extraButton = findViewById(R.id.extraWhiteButton)
        darkerButton = findViewById(R.id.darkerThemeButton)
        greenButton = findViewById(R.id.greenThemeButton)
        sendButton = findViewById(R.id.sendButton)
    }

    private fun bindingOnClickButton() {
        bloodButton!!.setOnClickListener {
            setAppTheme(BLOOD)
            recreate()
        }
        extraButton!!.setOnClickListener {
            setAppTheme(EXTRA_WHITE)
            recreate()
        }
        darkerButton!!.setOnClickListener {
            setAppTheme(DARKER)
            recreate()
        }
        greenButton!!.setOnClickListener {
            setAppTheme(GREEN)
            recreate()
        }
        sendButton!!.setOnClickListener {  openCalculator() }
    }

    private fun openCalculator() {
        val intent = Intent(this, CalculatorActivity::class.java)
        val theme = getAppTheme(R.style.Theme_MainContainer)
        intent.putExtra(THEME_TAG, theme)
        startActivity(intent)
    }

    private fun getAppTheme(codeStyle: Int): Int {
        return codeStyleToStyleId(getCodeStyle(codeStyle))
    }

    // Чтение настроек, параметр «тема»
    private fun getCodeStyle(codeStyle: Int): Int {
        // Работаем через специальный класс сохранения и чтения настроек
        val sharedPref = getSharedPreferences(NameSharedPreference, MODE_PRIVATE)
        //Прочитать тему, если настройка не найдена - взять по умолчанию
        return sharedPref.getInt(appTheme, codeStyle)
    }

    // Сохранение настроек
    private fun setAppTheme(codeStyle: Int) {
        val sharedPref = getSharedPreferences(NameSharedPreference, MODE_PRIVATE)
        // Настройки сохраняются посредством специального класса editor.
        val editor = sharedPref.edit()
        editor.putInt(appTheme, codeStyle)
        editor.apply()
    }

    private fun codeStyleToStyleId(codeStyle: Int): Int {
        return when (codeStyle) {
            BLOOD -> R.style.Theme_BloodTheme
            DARKER -> R.style.Theme_DarkerTheme
            GREEN -> R.style.Theme_GreenTheme
            else -> R.style.Theme_MainContainer
        }
    }

    companion object {
        // Имя настроек
        private const val NameSharedPreference = "STYLE"

        // Имя параметра в настройках
        private const val appTheme = "APP_THEME"
        private const val BLOOD = 0
        private const val EXTRA_WHITE = 1
        private const val DARKER = 2
        private const val GREEN = 3
    }
}