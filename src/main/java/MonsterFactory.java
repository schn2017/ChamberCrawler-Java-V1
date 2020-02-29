
import java.util.Random;

public class MonsterFactory {

    private int monstersCreated;

    public void createMonsters(Board gameBoard, Player player) {
        Random rand = new Random();

        while (this.monstersCreated < 20) {
            int monsterPick = rand.nextInt(90);
            Monster monster = new Monster();

            if (0 <= monsterPick && monsterPick < 20) { // Werewolf
                monster.setMonsterHealth(120);
                monster.setMonsterAttackPower(30);
                monster.setMonsterDefensePower(5);
                monster.setMonsterCharacter('W');
                monster.setMonsterGold(1);
            } else if (20 <= monsterPick && monsterPick < 35) { // Vampire
                monster.setMonsterHealth(50);
                monster.setMonsterAttackPower(25);
                monster.setMonsterDefensePower(25);
                monster.setMonsterCharacter('V');
                monster.setMonsterGold(1);
            } else if (35 <= monsterPick && monsterPick < 60) { // Goblin
                monster.setMonsterHealth(70);
                monster.setMonsterAttackPower(5);
                monster.setMonsterDefensePower(10);
                monster.setMonsterCharacter('N');
                monster.setMonsterGold(1);
            } else if (60 <= monsterPick && monsterPick < 70) { // Troll
                monster.setMonsterHealth(120);
                monster.setMonsterAttackPower(25);
                monster.setMonsterDefensePower(15);
                monster.setMonsterCharacter('T');
                monster.setMonsterGold(1);
            } else if (70 <= monsterPick && monsterPick < 80) { // Phoenix
                monster.setMonsterHealth(50);
                monster.setMonsterAttackPower(25);
                monster.setMonsterDefensePower(25);
                monster.setMonsterCharacter('V');
                monster.setMonsterGold(1);
            } else if (80 <= monsterPick && monsterPick < 90) { // Merchant
                monster.setMonsterHealth(30);
                monster.setMonsterAttackPower(70);
                monster.setMonsterDefensePower(5);
                monster.setMonsterCharacter('M');
                monster.setIsPeaceful(player.getMerchantsFriendly());
            }

            gameBoard.addMonster(monster);
            monster.spawnMonster(gameBoard);
            this.monstersCreated++;
        }
    }
}
