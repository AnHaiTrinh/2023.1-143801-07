package Homework01.All;// The Model performs all the calculations needed
<<<<<<< HEAD
// and that is it. It doesn't know the View
=======
// and that is it. It doesn't know the View 
>>>>>>> 30bffc71ce5277570a0819b62c77f948f203878f
// exists

public class CalculatorModel {

    // Holds the value of the sum of the numbers
    // entered in the view

    private int calculationValue;

    public void addTwoNumbers(int firstNumber, int secondNumber){

        calculationValue = firstNumber + secondNumber;

    }

    public int getCalculationValue(){

        return calculationValue;

    }

}