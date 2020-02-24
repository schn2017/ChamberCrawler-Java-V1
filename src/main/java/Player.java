
import java.util.ArrayList;
import java.util.Scanner;

public class Player {

    private int playerPositionX;
    private int playerPositionY;
    private int playerHealth;
    private String playerRace;
    private int playerFloor;
    private int playerGold;
    private boolean[] validPlayerDirections;
    private char playerCharacter;
    private String playerLastAction;
    private int playerAttackPower;
    private int playerDefensePower;
    private boolean playerReset;

    public Player() {
        this.playerHealth = 140;
        this.playerCharacter = '@';
        this.playerGold = 0;
        this.playerRace = "Human";
        this.validPlayerDirections = new boolean[8];
        this.playerAttackPower = 20;
        this.playerDefensePower = 20;

        for (int i = 0; i < 8; i++) {
            this.validPlayerDirections[i] = false;
        }
    }

    public void attackDirection(Board gameBoard, String playerAction) {
        String[] command = playerAction.split(" ");
        ArrayList<Monster> monsters = gameBoard.getMonsters();
        int targetXPosition = 0;
        int targetYPosition = 0;
        char tileASCII = ' ';

        if (command[1].equals("no")) {
            targetXPosition = this.playerPositionX;
            targetYPosition = this.playerPositionY - 1;
        } else if (command[1].equals("ne")) {
            targetXPosition = this.playerPositionX + 1;
            targetYPosition = this.playerPositionY - 1;
        } else if (command[1].equals("nw")) {
            targetXPosition = this.playerPositionX - 1;
            targetYPosition = this.playerPositionY - 1;
        } else if (command[1].equals("ea")) {
            targetXPosition = this.playerPositionX + 1;
            targetYPosition = this.playerPositionY;
        } else if (command[1].equals("se")) {
            targetXPosition = this.playerPositionX + 1;
            targetYPosition = this.playerPositionY + 1;
        } else if (command[1].equals("so")) {
            targetXPosition = this.playerPositionX;
            targetYPosition = this.playerPositionY + 1;
        } else if (command[1].equals("sw")) {
            targetXPosition = this.playerPositionX - 1;
            targetYPosition = this.playerPositionY + 1;
        } else if (command[1].equals("we")) {
            targetXPosition = this.playerPositionX - 1;
            targetYPosition = this.playerPositionY;
        }
        tileASCII = gameBoard.getBoardTile(targetYPosition, targetXPosition);
        if (tileASCII == '.') {
            this.playerLastAction = "attacked nothing.";
        } else if (tileASCII == '-' || tileASCII == '|') {
            this.playerLastAction = "attacked the wall.";
        } else if (tileASCII == 'P') {
            this.playerLastAction = "missed the potion.";
        } else {
            for (int i = 0; i < 20; i++) {
                int posX = monsters.get(i).getMonsterPositionX();
                int posY = monsters.get(i).getMonsterPositionY();

                if (posX == targetXPosition && posY == targetYPosition) {
                    monsters.get(i).takeDamage(this.playerAttackPower);
                    this.playerLastAction = ("dealt " + this.playerAttackPower + " damage to " + monsters.get(i).getMonsterCharacter() + " (" + monsters.get(i).getMonsterHealth() + ").");
                    break;
                }
            }
        }
    }

    public void findValidDirections(Board gameBoard) {
        //                       NO      NE        NW        E      SE      S        SW       W    
        int[][] directions = {{-1, 0}, {-1, 1}, {-1, -1}, {0, 1}, {1, 1}, {1, 0}, {1, -1}, {0, -1}};
        char tileASCII = ' ';
        for (int i = 0; i < 8; i++) {
            tileASCII = gameBoard.getBoardTile(this.playerPositionY + directions[i][0], this.playerPositionX + directions[i][1]);
            //System.out.println(tileASCII);
            if (tileASCII == '.' || tileASCII == '+' || tileASCII == '#' || tileASCII == 'G' || tileASCII == '\\') {
                this.validPlayerDirections[i] = true;
            } else {
                this.validPlayerDirections[i] = false;
            }
        }
    }

    public int getPlayerPositionX() {
        return this.playerPositionX;
    }

    public int getPlayerPositionY() {
        return this.playerPositionY;
    }

    public char getPlayerCharacter() {
        return this.playerCharacter;
    }

    public String getPlayerLastAction() {
        return this.playerLastAction;
    }
    
    public boolean getPlayerReset(){
        return this.playerReset;
    }

    public int getPlayerGold() {
        return this.playerGold;
    }

    public void setPlayerLastAction(String action) {
        this.playerLastAction = this.playerLastAction + " " + action;
    }

    public void setPlayerGold(int gold) {
        this.playerGold = this.playerGold + gold;
    }

    public void setPlayerFloor(int floor) {
        this.playerFloor = floor + 1;
    }

