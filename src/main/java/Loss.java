
import java.nio.file.Paths;
import java.util.Scanner;

public class Loss {

    public void displayLoss(int playerGold) {
        int rowCount = 0;
        try ( Scanner scanner = new Scanner(Paths.get("lossScreen.txt"))) {
            while (scanner.hasNextLine()) {
                String row = scanner.nextLine();
                System.out.println(row);
            }

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        System.out.println("Your score was " + playerGold);
        System.exit(0);
    }
}
