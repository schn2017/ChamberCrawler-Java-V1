
public class Tile {

    private int tilePositionX;
    private int tilePositionY;
    private char tileCharacter;
    private char originalCharacter;
    private boolean isOccupied;

    public Tile(int row, int col) {
        this.tilePositionX = col;
        this.tilePositionY = row;
        this.tileCharacter = '.';
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

    public void setIsOccupied(boolean status) {
        this.isOccupied = status;
    }
}