    public void performAction(Board gameBoard) {
        int oldYPosition = this.playerPositionY;
        int oldXPosition = this.playerPositionX;
        Scanner playerReader = new Scanner(System.in);
        int looper = -1;
        while (looper == -1) {
            findValidDirections(gameBoard);
            System.out.print("Please enter a valid action: ");
            String playerAction = playerReader.nextLine();
            //System.out.println("Player action entered is " + playerAction);
            if (playerAction.equals("quit")) {
                System.exit(0);
            } else if (playerAction.equals("reset")) {
                this.playerReset = true;
                looper = 0;
            } else if (playerAction.equals("no")) {
                if (validPlayerDirections[0] == true) {
                    this.playerPositionY = this.playerPositionY - 1;
                    this.playerLastAction = "moved north.";
                    looper = 0;
                }
            } else if (playerAction.equals("ne")) {
                if (validPlayerDirections[1] == true) {
                    this.playerPositionY = this.playerPositionY - 1;
                    this.playerPositionX = this.playerPositionX + 1;
                    this.playerLastAction = "moved north east.";
                    looper = 0;

                }
            } else if (playerAction.equals("nw")) {
                if (validPlayerDirections[2] == true) {
                    this.playerPositionY = this.playerPositionY - 1;
                    this.playerPositionX = this.playerPositionX - 1;
                    this.playerLastAction = "moved north west.";
                    looper = 0;
                }
            } else if (playerAction.equals("ea")) {
                if (validPlayerDirections[3] == true) {
                    this.playerPositionX = this.playerPositionX + 1;
                    this.playerLastAction = "moved east.";
                    looper = 0;
                }
            } else if (playerAction.equals("se")) {
                if (validPlayerDirections[4] == true) {
                    this.playerPositionY = this.playerPositionY + 1;
                    this.playerPositionX = this.playerPositionX + 1;
                    this.playerLastAction = "moved south east.";
                    looper = 0;
                }
            } else if (playerAction.equals("so")) {
                if (validPlayerDirections[5] == true) {
                    this.playerPositionY = this.playerPositionY + 1;
                    this.playerLastAction = "moved south.";
                    looper = 0;
                }
            } else if (playerAction.equals("sw")) {
                if (validPlayerDirections[6] == true) {
                    this.playerPositionY = this.playerPositionY + 1;
                    this.playerPositionX = this.playerPositionX - 1;
                    this.playerLastAction = "moved south west.";
                    looper = 0;
                }
            } else if (playerAction.equals("we")) {
                if (validPlayerDirections[7] == true) {
                    this.playerPositionX = this.playerPositionX - 1;
                    this.playerLastAction = "moved west.";
                    looper = 0;
                }
            } else if (playerAction.equals("a no")) {
                attackDirection(gameBoard, playerAction);
                looper = 1;
            } else if (playerAction.equals("a ne")) {
                attackDirection(gameBoard, playerAction);
                looper = 1;
            } else if (playerAction.equals("a nw")) {
                attackDirection(gameBoard, playerAction);
                looper = 1;
            } else if (playerAction.equals("a ea")) {
                attackDirection(gameBoard, playerAction);
                looper = 1;
            } else if (playerAction.equals("a se")) {
                attackDirection(gameBoard, playerAction);
                looper = 1;
            } else if (playerAction.equals("a so")) {
                attackDirection(gameBoard, playerAction);
                looper = 1;
            } else if (playerAction.equals("a sw")) {
                attackDirection(gameBoard, playerAction);
                looper = 1;
            } else if (playerAction.equals("a we")) {
                attackDirection(gameBoard, playerAction);
                looper = 1;
            } else if (playerAction.charAt(0) == 'u') {
                usePotion(gameBoard, playerAction);
                looper = 2;
            } else {
                looper = -1;
                System.out.println("Player action entered is " + playerAction);
            }
            if (looper == 0) {
                if (oldYPosition != this.playerPositionY || oldXPosition != this.playerPositionX) {
                    gameBoard.setBoardTile(this.playerPositionY, this.playerPositionX, this.playerCharacter);
                    gameBoard.setBoardTileOccupied(this.playerPositionY, this.playerPositionX, true);

                    char character = gameBoard.getBoardOriginalTile(oldYPosition, oldXPosition);

                    gameBoard.setBoardTile(oldYPosition, oldXPosition, character);
                    gameBoard.setBoardTileOccupied(oldYPosition, oldXPosition, false);
                }
            }

            for (int i = 0; i < 8; i++) {
                this.validPlayerDirections[i] = false;
            }
        }

    }

