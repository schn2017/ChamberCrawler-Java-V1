
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
                // If Player chose to reset game break loop
                if (player.getPlayerReset() == true) {
                    floor = 0;
                    break;
                }

                // check for Victory condition
                if (floor == 9) {
                    Victory victory = new Victory();
                    victory.displayVictory(player);
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

                // Setup Monsters, Potions, and Treasures
                MonsterFactory floorMonsterCreator = new MonsterFactory();
                TreasureFactory floorTreasureCreator = new TreasureFactory();
                PotionFactory floorPotionCreator = new PotionFactory();

                // Spawn Monsters, Potions, and Treasures
                floorMonsterCreator.createMonsters(gameBoard);
                floorTreasureCreator.createTreasures(gameBoard);
                floorPotionCreator.createPotions(gameBoard);

                // Set up stairs
                gameBoard.findSpawnableStairTile(player);
                Stair stair = new Stair();

                // Spawn stairs
                stair.spawnStair(gameBoard);

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
                    gameBoard.updateMonsters(gameBoard, player);
                    gameBoard.updatePotions(gameBoard, player);
                    gameBoard.updateTreasures(gameBoard, player);
                }
                floor++;
                player.resetPlayerStats();
            }
        }
    }
}
