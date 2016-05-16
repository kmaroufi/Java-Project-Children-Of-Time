import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import static javafx.scene.input.KeyCode.T;

/**
 * Created by asus-pc on 5/5/2016.
 */
public class Skill<E> extends Ability implements Cloneable{
    public static Map<String, Skill> listOfSkills = new HashMap<String, Skill>();
    private ArrayList<Property<E>> propertiesOfRelatedSoldiers;
    private Property<Hero> propertiesOfUser;
    protected Map<E, Time> mapOfEffectedSoldiers = new HashMap<E, Time>();
    private int nonTargetedEnemy;
    private boolean isRepeated;
    private Time timeOfEffecting;
    private ArrayList<String> blackList;
    private int[] cooldown;
    private int remainingCooldown;
    private boolean isDependsRelatedSoldiersSelectingOnPlayer;
    private boolean canStackUp;
    private boolean isUsed;
    private int[] requiredEnergyPoint;
    private int[] requiredMagicPoint;

    //---------------------------------------------------------------- Functions

    Skill(SkillHandler skillHandler, AbilityHandler<E> abilityHandler) {
        super(abilityHandler);
        setPropertiesOfRelatedSoldiers(skillHandler.getPropertiesOfRelatedSoldiers());
        setPropertiesOfUser(skillHandler.getPropertiesOfUser());
        setNonTargetedEnemy(skillHandler.getNonTargetedEnemy());
        setRepeated(skillHandler.isRepeated());
        setTimeOfEffecting(skillHandler.getTimeOfEffecting());
        setBlackList(skillHandler.getBlackList());
        setCooldown(skillHandler.getCooldown());
        setRemainingCooldown(0);
        setDependsRelatedSoldiersSelectingOnPlayer(skillHandler.isDependsRelatedSoldiersSelectingOnPlayer());
        setCanStackUp(skillHandler.isCanStackUp());
        setUsed(false);
        setRequiredEnergyPoint(skillHandler.getRequiredEnergyPoint());
        setRequiredMagicPoint(skillHandler.getRequiredMagicPoint());
    }

    protected Skill<E> clone() throws CloneNotSupportedException {
        Skill<E> skill = (Skill<E>) super.clone();
        ArrayList<Property<E>> cloneOfPropertiesOfRelatedSoldiers = new ArrayList<>();
        for (Property<E> property: this.propertiesOfRelatedSoldiers) {
            cloneOfPropertiesOfRelatedSoldiers.add(property.clone());
        }
        skill.setPropertiesOfRelatedSoldiers(cloneOfPropertiesOfRelatedSoldiers);
        if (this.propertiesOfUser != null)
            skill.setPropertiesOfUser(this.propertiesOfUser.clone());
        skill.setMapOfEffectedSoldiers(new HashMap<>());
        return skill;
    }

    public boolean isActivated() {
       return false;
    }

    public void useSkill(ArrayList<E> relatedSoldiers, Hero userHero) {
        this.choosingRelatedSoldiers();
        if (this.remainingCooldown != 0) {
            return;
        }
        for (E soldier: relatedSoldiers) {
            if (this.effectedSoldiers.contains(soldier) && (this.canStackUp == false))
                continue;
            for (Property property: this.propertiesOfRelatedSoldiers) {
                property.effect(soldier, Hero.mapOfHeroes.get(this.ownerName), userHero);
            }
            this.effectedSoldiers.add(soldier);
            this.mapOfEffectedSoldiers.put(soldier, new Time(this.timeOfEffecting));
        }
        if (this.propertiesOfUser != null)
            this.propertiesOfUser.effect(userHero, Hero.mapOfHeroes.get(this.ownerName), userHero);
        this.remainingCooldown = this.cooldown[this.currentGrade];
        userHero.setCurrentEnergyPoint(userHero.getCurrentEnergyPoint() - this.requiredEnergyPoint[this.currentGrade - 1]);
        userHero.setCurrentMagic(userHero.getCurrentMagic() - this.requiredMagicPoint[this.currentGrade - 1]);

    }

    public void removeEffect() {
        ArrayList<Integer> indexOfRemovedSoldiers = new ArrayList<Integer>();
        for (int i = 0; i < this.effectedSoldiers.size(); i++) {
            E soldier = (E) this.effectedSoldiers.get(i);
            if (this.mapOfEffectedSoldiers.get(soldier).isTimePassed()) {
                for (Property property: this.propertiesOfRelatedSoldiers) {
                    property.removeEffect(soldier);
                }
                indexOfRemovedSoldiers.add(new Integer(i));
                this.mapOfEffectedSoldiers.remove(soldier);
            }
        }
        for (int index: indexOfRemovedSoldiers) {
            this.effectedSoldiers.remove(index);
        }
    }

