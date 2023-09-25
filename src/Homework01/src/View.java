import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class View {
    private Controller controller;
    private JLabel result;
    private JLabel firstNumberLabel;


    private JLabel secondNumberLabel;
    private JLabel resultLabel;

    private JButton addButton;
    private JButton subtractButton;
    private JButton multipleButton;
    private JButton divideButton;
    private JButton moduleButton;
    private JButton clearButton;
    private JTextField txtFirstNumber;
    private JTextField txtSecondNumber;

    public void setController(Controller controller){
        this.controller = controller;
        initView();
    }
    public void show(){
        JFrame frame = new JFrame("calculator mvc");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocation(500, 300);

        JPanel calculatorPanel = new JPanel();
        GridLayout gridLayout = new GridLayout(6, 2, 10, 15);
        calculatorPanel.setLayout(gridLayout);
        calculatorPanel.setBorder(new EmptyBorder(20, 20, 20, 20));

        calculatorPanel.add(firstNumberLabel);
        calculatorPanel.add(txtFirstNumber);

        calculatorPanel.add(secondNumberLabel);
        calculatorPanel.add(txtSecondNumber);

        calculatorPanel.add(resultLabel);
        calculatorPanel.add(result);

        calculatorPanel.add(addButton);
        calculatorPanel.add(subtractButton);
        calculatorPanel.add(multipleButton);
        calculatorPanel.add(divideButton);
        calculatorPanel.add(moduleButton);
        calculatorPanel.add(clearButton);

        frame.add(calculatorPanel);
        frame.pack();
        frame.setVisible(true);
    }
    public void initView(){
        firstNumberLabel = new JLabel("First number:");
        secondNumberLabel = new JLabel("Second number:");
        resultLabel = new JLabel("Result:");
        txtFirstNumber = new JTextField(20);
        txtSecondNumber = new JTextField(20);
        result = new JLabel("0");
        addButton = new JButton("+");
        subtractButton = new JButton("-");
        multipleButton = new JButton("x");
        divideButton = new JButton("/");
        moduleButton = new JButton("%");
        clearButton = new JButton("C");
    }
    public void actionListener(){
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String s1 = txtFirstNumber.getText();
                String s2 = txtSecondNumber.getText();
                var res = controller.handleUserInput(s1, "+", s2);
                result.setText(String.valueOf(res));
            }
        });

        subtractButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String s1 = txtFirstNumber.getText();
                String s2 = txtSecondNumber.getText();
                var res = controller.handleUserInput(s1, "-", s2);
                result.setText(String.valueOf(res));
            }
        });

        multipleButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String s1 = txtFirstNumber.getText();
                String s2 = txtSecondNumber.getText();
                var res = controller.handleUserInput(s1, "x", s2);
                result.setText(String.valueOf(res));
            }
        });

        divideButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String s1 = txtFirstNumber.getText();
                String s2 = txtSecondNumber.getText();
                var res = controller.handleUserInput(s1, "/", s2);
                result.setText(String.valueOf(res));
            }
        });

        moduleButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String s1 = txtFirstNumber.getText();
                String s2 = txtSecondNumber.getText();
                var res = controller.handleUserInput(s1, "%", s2);
                result.setText(String.valueOf(res));
            }
        });

        clearButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                result.setText(String.valueOf(0));
                txtFirstNumber.setText("");
                txtSecondNumber.setText("");
            }
        });
    }
}