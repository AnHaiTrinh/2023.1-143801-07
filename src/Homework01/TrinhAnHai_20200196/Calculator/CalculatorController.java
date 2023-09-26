package Homework01.TrinhAnHai_20200196.Calculator;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

public class CalculatorController {

    private CalculatorView theView;
    private CalculatorModel theModel;

    public CalculatorController(CalculatorView theView, CalculatorModel theModel) {
        this.theView = theView;
        this.theModel = theModel;

        for(Map.Entry<String, JButton> entry: this.theView.getButtons().entrySet()){
            String operation = entry.getKey();
            JButton button = entry.getValue();
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int firstNumber, secondNumber = 0;
                    try {
                        firstNumber = theView.getFirstNumber();
                        secondNumber = theView.getSecondNumber();
                        if (operation.equals("CLEAR")) {
                            theView.clearTextFields();
                            return;
                        }
                        theModel.computeCalculationValue(firstNumber, secondNumber, operation);
                        theView.setResult(Integer.toString(theModel.getCalculationValue()));

                    } catch (NumberFormatException numberFormatException) {
                        numberFormatException.printStackTrace();
                        theView.displayErrorMessage("You Need to Enter 2 Integers");
                    } catch (IllegalStateException illegalStateException) {
                        illegalStateException.printStackTrace();
                        theView.displayErrorMessage(illegalStateException.getMessage());
                    }
                }
            });
        }
    }
}