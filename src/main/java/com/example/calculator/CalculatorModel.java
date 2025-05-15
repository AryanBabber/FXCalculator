package com.example.calculator;

public class CalculatorModel {
    private double number1;
    private String operation;

    public CalculatorModel() {
        clear();
    }

    public void setNumber(double number) {
        if (operation.isEmpty()) {
            number1 = number;
        } else {
            number1 = calculate(number);
        }
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public double calculate(double number2) {
        return switch (operation) {
            case "+" -> number1 + number2;
            case "-" -> number1 - number2;
            case "×" -> number1 * number2;
            case "÷" -> number1 / number2;
            default -> number2;
        };
    }

    public void clear() {
        number1 = 0;
        operation = "";
    }
}
