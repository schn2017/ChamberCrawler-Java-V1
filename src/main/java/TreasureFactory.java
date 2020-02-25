
import java.util.Random;

public class TreasureFactory {

    private int treasuresCreated;

    public void createTreasures(Board gameBoard) {
        Random rand = new Random();

        while (treasuresCreated < 10) {
            Treasure treasure = new Treasure();
            int treasurePick = rand.nextInt(8);

            if (treasurePick <= 5) {// Normal treasure pile 
                treasure.setTreasureValue(1);
                treasure.setValidDirection(true);

            } else if (treasurePick <= 8) { // Small horde of treasure
                treasure.setTreasureValue(2);
                treasure.setValidDirection(true);
            } else { // Dragon treasure horde
                treasure.setTreasureValue(6);
                treasure.setValidDirection(false);
            }

            gameBoard.addTreasure(treasure);
            treasure.spawnTreasure(gameBoard);
            this.treasuresCreated++;
        }
    }
}
