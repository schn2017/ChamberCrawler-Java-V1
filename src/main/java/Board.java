/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author murdoch
 */
import java.util.ArrayList;
import java.util.Scanner;
import java.nio.file.Paths;

public class Board {

    private int boardSizeX;
    private int boardSizeY;
    private Tile[][] boardTiles;
    private int boardFloor;
    private int boardRoomNumbers;
    private ArrayList<Monster>[] boardMonsters;
    private ArrayList<Potion>[] boardPotions;
    private ArrayList<Treasure>[] boardTreasures;

    public Board(int height, int width) {
        this.boardSizeX = width;
        this.boardSizeY = height;
        this.boardTiles = new Tile[this.boardSizeY][this.boardSizeX];
        this.boardRoomNumbers = 1;
        this.boardMonsters = new ArrayList[this.boardRoomNumbers];
        this.boardPotions = new ArrayList[this.boardRoomNumbers];
        this.boardTreasures = new ArrayList[this.boardRoomNumbers];

        for (int i = 0; i < this.boardRoomNumbers; i++) {
            this.boardMonsters[i] = new ArrayList<Monster>();
            this.boardPotions[i] = new ArrayList<Potion>();
            this.boardTreasures[i] = new ArrayList<Treasure>();
        }
    }

    public void addMonster(int roomNumber, Monster monster) {
        this.boardMonsters[roomNumber].add(monster);
    }

    public void addPotion(int roomNumber, Potion potion) {
        this.boardPotions[roomNumber].add(potion);
    }

    public void addTreasure(int roomNumber, Treasure treasure){
        this.boardTreasures[roomNumber].add(treasure);
    }
    
    
    public void initBoard() {
        for (int i = 0; i < this.boardSizeY; i++) {
            for (int j = 0; j < this.boardSizeX; j++) {
                this.boardTiles[i][j] = new Tile(i, j);
                this.boardTiles[i][j].setIsOccupied(false);
                if (i == 0) {
                    this.boardTiles[i][j].setTileCharacter('_');
                } else if (i == this.boardSizeY - 1) {
                    this.boardTiles[i][j].setTileCharacter('-');
                } else if (i >= 0 && j == 0) {
                    this.boardTiles[i][j].setTileCharacter('|');
                } else if (j == this.boardSizeX - 1) {
                    this.boardTiles[i][j].setTileCharacter('|');
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
                    //System.out.println("Hello");
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

    public ArrayList<Monster> getRoomMonsters(int roomNumber) {
        ArrayList<Monster> roomMonsters = new ArrayList<Monster>();

        for (int i = 0; i < this.boardMonsters[roomNumber].size(); i++) {
            Monster monster = this.boardMonsters[roomNumber].get(i);
            roomMonsters.add(monster);
        }
        return roomMonsters;
    }

    public ArrayList<Potion> getRoomPotions(int roomNumber) {
        ArrayList<Potion> roomPotions = new ArrayList<Potion>();

        for (int i = 0; i < this.boardPotions[roomNumber].size(); i++) {
            Potion potion = this.boardPotions[roomNumber].get(i);
            roomPotions.add(potion);
        }
        return roomPotions;
    }

    public void updatePotions(Board gameBoard) {
        int roomCount = this.boardRoomNumbers;
        for (int i = 0; i < roomCount; i++) {
            int potionListSize = this.boardPotions[i].size();
            for (int j = 0; j < potionListSize; j++) {

                // Check if potion is used
                if (this.boardPotions[i].get(j).getIsUsed() == true) {
                    setBoardTile(this.boardPotions[i].get(j).getPotionPositionY(), this.boardPotions[i].get(j).getPotionPositionX(), '.');
                    this.boardPotions[i].remove(j);
                }
            }
        }
    }
    public void updateTreasures(Board gameBoard, Player player) {
        int roomCount = this.boardRoomNumbers;
        for (int i = 0; i < roomCount; i++) {
            int treasureListSize = this.boardTreasures[i].size();
            for (int j = 0; j < treasureListSize; j++) {

                // Check if potion is used
                if (this.boardTreasures[i].get(j).getIsUsed() == true) {
                    setBoardTile(this.boardTreasures[i].get(j).getTreasurePositionY(), this.boardTreasures[i].get(j).getTreasurePositionX(), '.');
                    this.boardTreasures[i].remove(j);
                }else{
                    this.boardTreasures[i].get(j).update(gameBoard, player);
                }
            }
        }
    }    
    public void updateMonsters(Board gameBoard, Player player) {
        int roomCount = this.boardRoomNumbers;
        for (int i = 0; i < roomCount; i++) {
            int monsterListSize = this.boardMonsters[i].size();
            for (int j = 0; j < monsterListSize; j++) {

                // Check if monster is dead
                if (this.boardMonsters[i].get(j).getMonsterHealth() <= 0) {
                    setBoardTile(this.boardMonsters[i].get(j).getMonsterPositionY(), this.boardMonsters[i].get(j).getMonsterPositionX(), '.');
                    this.boardMonsters[i].remove(j);
                } else { // Monster is alive
                    this.boardMonsters[i].get(j).update(gameBoard, player);
                }
            }
        }
    }
}
