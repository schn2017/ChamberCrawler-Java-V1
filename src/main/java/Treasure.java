
import java.util.ArrayList;

public class Treasure {

    private int treasurePositionX;
    private int treasurePositionY;
    private char treasureCharacter;
    private double treasureValue;
    private boolean isValidDirection;
    private boolean isUsed;

    public Treasure() {
        this.isUsed = false;
        this.treasureCharacter = 'G';
    }

    public int getTreasurePositionX() {
        return this.treasurePositionX;
    }

    public int getTreasurePositionY() {
        return this.treasurePositionY;
    }

    public boolean getIsUsed() {
        return this.isUsed;
    }

    public boolean getValidDirection() {
        return this.isValidDirection;
    }

    public void setValidDirection(boolean status) {
         this.isValidDirection = status;
    }

    public void setTreasureValue(double treasureValue) {
        this.treasureValue = treasureValue;
    }

    public void update(Board gameBoard, Player player) {
        char tileASCII = gameBoard.getBoardTile(this.treasurePositionY, this.treasurePositionX);

        if (tileASCII == '@') {
            addTreasureToPlayer(player);
            this.isUsed = true;
        }
    }

    public void spawnTreasure(Board gameBoard) {
        int tileElement = gameBoard.getRandomSpawnTileElement();
        ArrayList<Tile> spawnableTiles = gameBoard.getSpawnableTiles();

        this.treasurePositionY = spawnableTiles.get(tileElement).getTilePositionY();
        this.treasurePositionX = spawnableTiles.get(tileElement).getTilePositionX();
        
        gameBoard.setBoardTile(this.treasurePositionY, this.treasurePositionX, this.treasureCharacter);
        gameBoard.setBoardTileOccupied(this.treasurePositionY, this.treasurePositionX, false);
        gameBoard.removeSpawnableTile(tileElement);
    }

    public void addTreasureToPlayer(Player player) {
        player.setPlayerGold(this.treasureValue);
    }

}
