package Homework01.TrinhAnHai_20200196.Calculator;
public class CalculatorModel {

    // Holds the value of the sum of the numbers
    // entered in the view

    private int calculationValue;

    public void computeCalculationValue(int firstNumber, int secondNumber, String operator){
        switch (operator) {
            case "+":
                calculationValue = firstNumber + secondNumber;
                break;
            case "-":
                calculationValue = firstNumber - secondNumber;
                break;
            case "*":
                calculationValue = firstNumber * secondNumber;
                break;
            case "/":
                calculationValue = firstNumber / secondNumber;
                break;
            case "%":
                calculationValue = firstNumber % secondNumber;
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + operator);
        }
    }
    public int getCalculationValue(){

        return calculationValue;

    }

}