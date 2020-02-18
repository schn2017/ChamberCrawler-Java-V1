/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author murdoch
 */
public class Tile {
    private int tilePositionX;
    private int tilePositionY;
    private char tileCharacter;
    private char originalCharacter;
    private boolean isOccupied;
    
    public Tile(int positionX, int positionY){
        this.tilePositionX = positionX;
        this.tilePositionY = positionY;
        this.tileCharacter = '.';
    }
    
    public char getTileCharacter(){
        return this.tileCharacter;
    }
    
    public void setTileCharacter(char tileCharacter){
        this.tileCharacter = tileCharacter;
    }
    
    public void setOriginalCharacter(char tileCharacter){
        this.originalCharacter = tileCharacter;
    }
    
    public char getOriginalCharacter(){
        return this.originalCharacter;
    }
    
    public boolean getIsOccupied(){
        return this.isOccupied;
    }
    
    public void setIsOccupied(boolean status){
        this.isOccupied = status;
    }
}
