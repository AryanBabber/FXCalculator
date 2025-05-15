package com.example.calculator;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

public class Controller implements Initializable {

    @FXML
    private TextField display;

    private final CalculatorModel model = new CalculatorModel();
    private boolean startNewInput = true;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        display.setText("0");
    }

    @FXML
    private void handleNumberButton(ActionEvent event) {
        String digit = ((Button) event.getSource()).getText();
        handleDigitInput(digit);
    }

    @FXML
    private void handleOperationButton(ActionEvent event) {
        String operation = ((Button) event.getSource()).getText();
        handleOperationInput(operation);
    }

    @FXML
    private void handleClearButton() {
        clearCalculator();
    }

    @FXML
    private void handleEqualsButton() {
        calculateResult();
    }

    @FXML
    private void handleDecimalButton() {
        handleDecimalInput();
    }

    @FXML
    private void handleDeleteButton() {
        handleDelete();
    }

    public void handleKeyPress(KeyEvent event) {
        switch (event.getCode()) {
            case DIGIT0, NUMPAD0 -> handleDigitInput("0");
            case DIGIT1, NUMPAD1 -> handleDigitInput("1");
            case DIGIT2, NUMPAD2 -> handleDigitInput("2");
            case DIGIT3, NUMPAD3 -> handleDigitInput("3");
            case DIGIT4, NUMPAD4 -> handleDigitInput("4");
            case DIGIT5, NUMPAD5 -> handleDigitInput("5");
            case DIGIT6, NUMPAD6 -> handleDigitInput("6");
            case DIGIT7, NUMPAD7 -> handleDigitInput("7");
            case DIGIT8, NUMPAD8 -> handleDigitInput("8");
            case DIGIT9, NUMPAD9 -> handleDigitInput("9");
            case PERIOD, DECIMAL -> handleDecimalInput();
            case ADD, PLUS -> handleOperationInput("+");
            case SUBTRACT, MINUS -> handleOperationInput("-");
            case MULTIPLY -> handleOperationInput("×");
            case DIVIDE -> handleOperationInput("÷");
            case EQUALS, ENTER -> calculateResult();
            case ESCAPE -> clearCalculator();
            case BACK_SPACE -> handleDelete();
            default -> { /* Ignore other keys */ }
        }
    }

    private void handleDigitInput(String digit) {
        if (startNewInput) {
            display.setText(digit);
            startNewInput = false;
        } else {
            if (display.getText().equals("0") && digit.equals("0")) {
                return;
            }
            if (display.getText().equals("0") && !digit.equals("0")) {
                display.setText(digit);
            } else {
                display.setText(display.getText() + digit);
            }
        }
    }

    private void handleOperationInput(String operation) {
        if (!startNewInput) {
            model.setNumber(Double.parseDouble(display.getText()));
        }

        model.setOperation(operation);
        startNewInput = true;
    }

    private void calculateResult() {
        if (!startNewInput) {
            double result = model.calculate(Double.parseDouble(display.getText()));

            String resultText = formatResult(result);
            display.setText(resultText);

            startNewInput = true;
        }
    }

    private void handleDecimalInput() {
        if (startNewInput) {
            display.setText("0.");
            startNewInput = false;
        } else if (!display.getText().contains(".")) {
            display.setText(display.getText() + ".");
        }
    }

    private void clearCalculator() {
        display.setText("0");
        model.clear();
        startNewInput = true;
    }

    private void handleDelete() {
        String currentText = display.getText();

        if (startNewInput) {
            return;
        }

        if (currentText.length() > 1) {
            display.setText(currentText.substring(0, currentText.length() - 1));
        } else {
            display.setText("0");
            startNewInput = true;
        }
    }

    private String formatResult(double result) {
        if (result == (long) result) {
            return String.format("%d", (long) result);
        } else {
            return String.valueOf(result);
        }
    }
}