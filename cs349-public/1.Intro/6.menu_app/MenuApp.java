import java.util.Scanner;

class MenuApp {
    // constants for menu items
    final int MENU_PRINT = 1;
    final int MENU_SOMETHING = 2;
    final int MENU_QUIT = 3;

    // scans console for input
    final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        MenuApp app = new MenuApp();
        app.printMenu();
        app.loop();
    }

    private void printMenu() {
        System.out.println("Menu");
        System.out.println("1. Print instructions");
        System.out.println("2. Do something else");
        System.out.println("3. Exit app");
    }

    private void loop() {
        int s;
        while ((s = scanner.nextInt()) != MENU_QUIT) {
            System.out.println(s + " is a valid selection");
        }
        System.out.println(s + " exits the app");
    }
}