
import java.util.ArrayList;

public class Chamber_Crawler {

    public static void main(String[] args) {
        int width = 79;
        int height = 25;
        Board gameBoard = new Board(height, width);
        Player player = new Player();
        Monster vampire = new Monster();
        Potion potion = new Potion();
        Treasure treasure = new Treasure();
        gameBoard.initBoard();
        gameBoard.loadBoardFromFile();
        player.spawnPlayer(gameBoard);
        gameBoard.addMonster(0, vampire);
        vampire.spawnMonster(gameBoard);
        gameBoard.addPotion(0, potion);
        potion.spawnPotion(gameBoard);
        gameBoard.addTreasure(0, treasure);       
        treasure.spawnTreasure(gameBoard);
        
        
        while (true) {
            gameBoard.drawBoard();
            player.update(gameBoard);
            gameBoard.updatePotions(gameBoard);
            gameBoard.updateTreasures(gameBoard, player);
            gameBoard.updateMonsters(gameBoard, player);
        }
    }
}
