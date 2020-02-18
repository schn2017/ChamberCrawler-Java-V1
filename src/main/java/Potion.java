/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author murdoch
 */
public class Potion {

    private int potionPositionX;
    private int potionPositionY;
    private char potionCharacter;
    private String potionType;
    private boolean isUsed;

    public Potion() {
        this.potionPositionX = 10;
        this.potionPositionY = 4;
        this.potionCharacter = 'P';
        this.potionType = "RH";
        this.isUsed = false;
    }

    public void spawnPotion(Board gameBoard) {
        gameBoard.setBoardTile(this.potionPositionY, this.potionPositionX, this.potionCharacter);
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
    
    public int getPotionPositionX(){
        return this.potionPositionX;
    }
    
    public int getPotionPositionY(){
        return this.potionPositionY;
    }
}
