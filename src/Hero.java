/**
 * Created by Future on 5/6/2016.
 */
import java.lang.reflect.Field;
import java.util.*;
import java.util.HashMap;
import java.util.Map;


public class Hero extends HeroClass {
    public static HashMap<String, Hero> mapOfHeroes = new HashMap<String, Hero>();
    public static ArrayList<Skill> listOfActiveGlobalSkills = new ArrayList<Skill>();
    public static ArrayList<Perk> listOfActiveGlobalPerks = new ArrayList<Perk>();
    private HashMap<String, Item> listOfItems = new HashMap<String, Item>();
    private ArrayList<Item> items = new ArrayList<Item>();
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

    public Hero(){

    }

    public Hero(String name,HeroClassHandler heroClassHandler){
        super(heroClassHandler);
        this.setName(name);
        for (Skill skill: this.skills) {
            skill.setOwnerName(name);
        }
        for (Perk perk: this.perks) {
            perk.setOwnerName(name);
        }
    }

    public Hero(String name,HeroClass heroClass){
        super(heroClass);
        this.setName(name);
    }

    //------------------------------------------ Functions
    public void removeItem(Item item){
        if(hasItem(item)){
            this.items.remove(item);
            this.listOfItems.remove(item);
            this.sizeOfOccupiedInventory -= item.getSize();
        }
        else{
            Display.printInEachLine(this.getName() + " Has not This item");
        }
    }

    public void addItem(Item item){
        this.listOfItems.put(item.getName(), item);
        this.items.add(item);
        this.sizeOfOccupiedInventory += item.getSize();
    }

    public void addSkill(Skill skill){
        this.skills.add(skill);
    }

    public void addPerk(Perk perk){
        this.perks.add(perk);
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
        return finalAttackPower;
    }

    public void attackOnNonTargetedEnemies(Enemy enemy) {
        Random random = new Random();
        int criticalHitChance = 0;
        if (this.criticalHitChance != 0) {
            criticalHitChance = (int)(1 / this.criticalHitChance);
        }
        if (this.numberOfNonTargetedEnemyEffected == -5) {
            for (Enemy nonTargetedEnemy: GameEngine.listOfEnemies) {
                if (nonTargetedEnemy == enemy)
                    continue;
                if ((criticalHitChance != 0) && ((criticalHitChance - 1) == random.nextInt(criticalHitChance))) {
                    nonTargetedEnemy.getDamage((this.attackPowerOnNonTargetedEnemy + this.attackPowerRatioOnNonTargetedEnemy * this.attackPower * this.attackPowerRatioDuringAttack) * this.criticalHitDamage);
                }
                else {
                    nonTargetedEnemy.getDamage(this.attackPowerOnNonTargetedEnemy + this.attackPowerRatioOnNonTargetedEnemy * this.attackPower * this.attackPowerRatioDuringAttack);
                }
            }
        }
        else {
            ArrayList<Enemy> enemies = new ArrayList<Enemy>();
            enemies.addAll(GameEngine.listOfEnemies);
            enemies.remove(enemy);
            for (int i = 0; i < this.numberOfNonTargetedEnemyEffected; i++) {
                int randomIndex = random.nextInt(enemies.size());
                if ((criticalHitChance != 0) && ((criticalHitChance - 1) == random.nextInt(criticalHitChance))) {
                    enemies.get(randomIndex).getDamage((this.attackPowerOnNonTargetedEnemy + this.attackPowerRatioOnNonTargetedEnemy * this.attackPower * this.attackPowerRatioDuringAttack) * this.criticalHitDamage);
                }
                else {
                    enemies.get(randomIndex).getDamage(this.attackPowerOnNonTargetedEnemy + this.attackPowerRatioOnNonTargetedEnemy * this.attackPower * this.attackPowerRatioDuringAttack);
                }
                enemies.remove(randomIndex);
            }
        }
    }

    public void useSkill(String skillName, ArrayList<String> fromCommandLine){
        for(int i = 0;i < this.skills.size();i++){                  //finding Skill with SkillName
            if(this.skills.get(i).getName().equals(skillName)){
                this.skills.get(i).useSkill(this, fromCommandLine);
            }
        }
    }

    public boolean upgradeAbility(Player player,String abilityName){
        for(int i = 0;i < this.skills.size();i++){                  //finding Skill with abilityName
            if(this.skills.get(i).getName().equals(abilityName)){
                return this.skills.get(i).upgrade(player);
            }
        }
        for(int i = 0;i < this.perks.size();i++){                  //finding perk with abilityName
            if(this.perks.get(i).getName().equals(abilityName)){
                return this.perks.get(i).upgrade(player);
            }
        }
        return false;
    }

    public void useItem(String itemName, ArrayList<Hero> soldiers){
        for(int i = 0;i < this.items.size();i++){                  //finding item with itemName
            if(this.items.get(i).getName().equals(itemName)){
                this.items.get(i).useItem(soldiers);                        // using that item
                return;
            }
        }
        Display.printInEachLine("item Not Found!");
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
        for(int i = 0;i < this.items.size();i++){
            try {
                Display.printInEachLine("Can Use " + this.items.get(i).getName() + " for " + this.items.get(i).getRequiredEnergyPoint() + " energy points, " + this.items.get(i).getRequiredMagicPoint() + " magic points and a " + this.items.get(i).getCooldown() + " turn cooldown");
            }catch (Exception e){
                continue;
            }
        }
    }

    public boolean hasPerk(Perk perk){
        for(int i = 0;i < this.perks.size();i++){
            if(perk.equals(this.perks.get(i))){
                return true;
            }
        }
        return false;
    }

    public void showItems(){
        Display.printf(this.name + " has :");
        if(this.items.isEmpty()){
            Display.printInEachLine(" Nothing!");
            return;
        }
        for(Item item : this.items){
            if (item.isInstantlyUsed()) {
                Display.printf(item.getName() + " worth " + (item.getWorth() / 2.0) + "dollars , ");
            }
            else {
                Display.printf(item.getName() + " worth " + (item.getWorth() / 2.0 * (item.getRemainingTimeOfUsed() / (double)item.getMaximumTimeOfUsed())) + " dollars , ");
            }
        }
        Display.printInEachLine("");
    }

    public boolean hasItem(Item item){
        for(int i = 0;i < this.items.size();i++){
            if(this.items.get(i).equals(item)){
                return true;
            }
        }
        return false;
    }

    public boolean hasSkill(Skill skill){
        for(int i = 0;i < this.skills.size();i++){
            if(this.skills.get(i).equals(skill)){
                return true;
            }
        }
        return false;
    }

    public Item getItem(String name) {
        return this.listOfItems.get(name);
    }

    //------------------------------------------ Getter && Setters

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HashMap<String, Item> getListOfItems() {
        return listOfItems;
    }

    public void setListOfItems(HashMap<String, Item> listOfItems) {
        this.listOfItems = listOfItems;
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public void setItems(ArrayList<Item> items) {
        this.items = items;
    }

}
