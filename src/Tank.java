/**
 * Created by asus-pc on 5/7/2016.
 */
public class Tank extends Enemy {
    public void doTurn() {
        for (Hero hero: Hero.listOfHeroes) {
            hero.getDamage(this.attackPower);
        }
    }
}
