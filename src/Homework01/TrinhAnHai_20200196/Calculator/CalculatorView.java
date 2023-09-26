package Homework01.TrinhAnHai_20200196.Calculator;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class CalculatorView extends JFrame{

    private final JTextField firstNumber  = new JTextField();
    private final JLabel firstNumberLabel = new JLabel("First Number");
    private final JTextField secondNumber = new JTextField();
    private final JLabel secondNumberLabel = new JLabel("First Number");
    private final JTextField result = new JTextField();

    private final JLabel resultLabel = new JLabel("Result");

    private Map<String, JButton> buttons = new HashMap<String, JButton>() {{
        put("+", new JButton("+"));
        put("-", new JButton("-"));
        put("*", new JButton("*"));
        put("/", new JButton("/"));
        put("%", new JButton("%"));
        put("CLEAR", new JButton("CLEAR"));
    }};

    CalculatorView(){
        // Sets up the Panel to Grid Layout
        JPanel calcPanel = new JPanel();
        calcPanel.setLayout(new GridLayout(6, 2, 20, 15));
        calcPanel.setBorder(new EmptyBorder(20, 20, 20, 20));

        // Add labels and textFields
        calcPanel.add(firstNumberLabel);
        calcPanel.add(firstNumber);

        calcPanel.add(secondNumberLabel);
        calcPanel.add(secondNumber);

        result.setEditable(false);
        calcPanel.add(resultLabel);
        calcPanel.add(result);

        // Add buttons
        for(String op: new String[]{"+", "-", "*", "/", "%", "CLEAR"}){
            calcPanel.add(buttons.get(op));
        }

        // Set up the main frame
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(500, 400);
        this.setTitle("Arithmetics");
        this.setLocationRelativeTo(null);
        this.add(calcPanel);
    }

    public int getFirstNumber(){
        return Integer.parseInt(firstNumber.getText());
    }

    public int getSecondNumber(){
        return Integer.parseInt(secondNumber.getText());
    }

    public void setResult(String solution){
        result.setText(solution);
    }

    public void displayErrorMessage(String errorMessage){
        JOptionPane.showMessageDialog(this, errorMessage);
    }

    public Map<String, JButton> getButtons() {
        return buttons;
    }

    public void clearTextFields() {
        firstNumber.setText("");
        secondNumber.setText("");
        result.setText("");
    }
}