import java.util.Random;

/**
 * Created by asus-pc on 5/7/2016.
 */
public class Angel extends Enemy {
    private Integer healPower;              // i think it must be Integer it is not neccessery to be Double
    private Integer tempHealPower;          // i'm not sure about this field!


    public Angel(String version){
        this.setClassName("Angel");
        this.setFullName();
        if(version.equals("Weak")){
            this.setHealPower(100);
            this.setMaximumHealth(150);
        }
        else if(version.equals("Able")) {
            this.setHealPower(150);
            this.setMaximumHealth(250);
        }
    }
    public void doTurn() {
        Random random = new Random();
        int randomIndex = random.nextInt(GameEngine.listOfEnemies.size());
        Enemy targetedEnemy = GameEngine.listOfEnemies.get(randomIndex);
        targetedEnemy.setCurrentHealth(targetedEnemy.currentHealth + this.healPower);
        Console.printInEachLine("Angel just healed " + targetedEnemy.getName() + " with " + this.healPower + " health points");
    }
    public Integer getHealPower() {
        return healPower;
    }

    public void setHealPower(Integer healPower) {
        this.healPower = healPower;
    }

}
