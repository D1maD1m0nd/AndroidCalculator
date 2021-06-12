package com.example.lesson1.model;

import java.io.Serializable;
import java.util.Locale;

import static com.example.lesson1.model.CalculatorConstants.DIVIDE;
import static com.example.lesson1.model.CalculatorConstants.DIVIDE_ZERO;
import static com.example.lesson1.model.CalculatorConstants.EQUAL;
import static com.example.lesson1.model.CalculatorConstants.INPUT_NUMBER;
import static com.example.lesson1.model.CalculatorConstants.MINUS;
import static com.example.lesson1.model.CalculatorConstants.MULTIPLY;
import static com.example.lesson1.model.CalculatorConstants.PLUS;
import static com.example.lesson1.model.CalculatorConstants.REGEX_IS_NUMBER;
import static com.example.lesson1.model.CalculatorConstants.REGEX_IS_SYMBOL_OPERATION;

public class Calculator implements ICalculator, Serializable {
    private StringBuilder EXP;
    private String result;
    private String operation;


    public Calculator() {
        EXP = new StringBuilder();
    }

    /**
     * Метод добавления значения к выражению, добавляет переданный символ
     * проверяет его число он или операция. далее производит подсчет через метод calc
     *
     * @param symbol - переданный символ
     */
    @Override
    public void add(String symbol) {
        boolean isSymbol = symbol.matches(REGEX_IS_SYMBOL_OPERATION);
        //проверяем наличие на вхде операции
        if (isSymbol) {
            if (EXP.length() == 0) {
                EXP.append("0");
            } else {
                //TODO вынести код проверки двух подряд идущих символов в отдельный метод
                //проверка на две идущие символа операции подряд
                String exp = getEXP();
                int len = exp.length() - 1;
                String last = String.valueOf(exp.charAt(len));
                isSymbol = last.matches(REGEX_IS_SYMBOL_OPERATION);
                if (isSymbol) {
                    //обновляем выражение, созданием нового билдера
                    EXP = new StringBuilder(exp.replace(operation, symbol));
                    operation = symbol;
                    return;
                }
            }
            calculate();
            if (!symbol.equals(EQUAL)) {
                operation = symbol;
            }

        }

        if (!symbol.equals(EQUAL)) {
            EXP.append(symbol);

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
        if (operand1 < 0.0000000000001) {
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

    public void clear() {
        result = "";
        EXP.setLength(0);
    }

    /**
     * Класс производит расчет значений двух переданных операндов, по операции
     *
     * @param operation - операция, которую необходимо произвести, сравнивается с константами
     * @param operand1  - первый операнд
     * @param operand2  - второй операнд
     */
    private void calculate(String operation, double operand1, double operand2) {
        switch (operation) {
            case PLUS:
                result = String.format(Locale.getDefault(), "%.2f", plus(operand1, operand2));
                break;
            case MINUS:
                result = String.format(Locale.getDefault(), "%.2f", minus(operand1, operand2));
                break;
            case DIVIDE:
                double tmp = divide(operand1, operand2);
                result = tmp == -1 ? DIVIDE_ZERO : String.format(Locale.getDefault(), "%.2f", tmp);
                break;
            case MULTIPLY:
                result = String.format(Locale.getDefault(), "%.2f", multiply(operand1, operand2));
                break;
            case EQUAL:
                calculate(operation, operand1, operand2);
                break;
        }
    }

    /**
     * Метод расчета
     * В данном методе назодятся основные проверки
     * Пользовательского ввода, далее производится расчет по введенным данным
     */
    private void calculate() {
        String textEXP = EXP.toString();

        boolean isSymbol = textEXP.matches(REGEX_IS_SYMBOL_OPERATION);
        boolean isNumber = textEXP.matches(REGEX_IS_NUMBER);
        //состоит ли строка только из цифр
        if (isNumber) {
            result = textEXP;
        }
        //состоит ли строка только из символов операций
        else if (isSymbol) {
            result = INPUT_NUMBER;
        } else {
            String[] partsEXPArray = textEXP.split("[" + operation + "]");

            if (partsEXPArray.length == 1) {
                result = partsEXPArray[0];
            } else {
                double operand1 = Double.parseDouble(partsEXPArray[0].replace(',', '.'));
                double operand2 = Double.parseDouble(partsEXPArray[1].replace(',', '.'));
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

