
import java.util.ArrayList;

public class Potion {

    private int potionPositionX;
    private int potionPositionY;
    private char potionCharacter;
    private String potionType;
    private int potionModifier;
    private boolean isUsed;

    public Potion() {
        this.potionCharacter = 'P';
        this.isUsed = false;
    }

    public void spawnPotion(Board gameBoard) {
        int tileElement = gameBoard.getRandomSpawnTileElement();
        ArrayList<Tile> spawnableTiles = gameBoard.getSpawnableTiles();

        this.potionPositionY = spawnableTiles.get(tileElement).getTilePositionY();
        this.potionPositionX = spawnableTiles.get(tileElement).getTilePositionX();

        gameBoard.setBoardTile(this.potionPositionY, this.potionPositionX, this.potionCharacter);
        gameBoard.setBoardTileOccupied(this.potionPositionY, this.potionPositionX, false);
        gameBoard.removeSpawnableTile(tileElement);
    }

    public boolean getIsUsed() {
        return this.isUsed;
    }

    public void setIsUsed(boolean status) {
        this.isUsed = status;
    }

    public String getPotionType() {
        return this.potionType;
    }

    public void applyPotionEffect(Player player) {
        if (this.potionType.equals("Health")) {
            player.setPlayerHealth(this.potionModifier);
        } else if (this.potionType.equals("Attack")) {
            player.setPlayerAttackPower(this.potionModifier);
        } else if (this.potionType.equals("Defense")) {
            player.setPlayerDefensePower(this.potionModifier);
        }
    }

    public void setPotionType(String potionType) {
        this.potionType = potionType;
    }

    public void setModifier(int potionModifier) {
        this.potionModifier = potionModifier;
    }
    
    public int getModifier(){
        return this.potionModifier;
    }

    public int getPotionPositionX() {
        return this.potionPositionX;
    }

    public int getPotionPositionY() {
        return this.potionPositionY;
    }
}
