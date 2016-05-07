/**
 * Created by asus-pc on 5/7/2016.
 */
public class Tank extends Enemy {
    public Tank(String version){
        this.setClassName("Tank");
        this.setFullName();
        if(version.equals("Weak")){
            this.setAttackPower(30);
            this.setMaximumHealth(400);
            this.setCurrentHealth(400);
        }
        else if(version.equals("Able")){
            this.setAttackPower(90);
            this.setMaximumHealth(500);
            this.setCurrentHealth(500);
        }
    }
    public void doTurn() {
        for (Hero hero: GameEngine.listOfHeroes) {
            hero.getDamage(this.getAttackPower());
        }
        Console.printInEachLine("Tank just damaged all of your heroes with " + this.getAttackPower() + " power");
    }
}
