package Homework01.NguyenVanNam20200421.Calculator;

public class CalculatorMain {
    public static void main(String[] args) {
        CalculatorModel model = new CalculatorModel();
        CalculatorView view = new CalculatorView();
        CalculatorController controller = new CalculatorController(view, model);

        view.setVisible(true);
    }
}