    public void showDescription(){
        Display.printInEachLine(this.getDescription());
    }

    public void choosingRelatedSoldiers(Enemy enemy,Hero hero) {
        this.relatedSoldiers.clear();
        if (this.hasEffectedOnEnemy) {
            this.relatedSoldiers.add(enemy);
            return;
        }
        this.relatedSoldiers.add(hero);
    }

    public boolean equals(Skill skill){
        if(this.name.equals(skill.getName())){
            return true;
        }
        return false;
    }

    public void choosingRelatedSoldiers() {
        this.relatedSoldiers.clear();
        if (this.hasEffectedOnEnemy) {
            if (this.numberOfRelatedSoldiers == -5)
                this.relatedSoldiers.addAll(GameEngine.listOfEnemies);
            else if (this.isRandomSoldierSelecting) {
                ArrayList<Enemy> enemies = new ArrayList<Enemy>();
                enemies.addAll(GameEngine.listOfEnemies);
                for (int i = 0; i < this.numberOfRelatedSoldiers; i++) {
                    Random random = new Random();
                    int randomIndex = random.nextInt(enemies.size());
                    this.relatedSoldiers.add(enemies.get(randomIndex));
                    enemies.remove(randomIndex);
                }
            }
            else if (this.isDependsRelatedSoldiersSelectingOnPlayer){
                ArrayList<String> nameOfEnemies = new ArrayList<>();
                while (true) {
                    Display.printInEachLine("please determine your target! At end, enter 0");
                    String input = Display.getString();
                    if (input.equals("0"))
                        break;
                    int cond = 0;
                    for (Enemy enemy: GameEngine.listOfEnemies) {
                        if (enemy.getName().equals(input)) {
                            cond = 1;
                            break;
                        }
                    }
                    if (cond == 1)
                        nameOfEnemies.add(input);
                    else {
                        Display.printInEachLine("Please enter valid command!");
                    }
                }
                for (String nameOfEnemy: nameOfEnemies) {
                    this.relatedSoldiers.add(Enemy.mapOfEnemies.get(nameOfEnemy));
                }
            }
        }
        else {
            if (this.numberOfRelatedSoldiers == GameEngine.listOfHeroes.size())
                this.relatedSoldiers.addAll(GameEngine.listOfHeroes);
            else if (this.isRandomSoldierSelecting) {
                ArrayList<Hero> heroes = new ArrayList<Hero>();
                heroes.addAll(GameEngine.listOfHeroes);
                for (int i = 0; i < this.numberOfRelatedSoldiers; i++) {
                    Random random = new Random();
                    int randomIndex = random.nextInt(heroes.size());
                    this.relatedSoldiers.add(heroes.get(randomIndex));
                    heroes.remove(randomIndex);
                }
            }
            else if (this.isDependsRelatedSoldiersSelectingOnPlayer){
                ArrayList<String> nameOfHeroes = new ArrayList<>();
                while (true) {
                    Display.printInEachLine("please determine your target! At end, enter 0");
                    String input = Display.getString();
                    if (input.equals("0"))
                        break;
                    int cond = 0;
                    for (Hero hero: GameEngine.listOfHeroes) {
                        if (hero.getName().equals(input)) {
                            cond = 1;
                            break;
                        }
                    }
                    if (cond == 1)
                        nameOfHeroes.add(input);
                    else {
                        Display.printInEachLine("Please enter valid command!");
                    }
                }
                for (String nameOfHero: nameOfHeroes) {
                    this.relatedSoldiers.add(Hero.mapOfHeroes.get(nameOfHero));
                }
            }
            else {
                this.relatedSoldiers.add(Hero.mapOfHeroes.get(this.ownerName));
            }
        }
    }

