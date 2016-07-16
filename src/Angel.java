import java.util.Random;

/**
 * Created by asus-pc on 5/7/2016.
 */
public class Angel extends Enemy {
    private int healPower;              // i think it must be Integer it is not neccessery to be Double
    private int tempHealPower;          // i'm not sure about this field!



    public Angel(String version){
        this.setClassName("Angel");
        this.setVersion(version);
        this.setFullName();
        if(version.equalsIgnoreCase("Weak")){
            this.setHealPower(100);
            this.setMaximumHealth(150);
            this.setCurrentHealth(150);
        }
        else if(version.equalsIgnoreCase("Able")) {
            this.setHealPower(150);
            this.setMaximumHealth(250);
            this.setCurrentHealth(250);
        }
    }

    public void doTurn() {
        Random random = new Random();
        int randomIndex = random.nextInt(GameEngine.listOfEnemies.size());
        Enemy targetedEnemy = GameEngine.listOfEnemies.get(randomIndex);
        if (targetedEnemy.currentHealth + this.healPower > targetedEnemy.getMaximumHealth()) {
            Display.printInEachLine("Angel just healed " + targetedEnemy.getName() + " with " + (targetedEnemy.getMaximumHealth() - targetedEnemy.getCurrentHealth()) + " health points");
            targetedEnemy.setCurrentHealth(targetedEnemy.getMaximumHealth());
        }
        else {
            Display.printInEachLine("Angel just healed " + targetedEnemy.getName() + " with " + this.healPower + " health points");
            targetedEnemy.setCurrentHealth(targetedEnemy.currentHealth + this.healPower);
        }
    }

    public int getHealPower() {
        return healPower;
    }

    public void setHealPower(Integer healPower) {
        this.healPower = healPower;
    }

}
