
import java.util.Random;

public class PotionFactory {

    private int potionsCreated;

    public void potionsTreasures(Board gameBoard) {
        Random rand = new Random();

        while (potionsCreated < 10) {
            Potion potion = new Potion();
            int potionPick = rand.nextInt(6);
            
            if (potionPick == 1){ // Restore Health
                
            } else if (potionPick == 2){ // Booster Attack
                
            } else if (potionPick == 3){ // Boost Defense
                
            } else if (potionPick == 4){ // Poision Health
                
            }else if (potionPick == 5){ // Decrease Attack
                
            }else if (potionPick == 6){ // Decrease Defense
                
            }



            gameBoard.addPotion(potion);
            potion.spawnPotion(gameBoard);
            this.potionsCreated++;
        }
    }
}

