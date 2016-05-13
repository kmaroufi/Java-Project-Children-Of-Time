import java.util.Random;

/**
 * Created by asus-pc on 5/7/2016.
 */
public class Thug extends Enemy {
    public Thug(String version){
        this.setClassName("Thug");
        this.setVersion(version);
        this.setFullName();
        if(version.equalsIgnoreCase("Weak")){
            this.setAttackPower(50);
            this.setMaximumHealth(200);
            this.setCurrentHealth(200);
        }
        else if(version.equalsIgnoreCase("Able")){
            this.setAttackPower(90);
            this.setMaximumHealth(300);
            this.setCurrentHealth(300);
        }
        else if(version.equalsIgnoreCase("Mighty")){
            this.setAttackPower(150);
            this.setMaximumHealth(400);
            this.setCurrentHealth(400);
        }
    }
    public void doTurn() {                          // Chooses Random Hero From All Heroes
        Random random = new Random();
        int randomIndex = random.nextInt(GameEngine.listOfHeroes.size());
        Hero targetedHero = GameEngine.listOfHeroes.get(randomIndex);
        targetedHero.getDamage(this.attackPower);
        Display.printInEachLine("Thug just attacked " + targetedHero.getName() + " with " + this.getAttackPower() + " power");
        if(targetedHero.isDead()){
            GameEngine.listOfHeroes.remove(targetedHero);
        }
    }
}
