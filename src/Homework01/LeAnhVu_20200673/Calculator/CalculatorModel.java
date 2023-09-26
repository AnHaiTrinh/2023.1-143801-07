package Homework01.LeAnhVu_20200673.Calculator;

public class CalculatorModel {

    // Holds the value of the sum of the numbers
    // entered the view

    private int calculationValue;

    public void addTwoNumbers(int firstNumber, int secondNumber){

        calculationValue = firstNumber + secondNumber;

    }
    public void acceptTwoNumbers(int firstNumber, int secondNumber){

        calculationValue = firstNumber * secondNumber;

    }
    public void divideTwoNumbers(int firstNumber, int secondNumber){
        calculationValue =  firstNumber /secondNumber;
    }
    public void subtractTwoNumbers(int firstNumber, int secondNumber){

        calculationValue = firstNumber - secondNumber;

    }
    public void rTwoNumbers(int firstNumber, int secondNumber){

        calculationValue = firstNumber % secondNumber;

    }
    public void clearTwoNumbers(){

        calculationValue = 0;

    }
    
    public int getCalculationValue(){

        return calculationValue;

    }

}
