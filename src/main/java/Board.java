
import java.util.ArrayList;
import java.util.Scanner;
import java.nio.file.Paths;
import java.util.Random;

public class Board {

    private int boardSizeX;
    private int boardSizeY;
    private Tile[][] boardTiles;
    private int boardFloor;
    private ArrayList<Monster> boardMonsters;
    private ArrayList<Potion> boardPotions;
    private ArrayList<Treasure> boardTreasures;
    private ArrayList<Tile> spawnableTiles;

    public Board(int height, int width) {
        this.boardSizeX = width;
        this.boardSizeY = height;
        this.boardTiles = new Tile[this.boardSizeY][this.boardSizeX];
        this.boardMonsters = new ArrayList();
        this.boardPotions = new ArrayList();
        this.boardTreasures = new ArrayList();
        this.spawnableTiles = new ArrayList();
    }

    public void addMonster(Monster monster) {
        this.boardMonsters.add(monster);
    }

    public void addPotion(Potion potion) {
        this.boardPotions.add(potion);
    }

    public void addTreasure(Treasure treasure) {
        this.boardTreasures.add(treasure);
    }

    public void initBoard() {
        for (int row = 0; row < this.boardSizeY; row++) {
            for (int col = 0; col < this.boardSizeX; col++) {
                this.boardTiles[row][col] = new Tile(row, col);
                this.boardTiles[row][col].setIsOccupied(false);
                if (row == 0) {
                    this.boardTiles[row][col].setTileCharacter('_');
                } else if (row == this.boardSizeY - 1) {
                    this.boardTiles[row][col].setTileCharacter('-');
                } else if (row >= 0 && col == 0) {
                    this.boardTiles[row][col].setTileCharacter('|');
                } else if (row == this.boardSizeX - 1) {
                    this.boardTiles[row][col].setTileCharacter('|');
                }
            }
        }
    }

