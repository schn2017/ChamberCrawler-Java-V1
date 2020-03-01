import java.lang.Math; 
public class Tile {

    private int tilePositionX;
    private int tilePositionY;
    private char tileCharacter;
    private char originalCharacter;
    private boolean isOccupied;
    private boolean isDiscoverd;
    private boolean isSeeable;

    public Tile(int row, int col) {
        this.tilePositionX = col;
        this.tilePositionY = row;
        this.tileCharacter = '.';
        this.isDiscoverd = false;
        
    }

    public int getTilePositionY() {
        return this.tilePositionY;
    }

    public int getTilePositionX() {
        return this.tilePositionX;
    }

    public char getTileCharacter() {
        return this.tileCharacter;
    }
    
    public boolean getIsSeeable(){
        return isSeeable;
    }

    public void setTileCharacter(char tileCharacter) {
        this.tileCharacter = tileCharacter;
    }

    public void setOriginalCharacter(char tileCharacter) {
        this.originalCharacter = tileCharacter;
    }

    public char getOriginalCharacter() {
        return this.originalCharacter;
    }

    public boolean getIsOccupied() {
        return this.isOccupied;
    }
    public boolean getIsDiscovered(){
        return this.isDiscoverd;
    }

    public void setIsOccupied(boolean status) {
        this.isOccupied = status;
    }
    
    public void getDistanceFromPlayer(Player player){
        double tileY = this.tilePositionY;
        double tileX = this.tilePositionX;
        double playerY = player.getPlayerPositionY();
        double playerX = player.getPlayerPositionX();
        double distance = Math.sqrt(((tileY - playerY) * (tileY - playerY)) + ((tileX - playerX) * (tileX - playerX))); 
        
        if (distance <= 4){
            if (this.isDiscoverd == false){
                this.isDiscoverd = true;
            }
            this.isSeeable = true;
        }else{
            this.isSeeable = false;
        }
    } 
}
