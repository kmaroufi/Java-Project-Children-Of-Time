import java.util.Random;

/**
 * Created by asus-pc on 5/7/2016.
 */
public class FinalBoss extends Enemy {
    public FinalBoss(){
        this.setClassName("FinalBoss");
        this.setVersion("");
        this.setFullName();
        this.setMaximumHealth(1000);
        this.setCurrentHealth(1000);
    }


    public void doTurn() {
        if(this.getCurrentHealth() > 400){
            this.setAttackPower(150);
        }
        else {
            this.setAttackPower(250);
        }
        Random random = new Random();
        int randomIndex1 = random.nextInt(GameEngine.listOfHeroes.size());
        int randomIndex2 = random.nextInt(GameEngine.listOfHeroes.size());
        while (randomIndex1 == randomIndex2) {
            randomIndex2 = random.nextInt(GameEngine.listOfHeroes.size());
        }
        Hero targetedHero1 = GameEngine.listOfHeroes.get(randomIndex1);
        Hero targetedHero2 = GameEngine.listOfHeroes.get(randomIndex2);
        targetedHero1.getDamage(this.attackPower);
        targetedHero2.getDamage(this.attackPower);
        Display.printInEachLine("Collector just attacked " + targetedHero1.getName() + " with " + this.getAttackPower() + " power");
        Display.printInEachLine("Collector just attacked " + targetedHero2.getName() + " with " + this.getAttackPower() + " power");
        for (Hero hero: GameEngine.listOfHeroes) {
            int randomValue = random.nextInt(3) + 2;
            hero.setCurrentEnergyPoint(hero.getCurrentEnergyPoint() - randomValue);
            Display.printInEachLine("Collector just burned " + randomValue + " energy points from " + hero.getName());
        }
        Display.printInEachLine("Collector has mutated");
    }
}
