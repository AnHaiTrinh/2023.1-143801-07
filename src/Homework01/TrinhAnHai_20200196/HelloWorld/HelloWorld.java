package Homework01.TrinhAnHai_20200196.HelloWorld;
import javax.swing.JOptionPane;
public class HelloWorld {
    public static void main(String[] args) {
        // ask the user to input their name
        String name = JOptionPane.showInputDialog("What is your name?");
        // display the hello message
        JOptionPane.showMessageDialog(null, "Hello " + name + "!");
    }
}