    public void upgrade(Player player) {

        if (this.currentGrade == this.numberOfGrades)
            return;

        for (String nameOfAbility: (ArrayList<String>)this.nameOfNecessaryAbilities.get(this.currentGrade + 1)) {
            if (Ability.listOfAbilities.get(nameOfAbility).equals("skill")) {
                for (Skill skill: Hero.mapOfHeroes.get(this.ownerName).getSkills()) {
                    if (skill.getName().equals(nameOfAbility)) {
                        if (skill.getCurrentGrade() > ((Map<String, Integer>)this.gradeOfNecessaryAbilities.get(this.currentGrade + 1)).get(this.currentGrade + 1))
                            return;
                        break;
                    }
                }
            }
            if (Ability.listOfAbilities.get(nameOfAbility).equals("perk")) {
                for (Perk perk: Hero.mapOfHeroes.get(this.ownerName).getPerks()) {
                    if (perk.getName().equals(nameOfAbility)) {
                        if (perk.getCurrentGrade() > ((Map<String, Integer>)this.gradeOfNecessaryAbilities.get(this.currentGrade + 1)).get(this.currentGrade + 1))
                            return;
                        break;
                    }
                }
            }
        }

        this.currentGrade += 1;
        player.setXp(player.getXp() - this.costOfUpgrade[this.currentGrade]);

        for (Property<E> property: this.propertiesOfRelatedSoldiers) {
            property.setCurrentGrade(this.currentGrade);
        }
        this.propertiesOfUser.setCurrentGrade(this.currentGrade);

    }
    //---------------------------------------------------- Getter && Setters


    public Property getPropertiesOfUser() {
        return propertiesOfUser;
    }

    public void setPropertiesOfUser(Property propertiesOfUser) {
        this.propertiesOfUser = propertiesOfUser;
    }

    public Map<E, Time> getMapOfEffectedSoldiers() {
        return mapOfEffectedSoldiers;
    }

    public void setMapOfEffectedSoldiers(Map<E, Time> mapOfEffectedSoldiers) {
        this.mapOfEffectedSoldiers = mapOfEffectedSoldiers;
    }

    public ArrayList<String> getBlackList() {
        return blackList;
    }

    public void setBlackList(ArrayList<String> blackList) {
        this.blackList = blackList;
    }

    public ArrayList<Property<E>> getPropertiesOfRelatedSoldiers() {
        return propertiesOfRelatedSoldiers;
    }

    public void setPropertiesOfRelatedSoldiers(ArrayList<Property<E>> propertiesOfRelatedSoldiers) {
        this.propertiesOfRelatedSoldiers = propertiesOfRelatedSoldiers;
    }

    public int getNonTargetedEnemy() {
        return nonTargetedEnemy;
    }

    public void setNonTargetedEnemy(int nonTargetedEnemy) {
        this.nonTargetedEnemy = nonTargetedEnemy;
    }

    public boolean isRepeated() {
        return isRepeated;
    }

    public void setRepeated(boolean repeated) {
        isRepeated = repeated;
    }

    public int[] getCooldown() {
        return cooldown;
    }

    public void setCooldown(int[] cooldown) {
        this.cooldown = cooldown;
    }

    public int getRemainingCooldown() {
        return remainingCooldown;
    }

    public void setRemainingCooldown(int remainingCooldown) {
        this.remainingCooldown = remainingCooldown;
    }

    public boolean isUsed() {
        return isUsed;
    }

    public void setUsed(boolean used) {
        isUsed = used;
    }

    public int[] getRequiredEnergyPoint() {
        return requiredEnergyPoint;
    }

    public void setRequiredEnergyPoint(int[] requiredEnergyPoint) {
        this.requiredEnergyPoint = requiredEnergyPoint;
    }

    public int[] getRequiredMagicPoint() {
        return requiredMagicPoint;
    }

    public void setRequiredMagicPoint(int[] requiredMagicPoint) {
        this.requiredMagicPoint = requiredMagicPoint;
    }

    public Time getTimeOfEffecting() {
        return timeOfEffecting;
    }

    public void setTimeOfEffecting(Time timeOfEffecting) {
        this.timeOfEffecting = timeOfEffecting;
    }

    public boolean isDependsRelatedSoldiersSelectingOnPlayer() {
        return isDependsRelatedSoldiersSelectingOnPlayer;
    }

    public void setDependsRelatedSoldiersSelectingOnPlayer(boolean dependsRelatedSoldiersSelectingOnPlayer) {
        isDependsRelatedSoldiersSelectingOnPlayer = dependsRelatedSoldiersSelectingOnPlayer;
    }

    public boolean isCanStackUp() {
        return canStackUp;
    }

    public void setCanStackUp(boolean canStackUp) {
        this.canStackUp = canStackUp;
    }

}
