/**
 * Created by Future on 5/6/2016.
 */
import GUI.Display;

import java.lang.reflect.Field;
import java.util.*;
public class Enemy extends Soldier{
    public static HashMap<String , Enemy> mapOfEnemies = new HashMap<>();        // ArrayList is Wrong
    private String version;                         // (Weak-Able-Mighty)
    protected SelectingObjectsDetail<Hero> selectingNonTargetedHeroForAttack;
    protected Skill attackSkill;
//    private Integer isAstounded;
    protected String name;                            // Full name of An Enemy   ----->     (ClassName is Like Thug)
    private CraftingRequirement prizeOfKillThisEnemy;
    private int levelOfIntelligence;

//    private static Map<String, Field> fieldsMap = new HashMap<>();
//
//    static {
//        Class clazz = Hero.class;
//        while (clazz != null) {
//            Field[] fields = clazz.getDeclaredFields();
//            for (Field field : fields) {
//                fieldsMap.put(field.getName(), field);
//            }
//            clazz = clazz.getSuperclass();
//        }
//    }

    //---------------------------------------------------- Constructors

    public Enemy(SoldierHandler soldierHandler, String version, SelectingObjectsDetail<Hero> selectingNonTargetedHeroForAttack, Skill attackSkill, String name, CraftingRequirement prizeOfKillThisEnemy, int levelOfIntelligence) {
        super(soldierHandler);
        this.version = version;
        this.selectingNonTargetedHeroForAttack = selectingNonTargetedHeroForAttack;
        this.attackSkill = attackSkill;
        this.name = name;
        this.prizeOfKillThisEnemy = prizeOfKillThisEnemy;
        this.levelOfIntelligence = levelOfIntelligence;
    }

    //---------------------------------------------------- Functions
    public void showDescription(){
        Display.printInEachLine(this.getName());
        Display.printInEachLine("Health: " + this.getCurrentHealth() + " / " + this.getMaximumHealth());
    }

    public void attack() {
        if (this.attackSkill == null) {
            return;
        }
        int previousValueOfAttackPower = this.attackPower;
        this.attackPower *= this.attackPowerRatioDuringAttack;
        this.attackSkill.useSkill(this);
        this.attackPower = previousValueOfAttackPower;
        ArrayList<Hero> targetedHeroes = (ArrayList<Hero>) this.attackSkill.getCurrentNode().getData().getSubSkillComponents().get(0).listOfEffectedObjects;
        attackOnNonTargetedEnemies(targetedHeroes);
        this.setCurrentEnergyPoint(this.currentEnergyPoint - 2);
    }

    public void attackOnNonTargetedEnemies(ArrayList<Hero> targetedHeroes) {
        ArrayList<Hero> nonTargetedHeroes = this.selectingNonTargetedHeroForAttack.selectingObjects();
        for (Hero hero: targetedHeroes) {
            if (nonTargetedHeroes.contains(hero)) {
                nonTargetedHeroes.remove(hero);
            }
        }
        if (nonTargetedHeroes.size() == 0) {
            return;
        }
        for (Hero hero: nonTargetedHeroes) {
            hero.getDamage(this.attackPowerOnNonTargetedSoldiers + this.attackPowerRatioOnNonTargetedSoldiers * this.attackPower * this.attackPowerRatioDuringAttack);
        }
    }

    public void doTurn() {
        if (levelOfIntelligence == 1) {
            attack();
        }
        else if (levelOfIntelligence == 2) {
            attack();
            Random random = new Random();
            ArrayList<Skill> listOfSkills = new ArrayList<>();
            listOfSkills.addAll(this.skills);
            while (listOfSkills.size() != 0) {
                Skill skill = listOfSkills.get(random.nextInt(skills.size()));
                if ((skill.getRequiredEnergyPoint() <= this.currentEnergyPoint) && (skill.getRequiredMagicPoint() <= this.currentMagic)) {
                    skill.useSkill(this);
                    break;
                }
                listOfSkills.remove(skill);
            }
        }
        else if (levelOfIntelligence == 3) {
            Random random = new Random();
            ArrayList<Skill> listOfSkills = new ArrayList<>();
            listOfSkills.addAll(this.skills);
            while (this.currentEnergyPoint > 0) {
                attack();
                while (listOfSkills.size() != 0) {
                    Skill skill = listOfSkills.get(random.nextInt(skills.size()));
                    if ((skill.getRequiredEnergyPoint() <= this.currentEnergyPoint) && (skill.getRequiredMagicPoint() <= this.currentMagic)) {
                        skill.useSkill(this);
                        break;
                    }
                    listOfSkills.remove(skill);
                }
            }
        }
    }

    public void setFullName(){
        int numberOfEnemiesWithSameName = 0;
        for(Enemy e : GameEngine.listOfEnemies){
            String formalNameE = e.getVersion() + " " + e.getClassName();
            String formalNameThis = this.getVersion() + " " + this.getClassName();
            if(formalNameE.equals(formalNameThis)){
                numberOfEnemiesWithSameName++;
            }
        }
        this.setName(this.getVersion() + " " + this.getClassName() + " " + (numberOfEnemiesWithSameName + 1));

    }
    //---------------------------------------------------- Getter && Setters
    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getName() {
        return name;
    }                               // gets The Full Name

    public void setName(String name) {
        this.name = name;
    }

    public String toString() {
        return this.name;
    }
}
