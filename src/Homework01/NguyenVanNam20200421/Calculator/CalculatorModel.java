package Homework01.NguyenVanNam20200421.Calculator;

public class CalculatorModel {
    private double result;
    public void performOperation(double num1, double num2, String operator) {
        switch (operator) {
            case "+":
                result = num1 + num2;
                break;
            case "-":
                result = num1 - num2;
                break;
            case "*":
                result = num1 * num2;
                break;
            case "/":
                if (num2 != 0) {
                    result = num1 / num2;
                } else {
                    result = Double.NaN;
                }
                break;
        }
    }
    public double getResult() {
        return result;
    }
}