    public void printValidDirections() {
        System.out.print("Valid Directions are: ");
        // Check North
        if (this.validPlayerDirections[0] == true) {
            System.out.print("(no) North, ");
        }
        // Check North east
        if (this.validPlayerDirections[1] == true) {
            System.out.print("(ne) North East, ");
        }
        // Check North west
        if (this.validPlayerDirections[2] == true) {
            System.out.print("(nw) North West, ");
        }
        // Check East 
        if (this.validPlayerDirections[3] == true) {
            System.out.print("(ea) East, ");
        }
        //Check South East
        if (this.validPlayerDirections[4] == true) {
            System.out.print("(se) South East, ");
        }
        //Check South
        if (this.validPlayerDirections[5] == true) {
            System.out.print("(so) South, ");
        }
        //Check South West
        if (this.validPlayerDirections[6] == true) {
            System.out.print("(sw) South West, ");
        }
        //Check West
        if (this.validPlayerDirections[7] == true) {
            System.out.print("(we) West");
        }
        System.out.println("");
    }

    public void printPlayerInformation() {
        System.out.println("Floor: " + this.playerFloor);
        System.out.println("Race: " + this.playerRace + "   " + "Health: " + this.playerHealth + "    " + "Gold: " + this.playerGold);
        System.out.println("Attack: " + this.playerAttackPower);
        System.out.println("Defensive: " + this.playerDefensePower);
        System.out.println("Last Round: PC " + this.playerLastAction);
    }

    public void spawnPlayer(Board gameBoard) {
        int tileElement = gameBoard.getRandomSpawnTileElement();
        ArrayList<Tile> spawnableTiles = gameBoard.getSpawnableTiles();

        this.playerPositionY = spawnableTiles.get(tileElement).getTilePositionY();
        this.playerPositionX = spawnableTiles.get(tileElement).getTilePositionX();

        //setBoardTile(int row, int column
        gameBoard.setBoardTile(this.playerPositionY, this.playerPositionX, this.playerCharacter);
        gameBoard.setBoardTileOccupied(this.playerPositionY, this.playerPositionX, true);
        gameBoard.removeSpawnableTile(tileElement);
        this.playerLastAction = "spawned";
    }

    public void spawnPlayerNextLevel(Board gameBoard) {
        gameBoard.setBoardTile(this.playerPositionY, this.playerPositionX, this.playerCharacter);
        gameBoard.setBoardTileOccupied(this.playerPositionY, this.playerPositionX, true);
        this.playerLastAction = "descended to floor " + this.playerFloor + ".";

    }

    public void takeDamage(int damage) {
        double damageTaken = Math.ceil((100 / (100 + (double) this.playerDefensePower))) * (double) damage;
        this.playerHealth = this.playerHealth - (int) damageTaken;
    }

    public void update(Board gameBoard) {
        printPlayerInformation();
        findValidDirections(gameBoard);
        printValidDirections();
        if (this.playerHealth > 0) {
            performAction(gameBoard);
        } else {
            Loss loss = new Loss();
            loss.displayLoss(this.playerGold);
        }
    }

    public void usePotion(Board gameBoard, String playerAction) {
        String[] command = playerAction.split(" ");
        ArrayList<Potion> potions = gameBoard.getPotions();
        int targetX = 0;
        int targetY = 0;
        char tileASCII = ' ';
        if (command[0].equals("u")) {
            if (command[1].length() == 2) {
                char direction1 = command[1].charAt(0);
                char direction2 = command[1].charAt(1);

                if (direction1 == 'n') {
                    if (direction2 == 'o') {
                        targetY = this.playerPositionY - 1;
                        targetX = this.playerPositionX;
                    } else if (direction2 == 'e') {
                        targetY = this.playerPositionY - 1;
                        targetX = this.playerPositionX + 1;
                    } else if (direction2 == 'w') {
                        targetY = this.playerPositionY - 1;
                        targetX = this.playerPositionX - 1;
                    }
                } else if (direction1 == 's') {
                    if (direction2 == 'o') {
                        targetY = this.playerPositionY + 1;
                        targetX = this.playerPositionX;
                    } else if (direction2 == 'e') {
                        targetY = this.playerPositionY + 1;
                        targetX = this.playerPositionX + 1;
                    } else if (direction2 == 'w') {
                        targetY = this.playerPositionY + 1;
                        targetX = this.playerPositionX - 1;
                    }
                } else if (direction1 == 'e' && direction2 == 'a') {
                    targetY = this.playerPositionY;
                    targetX = this.playerPositionX + 1;
                } else if (direction1 == 'w' && direction2 == 'e') {
                    targetY = this.playerPositionY;
                    targetX = this.playerPositionX - 1;
                }

                tileASCII = gameBoard.getBoardTile(targetY, targetX);
                System.out.println(tileASCII);
                if (tileASCII != 'P') {
                    this.playerLastAction = "used nothing.";
                } else {
                    for (int i = 0; i < potions.size(); i++) {
                        int posX = potions.get(i).getPotionPositionX();
                        int posY = potions.get(i).getPotionPositionY();

                        if (posX == targetX && posY == targetY) {

                            potions.get(i).setIsUsed();
                            this.playerLastAction = "used the mysterious potion";
                        }
                    }
                }
            }
        }
    }
}
