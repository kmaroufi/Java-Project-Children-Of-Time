/**
 * Created by asus-pc on 5/7/2016.
 */
public class Tank extends Enemy {
    public Tank(){

    }
    public void doTurn() {
        for (Hero hero: GameEngine.listOfHeroes) {
            hero.getDamage(this.getAttackPower());
        }
        Console.printInEachLine("Tank just damaged all of your heroes with " + this.getAttackPower() + " power");
    }
}
