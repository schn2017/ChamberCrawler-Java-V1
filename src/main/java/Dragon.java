
import java.util.ArrayList;
import java.util.Random;

public class Dragon extends Monster {

    private Treasure treasure;

    public Dragon(Board gameBoard, Treasure treasure) {
        this.setMonsterCharacter('D');
        this.setMonsterHealth(150);
        this.setMonsterAttackPower(20);
        this.setMonsterDefensePower(20);
        this.setMonsterGold(0);
        this.treasure = treasure;
        this.setCanMove(false);
    }

    public void findSpawnLocation(Board gameBoard) {
        int[][] directions = {{-1, 0}, {-1, 1}, {-1, -1}, {0, 1}, {1, 1}, {1, 0}, {1, -1}, {0, -1}};
        ArrayList<Integer> possibleSpawnPoints = new ArrayList<>();
        int posY = this.treasure.getTreasurePositionY();
        int posX = this.treasure.getTreasurePositionX();
        System.out.println(posY + " " + posX);
        Random rand = new Random();
        int spawnPick = 0;
        char tileASCII = ' ';

        for (int i = 0; i < 8; i++) {
            tileASCII = gameBoard.getBoardTile(posY + directions[i][0], posX + directions[i][1]);
            //System.out.println(tileASCII);
            if (tileASCII == '.') {
                possibleSpawnPoints.add(i);
            }
        }
        spawnPick = rand.nextInt(possibleSpawnPoints.size());
        spawnPick = possibleSpawnPoints.get(spawnPick);

        posY = posY + directions[spawnPick][0];
        posX = posX + directions[spawnPick][1];
        System.out.println(posY + " " + posX);
        this.setMonsterPositionY(posY);
        System.out.println(this.getMonsterPositionY());
        this.setMonsterPositionX(posX);
        System.out.println(this.getMonsterPositionY() + " " + this.getMonsterPositionX());

    }

    public void awardGold(Board gameBoard, Player player) {
        int goldCount = 0;
        
        for(Monster monster:gameBoard.getMonsters()){
            if (monster.getMonsterCharacter() != 'D' && monster.getMonsterCharacter() != 'M'){
                goldCount++;
            }
        }
        
        player.setPlayerGold(goldCount);
    }

    @Override
    public void update(Board gameBoard, Player player) {
        findValidDirections(gameBoard);

        if (this.getPlayerFound() == true) {
            attackPlayer(player);
        }
        
        if (this.getMonsterHealth() <= 0){
            awardGold(gameBoard, player);
            this.treasure.setValidDirection(true);
        }
        
    }
}
