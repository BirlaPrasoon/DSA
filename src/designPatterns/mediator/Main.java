package designPatterns.mediator;

public class Main {

    public static void main(String[] args) {
        Dialog dialog = new Dialog();
        dialog.enterUsername("Geekific");
        dialog.enterPassword("LikeAndSubscribe");
        dialog.simulateLoginClicked();
        dialog.simulateForgotPassClicked();
    }
}
