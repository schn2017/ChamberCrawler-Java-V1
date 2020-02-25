
import java.nio.file.Paths;
import java.util.Scanner;

public class Menu {

    private String optionSelected;

    public Menu() {
        this.optionSelected = "";
    }

    public void displayMenu() {
        int rowCount = 0;
        try ( Scanner scanner = new Scanner(Paths.get("menuLayout.txt"))) {
            while (scanner.hasNextLine()) {
                String row = scanner.nextLine();
                System.out.println(row);
            }

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void displaySelected() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("Enter a valid integer option: ");

            try {
                int selectedOption = Integer.valueOf(scanner.nextLine());
                if (selectedOption == 1) {
                    break;
                } else if (selectedOption == 2) {
                    displayHelp();
                } else if (selectedOption == 3) {
                    System.exit(0);
                } else {
                    System.out.print("Please enter a valid integer as an option.");
                }
            } catch (Exception e) {
                System.out.println("Invalid option!");
            }
        }

    }

    public void displayHelp() {
        System.out.println("Display Help: To do");
    }

}
