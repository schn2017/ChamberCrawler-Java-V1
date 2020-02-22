public class Treasure {

    private int treasurePositionX;
    private int treasurePositionY;
    private char treasureCharacter;
    private int treasureValue;
    private boolean isUsed;

    public Treasure() {
        this.treasureCharacter = 'G';
        this.treasurePositionX = 13;
        this.treasurePositionY = 4;
        this.treasureValue = 1;
        this.isUsed = false;
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

    public void update(Board gameBoard, Player player) {
        char tileASCII = gameBoard.getBoardTile(this.treasurePositionY, this.treasurePositionX);
       
        if (tileASCII == '@') {
            addTreasureToPlayer(player);
            this.isUsed = true;
        }
    }
    

    public void spawnTreasure(Board gameBoard) {
        gameBoard.setBoardTile(this.treasurePositionY, this.treasurePositionX, this.treasureCharacter);
    }

    public void addTreasureToPlayer(Player player) {
        player.setPlayerGold(this.treasureValue);
    }

}
