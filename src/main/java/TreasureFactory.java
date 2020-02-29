
import java.util.Random;

public class TreasureFactory {

    private int treasuresCreated;

    public void createTreasures(Board gameBoard) {
        Random rand = new Random();
        boolean dragonCreated = false;

        while (treasuresCreated < 10) {
            Treasure treasure = new Treasure();
            int treasurePick = rand.nextInt(8);

            if (treasurePick <= 4) {// Normal treasure pile 
                treasure.setTreasureValue(1);
                treasure.setValidDirection(true);

            } else if (treasurePick == 5 || treasurePick == 6) { // Small horde of treasure
                treasure.setTreasureValue(2);
                treasure.setValidDirection(true);
            } else if (treasurePick == 7) { // Dragon treasure horde
                treasure.setTreasureValue(6);
                treasure.setValidDirection(false);
                dragonCreated = true;
            }

            gameBoard.addTreasure(treasure);
            treasure.spawnTreasure(gameBoard);
            if (dragonCreated == true) {
                Dragon dragon = new Dragon(gameBoard, treasure);
                dragon.findSpawnLocation(gameBoard);
                dragon.spawnMonster(gameBoard, dragon.getMonsterPositionY(), dragon.getMonsterPositionX());
                gameBoard.addMonster(dragon);

                dragonCreated = false;
            }

            this.treasuresCreated++;
        }
    }
}
