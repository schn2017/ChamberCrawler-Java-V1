
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
    
    public boolean getIsUsed(){
        return this.isUsed;
    }
    
    public void setIsUsed(){
        this.isUsed = true;
    }
    
    public String getPotionType()
    {
        return this.potionType;
    }
    
    public void setPotionType(String potionType){
        this.potionType = potionType;     
    }
    
    public void setModifier(int potionModifier){
        this.potionModifier = potionModifier;
    }
    
    public int getPotionPositionX(){
        return this.potionPositionX;
    }
    
    public int getPotionPositionY(){
        return this.potionPositionY;
    }
}
