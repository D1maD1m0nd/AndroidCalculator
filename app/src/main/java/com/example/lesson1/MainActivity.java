package com.example.lesson1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioButton;


public class MainActivity extends AppCompatActivity implements IntentConstants{
    // Имя настроек
    private static final String NameSharedPreference = "STYLE";

    // Имя параметра в настройках
    private static final String appTheme = "APP_THEME";



    private static final int BLOOD = 0;
    private static final int EXTRA_WHITE = 1;
    private static final int DARKER = 2;
    private static final int GREEN = 3;




    private RadioButton bloodButton ;
    private RadioButton extraButton;
    private RadioButton darkerButton;
    private RadioButton greenButton;

    private Button sendButton;
    private Calculator bufCalculator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        int themeId = getAppTheme(R.style.Theme_MainContainer);

        Intent intent = getIntent();

        if(intent.getExtras() != null){
            bufCalculator = (Calculator) intent.getExtras().getSerializable(CALCULATOR_TAG);
            themeId = intent.getExtras().getInt(THEME_TAG);
        }

        setTheme(themeId);
        setContentView(R.layout.main_activity);
        initView();
        bindingOnClickButton();
    }
    private void initView(){
        bloodButton = findViewById(R.id.bloodThemeButton);
        extraButton = findViewById(R.id.extraWhiteButton);
        darkerButton = findViewById(R.id.darkerThemeButton);
        greenButton = findViewById(R.id.greenThemeButton);
        sendButton = findViewById(R.id.sendButton);

    }
    private void bindingOnClickButton(){
        bloodButton.setOnClickListener(v -> {
            setAppTheme(BLOOD);
            recreate();
        });
        extraButton.setOnClickListener(v -> {
            setAppTheme(EXTRA_WHITE);
            recreate();
        } );
        darkerButton.setOnClickListener(v -> {
            setAppTheme(DARKER);
            recreate();
        });
        greenButton.setOnClickListener(v ->{
            setAppTheme(GREEN);
            recreate();
        });

        sendButton.setOnClickListener(v ->{
            openCalculator();
        });

    }

    private void openCalculator(){
        Intent intent = new Intent(this, CalculatorActivity.class);
        int theme = getAppTheme(R.style.Theme_MainContainer);
        intent.putExtra(THEME_TAG, theme);
        if(bufCalculator != null){
            intent.putExtra(CALCULATOR_TAG, bufCalculator);
        }
        startActivity(intent);
    }
    private int getAppTheme(int codeStyle) {
        return codeStyleToStyleId(getCodeStyle(codeStyle));
    }

    // Чтение настроек, параметр «тема»
    private int getCodeStyle(int codeStyle){
        // Работаем через специальный класс сохранения и чтения настроек
        SharedPreferences sharedPref = getSharedPreferences(NameSharedPreference, MODE_PRIVATE);
        //Прочитать тему, если настройка не найдена - взять по умолчанию
        return sharedPref.getInt(appTheme, codeStyle);
    }

    // Сохранение настроек
    private void setAppTheme(int codeStyle) {
        SharedPreferences sharedPref = getSharedPreferences(NameSharedPreference, MODE_PRIVATE);
        // Настройки сохраняются посредством специального класса editor.
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt(appTheme, codeStyle);
        editor.apply();
    }

    private int codeStyleToStyleId(int codeStyle){
        switch (codeStyle){
            case BLOOD:
                return  R.style.Theme_BloodTheme;
            case DARKER:
                return  R.style.Theme_DarkerTheme;
            case GREEN:
                return R.style.Theme_GreenTheme;
            default:
                return R.style.Theme_MainContainer;
        }
    }
}