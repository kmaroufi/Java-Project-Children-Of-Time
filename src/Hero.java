/**
 * Created by Future on 5/6/2016.
 */
import GUI.Display;

import java.util.*;
import java.util.HashMap;


public class Hero extends HeroClass {
    public static HashMap<String, Hero> mapOfHeroes = new HashMap<String, Hero>();
    private Map<String, SkillItem> mapOfSkillItems = new HashMap<>();
    private ArrayList<SkillItem> listOfSkillItems = new ArrayList<>();
    private Map<String, PerkItem> mapOfPerkItems = new HashMap<>();
    private ArrayList<PerkItem> listOfPerkItems = new ArrayList<>();
    private String name;

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
//
//    public double getFieldValue(String fieldName) {
//        try {
//            return Hero.fieldsMap.get(fieldName).getDouble(this);
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        }
//        return -1;
//    }
//
//    public void setFieldValue(String fieldName, double value) {
//        try {
//            Hero.fieldsMap.get(fieldName).set(this, value);
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        }
//    }

    //------------------------------------------ Constructors

    public Hero(String name,HeroClassHandler heroClassHandler, SoldierHandler soldierHandler){
        super(soldierHandler, heroClassHandler);
        this.setName(name);
        for (Skill skill: this.skills) {
            skill.setOwnerName(name);
        }
        for (Perk perk: this.perks) {
            perk.setOwnerName(name);
        }
    }

    //------------------------------------------ Functions

    public void removeItem(SkillItem skillItem){
        if(hasItem(skillItem)){
            this.mapOfSkillItems.remove(skillItem.getName());
            this.listOfSkillItems.remove(skillItem);
            this.sizeOfOccupiedInventory -= skillItem.getSize();
        }
        else{
            Display.printInEachLine(this.getName() + " Has not This item");
        }
    }

    public void removeItem(PerkItem perkItem){
        if(hasItem(perkItem)){
            this.mapOfPerkItems.remove(perkItem.getName());
            this.listOfPerkItems.remove(perkItem);
            this.sizeOfOccupiedInventory -= perkItem.getSize();
            perkItem.removeEffect();
        }
        else{
            Display.printInEachLine(this.getName() + " Has not This item");
        }
    }

    public void addItem(SkillItem skillItem){
        this.mapOfSkillItems.put(skillItem.getName(), skillItem);
        this.listOfSkillItems.add(skillItem);
        this.sizeOfOccupiedInventory += skillItem.getSize();
    }

    public void addItem(PerkItem perkItem){
        this.mapOfPerkItems.put(perkItem.getName(), perkItem);
        this.listOfPerkItems.add(perkItem);
        this.sizeOfOccupiedInventory += perkItem.getSize();
        perkItem.updateItemEffect(this);
    }

    public int attack(Enemy enemy){
        int finalAttackPower = 0;
        Random random = new Random();
        int criticalHitChance = 0;
        if (this.criticalHitChance != 0) {
            criticalHitChance = (int) (1 / this.criticalHitChance);
        }
        if ((criticalHitChance != 0) && ((criticalHitChance - 1) == random.nextInt(criticalHitChance))) {
            finalAttackPower = (int) (this.attackPower * this.attackPowerRatioDuringAttack *  this.criticalHitDamage);
            enemy.getDamage(finalAttackPower);
            System.out.println("Critical Attack!");
        }
        else {
            finalAttackPower = (int) (this.attackPower * this.attackPowerRatioDuringAttack);
            enemy.getDamage(finalAttackPower);
            System.out.println("Normal Attack!");
        }
        this.setCurrentEnergyPoint(this.currentEnergyPoint - 2);
        return finalAttackPower;
    }

    public void attackOnNonTargetedEnemies(Enemy enemy) {
        ArrayList<Enemy> nonTargetedEnemies = this.selectingNonTargetedEnemiesForAttack.selectingObjects();
        if (nonTargetedEnemies.contains(enemy)) {
            nonTargetedEnemies.remove(enemy);
        }
        Random random = new Random();
        int criticalHitChance = 0;
        if (this.criticalHitChance != 0) {
            criticalHitChance = (int)(1 / this.criticalHitChance);
        }
        for (Enemy nonTargetedEnemy: nonTargetedEnemies) {
            if ((criticalHitChance != 0) && ((criticalHitChance - 1) == random.nextInt(criticalHitChance))) {
                nonTargetedEnemy.getDamage((this.attackPowerOnNonTargetedSoldiers + this.attackPowerRatioOnNonTargetedSoldiers * this.attackPower * this.attackPowerRatioDuringAttack) * this.criticalHitDamage);
            }
            else {
                nonTargetedEnemy.getDamage(this.attackPowerOnNonTargetedSoldiers + this.attackPowerRatioOnNonTargetedSoldiers * this.attackPower * this.attackPowerRatioDuringAttack);
            }
        }
    }

    public void useSkill(String skillName){
        for(int i = 0;i < this.skills.size();i++){                  //finding Skill with SkillName
            if(this.skills.get(i).getName().equals(skillName)){
                this.skills.get(i).useSkill(this);
            }
        }
    }

    public void useSkillItem(String itemName){
        SkillItem skillItem = this.mapOfSkillItems.get(itemName);
        if (skillItem != null) {
            skillItem.useItem();
            return;
        }
        Display.printInEachLine("item Not Found!");
    }

    public <T> boolean upgradeAbility(Player player, String abilityName, T sub){
        for(int i = 0;i < this.skills.size();i++){                  //finding Skill with abilityName
            if(this.skills.get(i).getName().equals(abilityName)){
                return this.skills.get(i).upgrade(player, (SubSkill) sub);
            }
        }
        for(int i = 0;i < this.perks.size();i++){                  //finding perk with abilityName
            if(this.perks.get(i).getName().equals(abilityName)){
                return this.perks.get(i).upgrade(player, (SubPerk) sub);
            }
        }
        return false;
    }

    public void showDescription(){
        Display.printInEachLine(this.getName());
        Display.printInEachLine("Health: " + this.getCurrentHealth() + " / " + this.getMaximumHealth());
        Display.printInEachLine("Magic: " + this.getCurrentMagic() + " / " + this.getMaximumMagic());
        Display.printInEachLine("Energy points: " + this.getCurrentEnergyPoint());
        Display.printInEachLine("Attack power: " + this.getAttackPower());
        System.out.println("Perks:");
        for (Perk perk: this.perks) {
            System.out.println(perk.getName() + " " + perk.getOwnerName());
        }
        System.out.println("Skills:");
        for (Skill skill: this.skills) {
            System.out.println(skill.getName() + " " + skill.getOwnerName());
        }
        for(int i = 0;i < this.skills.size();i++){
            if (this.skills.get(i).getCurrentGrade() == 0)
                continue;
            Display.printInEachLine("Can Cast " + this.skills.get(i).getName() + " for " + this.skills.get(i).getRequiredEnergyPoint() + " energy points, " + this.skills.get(i).getRequiredMagicPoint() + " magic points and a " + this.skills.get(i).getCooldown() + " turn cooldown");
        }
        for (SkillItem skillItem: this.listOfSkillItems) {
            Display.printInEachLine("Can Use " + skillItem.getName() + " for " + skillItem.getRequiredEnergyPoint() + " energy points, " + skillItem.getRequiredMagicPoint() + " magic points and a " + skillItem.getCooldown() + " turn cooldown");
        }
        //TODO
    }

    public void showItems(){
        Display.printf(this.name + " has :");
        if(this.listOfSkillItems.isEmpty() && this.listOfPerkItems.isEmpty()){
            Display.printInEachLine(" Nothing!");
            return;
        }
        for (PerkItem perkItem: this.listOfPerkItems) {
            Display.printf(perkItem.getName() + " worth" + perkItem.getWorth() + "dollars , ");
        }
        for (SkillItem skillItem: this.listOfSkillItems) {
            Display.printf(skillItem.getName() + " worth" + skillItem.getWorth() + "dollars , ");
        }
        Display.printInEachLine("");
    }

    public boolean hasItem(SkillItem skillItem){
        return this.mapOfSkillItems.containsKey(skillItem.getName());
    }

    public boolean hasItem(PerkItem perkItem){
        return this.mapOfPerkItems.containsKey(perkItem.getName());
    }

    public <T> T getItem(String name) {
        SkillItem skillItem = this.mapOfSkillItems.get(name);
        if (skillItem != null) {
            return (T) skillItem;
        }
        else {
            return (T) this.mapOfPerkItems.get(name);
        }
    }

    //------------------------------------------ Getter && Setters

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<String, SkillItem> getMapOfSkillItems() {
        return mapOfSkillItems;
    }

    public void setMapOfSkillItems(Map<String, SkillItem> mapOfSkillItems) {
        this.mapOfSkillItems = mapOfSkillItems;
    }

    public ArrayList<SkillItem> getListOfSkillItems() {
        return listOfSkillItems;
    }

    public void setListOfSkillItems(ArrayList<SkillItem> listOfSkillItems) {
        this.listOfSkillItems = listOfSkillItems;
    }

    public Map<String, PerkItem> getMapOfPerkItems() {
        return mapOfPerkItems;
    }

    public void setMapOfPerkItems(Map<String, PerkItem> mapOfPerkItems) {
        this.mapOfPerkItems = mapOfPerkItems;
    }

    public ArrayList<PerkItem> getListOfPerkItems() {
        return listOfPerkItems;
    }

    public void setListOfPerkItems(ArrayList<PerkItem> listOfPerkItems) {
        this.listOfPerkItems = listOfPerkItems;
    }

    public String toString() {
        return this.name;
    }
}
