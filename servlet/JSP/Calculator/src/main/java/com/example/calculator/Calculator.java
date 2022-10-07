package com.example.calculator;

public class Calculator {
    public static float calculate(float number1,float number2, char operator){
        switch (operator){
            case '+':
                return number1 + number2;
            case '-':
                return number1 - number2;
            case '*':
                return number1 * number2;
            case '/':
                if(number2 != 0)
                    return number1 / number2;
                else
                    throw new RuntimeException("Can't divide by zero");
            default:
                throw new RuntimeException("Invalid operation");
        }
    }
}
