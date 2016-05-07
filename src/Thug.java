import java.util.Random;

/**
 * Created by asus-pc on 5/7/2016.
 */
public class Thug extends Enemy {
    public void doTurn() {
        Random random = new Random();
        int randomIndex = random.nextInt(Hero.listOfHeroes.size());
        Hero targetedHero = Hero.listOfHeroes.get(randomIndex);
        targetedHero.getDamage(this.attackPower);
    }
}
