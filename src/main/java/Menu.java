
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
                System.out.println("Invalid option entered!");
            }
        }
    }

    public void displayHelp() {
        System.out.println("no,so,ea,we,ne,nw,se,sw: moves the player character one block in the appropriate cardinal direction");
        System.out.println("u <direction>: uses the potion indicated by the direction (e.g. no, so, ea)");
        System.out.println("u <direction> store: stores the potion indicated by the direction (e.g. no, so, ea) in the player's inventory");
        System.out.println("u potion: uses a potion stored in the player's inventory");
        System.out.println("a <direction>: attacks the enemy in the specified direction, if the monster is in the immediately specified block");
        System.out.println("(e.g. must be one block north of the @");
        System.out.println("map: displays all discovered tiles in the dungeon");
        System.out.println("help: displays all possible commands");
        System.out.println("reset: restarts the game");
        System.out.println("quit: exits the game");
    }

}
