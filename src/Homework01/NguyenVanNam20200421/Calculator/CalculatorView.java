package Homework01.NguyenVanNam20200421.Calculator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class CalculatorView extends JFrame {
    private JTextField numField1 = new JTextField(10);
    private JTextField numField2 = new JTextField(10);
    private JTextField resultField = new JTextField(10);
    private JButton addButton = new JButton("+");
    private JButton subtractButton = new JButton("-");
    private JButton multiplyButton = new JButton("*");
    private JButton divideButton = new JButton("/");

    public CalculatorView() {
        setTitle("Simple Calculator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //setLayout(new FlowLayout());
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(2, 2, 10, 10));

        JPanel textPanel = new JPanel();
        textPanel.setLayout(new GridLayout(3, 1)); // 3 rows, 1 column

        textPanel.add(numField1);
        add(new JLabel("Operator"));
        textPanel.add(numField2);
        buttonPanel.add(addButton);
        buttonPanel.add(subtractButton);
        buttonPanel.add(multiplyButton);
        buttonPanel.add(divideButton);
        add(new JLabel("Result"));
        textPanel.add(resultField);

        setLayout(new BorderLayout());
        add(textPanel, BorderLayout.NORTH); // Add the text panel to the top
        add(buttonPanel, BorderLayout.WEST); // Add the button panel to the left
        add(new JLabel("Result"), BorderLayout.CENTER);
        add(resultField, BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(null); // Center the frame
    }

    public double getNum1() {
        return Double.parseDouble(numField1.getText());
    }

    public double getNum2() {
        return Double.parseDouble(numField2.getText());
    }

    public void setResult(double result) {
        resultField.setText(String.valueOf(result));
    }

    public void addCalculationListener(ActionListener listener) {
        addButton.addActionListener(listener);
        subtractButton.addActionListener(listener);
        multiplyButton.addActionListener(listener);
        divideButton.addActionListener(listener);
    }

    public void displayErrorMessage(String message) {
        JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
    }
}
