package Homework01.LeAnhVu_20200673;

public class CalculatorModel {

    // Holds the value of the sum of the numbers
    // entered the view

    private float calculationValue;

    public void addTwoNumbers(float firstNumber, float secondNumber){

        calculationValue = firstNumber + secondNumber;

    }
    public void acceptTwoNumbers(float firstNumber, float secondNumber){

        calculationValue = firstNumber * secondNumber;

    }
    public void divideTwoNumbers(float firstNumber, float secondNumber){
        calculationValue =  firstNumber /secondNumber;
    }
    public void subtractTwoNumbers(float firstNumber, float secondNumber){

        calculationValue = firstNumber - secondNumber;

    }
    public float getCalculationValue(){

        return calculationValue;

    }

}
