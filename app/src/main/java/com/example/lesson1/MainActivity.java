package com.example.lesson1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Locale;

import static com.example.lesson1.CalculatorConstants.CLEAR;
import static com.example.lesson1.CalculatorConstants.REGEX_IS_SYMBOL_OPERATION;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Calculator calculator;
    private TextView result;
    private TextView userInput;
    private static final String STATE_CALCULATOR = "STATE_CALCULATOR";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calculator_layout);

        //init TextView
        result = findViewById(R.id.textViewResult);
        userInput = findViewById(R.id.textViewInput);

        //binding onClickListner on button
        initClickListenerButton();

        //init calculator
        calculator = new Calculator();
    }
    private void initClickListenerButton(){
        //values
        findViewById(R.id.buttonOne).setOnClickListener(this);
        findViewById(R.id.buttonTwo).setOnClickListener(this);
        findViewById(R.id.buttonThree).setOnClickListener(this);
        findViewById(R.id.buttonFour).setOnClickListener(this);
        findViewById(R.id.buttonFive).setOnClickListener(this);
        findViewById(R.id.buttonSix).setOnClickListener(this);
        findViewById(R.id.buttonSeven).setOnClickListener(this);
        findViewById(R.id.buttonEight).setOnClickListener(this);
        findViewById(R.id.buttonNine).setOnClickListener(this);
        findViewById(R.id.buttonZero).setOnClickListener(this);

        //actions
        findViewById(R.id.buttonPlus).setOnClickListener(this);
        findViewById(R.id.buttonMinus).setOnClickListener(this);
        findViewById(R.id.buttonEqual).setOnClickListener(this);
        findViewById(R.id.buttonDivide).setOnClickListener(this);
        findViewById(R.id.buttonMulti).setOnClickListener(this);
        findViewById(R.id.Clear).setOnClickListener(this);

    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        //сохраняем состояние
        outState.putSerializable(STATE_CALCULATOR,calculator);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        //возвращаем состояние
        calculator = (Calculator) savedInstanceState.getSerializable(STATE_CALCULATOR);
        result.setText(calculator.getResult());
        userInput.setText(calculator.getEXP());
    }
    private void clearState(){
        calculator.clear();
        userInput.setText(calculator.getResult());
        result.setText(calculator.getEXP());
    }
    @Override
    public void onClick(View v) {
        Button button = (Button)v;
        String text = button.getText().toString();
        if(text.equals(CLEAR)){
            clearState();
        } else {
            calculator.add(text);
            userInput.setText(calculator.getEXP());
            if(text.matches(REGEX_IS_SYMBOL_OPERATION)){
                result.setText(calculator.getResult());
            }
        }

    }
}