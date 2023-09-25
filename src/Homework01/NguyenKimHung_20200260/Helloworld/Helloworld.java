package Homework01.NguyenKimHung_20200260.Helloworld;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class Helloworld {

    public static void main(String[] args) {
        JFrame frame = new JFrame("Helloworld app");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocation(500, 300);

        JPanel helloworldPanel = new JPanel();
        JLabel lblUsername = new JLabel("Please enter your name:");
        JTextField txtUsername = new JTextField(20);
        JButton btnEnter = new JButton("Enter");
        GridLayout gridLayout = new GridLayout(3, 1, 20, 25);
        helloworldPanel.setLayout(gridLayout);
        helloworldPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        btnEnter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "hello to "+txtUsername.getText());
            }
        });

        helloworldPanel.add(lblUsername);
        helloworldPanel.add(txtUsername);
        helloworldPanel.add(btnEnter);
        frame.add(helloworldPanel);
        frame.pack();
        frame.setVisible(true);
    }

}
