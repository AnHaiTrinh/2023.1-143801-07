package Calculator;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CalculatorController {
    private CalculatorView view;
    private CalculatorModel model;

    public CalculatorController(CalculatorView view, CalculatorModel model) {
        this.view = view;
        this.model = model;

        view.addCalculationListener(new CalculationListener());
    }

    class CalculationListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            try {
                double num1 = view.getNum1();
                double num2 = view.getNum2();
                String operator = e.getActionCommand(); // Get the operator from the button text

                model.performOperation(num1, num2, operator);
                view.setResult(model.getResult());
            } catch (NumberFormatException ex) {
                view.displayErrorMessage("Invalid input. Please enter valid numbers.");
            }
        }
    }
}
