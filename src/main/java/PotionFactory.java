
import java.util.Random;

public class PotionFactory {

    private int potionsCreated;

    public void createPotions(Board gameBoard) {
        Random rand = new Random();

        while (potionsCreated < 10) {
            Potion potion = new Potion();
            int potionPick = rand.nextInt(5);

            if (potionPick == 0) { // Restore Health
                potion.setPotionType("Health");
                potion.setModifier(10);
            } else if (potionPick == 1) { // Booster Attack
                potion.setPotionType("Attack");
                potion.setModifier(5);
            } else if (potionPick == 2) { // Boost Defense
                potion.setPotionType("Defense");
                potion.setModifier(5);
            } else if (potionPick == 3) { // Poison Health
                potion.setPotionType("Health");
                potion.setModifier(-10);
            } else if (potionPick == 4) { // Decrease Attack
                potion.setPotionType("Attack");
                potion.setModifier(-5);
            } else if (potionPick == 5) { // Decrease Defense
                potion.setPotionType("Defense");
                potion.setModifier(-5);
            }

            gameBoard.addPotion(potion);
            potion.spawnPotion(gameBoard);
            this.potionsCreated++;
        }
    }
}
