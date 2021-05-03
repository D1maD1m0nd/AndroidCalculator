package com.example.lesson1;

import java.io.Serializable;

import static com.example.lesson1.CalculatorConstants.DIVIDE;
import static com.example.lesson1.CalculatorConstants.DIVIDE_ZERO;
import static com.example.lesson1.CalculatorConstants.EQUAL;
import static com.example.lesson1.CalculatorConstants.INPUT_NUMBER;
import static com.example.lesson1.CalculatorConstants.MINUS;
import static com.example.lesson1.CalculatorConstants.MULTIPLY;
import static com.example.lesson1.CalculatorConstants.PLUS;
import static com.example.lesson1.CalculatorConstants.REGEX_IS_NUMBER;
import static com.example.lesson1.CalculatorConstants.REGEX_IS_SYMBOL_OPERATION;

public class Calculator implements ICalculator, Serializable {
    private  StringBuilder EXP;
    private String result;
    private String operation;


    public Calculator() {
        EXP = new StringBuilder();
    }

    @Override
    public void add(String i) {
        boolean isSymbol = i.matches(REGEX_IS_SYMBOL_OPERATION);
        //проверяем наличие на вхде операции
        if (isSymbol) {
            if(EXP.length() == 0){
                EXP.append("0");
            } else {
                //TODO вынести код проверки двух подряд идущих символов в отдельный метод
                //проверка на две идущие символа операции подряд
                String exp = getEXP();
                int len = exp.length() - 1;
                String last = String.valueOf(exp.charAt(len));
                isSymbol = last.matches(REGEX_IS_SYMBOL_OPERATION);
                if(isSymbol){
                    //обновляем выражение, созданием нового билдера
                    EXP = new StringBuilder(exp.replace(operation, i));
                    operation = i;
                    return;
                }
            }
            calculate();
            if(!i.equals(EQUAL)){
                operation = i;
            }

        }

        if(!i.equals(EQUAL) ){
            EXP.append(i);

        }
    }

    @Override
    public double minus(double operand, double operand1) {
        return operand - operand1;
    }

    @Override
    public double plus(double operand, double operand1) {
        return operand + operand1;
    }

    @Override
    public double divide(double operand, double operand1) {
        //проверка деления на 0
        if(operand1 < 0.0000000000001){
            return -1;
        }
        return operand / operand1;
    }

    @Override
    public double multiply(double operand, double operand1) {
        return operand * operand1;
    }

    @Override
    public String getResult() {
        return result;
    }

    @Override
    public String getEXP() {
        return EXP.toString();
    }
    public void clear(){
        result = "";
        EXP.setLength(0);
    }

    private void calculate(String operation, double operand1, double operand2){
        switch (operation){
            case PLUS:
                result = String.valueOf(plus(operand1,operand2));
                break;
            case MINUS:
                result = String.valueOf(minus(operand1,operand2));
                break;
            case DIVIDE:
                double tmp = divide(operand1, operand2);
                result = tmp == -1 ? DIVIDE_ZERO: String.valueOf(tmp);
                break;
            case MULTIPLY:
                result = String.valueOf(divide(operand1,operand2));
                break;
            case EQUAL:
                calculate(operation, operand1, operand2);
                break;
        }
    }
    private void calculate() {
        String textEXP = EXP.toString();
        //состоит ли строка только из цифр
        if (textEXP.matches(REGEX_IS_NUMBER)) {
            result = textEXP;
        }
        //состоит ли строка только из символов операций
        else if (textEXP.matches(REGEX_IS_SYMBOL_OPERATION)) {
            result = INPUT_NUMBER;
        } else {
            String[] partsEXPArray = textEXP.split("[" + operation + "]");

            if (partsEXPArray.length == 1) {
                result = partsEXPArray[0];
            } else {
                double operand1 = Double.parseDouble(partsEXPArray[0]);
                double operand2 = Double.parseDouble(partsEXPArray[1]);
                //расчитываем результат выражения
                calculate(operation, operand1, operand2);
//              //очищаем ткущее выражение
                EXP.setLength(0);
                //добавляем результат предудщего
                EXP.append(result.equals(DIVIDE_ZERO) ? "" : result);
            }
        }
    }
}

