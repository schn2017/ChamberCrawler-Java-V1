
import java.nio.file.Paths;
import java.util.Scanner;

public class Victory {

    public void displayVictory(Player player) {
        int rowCount = 0;
        try ( Scanner scanner = new Scanner(Paths.get("victoryScreen.txt"))) {
            while (scanner.hasNextLine()) {
                String row = scanner.nextLine();
                System.out.println(row);
            }

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        System.out.println("Your score was " + player.getPlayerGold());
        System.exit(0);
    }
}
