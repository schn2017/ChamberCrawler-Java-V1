
import java.util.ArrayList;
import java.util.Scanner;

public class Player {

    private int playerPositionX;
    private int playerPositionY;
    private int playerHealth;
    private int playerMaxHealth;
    private String playerRace;
    private int playerFloor;
    private double playerGoldModifier;
    private double playerGold;
    private int playerPotionModifier;
    private boolean[] validPlayerDirections;
    private char playerCharacter;
    private String playerLastAction;
    private int playerAttackPower;
    private int playerDefensePower;
    private boolean playerReset;
    private boolean merchantsFriendly;
    private Inventory playerInventory;

    public Player() {
        createPlayer();
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
            for (Monster monster : gameBoard.getMonsters()) {
                if (monster.getMonsterPositionY() == targetYPosition && monster.getMonsterPositionX() == targetXPosition) {
                    if (monster.getMonsterCharacter() == 'M') {
                        this.merchantsFriendly = false;
                    }

                    monster.takeDamage(this.playerAttackPower);
                    this.playerLastAction = ("dealt " + this.playerAttackPower + " damage to " + monster.getMonsterCharacter() + " (" + monster.getMonsterHealth() + ").");
                    break;
                }
            }
        }
    }

    public void createPlayer() {
        int optionSelected;
        Scanner playerScanner = new Scanner(System.in);

        System.out.println("Please select a race from the options below:");
        System.out.println("1. Human");
        System.out.println("2. Dwarf");
        System.out.println("3. Elf");
        System.out.println("4. Orc");

        while (true) {
            System.out.println("Please enter the integer of the race you wish to play.");
            try {
                optionSelected = Integer.valueOf(playerScanner.nextLine());
                if (optionSelected <= 4 && optionSelected >= 1) {
                    break;
                } else {
                    System.out.println("Please enter the integer of the race you wish to play.");
                }
            } catch (Exception e) {
                System.out.println("Invalid option entered!");
            }
        }

        if (optionSelected == 1) {// Human
            this.playerRace = "Human";
            this.playerHealth = 140;
            this.playerMaxHealth = 140;
            this.playerAttackPower = 20;
            this.playerDefensePower = 20;
            this.playerGoldModifier = 1;
            this.playerPotionModifier = 1;

        } else if (optionSelected == 2) {// Dwarf
            this.playerRace = "Dwarf";
            this.playerHealth = 100;
            this.playerMaxHealth = 100;
            this.playerAttackPower = 20;
            this.playerDefensePower = 30;
            this.playerGoldModifier = 2;
            this.playerPotionModifier = 1;
        } else if (optionSelected == 3) {// Elf
            this.playerRace = "Elf";
            this.playerHealth = 140;
            this.playerMaxHealth = 140;
            this.playerAttackPower = 30;
            this.playerDefensePower = 10;
            this.playerGoldModifier = 1;
            this.playerPotionModifier = -1;
            //negative potions have positive effect
        } else if (optionSelected == 4) {// Orc
            this.playerRace = "Orc";
            this.playerHealth = 2000;
            this.playerMaxHealth = 2000;
            this.playerAttackPower = 300;
            this.playerDefensePower = 300;
            this.playerGoldModifier = 0.5;
            this.playerPotionModifier = 1;
        }

        this.playerCharacter = '@';
        this.merchantsFriendly = true;
        this.playerGold = 0;
        this.playerInventory = new Inventory();
        this.validPlayerDirections = new boolean[8];
        for (int i = 0; i < 8; i++) {
            this.validPlayerDirections[i] = false;
        }
    }

    public void findValidDirections(Board gameBoard) {
        //                       NO      NE        NW        E      SE      S        SW       W    
        int[][] directions = {{-1, 0}, {-1, 1}, {-1, -1}, {0, 1}, {1, 1}, {1, 0}, {1, -1}, {0, -1}};
        char tileASCII = ' ';
        for (int i = 0; i < 8; i++) {
            tileASCII = gameBoard.getBoardTile(this.playerPositionY + directions[i][0], this.playerPositionX + directions[i][1]);
            //System.out.println(tileASCII);
            if (tileASCII == '.' || tileASCII == '+' || tileASCII == '#' || tileASCII == '\\') {
                this.validPlayerDirections[i] = true;
            } else if (tileASCII == 'G') {
                ArrayList<Treasure> treasures = gameBoard.getTreasures();

                for (Treasure treasure : treasures) {
                    if (treasure.getTreasurePositionY() == this.playerPositionY + directions[i][0]
                            && treasure.getTreasurePositionX() == this.playerPositionX + directions[i][1]) {
                        if (treasure.getValidDirection() == true) {
                            this.validPlayerDirections[i] = true;
                            break;
                        } else {
                            this.validPlayerDirections[i] = false;
                        }
                    }
                }
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

    public boolean getPlayerReset() {
        return this.playerReset;
    }

    public double getPlayerGold() {
        return this.playerGold;
    }

    public boolean getMerchantsFriendly() {
        return this.merchantsFriendly;
    }

    public void setMerchantsFriendly(boolean status) {
        this.merchantsFriendly = status;
    }

    public void setPlayerLastAction(String action) {
        this.playerLastAction = this.playerLastAction + " " + action;
    }

    public void setPlayerGold(double gold) {
        //System.out.println(gold);
        this.playerGold = this.playerGold + gold;
        //System.out.println(this.playerGold);
    }

    public void setPlayerFloor(int floor) {
        this.playerFloor = floor + 1;
    }

    public void setPlayerHealth(int health) {

        if (health > 0) {
            if (this.playerHealth + health >= this.playerMaxHealth) {
                this.playerHealth = this.playerMaxHealth;
            } else {
                this.playerHealth = this.playerHealth + health;
            }
        } else {
            if (this.playerRace.equals("Elf")) {
                if (this.playerHealth + (health * -1) >= this.playerMaxHealth) {
                    this.playerHealth = this.playerMaxHealth;
                } else {
                    this.playerHealth = this.playerHealth + (health * -1);
                }

            } else if (this.playerHealth + health <= 0) {
                this.playerHealth = 0;
            } else {
                this.playerHealth = this.playerHealth + health;
            }
        }
    }

    public void setPlayerAttackPower(int potionModifier) {

        if (potionModifier > 0) {
            this.playerAttackPower = this.playerAttackPower + potionModifier;
        } else {
            if (this.playerRace.equals("Elf")) {
                this.playerAttackPower = this.playerAttackPower + (potionModifier * -1);
            } else if (this.playerAttackPower + potionModifier < 0) {
                this.playerAttackPower = 0;
            } else {
                this.playerAttackPower = this.playerAttackPower + potionModifier;
            }
        }
    }

    public void setPlayerDefensePower(int potionModifier) {

        if (potionModifier > 0) {
            this.playerDefensePower = this.playerDefensePower + potionModifier;
        } else {
            if (this.playerRace.equals("Elf")) {
                this.playerDefensePower = this.playerDefensePower + (potionModifier * -1);
            } else if (this.playerDefensePower + potionModifier < 0) {
                this.playerDefensePower = 0;
            } else {
                this.playerDefensePower = this.playerDefensePower + potionModifier;
            }
        }
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
                String[] command = playerAction.split(" ");
                String[] validEntries = {"no", "ne", "nw", "ea", "se", "so", "sw", "we"};
                if (command.length > 1) {
                    if (command[1].equals("potion") && this.playerInventory.isInventoryEmpty() == true) {
                        System.out.println("There are no potions to use.");
                        looper = -1;
                    } else {
                        for (int i = 0; i < 8; i++) {
                            if (validEntries[i].equals(command[1])) {

                                if (this.playerInventory.isInventoryEmpty() == true) {
                                    System.out.println("There are no potions to use.");
                                    looper = -1;
                                    break;
                                } else {
                                    usePotion(gameBoard, playerAction);
                                    looper = 2;
                                    break;
                                }
                            } else {
                                looper = -1;
                            }
                        }
                    }
                } else {
                    looper = -1;
                    System.out.println("Player action entered is " + playerAction);
                }
                if (looper == 0) {
                    if (oldYPosition != this.playerPositionY || oldXPosition != this.playerPositionX) {

                        if (gameBoard.getBoardTile(this.playerPositionY, this.playerPositionX) == 'G') {
                            ArrayList<Treasure> treasures = gameBoard.getTreasures();
                            for (Treasure treasure : treasures) {
                                if (treasure.getTreasurePositionY() == this.playerPositionY && treasure.getTreasurePositionX() == this.playerPositionX) {
                                    treasure.addTreasureToPlayer(this);
                                    System.out.println("Added treasure!");
                                }
                            }
                        }

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
        System.out.println("Potions in inventory: " + this.playerInventory.getPotions().size());
        System.out.println("Last Round: PC " + this.playerLastAction);
    }

    public void resetPlayerStats() {

        if (this.playerRace.equals("Human")) {// Human
            this.playerAttackPower = 20;
            this.playerDefensePower = 20;
        } else if (this.playerRace.equals("Dwarf")) {// Dwarf
            this.playerAttackPower = 20;
            this.playerDefensePower = 30;
        } else if (this.playerRace.equals("Elf")) {// Elf
            this.playerAttackPower = 30;
            this.playerDefensePower = 10;
            //negative potions have positive effect
        } else if (this.playerRace.equals("Orc")) {// Orc
            this.playerAttackPower = 30;
            this.playerDefensePower = 25;
        }
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

                if (gameBoard.getBoardTile(targetY, targetX) == 'P') {

                    for (Potion potion : potions) {
                        if (potion.getPotionPositionY() == targetY && potion.getPotionPositionX() == targetX) {
                            if (command.length == 2) {
                                potion.applyPotionEffect(this);
                                potion.setIsUsed(true);
                                gameBoard.setBoardTile(potion.getPotionPositionY(), potion.getPotionPositionX(), '.');
                                this.playerLastAction = "used the mysterious potion.";
                                break;
                            } else if (command[2].equals("store")) {
                                //store potion in inventory
                                this.playerInventory.addPotion(potion);
                                this.playerLastAction = "stored the mysterious potion.";
                                potion.setIsUsed(true);
                                gameBoard.setBoardTile(potion.getPotionPositionY(), potion.getPotionPositionX(), '.');
                                break;
                            }
                        }
                    }
                } else {
                    this.playerLastAction = "used nothing.";
                }
            } else if (command[1].equals("potion")) {
                this.playerInventory.usePotion(this);
            }
        }
    }
}
