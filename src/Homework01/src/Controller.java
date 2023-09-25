import javax.swing.*;

public class Controller {

    Model model;
    View view;

    public Controller(Model model, View view) {
        this.model = model;
        this.view = view;
    }

    public int handleUserInput(String s1, String op, String s2) {
        int res = 0;
        try {
            int i1 = Integer.parseInt(s1);
            int i2 = Integer.parseInt(s2);
            switch (op){
                case "+":
                    res = i1 + i2;
                    break;
                case "-":
                    res = i1 - i2;
                    break;
                case "x":
                    res = i1 * i2;
                    break;
                case "/":
                    res = i1 / i2;
                    break;
                case "%":
                    res = i1 % i2;
                    break;
                default:
                    throw new RuntimeException("impossible operator");
            }
        }
        catch (NumberFormatException ex){
            JOptionPane.showMessageDialog( null , "Input is not a valid integer.");
        }
        catch (ArithmeticException ex){
            if (ex.getMessage().contains("/ by zero")){
                JOptionPane.showMessageDialog( null , "Division by zero does not exist");
            }
        }
        return res;
    }

    public void start() {
        this.view.show();
    }

    public void actionListener(){
        this.view.actionListener();
    }
}