    public void loadBoardFromFile() {
        int rowCount = 0;
        try ( Scanner scanner = new Scanner(Paths.get("map.txt"))) {
            while (scanner.hasNextLine()) {
                String row = scanner.nextLine();
                for (int i = 0; i < this.boardSizeX; i++) {
                    this.boardTiles[rowCount][i].setTileCharacter(row.charAt(i));
                    this.boardTiles[rowCount][i].setOriginalCharacter(row.charAt(i));
                }
                rowCount++;
            }

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void drawBoard() {
        for (int i = 0; i < this.boardSizeY; i++) {
            for (int j = 0; j < this.boardSizeX; j++) {
                System.out.print(this.boardTiles[i][j].getTileCharacter());
            }
            System.out.println("");
        }
    }

    public void setBoardTile(int row, int column, char character) {
        this.boardTiles[row][column].setTileCharacter(character);
    }

    public void setBoardTileOccupied(int row, int column, boolean status) {
        this.boardTiles[row][column].setIsOccupied(status);
    }

    public char getBoardTile(int row, int column) {
        return this.boardTiles[row][column].getTileCharacter();
    }

    public char getBoardOriginalTile(int row, int column) {
        return this.boardTiles[row][column].getOriginalCharacter();
    }

    public ArrayList<Monster> getMonsters() {
        return this.boardMonsters;
    }

    public ArrayList<Potion> getPotions() {
        return this.boardPotions;
    }

    public ArrayList<Treasure> getTreasures() {
        return this.boardTreasures;
    }

    public ArrayList<Tile> getSpawnableTiles() {
        return this.spawnableTiles;
    }

    public void findSpawnableTiles() {
        for (int row = 0; row < this.boardSizeY; row++) {
            for (int col = 0; col < this.boardSizeX; col++) {
                if (this.boardTiles[row][col].getTileCharacter() == '.') {
                    this.spawnableTiles.add(this.boardTiles[row][col]);
                }
            }
        }
    }

    public int getRandomSpawnTileElement() {
        Random rand = new Random();
        int spawnPick = rand.nextInt(this.spawnableTiles.size());
        return spawnPick;
    }

    public void removeSpawnableTile(int usedTile) {
        this.spawnableTiles.remove(usedTile);
    }

    public void findSpawnableStairTile(Player player) {
        int playerX = player.getPlayerPositionX();
        int playerY = player.getPlayerPositionY();

        // Room 1 Dimensions: 3 <= y <= 6, 3 <= x <=28
        // Room 2 Dimensions: 15 <= y <= 21, 3 <= x <= 24
        // Room 3 Dimensions: 10 <= y <= 12, 27 <= x <= 38
        // Room 4 Dimensions: 19 <= y <= 21, 37 <= x <= 75 | 16 <= y <= 18, 65 <= x <= 75
        // Room 5 Dimensions: 3 <= y <= 6, 39 <= x <= 60 | 3 <= y <= 12, 60 <= x <= 75
        if ((playerX <= 28 && playerX >= 3) && (playerY >= 3 && playerY <= 6)) { // Remove room 1 coordinates
            for (int i = this.spawnableTiles.size() - 1; i >= 0; i--) {
                int spawnTileX = this.spawnableTiles.get(i).getTilePositionX();
                int spawnTileY = this.spawnableTiles.get(i).getTilePositionY();
                if ((spawnTileX <= 28 && spawnTileX >= 3) && (spawnTileY >= 3 && spawnTileY <= 6)) {
                    this.spawnableTiles.remove(i);
                }
            }

        } else if ((playerX <= 24 && playerX >= 3) && (playerY >= 15 && playerY <= 21)) { // Remove room 2 coordinates
            for (int i = this.spawnableTiles.size() - 1; i >= 0; i--) {
                int spawnTileX = this.spawnableTiles.get(i).getTilePositionX();
                int spawnTileY = this.spawnableTiles.get(i).getTilePositionY();
                if ((spawnTileX <= 24 && spawnTileX >= 3) && (spawnTileY >= 15 && spawnTileY <= 21)) {
                    this.spawnableTiles.remove(i);
                }
            }

        } else if ((playerX <= 38 && playerX >= 27) && (playerY >= 10 && playerY <= 12)) { // Remove room 3 coordinates
            for (int i = this.spawnableTiles.size() - 1; i >= 0; i--) {
                int spawnTileX = this.spawnableTiles.get(i).getTilePositionX();
                int spawnTileY = this.spawnableTiles.get(i).getTilePositionY();
                if ((spawnTileX <= 38 && spawnTileX >= 27) && (spawnTileY >= 10 && spawnTileY <= 12)) {
                    this.spawnableTiles.remove(i);
                }
            }

        } else if (((playerX <= 75 && playerX >= 37) && (playerY >= 19 && playerY <= 21))
                || ((playerX <= 75 && playerX >= 65) && (playerY >= 16 && playerY <= 18))) { // Remove room 4 coordinates
            for (int i = this.spawnableTiles.size() - 1; i >= 0; i--) {
                int spawnTileX = this.spawnableTiles.get(i).getTilePositionX();
                int spawnTileY = this.spawnableTiles.get(i).getTilePositionY();
                if (((spawnTileX <= 75 && spawnTileX >= 37) && (spawnTileY >= 19 && spawnTileY <= 21))
                        || ((spawnTileX <= 75 && spawnTileX >= 65) && (spawnTileY >= 16 && spawnTileY <= 18))) {
                    this.spawnableTiles.remove(i);
                }
            }
        } else if (((playerX <= 60 && playerX >= 39) && (playerY >= 3 && playerY <= 6))
                || ((playerX <= 75 && playerX >= 60) && (playerY >= 3 && playerY <= 12))) { // Remove room 5 coordinates
            for (int i = this.spawnableTiles.size() - 1; i >= 0; i--) {
                int spawnTileX = this.spawnableTiles.get(i).getTilePositionX();
                int spawnTileY = this.spawnableTiles.get(i).getTilePositionY();
                if (((spawnTileX <= 60 && spawnTileX >= 39) && (spawnTileY >= 3 && spawnTileY <= 6))
                        || ((spawnTileX <= 75 && spawnTileX >= 60) && (spawnTileY >= 3 && spawnTileY <= 12))) {
                    this.spawnableTiles.remove(i);
                }
            }
        }

    }

    public void updatePotions(Board gameBoard, Player player) {
        for (Potion potion : this.boardPotions) {
            if (potion.getIsUsed() == true) {
                System.out.println("Potion is used");

                setBoardTile(potion.getPotionPositionY(), potion.getPotionPositionX(), '.');
                this.boardPotions.remove(potion);
                break;
            }
        }
    }

    public void updateTreasures(Board gameBoard, Player player) {
        ArrayList<Treasure> usedTreasures = new ArrayList<>();

        for (Treasure treasure : this.boardTreasures) {
            if (treasure.getIsUsed() == true) {
                usedTreasures.add(treasure);
            }
        }
        for (Treasure treasure : usedTreasures) {
            this.boardTreasures.remove(treasure);
        }
    }

    public void updateMonsters(Board gameBoard, Player player) {
        ArrayList<Monster> deadMonsters = new ArrayList<>();
        for (Monster monster : this.boardMonsters) {

            // Check if monster is dead
            if (monster.getMonsterHealth() <= 0) {
                if(monster.getMonsterCharacter() == 'D'){
                    monster.update(gameBoard, player);
                }
                
                deadMonsters.add(monster);
            } else { // Monster is alive'
                if (monster.getMonsterCharacter() == 'M') {
                    if (player.getMerchantsFriendly() == false) {
                        monster.setIsPeaceful(false);
                    }
                }
                monster.update(gameBoard, player);
            }
        }

        for (Monster monster : deadMonsters) {
            setBoardTile(monster.getMonsterPositionY(), monster.getMonsterPositionX(), '.');
            player.setPlayerGold(monster.getMonsterGold());

            // If merchant died spawn gold hoard
            if (monster.getMonsterCharacter() == 'M') {
                this.boardTreasures.add(new Treasure(monster.getMonsterPositionX(), monster.getMonsterPositionY(), 4, true));
                setBoardTile(monster.getMonsterPositionY(), monster.getMonsterPositionX(), 'G');
            }
            this.boardMonsters.remove(monster);
        }

    }
}
