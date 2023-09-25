package Homework01.NguyenKimHung_20200260;
public class Main {

    public static void main(String[] args) {
        Model model = new Model();
        View view = new View();
        Controller controller = new Controller(model, view);
        view.setController(controller);
        controller.start();
        controller.actionListener();
    }

}