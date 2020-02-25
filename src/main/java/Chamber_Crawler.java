
import java.util.ArrayList;

public class Chamber_Crawler {

    public static void main(String[] args) {
        int width = 79;
        int height = 25;
        int floor = 0;

        Menu mainMenu = new Menu();

        // Menu Loop
        while (true) {
            mainMenu.displayMenu();
            mainMenu.displaySelected();

            // Player Race Selection and Player creation
            Player player = new Player();

            // Create Floor Loop
            while (true) {
                if (player.getPlayerReset() == true) {
                    floor = 0;
                    break;
                }

                // Setup Floor
                Board gameBoard = new Board(height, width);
                gameBoard.initBoard();
                gameBoard.loadBoardFromFile();
                gameBoard.findSpawnableTiles();

                // Set up Player
                player.setPlayerFloor(floor);
                if (floor == 0) {
                    player.spawnPlayer(gameBoard);
                } else {
                    player.spawnPlayerNextLevel(gameBoard);
                }

                MonsterFactory floorMonsterCreator = new MonsterFactory();

                //floorMonsterCreator.createMonsters(gameBoard);
                
                TreasureFactory floorTreasureCreator = new TreasureFactory();
                
                floorTreasureCreator.createTreasures(gameBoard);
                
                gameBoard.findSpawnableStairTile(player);
                Stair stair = new Stair();
                stair.spawnStair(gameBoard);
                /*
                gameBoard.addPotion(0, potion);
                potion.spawnPotion(gameBoard);
                gameBoard.addTreasure(0, treasure);
                treasure.spawnTreasure(gameBoard);*/

                if (floor == 9) {
                    Victory victory = new Victory();
                    victory.displayVictory(player);
                }

                //Active Floor Loop
                while (true) {
                    gameBoard.drawBoard();
                    player.update(gameBoard);

                    if (player.getPlayerReset() == true) {
                        break;
                    }

                    if (stair.checkNextLevel(gameBoard) == true) {
                        break;
                    }
                    //gameBoard.updatePotions(gameBoard);
                    gameBoard.updateTreasures(gameBoard, player);
                    //gameBoard.updateMonsters(gameBoard, player);
                }
                floor++;
                player.resetPlayerStats();
            }
        }
    }
}
