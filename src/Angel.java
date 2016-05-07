import java.util.Random;

/**
 * Created by asus-pc on 5/7/2016.
 */
public class Angel extends Enemy {
    private double healPower;
    private double tempHealPower; // i'm not sure about this field!

    public void doTurn() {
        Random random = new Random();
        int randomIndex = random.nextInt(GameEngine.listOfEnemies.size());
        Enemy targetedEnemy = GameEngine.listOfEnemies.get(randomIndex);
        targetedEnemy.setCurrentHealth(targetedEnemy.currentHealth + this.healPower);
    }
}
