import java.util.ArrayList;
import java.util.Random;

/**
 * Created by asus-pc on 5/7/2016.
 */
public class FinalBoss extends Enemy {
    public FinalBoss(){
        this.setClassName("FinalBoss");
        this.setFullName();
        this.setMaximumHealth(1000);
    }

    public void doTurn() {
        if(this.getCurrentHealth() > 400){
            this.setAttackPower(150);
        }
        else {
            this.setAttackPower(250);
        }
        Random random = new Random();
        int randomIndex1 = random.nextInt(Hero.listOfHeroes.size());
        int randomIndex2 = random.nextInt(Hero.listOfHeroes.size());
        while (randomIndex1 == randomIndex2) {
            randomIndex2 = random.nextInt(Hero.listOfHeroes.size());
        }
        Hero targetedHero1 = Hero.listOfHeroes.get(randomIndex1);
        Hero targetedHero2 = Hero.listOfHeroes.get(randomIndex2);
        targetedHero1.getDamage(this.attackPower);
        targetedHero2.getDamage(this.attackPower);
        Console.printInEachLine("Collector just attacked " + targetedHero1.getName() + " with " + this.getAttackPower() + " power");
        Console.printInEachLine("Collector just attacked " + targetedHero2.getName() + " with " + this.getAttackPower() + " power");
        for (Hero hero: Hero.listOfHeroes) {
            int randomValue = random.nextInt(3) + 2;
            hero.setCurrentEnergyPoint(hero.getCurrentEnergyPoint() - randomValue);
            Console.printInEachLine("Collector just burned " + randomValue + " energy points from " + hero.getName());
        }
        Console.printInEachLine("Collector has mutated");
    }
}
