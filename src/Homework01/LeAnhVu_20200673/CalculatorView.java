package Homework01.LeAnhVu_20200673;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class CalculatorView extends JFrame{

    private JTextField firstNumber  = new JTextField(10);
    private JLabel firstNumberLabel = new JLabel("First Number");
    //private JLabel additionLabel = new JLabel("+");
    private JTextField secondNumber = new JTextField(10);
    private JButton addButton = new JButton("+");
    private JButton subtractButton = new JButton("-");
    private JButton acceptButton = new JButton("*");
    private JButton devideButton = new JButton("/");
    private JLabel secondNumberLabel = new JLabel("Second Number");
    private JButton calculateButton = new JButton("Calculate");
    private JTextField calcSolution = new JTextField(10);
    //private JButton buttonResult = new JButton("=");
    private JLabel jLabelEmpty = new JLabel("Result");
    private JLabel jLabelResult = new JLabel("");
     private  JButton rButton = new JButton("%");
     private JButton clearButton = new JButton("CLEAR");
    CalculatorView(){

        // Sets up the view and adds the components

        JPanel calcPanel = new JPanel();
        GridLayout  gridLayout = new GridLayout(6,2,20,25);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(700, 400);
        calcPanel.setLayout(gridLayout);
        calcPanel.add(firstNumberLabel);
        calcPanel.add(firstNumber);
        calcPanel.add(secondNumberLabel);
        calcPanel.add(secondNumber);
        calcPanel.add(jLabelEmpty);
        calcPanel.add(jLabelResult);
        calcPanel.add(addButton);
        calcPanel.add(subtractButton);
        calcPanel.add(acceptButton);
        calcPanel.add(devideButton);
        calcPanel.add(rButton);
        calcPanel.add(clearButton);
        //calcPanel.add(buttonResult);
        this.add(calcPanel);
        // End of setting up the components --------

    }

    public int getFirstNumber(){

        return Integer.parseInt(firstNumber.getText());

    }

    public int getSecondNumber(){

        return Integer.parseInt(secondNumber.getText());

    }

//    public float getCalcSolution(){
//
//        return Float.parseFloat(calcSolution.getText());
//
//    }

    public void setCalcSolution(float solution){

        jLabelResult.setText(Double.toString(solution));

    }
    // If the calculateButton is clicked execute a method
    // in the Controller named actionPerformed

    void acceptCalculateListener(ActionListener listenForCalcButton){

        acceptButton.addActionListener(listenForCalcButton);

    }
    void subtractCalculateListener(ActionListener listenForCalcButton){

        subtractButton.addActionListener(listenForCalcButton);

    }
    void divideCalculateListener(ActionListener listenForCalcButton){

        devideButton.addActionListener(listenForCalcButton);

    }
    void addCalculateListener(ActionListener listenForCalcButton){

        addButton.addActionListener(listenForCalcButton);

    }

    void rCalculateListener( ActionListener listenForCalcButton){
        rButton.addActionListener(listenForCalcButton);
    }

    void clearCalculateListener( ActionListener listenForCalcButton){
        clearButton.addActionListener(listenForCalcButton);
    }

    // Open a popup that contains the error message passed

    void displayErrorMessage(String errorMessage){

        JOptionPane.showMessageDialog(this, errorMessage);

    }


    public void getClearSolution() {
        firstNumber.setText("");
        secondNumber.setText("");
        jLabelResult.setText("");
    }
}
