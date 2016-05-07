import java.util.Random;

/**
 * Created by asus-pc on 5/7/2016.
 */
public class Angel extends Enemy {
    private Integer healPower;              // i think it must be Integer it is not neccessery to be Double
    private Integer tempHealPower;          // i'm not sure about this field!
    public Angel(String version){
        if(version.equals("Weak")){

        }
        else if(version.equals("Able")){

        }
        else if(version.equals("Mighty")){

        }
    }
    public void doTurn() {
        Random random = new Random();
        int randomIndex = random.nextInt(GameEngine.listOfEnemies.size());
        Enemy targetedEnemy = GameEngine.listOfEnemies.get(randomIndex);
        targetedEnemy.setCurrentHealth(targetedEnemy.currentHealth + this.healPower);
        Console.printInEachLine("Angel just healed " + targetedEnemy.getName() + " with " + this.healPower + " health points");
    }
}
