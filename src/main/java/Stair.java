import java.util.ArrayList;

public class Stair {

    private int stairPositionX;
    private int stairPositionY;
    private char stairCharacter;

    public Stair() {
        this.stairCharacter = '\\';
    }

    public char getStairCharacter() {
        return this.stairCharacter;
    }

    public void setStairCharacter(char character) {
        this.stairCharacter = character;
    }
    
    public void spawnStair(Board gameBoard){
        int tileElement = gameBoard.getRandomSpawnTileElement();
        ArrayList<Tile> spawnableTiles = gameBoard.getSpawnableTiles();

        this.stairPositionY = spawnableTiles.get(tileElement).getTilePositionY();
        this.stairPositionX = spawnableTiles.get(tileElement).getTilePositionX();

        //setBoardTile(int row, int column
        gameBoard.setBoardTile(this.stairPositionY, this.stairPositionX, this.stairCharacter);
        gameBoard.removeSpawnableTile(tileElement);
    }
    
    public boolean checkNextLevel(Board gameBoard) {
        if(gameBoard.getBoardTile(this.stairPositionY, this.stairPositionX) == '@'){
            return true;
        }
        
        return false;
    }
}
