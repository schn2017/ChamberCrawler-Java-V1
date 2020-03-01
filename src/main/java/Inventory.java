
import java.util.ArrayList;

public class Inventory {

    private ArrayList<Potion> potions;

    public Inventory() {
        this.potions = new ArrayList<>();
    }

    public void addPotion(Potion potion) {
        Potion newPotion = new Potion();
        newPotion.setPotionType(potion.getPotionType());
        newPotion.setModifier(potion.getModifier());
        this.potions.add(potion);
    }

    public boolean isInventoryEmpty() {
        return this.potions.isEmpty();
    }

    public void usePotion(Player player) {
        if (this.potions.isEmpty() == true) {
            System.out.println("There are no potions to use.");
        } else {
            Potion potion = potions.get(0);
            potion.applyPotionEffect(player);
            this.potions.remove(potion);
            player.setPlayerLastAction("PC used the mysterious potion.");
        }
    }

    public ArrayList getPotions() {
        return this.potions;
    }
}