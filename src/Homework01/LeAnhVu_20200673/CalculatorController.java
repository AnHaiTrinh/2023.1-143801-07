package Homework01.LeAnhVu_20200673;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CalculatorController {

    private CalculatorView theView;
    private CalculatorModel theModel;

    public CalculatorController(CalculatorView theView, CalculatorModel theModel) {
        this.theView = theView;
        this.theModel = theModel;


        this.theView.addCalculateListener(new AddListener());
        this.theView.acceptCalculateListener(new AcceptListener());
        this.theView.subtractCalculateListener(new SubtractListener());
        this.theView.divideCalculateListener(new DivideListener());
    }

    class AddListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {

            float firstNumber, secondNumber ;


            try{

                firstNumber = theView.getFirstNumber();
                secondNumber = theView.getSecondNumber();

                theModel.addTwoNumbers(firstNumber, secondNumber);

                theView.setCalcSolution(theModel.getCalculationValue());

            }

            catch(NumberFormatException ex){

                theView.displayErrorMessage("You Need to Enter 2 floategers");

            }

        }

    }
    class DivideListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {

            float firstNumber, secondNumber ;

            try{

                firstNumber = theView.getFirstNumber();
                secondNumber = theView.getSecondNumber();
                if ( secondNumber ==0 ){
                    theView.displayErrorMessage("Divide by zero!!");
                }else {
                    theModel.divideTwoNumbers(firstNumber, secondNumber);

                    theView.setCalcSolution(theModel.getCalculationValue());
                }
            }

            catch(NumberFormatException ex){
                theView.displayErrorMessage("You Need to Enter 2 floategers");

            }

        }

    }
    class SubtractListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {

            float firstNumber, secondNumber ;

            try{

                firstNumber = theView.getFirstNumber();
                secondNumber = theView.getSecondNumber();

                theModel.subtractTwoNumbers(firstNumber, secondNumber);

                theView.setCalcSolution(theModel.getCalculationValue());

            }

            catch(NumberFormatException ex){
                theView.displayErrorMessage("You Need to Enter 2 floategers");

            }

        }

    }
    class AcceptListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {

            float firstNumber, secondNumber;


            try{

                firstNumber = theView.getFirstNumber();
                secondNumber = theView.getSecondNumber();

                theModel.acceptTwoNumbers(firstNumber, secondNumber);

                theView.setCalcSolution(theModel.getCalculationValue());

            }

            catch(NumberFormatException ex){
                theView.displayErrorMessage("You Need to Enter 2 floategers");

            }

        }

    }

}
