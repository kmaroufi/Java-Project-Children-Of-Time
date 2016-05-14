/**
 * Created by Future on 5/6/2016.
 */
import java.util.*;
public class Hero extends HeroClass {
    public static HashMap<String, Hero> mapOfHeroes = new HashMap<String, Hero>();
    public static ArrayList<Skill> listOfActiveGlobalSkills = new ArrayList<Skill>();
    public static ArrayList<Perk> listOfActiveGlobalPerks = new ArrayList<Perk>();
    private HashMap<String, Item> listOfItems = new HashMap<String, Item>();
    private ArrayList<Item> items = new ArrayList<Item>();
    private String name;

    //------------------------------------------ Constructors
    public Hero(){

    }

    public Hero(String name,HeroClassHandler heroClassHandler){
        super(heroClassHandler);
        this.setName(name);
    }

    public Hero(String name,HeroClass heroClass){
        super(heroClass);
        this.setName(name);
    }

    //------------------------------------------ Functions
    public void addSkill(Skill skill){
        this.skills.add(skill);
    }

    public void addPerk(Perk perk){
        this.perks.add(perk);
    }

    public void attack(Enemy enemy){
        Random random = new Random();
        int criticalHitChance = (int)(1 / this.criticalHitChance);
        if ((criticalHitChance - 1) == random.nextInt(criticalHitChance)) {
            enemy.getDamage(this.attackPower * this.attackPowerRatioDuringAttack * this.criticalHitDamage);
        }
        else {
            enemy.getDamage(this.attackPower * this.attackPowerRatioDuringAttack);
        }
        ArrayList<Enemy> enemies = new ArrayList<Enemy>();
        enemies.addAll(GameEngine.listOfEnemies);
        enemies.remove(enemy);
        for (int i = 0; i < this.numberOfNonTargetedEnemyEffected; i++) {
            int randomIndex = random.nextInt(enemies.size());
            if ((criticalHitChance - 1) == random.nextInt(criticalHitChance)) {
                enemies.get(randomIndex).getDamage((this.attackPowerOnNonTargetedEnemy + this.attackPowerRatioOnNonTargetedEnemy * this.attackPower) * this.criticalHitDamage);
            }
            else {
                enemies.get(randomIndex).getDamage(this.attackPowerOnNonTargetedEnemy + this.attackPowerRatioOnNonTargetedEnemy * this.attackPower);
            }
            enemies.remove(randomIndex);
        }

    }

    public void useSkill(String skillName){
        for(int i = 0;i < this.skills.size();i++){                  //finding Skill with SkillName
            if(this.skills.get(i).getName().equals(skillName)){
                this.skills.get(i).useSkill(this.skills.get(i).relatedSoldiers,this);
            }
        }
    }

    public void upgradeAbility(Player player,String abilityName){
        for(int i = 0;i < this.skills.size();i++){                  //finding Skill with abilityName
            if(this.skills.get(i).getName().equals(abilityName)){
                this.skills.get(i).upgrade(player);
                return;
            }
        }
        for(int i = 0;i < this.perks.size();i++){                  //finding perk with abilityName
            if(this.perks.get(i).getName().equals(abilityName)){
                this.perks.get(i).upgrade(player);
                return;
            }
        }
    }

    public void useItem(String itemName){
        for(int i = 0;i < this.items.size();i++){                  //finding item with itemName
            if(this.items.get(i).getName().equals(itemName)){
                this.items.get(i).useItem();                        // using that item
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
        for (Perk perk: this.perks) {
            System.out.println(perk.getName());
        }
        for(int i = 0;i < this.skills.size();i++){
            if (this.skills.get(i).getCurrentGrade() == 0)
                continue;
            Display.printInEachLine("Can Cast " + this.skills.get(i).getName() + " for " + this.skills.get(i).getRequiredEnergyPoint()[this.skills.get(i).currentGrade - 1] + " energy points, " + this.skills.get(i).getRequiredMagicPoint()[this.skills.get(i).currentGrade - 1] + " magic points and a " + this.skills.get(i).getCooldown()[this.skills.get(i).currentGrade - 1] + " turn cooldown");
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
        Display.printInEachLine(this.name + " has :");
        for(Item item : this.items){
            Display.printInEachLine(item.getName() + " worth " + (item.getWorth()/2.0) + " dollars ,");
        }
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
