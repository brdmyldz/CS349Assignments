import java.util.Scanner;

class MenuApp {
    public static void main(String[] args) {
        MenuApp app = new MenuApp();
        printMenu();
        app.loop();
    }

    final int MENU_QUIT = 3;
    final Scanner scanner = new Scanner(System.in);

    private static void printMenu() {
        System.out.println("Menu");
        System.out.println("1. Print instructions");
        System.out.println("2. Do something else");
        System.out.println("3. Exit app");
    }

    private void loop() {
        int s;
        while ((s = getSelection()) != MENU_QUIT) {
            System.out.println(s + " is a valid selection");
        }
        System.out.println(s + " exits the app");
    }

    private int getSelection() {
        return scanner.nextInt();
    }
}