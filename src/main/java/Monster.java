
import java.util.ArrayList;
import java.util.Random;

public class Monster {

    private int monsterPositionX;
    private int monsterPositionY;
    private int monsterHealth;
    private int monsterAttackPower;
    private int monsterDefensePower;
    private boolean[] validMonsterDirections;
    private boolean playerFound;
    private char monsterCharacter;

    public Monster() {
        this.validMonsterDirections = new boolean[8];
        this.playerFound = false;
    }

    public void attackPlayer(Player player) {
        Random rand = new Random();
        int hitChance = rand.nextInt(100);

        if (hitChance <= 50) {
            player.takeDamage(this.monsterAttackPower);
            player.setPlayerLastAction(monsterCharacter + " dealt " + this.monsterAttackPower + " damage to PC.");
        } else {
            player.setPlayerLastAction(monsterCharacter + " missed PC.");
        }

    }

    public void setMonsterHealth(int monsterHealth) {
        this.monsterHealth = monsterHealth;
    }

    public void setMonsterAttackPower(int monsterAttackPower) {
        this.monsterAttackPower = monsterAttackPower;
    }

    public void setMonsterDefensePower(int monsterDefensePower) {
        this.monsterDefensePower = monsterDefensePower;
    }

    public void setMonsterCharacter(char monsterCharacter) {
        this.monsterCharacter = monsterCharacter;
    }

    public void setMonsterPositionX(int monsterPostionX) {
        this.monsterPositionX = monsterPositionX;
    }

    public void setMonsterPositionY(int monsterPostionY) {
        this.monsterPositionY = monsterPositionY;
    }

    public int getMonsterPositionX() {
        return this.monsterPositionX;
    }

    public int getMonsterPositionY() {
        return this.monsterPositionY;
    }

    public char getMonsterCharacter() {
        return this.monsterCharacter;
    }

    public int getMonsterHealth() {
        return this.monsterHealth;
    }

    public void update(Board gameBoard, Player player) {
        findValidDirections(gameBoard);

        if (playerFound == true) {
            attackPlayer(player);
        } else {
            moveMonster(gameBoard);
        }
    }

    public void spawnMonster(Board gameBoard) {
        gameBoard.setBoardTile(this.monsterPositionY, this.monsterPositionX, this.monsterCharacter);
    }

    public void findValidDirections(Board gameBoard) {
        //                       NO      NE        NW        E      SE      S        SW       W    
        int[][] directions = {{-1, 0}, {-1, 1}, {-1, -1}, {0, 1}, {1, 1}, {1, 0}, {1, -1}, {0, -1}};
        char tileASCII = ' ';
        boolean found = false;

        for (int i = 0; i < 8; i++) {
            tileASCII = gameBoard.getBoardTile(this.monsterPositionY + directions[i][0], this.monsterPositionX + directions[i][1]);
            //System.out.println(tileASCII);
            if (tileASCII == '.') {
                this.validMonsterDirections[i] = true;
            } else if (tileASCII == '@') {
                found = true;
                this.playerFound = true;
            } else {
                this.validMonsterDirections[i] = false;
            }
        }

        if (found == false) {
            this.playerFound = false;
        }
    }

    public void moveMonster(Board gameBoard) {
        //                       NO      NE        NW        E      SE      S        SW       W            
        int[][] directions = {{-1, 0}, {-1, 1}, {-1, -1}, {0, 1}, {1, 1}, {1, 0}, {1, -1}, {0, -1}};
        ArrayList<Integer> moves = new ArrayList<>();
        Random rand = new Random();
        int oldYPosition = this.monsterPositionY;
        int oldXPosition = this.monsterPositionX;
        int randomDirection = 0;

        for (int i = 0; i < 8; i++) {
            if (this.validMonsterDirections[i] == true) {
                moves.add(i);
            }
        }
        randomDirection = rand.nextInt(moves.size());
        randomDirection = moves.get(randomDirection);
        this.monsterPositionY = this.monsterPositionY + directions[randomDirection][0];
        this.monsterPositionX = this.monsterPositionX + directions[randomDirection][1];
        gameBoard.setBoardTile(this.monsterPositionY, this.monsterPositionX, this.monsterCharacter);
        gameBoard.setBoardTileOccupied(this.monsterPositionY, this.monsterPositionX, true);
        gameBoard.setBoardTile(oldYPosition, oldXPosition, '.');
        gameBoard.setBoardTileOccupied(oldYPosition, oldXPosition, false);
        for (int i = 0; i < 8; i++) {
            this.validMonsterDirections[i] = false;
        }

    }

    public void takeDamage(int damage) {
        double damageTaken = Math.ceil((100 / (100 + (double) this.monsterDefensePower))) * (double) damage;
        this.monsterHealth = this.monsterHealth - (int) damageTaken;
    }
}
