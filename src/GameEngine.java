import GUI.Display;
import javafx.util.Pair;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;

/**
 * Created by asus-pc on 5/6/2016.
 */
public class GameEngine {
    private Player player;
    private boolean isCustomGame = false;
    public static ArrayList<Enemy> listOfEnemies = new ArrayList<>();
    public static ArrayList<HeroClass> listOfHeroClasses = new ArrayList<>();
    public static ArrayList<Hero> listOfHeroes = new ArrayList<>();
    public static ArrayList<Skill> listOfSkills = new ArrayList<>();
    public static ArrayList<Perk> listOfPerks = new ArrayList<>();
    public static ArrayList<Shop> shops = new ArrayList<>();
    private int NumberOfBattle;                     // which battle we are in(1-2-3-4-5)
    private String levelOfGame;                     // level of Game(Easy-Medium-Hard)
    private int[] rewardedXP = new int[4];
    private int[] rewardedMoney = new int[4];
    private Hero currentHeroAttacker;
    private Enemy currentEnemyDefender;
    public static ArrayList<SkillItem> listOfRemovedSkillItemsButHaveEffect;

    //------------------------------------------ Functions
    public void showHeroesTraits(String name){
        for(Hero hero : this.listOfHeroes){
            if(hero.getName().equalsIgnoreCase(name)){
                Display.printInEachLine(hero.getName());
                Display.printInEachLine("Health: " + hero.getCurrentHealth() + " / " + hero.getMaximumHealth());
                Display.printInEachLine("Magic: " + hero.getCurrentMagic() + " / " + hero.getMaximumMagic());
                Display.printInEachLine("Energy points: " + hero.getCurrentEnergyPoint());
                Display.printInEachLine("Attack power: " + hero.getAttackPower());
                Display.printInEachLine("criticalHitChance: " + hero.getCriticalHitChance());
                Display.printInEachLine("criticalHitDamage: " + hero.getCriticalHitDamage());
                Display.printInEachLine("Abilities:");
                for(Skill skill : hero.skills) {
                    try {
                        Display.printInEachLine(skill.getName() + "; current grade: " + skill.getCurrentGrade());
                        skill.showDescription();
                    } catch (Exception e) {
                        continue;
                    }
                }
                for(Perk perk : hero.perks) {
                    try {
                        Display.printInEachLine(perk.getName() + "; current grade: " + perk.getCurrentGrade());
                        perk.showDescription();
                    } catch (Exception e) {
                        continue;
                    }
                }
            }
        }
    }

    public void shoppingCommands(){
        Display.printInEachLine("Welcome To The Shop!");
        while(true) {
            Display.printInEachLine("What Do You Want To Do?");
            Display.printInEachLine("1 - Show List Of Items");
            Display.printInEachLine("2 - Show Hero Team Items");
            Display.printInEachLine("3 - Buy Or Sell Item");
            Display.printInEachLine("4 - Show Your Wealth");
            Display.printInEachLine("5 - Exit Shop");
            int numberEntered = Display.getInteger();
            if (numberEntered == 1) {
                Shop.showItems();
            }
            else if (numberEntered == 2) {
                Display.printInEachLine("Your Team Has: ");
                for (Hero hero : this.listOfHeroes) {
                    hero.showItems();
                }
            }
            else if (numberEntered == 3) {
                Display.printInEachLine("Your current wealth is: " + this.player.getMoney() + " dollars");
                Display.printInEachLine("Enter Your Command:(type \"Next\" For Next Step!)");
                String command = Display.getString();
                for (ItemProperties<PerkItem> itemProperties : Shop.listOfPerkItems) {
                    if (command.equalsIgnoreCase(itemProperties.getItem().getName() + "?")) {                 //(item name) + “?”
                        itemProperties.getItem().showDescription();
                        break;
                    }
                    if (command.contains("Buy " + itemProperties.getItem().getName() + " for ") || command.contains("Sell " + itemProperties.getItem().getName() + " of ")) {
                        for (Hero hero : this.listOfHeroes) {
                            if (command.equalsIgnoreCase("Buy " + itemProperties.getItem().getName() + " for " + hero.getName())) { //“Buy “ + (item name) + “ for “ + (hero name)
                                if ((hero.getInventorySize() - hero.getSizeOfOccupiedInventory()) < itemProperties.getItem().getSize()) {
                                    Display.printInEachLine(hero.getName() + "'s inventory is full");
                                } else if (this.player.getMoney() < itemProperties.getPrice()) {
                                    Display.printInEachLine("You don’t have enough money");
                                } else {
                                    shops.get(0).sellPerkItemToHero(itemProperties, this.player, hero);
                                    Display.printInEachLine(itemProperties.getItem().getName() + " bought successfully, your current wealth is: " + this.player.getMoney());    //(item name) “ bought successfully, your current wealth is: ” + (current money)
                                }
                            } else if (command.equalsIgnoreCase("Sell " + itemProperties.getItem().getName() + " of " + hero.getName())) {//“Sell “ + (item name) + “ of” + (hero name)
                                if (hero.hasItem(itemProperties.getItem())) {
                                    shops.get(0).buyPerkItemFromHero(itemProperties, this.player, hero);
                                    Display.printInEachLine(itemProperties.getItem().getName() + " successfully sold, your current wealth is: " + player.getMoney());//(item name) + “ successfully sold, your current wealth is: “ + (current money)
                                }
                                else {
                                    Display.printInEachLine(hero.getName() + " has not this item!");
                                }
                            }
                        }
                    }
                }
                for (ItemProperties<SkillItem> itemProperties : Shop.listOfSkillItems) {
                    if (command.equalsIgnoreCase(itemProperties.getItem().getName() + "?")) {                 //(item name) + “?”
                        itemProperties.getItem().showDescription();
                        break;
                    }
                    if (command.contains("Buy " + itemProperties.getItem().getName() + " for ") || command.contains("Sell " + itemProperties.getItem().getName() + " of ")) {
                        for (Hero hero : this.listOfHeroes) {
                            if (command.equalsIgnoreCase("Buy " + itemProperties.getItem().getName() + " for " + hero.getName())) { //“Buy “ + (item name) + “ for “ + (hero name)
                                if ((hero.getInventorySize() - hero.getSizeOfOccupiedInventory()) < itemProperties.getItem().getSize()) {
                                    Display.printInEachLine(hero.getName() + "'s inventory is full");
                                } else if (this.player.getMoney() < itemProperties.getPrice()) {
                                    Display.printInEachLine("You don’t have enough money");
                                } else {
                                    shops.get(0).sellSkillItemToHero(itemProperties, this.player, hero);
                                    Display.printInEachLine(itemProperties.getItem().getName() + " bought successfully, your current wealth is: " + this.player.getMoney());    //(item name) “ bought successfully, your current wealth is: ” + (current money)
                                }
                            } else if (command.equalsIgnoreCase("Sell " + itemProperties.getItem().getName() + " of " + hero.getName())) {//“Sell “ + (item name) + “ of” + (hero name)
                                if (hero.hasItem(itemProperties.getItem())) {
                                    shops.get(0).buySkillItemFromHero(itemProperties, this.player, hero);
                                    Display.printInEachLine(itemProperties.getItem().getName() + " successfully sold, your current wealth is: " + player.getMoney());//(item name) + “ successfully sold, your current wealth is: “ + (current money)
                                }
                                else {
                                    Display.printInEachLine(hero.getName() + " has not this item!");
                                }
                            }
                        }
                    }
                }
            }
            else if(numberEntered == 4){
                Display.printInEachLine("Your Current Wealth is: " + this.player.getMoney() + " dollars.");
                continue;
            }
            else if(numberEntered == 5){
                return;
            }
            else{
                Display.printInEachLine("Wrong Number! Try Again!");
                continue;
            }
        }

    }

    public void abilityCastCommands(){
        while(true) {
            Display.printInEachLine("What Do You Want To Do?");
            Display.printInEachLine("1 - Hero Traits");
            Display.printInEachLine("2 - Casting Commands");
            Display.printInEachLine("3 - Finish");
            Display.printInEachLine("Enter An Integer");
            int numberEntered = Display.getInteger();
            if (numberEntered == 1) {
                this.showHeroTeamDescription();
                Display.printInEachLine("Enter Name of Hero:");
                String heroName = Display.getString();
                this.showHeroesTraits(heroName);
            }
            else if(numberEntered == 3){
                return;
            }
            else if(numberEntered == 2) {
                while (true) {
                    for(Hero hero : this.listOfHeroes){
                        hero.setCurrentEnergyPoint(hero.getMaximumEnergyPoint());
                        hero.setCurrentHealth(hero.getMaximumHealth());
                        hero.setCurrentMagic(hero.getMaximumMagic());
                    }
                    Display.printInEachLine("Type Any Commands :(Type 'Next' For skip)");
                    String command = Display.getString();
                    if (command.equalsIgnoreCase("Next")) {
                        break;
                    }
                    if (command.contains("Acquire ")) {//(ability name) (“acquired”/”upgraded”) + “ successfully, your current experience is: ” + (current xp)
                        boolean heroExistence = false;
                        boolean perkExistence = false;
                        boolean skillExistence = false;
                        boolean subPerkExistence = false;
                        boolean subSkillExistence = false;
                        boolean isOnMaxLevel = false;
                        for (Hero hero : this.listOfHeroes) {
                            if (command.contains(hero.getName())) {
                                for (Perk perk : hero.getPerks()) {
                                    if (command.contains(perk.getName())) {
                                        if (perk.getCurrentGrade() == perk.getNumberOfGrades()) {
                                            Display.printInEachLine("This ability cannot be upgraded anymore");
                                            isOnMaxLevel = true;
                                        }
                                        else {
                                            for (SubPerk subPerk: perk.getNextGradeSubPerks()) {
                                                if (command.contains(subPerk.getName())) {
                                                    if (subPerk.getCostOfUpgrade() > this.player.getXp()) {
                                                        Display.printInEachLine("Your experience is insufficient");
                                                    } else if (perk.isAcquire() == false) {
                                                        if (hero.upgradeAbility(this.player, perk.getName(), subPerk)) {
                                                            this.player.setXp(this.player.getXp() - subPerk.getCostOfUpgrade());
                                                            Display.printInEachLine(perk.getName() + " acquired " + "successfully, your current experience is: " + player.getXp());
                                                        }
                                                        else {
                                                            Display.printInEachLine("Required abilities aren't acquired");
                                                        }
                                                    } else {
                                                        if (hero.upgradeAbility(this.player, perk.getName(), subPerk)) {
                                                            this.player.setXp(this.player.getXp() - subPerk.getCostOfUpgrade());
                                                            Display.printInEachLine(perk.getName() + " upgraded " + "successfully, your current experience is: " + player.getXp());
                                                        }
                                                        else {
                                                            Display.printInEachLine("Required abilities aren't acquired");
                                                        }
                                                    }
                                                    subPerkExistence = true;
                                                    break;
                                                }
                                            }
                                        }
                                        perkExistence = true;
                                        break;
                                    }
                                }
                                for (Skill skill : hero.getSkills()) {
                                    if (command.contains(skill.getName())) {
                                        if (skill.getCurrentGrade() == skill.getNumberOfGrades()) {
                                            Display.printInEachLine("This ability cannot be upgraded anymore");
                                            isOnMaxLevel = true;
                                        }
                                        else {
                                            for (SubSkill subSkill: skill.getNextGradeSubSkills()) {
                                                if (command.contains(subSkill.getName())) {
                                                    if (subSkill.getCostOfUpgrade() > this.player.getXp()) {
                                                        Display.printInEachLine("Your experience is insufficient");
                                                    } else if (skill.isAcquire == false) {
                                                        if (hero.upgradeAbility(this.player, skill.getName(), subSkill)) {
                                                            this.player.setXp(this.player.getXp() - subSkill.getCostOfUpgrade());
                                                            Display.printInEachLine(skill.getName() + " acquired " + "successfully, your current experience is: " + player.getXp());
                                                        }
                                                        else {
                                                            Display.printInEachLine("Required abilities aren't acquired");
                                                        }
                                                    } else {
                                                        if (hero.upgradeAbility(this.player, skill.getName(), subSkill)) {
                                                            this.player.setXp(this.player.getXp() - subSkill.getCostOfUpgrade());
                                                            Display.printInEachLine(skill.getName() + " upgraded " + "successfully, your current experience is: " + player.getXp());
                                                        }
                                                        else {
                                                            Display.printInEachLine("Required abilities aren't acquired");
                                                        }
                                                    }
                                                    subSkillExistence = true;
                                                    break;
                                                }
                                            }
                                        }
                                        skillExistence = true;
                                        break;
                                    }
                                }
                                heroExistence = true;
                                break;
                            }
                        }
                        if (heroExistence == false) {
                            Display.printInEachLine("This hero doesn't exist!");
                        } else if ((perkExistence == false) && (skillExistence == false)) {
                            Display.printInEachLine("This hero has't this ability!");
                        } else if ((isOnMaxLevel == false) && (subPerkExistence == false) && (subSkillExistence == false)) {
                            Display.printInEachLine("This ability has't grade with this name!");
                        }
                    }
//                            if (command.contains("Acquire " + perk.getName() + " for ")) {
//                                for (Hero hero : this.listOfHeroes) {
//                                    if (command.equalsIgnoreCase("Acquire " + perk.getName() + " for " + hero.getName())) {
                    else {
                        Display.printInEachLine("Wrong Command!Try Again!");
                    }
                }
            }
            else {
                Display.printInEachLine("Wrong Number! Try Again!");
                continue;
            }
        }
    }

    public void chooseLevelOfGame(){
        Display.printInEachLine("Choose Level Of Game:");
        Display.printInEachLine("1 - Easy");
        Display.printInEachLine("2 - Medium");
        Display.printInEachLine("3 - Hard");
        int choose = Display.getInteger();
        while(true){
            if(choose == 1) {
                this.setLevelOfGame("Easy");
                break;
            }
            else if(choose == 2) {
                this.setLevelOfGame("Medium");
                break;
            }
            else if(choose == 3) {
                this.setLevelOfGame("Hard");
                break;
            }
            else {
                Display.printInEachLine("Wrong Number! Try Again!");
            }
        }
    }

    public void addDefaultAttributes(){            // Adds All Datas in PDF (Fighter-Meryl-......)


        this.setNumberOfBattle(1);                                  //Number of battle

        this.creatingDefaultSkills();
        this.creatingDefaultPerks();

        //Adding Fighter Class
        HeroClassHandler fighterHandler = new HeroClassHandler(200,120,0.1,"Fighter",0,1,1,1,0.05,1,1,1,1,0,0,120,0,6,2);
        fighterHandler.addPerk(Perk.listOfPerks.get("Fight training"));
        fighterHandler.addPerk(Perk.listOfPerks.get("Work out"));
        this.addNewHeroClass(new HeroClass(fighterHandler));
        //Adding Supporter Class
        HeroClassHandler supporterHandler = new HeroClassHandler(220,80,0.05,"Supporter",0,1,1,1,0.1,1,1,1,1,0,0,200,0,5,3);
        supporterHandler.addPerk(Perk.listOfPerks.get("Quick as a bunny"));
        supporterHandler.addPerk(Perk.listOfPerks.get("Magic lessons"));
        this.addNewHeroClass(new HeroClass(supporterHandler));
        //Adding Eley
        Hero eley = null;
        try {
            eley = new Hero("Eley",fighterHandler.clone());
            eley.addSkill(Skill.listOfSkills.get("Overpowered attack").clone());
            eley.addPerk(Perk.listOfPerks.get("Swirling attack").clone());
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        this.addNewHero(eley);
        //Adding Chrome
        Hero chrome = null;
        try {
            chrome = new Hero("Chrome",fighterHandler.clone());
            chrome.addSkill(Skill.listOfSkills.get("Sacrifice").clone());
            chrome.addPerk(Perk.listOfPerks.get("Critical strike").clone());
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        this.addNewHero(chrome);
        //Adding Meryl
        Hero meryl = null;
        try {
            meryl = new Hero("Meryl",supporterHandler.clone());
            meryl.addSkill(Skill.listOfSkills.get("Elixir").clone());
            meryl.addSkill(Skill.listOfSkills.get("Caretaker").clone());
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        this.addNewHero(meryl);
        //Adding Bolti
        Hero bolti = null;
        try {
            bolti = new Hero("Bolti",supporterHandler.clone());
            bolti.addSkill(Skill.listOfSkills.get("Boost").clone());
            bolti.addSkill(Skill.listOfSkills.get("Mana beam").clone());
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        this.addNewHero(bolti);
        //Adding Enemies
//        if(this.getLevelOfGame().equals("Easy")){
//            Thug thug = new Thug("Weak");
//            Angel angel = new Angel("Weak");
//            Tank tank = new Tank("Weak");
//            FinalBoss finalBoss = new FinalBoss();
//            this.listOfEnemies.add(thug);
//            this.listOfEnemies.add(angel);
//            this.listOfEnemies.add(tank);
//            this.listOfEnemies.add(finalBoss);
//        }
//        else if(this.getLevelOfGame().equals("Medium")){
//            Thug thug = new Thug("Able");
//            Angel angel = new Angel("Able");
//            Tank tank = new Tank("Able");
//            FinalBoss finalBoss = new FinalBoss();
//            this.listOfEnemies.add(thug);
//            this.listOfEnemies.add(angel);
//            this.listOfEnemies.add(tank);
//            this.listOfEnemies.add(finalBoss);
//        }
//        else if(this.getLevelOfGame().equals("Hard")){
//            Thug thug = new Thug("Mighty");
//            Angel angel = new Angel("Able");
//            Tank tank = new Tank("Able");
//            FinalBoss finalBoss = new FinalBoss();
//            this.listOfEnemies.add(thug);
//            this.listOfEnemies.add(angel);
//            this.listOfEnemies.add(tank);
//            this.listOfEnemies.add(finalBoss);
//        }
        //Adding Abilities

        //Adding Items
        Shop shop = new Shop();
        GameEngine.shops.add(shop);

        this.creatingDefaultItems();

        this.rewardedXP[0] = 20;
        this.rewardedXP[1] = 25;
        this.rewardedXP[2] = 30;
        this.rewardedXP[3] = 35;

        this.rewardedMoney[0] = 50;
        this.rewardedMoney[1] = 60;
        this.rewardedMoney[2] = 70;
        this.rewardedMoney[3] = 80;

        Player.imortalityPotion = 3;
    }

    private void creatingDefaultItems() {
        {
            // Thoughen
            PropertyHandler<Object> propertyHandler = new PropertyHandler<>("maximumHealth",true, null, ClassName.Hero, 20, null, null);
            Property<Hero, Object> property = new Property<>(propertyHandler);
            ArrayList<Property<Hero, ?>> properties = new ArrayList<>();
            properties.add(property);
            Tree<ArrayList<Property<Hero, ?>>> trieConditions = new Tree<>();
            trieConditions.getRoot().addChild(properties, new Condition());
            SelectingObjectsDetailHandler<Hero> selectingObjectsDetailHandler = new SelectingObjectsDetailHandler<>(ClassName.Hero, false, true, false, null, 0, false, 0, false, 0, false, false, false);
            SelectingObjectsDetail<Hero> selectingObjectsDetail = new SelectingObjectsDetail<>(selectingObjectsDetailHandler);
            SubAbilityComponentHandler<Hero> subAbilityComponentHandler = new SubAbilityComponentHandler<>(ClassName.Hero, trieConditions, selectingObjectsDetail, new ArrayList<>(), new HashMap<>());
            SubPerkComponent<Hero> subPerkComponent = new SubPerkComponent<>(subAbilityComponentHandler);
            ArrayList<SubPerkComponent<?>> subPerkComponents = new ArrayList<>();
            subPerkComponents.add(subPerkComponent);
            SubAbilityHandler subAbilityHandler = new SubAbilityHandler(false, 0, new ArrayList<>(), new HashMap<>(), null);
            SubPerk subPerk1 = new SubPerk(subAbilityHandler, subPerkComponents, "1");

            Tree<SubPerk> subPerkTree = new Tree<>();
            Tree.Node<SubPerk> node = subPerkTree.getRoot();
            node.addChild(subPerk1, new Condition());
            node = node.getChildren().get(0);
            AbilityHandler abilityHandler = new AbilityHandler("Thoughen", null ,1, null);
            Perk perk = new Perk(abilityHandler, Perk.TimeOfCheck.eachActivity, subPerkTree);
            subPerk1.setRelatedPerk(perk);
            perk.setCurrentGrade(1);
            perk.setCurrentNode(node);
            perk.setAcquire(true);

            ItemHandler itemHandler = new ItemHandler("Thoughen", Item.Type.PerkItem, 0, "Items which alter a hero’s traits (don’t take up inventory’s space): +20 maximum health", new CraftingRequirement(10,10, 10, 10));
            PerkItem perkItem = new PerkItem(itemHandler, perk, true);
            ItemProperties<PerkItem> itemProperties = new ItemProperties<>(perkItem, 2, 0, 4, 0, 0);
            Shop.listOfPerkItems.add(itemProperties);
        }
        {
            // Guide
            PropertyHandler<Object> propertyHandler = new PropertyHandler<>("maximumMagic",true, null, ClassName.Hero, 20, null, null);
            Property<Hero, Object> property = new Property<>(propertyHandler);
            ArrayList<Property<Hero, ?>> properties = new ArrayList<>();
            properties.add(property);
            Tree<ArrayList<Property<Hero, ?>>> trieConditions = new Tree<>();
            trieConditions.getRoot().addChild(properties, new Condition());
            SelectingObjectsDetailHandler<Hero> selectingObjectsDetailHandler = new SelectingObjectsDetailHandler<>(ClassName.Hero, false, true, false, null, 0, false, 0, false, 0, false, false, false);
            SelectingObjectsDetail<Hero> selectingObjectsDetail = new SelectingObjectsDetail<>(selectingObjectsDetailHandler);
            SubAbilityComponentHandler<Hero> subAbilityComponentHandler = new SubAbilityComponentHandler<>(ClassName.Hero, trieConditions, selectingObjectsDetail, new ArrayList<>(), new HashMap<>());
            SubPerkComponent<Hero> subPerkComponent = new SubPerkComponent<>(subAbilityComponentHandler);
            ArrayList<SubPerkComponent<?>> subPerkComponents = new ArrayList<>();
            subPerkComponents.add(subPerkComponent);
            SubAbilityHandler subAbilityHandler = new SubAbilityHandler(false, 0, new ArrayList<>(), new HashMap<>(), null);
            SubPerk subPerk1 = new SubPerk(subAbilityHandler, subPerkComponents, "1");

            Tree<SubPerk> subPerkTree = new Tree<>();
            Tree.Node<SubPerk> node = subPerkTree.getRoot();
            node.addChild(subPerk1, new Condition());
            node = node.getChildren().get(0);
            AbilityHandler abilityHandler = new AbilityHandler("Guide", null ,1, null);
            Perk perk = new Perk(abilityHandler, Perk.TimeOfCheck.eachActivity, subPerkTree);
            subPerk1.setRelatedPerk(perk);
            perk.setCurrentGrade(1);
            perk.setCurrentNode(node);
            perk.setAcquire(true);

            ItemHandler itemHandler = new ItemHandler("Guide", Item.Type.PerkItem, 0, "Items which alter a hero’s traits (don’t take up inventory’s space): +20 maximum magic", new CraftingRequirement(10,10, 10, 10));
            PerkItem perkItem = new PerkItem(itemHandler, perk, true);
            ItemProperties<PerkItem> itemProperties = new ItemProperties<>(perkItem, 2, 0, 4, 0, 0);
            Shop.listOfPerkItems.add(itemProperties);
        }
        {
            // Defy
            PropertyHandler<Object> propertyHandler = new PropertyHandler<>("attackPower",true, null, ClassName.Hero, 8, null, null);
            Property<Hero, Object> property = new Property<>(propertyHandler);
            ArrayList<Property<Hero, ?>> properties = new ArrayList<>();
            properties.add(property);
            Tree<ArrayList<Property<Hero, ?>>> trieConditions = new Tree<>();
            trieConditions.getRoot().addChild(properties, new Condition());
            SelectingObjectsDetailHandler<Hero> selectingObjectsDetailHandler = new SelectingObjectsDetailHandler<>(ClassName.Hero, false, true, false, null, 0, false, 0, false, 0, false, false, false);
            SelectingObjectsDetail<Hero> selectingObjectsDetail = new SelectingObjectsDetail<>(selectingObjectsDetailHandler);
            SubAbilityComponentHandler<Hero> subAbilityComponentHandler = new SubAbilityComponentHandler<>(ClassName.Hero, trieConditions, selectingObjectsDetail, new ArrayList<>(), new HashMap<>());
            SubPerkComponent<Hero> subPerkComponent = new SubPerkComponent<>(subAbilityComponentHandler);
            ArrayList<SubPerkComponent<?>> subPerkComponents = new ArrayList<>();
            subPerkComponents.add(subPerkComponent);
            SubAbilityHandler subAbilityHandler = new SubAbilityHandler(false, 0, new ArrayList<>(), new HashMap<>(), null);
            SubPerk subPerk1 = new SubPerk(subAbilityHandler, subPerkComponents, "1");

            Tree<SubPerk> subPerkTree = new Tree<>();
            Tree.Node<SubPerk> node = subPerkTree.getRoot();
            node.addChild(subPerk1, new Condition());
            node = node.getChildren().get(0);
            AbilityHandler abilityHandler = new AbilityHandler("Defy", null ,1, null);
            Perk perk = new Perk(abilityHandler, Perk.TimeOfCheck.eachActivity, subPerkTree);
            subPerk1.setRelatedPerk(perk);
            perk.setCurrentGrade(1);
            perk.setCurrentNode(node);
            perk.setAcquire(true);

            ItemHandler itemHandler = new ItemHandler("Defy", Item.Type.PerkItem, 0, "Items which alter a hero’s traits (don’t take up inventory’s space): +8 attack power", new CraftingRequirement(10,10, 10, 10));
            PerkItem perkItem = new PerkItem(itemHandler, perk, true);
            ItemProperties<PerkItem> itemProperties = new ItemProperties<>(perkItem, 2, 0, 4, 0, 0);
            Shop.listOfPerkItems.add(itemProperties);
        }
        {
            // Sword
            PropertyHandler<Object> propertyHandler = new PropertyHandler<>("attackPower",false, null, ClassName.Hero, 80, null, null);
            Property<Hero, Object> property = new Property<>(propertyHandler);
            ArrayList<Property<Hero, ?>> properties = new ArrayList<>();
            properties.add(property);
            Tree<ArrayList<Property<Hero, ?>>> trieConditions = new Tree<>();
            trieConditions.getRoot().addChild(properties, new Condition());
            SelectingObjectsDetailHandler<Hero> selectingObjectsDetailHandler = new SelectingObjectsDetailHandler<>(ClassName.Hero, false, true, false, null, 0, false, 0, false, 0, false, false, false);
            SelectingObjectsDetail<Hero> selectingObjectsDetail = new SelectingObjectsDetail<>(selectingObjectsDetailHandler);
            SubAbilityComponentHandler<Hero> subAbilityComponentHandler = new SubAbilityComponentHandler<>(ClassName.Hero, trieConditions, selectingObjectsDetail, new ArrayList<>(), new HashMap<>());
            SubPerkComponent<Hero> subPerkComponent = new SubPerkComponent<>(subAbilityComponentHandler);
            ArrayList<SubPerkComponent<?>> subPerkComponents = new ArrayList<>();
            subPerkComponents.add(subPerkComponent);
            SubAbilityHandler subAbilityHandler = new SubAbilityHandler(false, 0, new ArrayList<>(), new HashMap<>(), null);
            SubPerk subPerk1 = new SubPerk(subAbilityHandler, subPerkComponents, "1");

            Tree<SubPerk> subPerkTree = new Tree<>();
            Tree.Node<SubPerk> node = subPerkTree.getRoot();
            node.addChild(subPerk1, new Condition());
            node = node.getChildren().get(0);
            AbilityHandler abilityHandler = new AbilityHandler("Sword", null ,1, null);
            Perk perk = new Perk(abilityHandler, Perk.TimeOfCheck.eachActivity, subPerkTree);
            subPerk1.setRelatedPerk(perk);
            perk.setCurrentGrade(1);
            perk.setCurrentNode(node);
            perk.setAcquire(true);

            ItemHandler itemHandler = new ItemHandler("Sword", Item.Type.PerkItem, 1, "Items which have a permanent effect on a hero: +80 attack power, costs 25 dollars", new CraftingRequirement(10,10, 10, 10));
            PerkItem perkItem = new PerkItem(itemHandler, perk, false);
            ItemProperties<PerkItem> itemProperties = new ItemProperties<>(perkItem, 0, 0, 25, 0.5, 0);
            Shop.listOfPerkItems.add(itemProperties);
        }
        {
            // Energy Boots
            PropertyHandler<Object> propertyHandler = new PropertyHandler<>("maximumEnergyPoint", false, null, ClassName.Hero, 1, null, null);
            Property<Hero, Object> property = new Property<>(propertyHandler);
            ArrayList<Property<Hero, ?>> properties = new ArrayList<>();
            properties.add(property);
            Tree<ArrayList<Property<Hero, ?>>> trieConditions = new Tree<>();
            trieConditions.getRoot().addChild(properties, new Condition());
            SelectingObjectsDetailHandler<Hero> selectingObjectsDetailHandler = new SelectingObjectsDetailHandler<>(ClassName.Hero, false, true, false, null, 0, false, 0, false, 0, false, false, false);
            SelectingObjectsDetail<Hero> selectingObjectsDetail = new SelectingObjectsDetail<>(selectingObjectsDetailHandler);
            SubAbilityComponentHandler<Hero> subAbilityComponentHandler = new SubAbilityComponentHandler<>(ClassName.Hero, trieConditions, selectingObjectsDetail, new ArrayList<>(), new HashMap<>());
            SubPerkComponent<Hero> subPerkComponent = new SubPerkComponent<>(subAbilityComponentHandler);
            ArrayList<SubPerkComponent<?>> subPerkComponents = new ArrayList<>();
            subPerkComponents.add(subPerkComponent);
            SubAbilityHandler subAbilityHandler = new SubAbilityHandler(false, 0, new ArrayList<>(), new HashMap<>(), null);
            SubPerk subPerk1 = new SubPerk(subAbilityHandler, subPerkComponents, "1");

            Tree<SubPerk> subPerkTree = new Tree<>();
            Tree.Node<SubPerk> node = subPerkTree.getRoot();
            node.addChild(subPerk1, new Condition());
            node = node.getChildren().get(0);
            AbilityHandler abilityHandler = new AbilityHandler("Energy Boots", null ,1, null);
            Perk perk = new Perk(abilityHandler, Perk.TimeOfCheck.eachActivity, subPerkTree);
            subPerk1.setRelatedPerk(perk);
            perk.setCurrentGrade(1);
            perk.setCurrentNode(node);
            perk.setAcquire(true);

            ItemHandler itemHandler = new ItemHandler("Energy Boots", Item.Type.PerkItem, 1, "Items which have a permanent effect on a hero: +1 energy point, costs 20 dollars", new CraftingRequirement(10,10, 10, 10));
            PerkItem perkItem = new PerkItem(itemHandler, perk, false);
            ItemProperties<PerkItem> itemProperties = new ItemProperties<>(perkItem, 0, 0, 20, 0.5, 0);
            Shop.listOfPerkItems.add(itemProperties);
        }
        {
            // Armor
            PropertyHandler<Object> propertyHandler = new PropertyHandler<>("maximumHealth", false, null, ClassName.Hero, 200, null, null);
            Property<Hero, Object> property = new Property<>(propertyHandler);
            ArrayList<Property<Hero, ?>> properties = new ArrayList<>();
            properties.add(property);
            Tree<ArrayList<Property<Hero, ?>>> trieConditions = new Tree<>();
            trieConditions.getRoot().addChild(properties, new Condition());
            SelectingObjectsDetailHandler<Hero> selectingObjectsDetailHandler = new SelectingObjectsDetailHandler<>(ClassName.Hero, false, true, false, null, 0, false, 0, false, 0, false, false, false);
            SelectingObjectsDetail<Hero> selectingObjectsDetail = new SelectingObjectsDetail<>(selectingObjectsDetailHandler);
            SubAbilityComponentHandler<Hero> subAbilityComponentHandler = new SubAbilityComponentHandler<>(ClassName.Hero, trieConditions, selectingObjectsDetail, new ArrayList<>(), new HashMap<>());
            SubPerkComponent<Hero> subPerkComponent = new SubPerkComponent<>(subAbilityComponentHandler);
            ArrayList<SubPerkComponent<?>> subPerkComponents = new ArrayList<>();
            subPerkComponents.add(subPerkComponent);
            SubAbilityHandler subAbilityHandler = new SubAbilityHandler(false, 0, new ArrayList<>(), new HashMap<>(), null);
            SubPerk subPerk1 = new SubPerk(subAbilityHandler, subPerkComponents, "1");

            Tree<SubPerk> subPerkTree = new Tree<>();
            Tree.Node<SubPerk> node = subPerkTree.getRoot();
            node.addChild(subPerk1, new Condition());
            node = node.getChildren().get(0);
            AbilityHandler abilityHandler = new AbilityHandler("Armor", null ,1, null);
            Perk perk = new Perk(abilityHandler, Perk.TimeOfCheck.eachActivity, subPerkTree);
            subPerk1.setRelatedPerk(perk);
            perk.setCurrentGrade(1);
            perk.setCurrentNode(node);
            perk.setAcquire(true);

            ItemHandler itemHandler = new ItemHandler("Armor", Item.Type.PerkItem, 1, "Items which have a permanent effect on a hero: +200 maximum health, costs 25 dollars", new CraftingRequirement(10,10, 10, 10));
            PerkItem perkItem = new PerkItem(itemHandler, perk, false);
            ItemProperties<PerkItem> itemProperties = new ItemProperties<>(perkItem, 0, 0, 25, 0.5, 0);
            Shop.listOfPerkItems.add(itemProperties);
        }
        {
            // Magic stick
            PropertyHandler<Object> propertyHandler = new PropertyHandler<>("maximumMagic", false, null, ClassName.Hero, 150, null, null);
            Property<Hero, Object> property = new Property<>(propertyHandler);
            ArrayList<Property<Hero, ?>> properties = new ArrayList<>();
            properties.add(property);
            Tree<ArrayList<Property<Hero, ?>>> trieConditions = new Tree<>();
            trieConditions.getRoot().addChild(properties, new Condition());
            SelectingObjectsDetailHandler<Hero> selectingObjectsDetailHandler = new SelectingObjectsDetailHandler<>(ClassName.Hero, false, true, false, null, 0, false, 0, false, 0, false, false, false);
            SelectingObjectsDetail<Hero> selectingObjectsDetail = new SelectingObjectsDetail<>(selectingObjectsDetailHandler);
            SubAbilityComponentHandler<Hero> subAbilityComponentHandler = new SubAbilityComponentHandler<>(ClassName.Hero, trieConditions, selectingObjectsDetail, new ArrayList<>(), new HashMap<>());
            SubPerkComponent<Hero> subPerkComponent = new SubPerkComponent<>(subAbilityComponentHandler);
            ArrayList<SubPerkComponent<?>> subPerkComponents = new ArrayList<>();
            subPerkComponents.add(subPerkComponent);
            SubAbilityHandler subAbilityHandler = new SubAbilityHandler(false, 0, new ArrayList<>(), new HashMap<>(), null);
            SubPerk subPerk1 = new SubPerk(subAbilityHandler, subPerkComponents, "1");

            Tree<SubPerk> subPerkTree = new Tree<>();
            Tree.Node<SubPerk> node = subPerkTree.getRoot();
            node.addChild(subPerk1, new Condition());
            node = node.getChildren().get(0);
            AbilityHandler abilityHandler = new AbilityHandler("Magic stick", null ,1, null);
            Perk perk = new Perk(abilityHandler, Perk.TimeOfCheck.eachActivity, subPerkTree);
            subPerk1.setRelatedPerk(perk);
            perk.setCurrentGrade(1);
            perk.setCurrentNode(node);
            perk.setAcquire(true);

            ItemHandler itemHandler = new ItemHandler("Magic stick", Item.Type.PerkItem, 1, "Items which have a permanent effect on a hero: +150 maximum magic, costs 28 dollars", new CraftingRequirement(10,10, 10, 10));
            PerkItem perkItem = new PerkItem(itemHandler, perk, false);
            ItemProperties<PerkItem> itemProperties = new ItemProperties<>(perkItem, 0, 0, 28, 0.5, 0);
            Shop.listOfPerkItems.add(itemProperties);
        }
        {
            // Health potion
            PropertyHandler<Object> propertyHandler = new PropertyHandler<>("currentHealth", true, null, ClassName.Hero, 100, null ,null);
            Property<Hero, Object> property = new Property<>(propertyHandler);
            ArrayList<Property<Hero, ?>> properties = new ArrayList<>();
            properties.add(property);
            Tree<ArrayList<Property<Hero, ?>>> trieConditions = new Tree<>();
            trieConditions.getRoot().addChild(properties, new Condition());
            SelectingObjectsDetailHandler<Hero> selectingObjectsDetailHandler = new SelectingObjectsDetailHandler<>(ClassName.Hero, false, false, false, null, 0, false, 0, true, 1, false, false, false);
            SelectingObjectsDetail<Hero> selectingObjectsDetail = new SelectingObjectsDetail<>(selectingObjectsDetailHandler);
            SubAbilityComponentHandler<Hero> subAbilityComponentHandler = new SubAbilityComponentHandler<>(ClassName.Hero, trieConditions, selectingObjectsDetail, new ArrayList<>(), new HashMap<>());
            SubSkillComponent<Hero> subSkillComponent = new SubSkillComponent<>(subAbilityComponentHandler);
            ArrayList<SubSkillComponent<?>> subSkillComponents = new ArrayList<>();
            subSkillComponents.add(subSkillComponent);
            SubAbilityHandler subAbilityHandler = new SubAbilityHandler(false, 0, new ArrayList<>(), new HashMap<>(), null);
            SubSkillHandler subSkillHandler = new SubSkillHandler(false, new Time(), 0, true, 0, 0, "1", subSkillComponents);
            SubSkill subSkill1 = new SubSkill(subSkillHandler, subAbilityHandler);

            Tree<SubSkill> subSkillTree = new Tree<>();
            Tree.Node<SubSkill> node = subSkillTree.getRoot();
            node.addChild(subSkill1, new Condition());
            node = node.getChildren().get(0);
            AbilityHandler abilityHandler = new AbilityHandler("Health potion", null, 1, null);
            Skill skill = new Skill(abilityHandler, subSkillTree);
            subSkill1.setRelatedSkill(skill);
            skill.setCurrentGrade(1);
            skill.setCurrentNode(node);
            skill.setAcquire(true);

            ItemHandler itemHandler = new ItemHandler("Health potion", Item.Type.SkillItem, 1, "Items which you can use during the battle up to 3 times (they free the inventory after 3 uses): +100 health points for the user or one of his/her allies, costs 15 dollars", new CraftingRequirement(10,10, 10, 10));
            SkillItem skillItem = new SkillItem(itemHandler, skill, 3);
            ItemProperties<SkillItem> itemProperties = new ItemProperties<>(skillItem, 0, 0, 15, 0.5, 0);
            Shop.listOfSkillItems.add(itemProperties);
        }
        {
            // Magic potion
            PropertyHandler<Object> propertyHandler = new PropertyHandler<>("currentMagic", true, null, ClassName.Hero, 50, null ,null);
            Property<Hero, Object> property = new Property<>(propertyHandler);
            ArrayList<Property<Hero, ?>> properties = new ArrayList<>();
            properties.add(property);
            Tree<ArrayList<Property<Hero, ?>>> trieConditions = new Tree<>();
            trieConditions.getRoot().addChild(properties, new Condition());
            SelectingObjectsDetailHandler<Hero> selectingObjectsDetailHandler = new SelectingObjectsDetailHandler<>(ClassName.Hero, false, false, false, null, 0, false, 0, true, 1, false, false, false);
            SelectingObjectsDetail<Hero> selectingObjectsDetail = new SelectingObjectsDetail<>(selectingObjectsDetailHandler);
            SubAbilityComponentHandler<Hero> subAbilityComponentHandler = new SubAbilityComponentHandler<>(ClassName.Hero, trieConditions, selectingObjectsDetail, new ArrayList<>(), new HashMap<>());
            SubSkillComponent<Hero> subSkillComponent = new SubSkillComponent<>(subAbilityComponentHandler);
            ArrayList<SubSkillComponent<?>> subSkillComponents = new ArrayList<>();
            subSkillComponents.add(subSkillComponent);
            SubAbilityHandler subAbilityHandler = new SubAbilityHandler(false, 0, new ArrayList<>(), new HashMap<>(), null);
            SubSkillHandler subSkillHandler = new SubSkillHandler(false, new Time(), 0, true, 0, 0, "1", subSkillComponents);
            SubSkill subSkill1 = new SubSkill(subSkillHandler, subAbilityHandler);

            Tree<SubSkill> subSkillTree = new Tree<>();
            Tree.Node<SubSkill> node = subSkillTree.getRoot();
            node.addChild(subSkill1, new Condition());
            node = node.getChildren().get(0);
            AbilityHandler abilityHandler = new AbilityHandler("Magic potion", null, 1, null);
            Skill skill = new Skill(abilityHandler, subSkillTree);
            subSkill1.setRelatedSkill(skill);
            skill.setCurrentGrade(1);
            skill.setCurrentNode(node);
            skill.setAcquire(true);

            ItemHandler itemHandler = new ItemHandler("Magic potion", Item.Type.SkillItem, 1, "Items which you can use during the battle up to 3 times (they free the inventory after 3 uses): +50 magic points for the user or one of his/her allies, costs 15 dollars", new CraftingRequirement(10,10, 10, 10));
            SkillItem skillItem = new SkillItem(itemHandler, skill, 3);
            ItemProperties<SkillItem> itemProperties = new ItemProperties<>(skillItem, 0, 0, 15, 0.5, 0);
            Shop.listOfSkillItems.add(itemProperties);
        }
    }

    private void creatingDefaultPerks() {
        {
            //Fighter Class's Perk: Fight training

            Tree<ArrayList<Pair<String, Double>>> tree = new Tree<>();
            PropertyHandler<Object> propertyHandler = new PropertyHandler<>("attackPower",false, null, ClassName.Hero, 30, new SelectingObjectsDetail<>(), tree);
            Property<Hero, Object> property = new Property<>(propertyHandler);
            ArrayList<Property<Hero, ?>> properties = new ArrayList<>();
            properties.add(property);
            Tree<ArrayList<Property<Hero, ?>>> trieConditions = new Tree<>();
            trieConditions.getRoot().addChild(properties, new Condition());
            SelectingObjectsDetailHandler<Hero> selectingObjectsDetailHandler = new SelectingObjectsDetailHandler<>(ClassName.Hero, false, true, false, null, 0, false, 0, false, 0, false, false, false);
            SelectingObjectsDetail<Hero> selectingObjectsDetail = new SelectingObjectsDetail<>(selectingObjectsDetailHandler);
            SubAbilityComponentHandler<Hero> subAbilityComponentHandler = new SubAbilityComponentHandler<>(ClassName.Hero, trieConditions, selectingObjectsDetail, new ArrayList<>(), new HashMap<>());
            SubPerkComponent<Hero> subPerkComponent = new SubPerkComponent<>(subAbilityComponentHandler);
            ArrayList<SubPerkComponent<?>> subPerkComponents = new ArrayList<>();
            subPerkComponents.add(subPerkComponent);
            SubAbilityHandler subAbilityHandler = new SubAbilityHandler(false, 2, new ArrayList<>(), new HashMap<>(), "Upgrade1: +30 attack power for 2 xp points");
            SubPerk subPerk1 = new SubPerk(subAbilityHandler, subPerkComponents, "1");

            tree = new Tree<>();
            propertyHandler = new PropertyHandler<>("attackPower",false, null, ClassName.Hero, 60, new SelectingObjectsDetail<>(), tree);
            property = new Property<>(propertyHandler);
            properties = new ArrayList<>();
            properties.add(property);
            trieConditions = new Tree<>();
            trieConditions.getRoot().addChild(properties, new Condition());
            selectingObjectsDetailHandler = new SelectingObjectsDetailHandler<>(ClassName.Hero, false, true, false, null, 0, false, 0, false, 0, false, false, false);
            selectingObjectsDetail = new SelectingObjectsDetail<>(selectingObjectsDetailHandler);
            subAbilityComponentHandler = new SubAbilityComponentHandler<>(ClassName.Hero, trieConditions, selectingObjectsDetail, new ArrayList<>(), new HashMap<>());
            subPerkComponent = new SubPerkComponent<>(subAbilityComponentHandler);
            subPerkComponents = new ArrayList<>();
            subPerkComponents.add(subPerkComponent);
            subAbilityHandler = new SubAbilityHandler(false, 3, new ArrayList<>(), new HashMap<>(), "Upgrade2: +30 attack power for 3 xp points");
            SubPerk subPerk2 = new SubPerk(subAbilityHandler, subPerkComponents, "2");

            tree = new Tree<>();
            propertyHandler = new PropertyHandler<>("attackPower",false, null, ClassName.Hero, 90, new SelectingObjectsDetail<>(), tree);
            property = new Property<>(propertyHandler);
            properties = new ArrayList<>();
            properties.add(property);
            trieConditions = new Tree<>();
            trieConditions.getRoot().addChild(properties, new Condition());
            selectingObjectsDetailHandler = new SelectingObjectsDetailHandler<>(ClassName.Hero, false, true, false, null, 0, false, 0, false, 0, false, false, false);
            selectingObjectsDetail = new SelectingObjectsDetail<>(selectingObjectsDetailHandler);
            subAbilityComponentHandler = new SubAbilityComponentHandler<>(ClassName.Hero, trieConditions, selectingObjectsDetail, new ArrayList<>(), new HashMap<>());
            subPerkComponent = new SubPerkComponent<>(subAbilityComponentHandler);
            subPerkComponents = new ArrayList<>();
            subPerkComponents.add(subPerkComponent);
            subAbilityHandler = new SubAbilityHandler(false, 4, new ArrayList<>(), new HashMap<>(), "Upgrade3: +30 attack power for 4 xp points");
            SubPerk subPerk3 = new SubPerk(subAbilityHandler, subPerkComponents, "3");

            Tree<SubPerk> subPerkTree = new Tree<>();
            Tree.Node<SubPerk> node = subPerkTree.getRoot();
            node.addChild(subPerk1, new Condition());
            node = node.getChildren().get(0);
            node.addChild(subPerk2, new Condition());
            node = node.getChildren().get(0);
            node.addChild(subPerk3, new Condition());
            AbilityHandler abilityHandler = new AbilityHandler("Fight training", null ,3, "Permanently increases attack power");
            Perk perk = new Perk(abilityHandler, Perk.TimeOfCheck.eachActivity, subPerkTree);
            subPerk1.setRelatedPerk(perk);
            subPerk2.setRelatedPerk(perk);
            subPerk3.setRelatedPerk(perk);
            this.addNewPerk(perk);
        }
        {
            //Fighter Class's Perk: Work out

            Tree<ArrayList<Pair<String, Double>>> tree = new Tree<>();
            PropertyHandler<Object> propertyHandler = new PropertyHandler<>("maximumHealth",false, null, ClassName.Hero, 50, new SelectingObjectsDetail<>(), tree);
            Property<Hero, Object> property = new Property<>(propertyHandler);
            ArrayList<Property<Hero, ?>> properties = new ArrayList<>();
            properties.add(property);
            Tree<ArrayList<Property<Hero, ?>>> trieConditions = new Tree<>();
            trieConditions.getRoot().addChild(properties, new Condition());
            SelectingObjectsDetailHandler<Hero> selectingObjectsDetailHandler = new SelectingObjectsDetailHandler<>(ClassName.Hero, false, true, false, null, 0, false, 0, false, 0, false, false, false);
            SelectingObjectsDetail<Hero> selectingObjectsDetail = new SelectingObjectsDetail<>(selectingObjectsDetailHandler);
            SubAbilityComponentHandler<Hero> subAbilityComponentHandler = new SubAbilityComponentHandler<>(ClassName.Hero, trieConditions, selectingObjectsDetail, new ArrayList<>(), new HashMap<>());
            SubPerkComponent<Hero> subPerkComponent = new SubPerkComponent<>(subAbilityComponentHandler);
            ArrayList<SubPerkComponent<?>> subPerkComponents = new ArrayList<>();
            subPerkComponents.add(subPerkComponent);
            SubAbilityHandler subAbilityHandler = new SubAbilityHandler(false, 2, new ArrayList<>(), new HashMap<>(), "Upgrade 1: +50 maximum health for 2 xp points");
            SubPerk subPerk1 = new SubPerk(subAbilityHandler, subPerkComponents, "1");

            tree = new Tree<>();
            propertyHandler = new PropertyHandler<>("maximumHealth",false, null, ClassName.Hero, 100, new SelectingObjectsDetail<>(), tree);
            property = new Property<>(propertyHandler);
            properties = new ArrayList<>();
            properties.add(property);
            trieConditions = new Tree<>();
            trieConditions.getRoot().addChild(properties, new Condition());
            selectingObjectsDetailHandler = new SelectingObjectsDetailHandler<>(ClassName.Hero, false, true, false, null, 0, false, 0, false, 0, false, false, false);
            selectingObjectsDetail = new SelectingObjectsDetail<>(selectingObjectsDetailHandler);
            subAbilityComponentHandler = new SubAbilityComponentHandler<>(ClassName.Hero, trieConditions, selectingObjectsDetail, new ArrayList<>(), new HashMap<>());
            subPerkComponent = new SubPerkComponent<>(subAbilityComponentHandler);
            subPerkComponents = new ArrayList<>();
            subPerkComponents.add(subPerkComponent);
            subAbilityHandler = new SubAbilityHandler(false, 3, new ArrayList<>(), new HashMap<>(), "Upgrade 2: +50 maximum health for 3 xp points");
            SubPerk subPerk2 = new SubPerk(subAbilityHandler, subPerkComponents, "2");

            tree = new Tree<>();
            propertyHandler = new PropertyHandler<>("maximumHealth",false, null, ClassName.Hero, 150, new SelectingObjectsDetail<>(), tree);
            property = new Property<>(propertyHandler);
            properties = new ArrayList<>();
            properties.add(property);
            trieConditions = new Tree<>();
            trieConditions.getRoot().addChild(properties, new Condition());
            selectingObjectsDetailHandler = new SelectingObjectsDetailHandler<>(ClassName.Hero, false, true, false, null, 0, false, 0, false, 0, false, false, false);
            selectingObjectsDetail = new SelectingObjectsDetail<>(selectingObjectsDetailHandler);
            subAbilityComponentHandler = new SubAbilityComponentHandler<>(ClassName.Hero, trieConditions, selectingObjectsDetail, new ArrayList<>(), new HashMap<>());
            subPerkComponent = new SubPerkComponent<>(subAbilityComponentHandler);
            subPerkComponents = new ArrayList<>();
            subPerkComponents.add(subPerkComponent);
            subAbilityHandler = new SubAbilityHandler(false, 4, new ArrayList<>(), new HashMap<>(), "Upgrade 3: +50 maximum health for 4 xp points");
            SubPerk subPerk3 = new SubPerk(subAbilityHandler, subPerkComponents, "3");

            Tree<SubPerk> subPerkTree = new Tree<>();
            Tree.Node<SubPerk> node = subPerkTree.getRoot();
            node.addChild(subPerk1, new Condition());
            node = node.getChildren().get(0);
            node.addChild(subPerk2, new Condition());
            node = node.getChildren().get(0);
            node.addChild(subPerk3, new Condition());
            AbilityHandler abilityHandler = new AbilityHandler("Work out", null ,3, "Permanently increases maximum health");
            Perk perk = new Perk(abilityHandler, Perk.TimeOfCheck.eachActivity, subPerkTree);
            subPerk1.setRelatedPerk(perk);
            subPerk2.setRelatedPerk(perk);
            subPerk3.setRelatedPerk(perk);
            this.addNewPerk(perk);
        }
        {
            //Supporter Class's Perk: Quick as a bunny

            Tree<ArrayList<Pair<String, Double>>> tree = new Tree<>();
            PropertyHandler<Object> propertyHandler = new PropertyHandler<>("maximumEnergyPoint",false, null, ClassName.Hero, 1, new SelectingObjectsDetail<>(), tree);
            Property<Hero, Object> property = new Property<>(propertyHandler);
            ArrayList<Property<Hero, ?>> properties = new ArrayList<>();
            properties.add(property);
            Tree<ArrayList<Property<Hero, ?>>> trieConditions = new Tree<>();
            trieConditions.getRoot().addChild(properties, new Condition());
            SelectingObjectsDetailHandler<Hero> selectingObjectsDetailHandler = new SelectingObjectsDetailHandler<>(ClassName.Hero, false, true, false, null, 0, false, 0, false, 0, false, false, false);
            SelectingObjectsDetail<Hero> selectingObjectsDetail = new SelectingObjectsDetail<>(selectingObjectsDetailHandler);
            SubAbilityComponentHandler<Hero> subAbilityComponentHandler = new SubAbilityComponentHandler<>(ClassName.Hero, trieConditions, selectingObjectsDetail, new ArrayList<>(), new HashMap<>());
            SubPerkComponent<Hero> subPerkComponent = new SubPerkComponent<>(subAbilityComponentHandler);
            ArrayList<SubPerkComponent<?>> subPerkComponents = new ArrayList<>();
            subPerkComponents.add(subPerkComponent);
            SubAbilityHandler subAbilityHandler = new SubAbilityHandler(false, 2, new ArrayList<>(), new HashMap<>(), "Upgrade1: +1 energy point for 2 xp points");
            SubPerk subPerk1 = new SubPerk(subAbilityHandler, subPerkComponents, "1");

            tree = new Tree<>();
            propertyHandler = new PropertyHandler<>("maximumEnergyPoint", false, null, ClassName.Hero, 2, new SelectingObjectsDetail<>(), tree);
            property = new Property<>(propertyHandler);
            properties = new ArrayList<>();
            properties.add(property);
            trieConditions = new Tree<>();
            trieConditions.getRoot().addChild(properties, new Condition());
            selectingObjectsDetailHandler = new SelectingObjectsDetailHandler<>(ClassName.Hero, false, true, false, null, 0, false, 0, false, 0, false, false, false);
            selectingObjectsDetail = new SelectingObjectsDetail<>(selectingObjectsDetailHandler);
            subAbilityComponentHandler = new SubAbilityComponentHandler<>(ClassName.Hero, trieConditions, selectingObjectsDetail, new ArrayList<>(), new HashMap<>());
            subPerkComponent = new SubPerkComponent<>(subAbilityComponentHandler);
            subPerkComponents = new ArrayList<>();
            subPerkComponents.add(subPerkComponent);
            subAbilityHandler = new SubAbilityHandler(false, 3, new ArrayList<>(), new HashMap<>(), "Upgrade2: +1 energy point for 3 xp points");
            SubPerk subPerk2 = new SubPerk(subAbilityHandler, subPerkComponents, "2");

            tree = new Tree<>();
            propertyHandler = new PropertyHandler<>("maximumEnergyPoint", false, null, ClassName.Hero, 3, new SelectingObjectsDetail<>(), tree);
            property = new Property<>(propertyHandler);
            properties = new ArrayList<>();
            properties.add(property);
            trieConditions = new Tree<>();
            trieConditions.getRoot().addChild(properties, new Condition());
            selectingObjectsDetailHandler = new SelectingObjectsDetailHandler<>(ClassName.Hero, false, true, false, null, 0, false, 0, false, 0, false, false, false);
            selectingObjectsDetail = new SelectingObjectsDetail<>(selectingObjectsDetailHandler);
            subAbilityComponentHandler = new SubAbilityComponentHandler<>(ClassName.Hero, trieConditions, selectingObjectsDetail, new ArrayList<>(), new HashMap<>());
            subPerkComponent = new SubPerkComponent<>(subAbilityComponentHandler);
            subPerkComponents = new ArrayList<>();
            subPerkComponents.add(subPerkComponent);
            subAbilityHandler = new SubAbilityHandler(false, 4, new ArrayList<>(), new HashMap<>(), "Upgrade3: +1 energy point for 4 xp points");
            SubPerk subPerk3 = new SubPerk(subAbilityHandler, subPerkComponents, "3");

            Tree<SubPerk> subPerkTree = new Tree<>();
            Tree.Node<SubPerk> node = subPerkTree.getRoot();
            node.addChild(subPerk1, new Condition());
            node = node.getChildren().get(0);
            node.addChild(subPerk2, new Condition());
            node = node.getChildren().get(0);
            node.addChild(subPerk3, new Condition());
            AbilityHandler abilityHandler = new AbilityHandler("Quick as a bunny", null ,3, "Permanently increases energy points");
            Perk perk = new Perk(abilityHandler, Perk.TimeOfCheck.eachActivity, subPerkTree);
            subPerk1.setRelatedPerk(perk);
            subPerk2.setRelatedPerk(perk);
            subPerk3.setRelatedPerk(perk);
            this.addNewPerk(perk);
        }
        {
            //Supporter Class's Perk: Magic lessons

            Tree<ArrayList<Pair<String, Double>>> tree = new Tree<>();
            PropertyHandler<Object> propertyHandler = new PropertyHandler<>("maximumMagic", false, null, ClassName.Hero, 50, new SelectingObjectsDetail<>(), tree);
            Property<Hero, Object> property = new Property<>(propertyHandler);
            ArrayList<Property<Hero, ?>> properties = new ArrayList<>();
            properties.add(property);
            Tree<ArrayList<Property<Hero, ?>>> trieConditions = new Tree<>();
            trieConditions.getRoot().addChild(properties, new Condition());
            SelectingObjectsDetailHandler<Hero> selectingObjectsDetailHandler = new SelectingObjectsDetailHandler<>(ClassName.Hero, false, true, false, null, 0, false, 0, false, 0, false, false, false);
            SelectingObjectsDetail<Hero> selectingObjectsDetail = new SelectingObjectsDetail<>(selectingObjectsDetailHandler);
            SubAbilityComponentHandler<Hero> subAbilityComponentHandler = new SubAbilityComponentHandler<>(ClassName.Hero, trieConditions, selectingObjectsDetail, new ArrayList<>(), new HashMap<>());
            SubPerkComponent<Hero> subPerkComponent = new SubPerkComponent<>(subAbilityComponentHandler);
            ArrayList<SubPerkComponent<?>> subPerkComponents = new ArrayList<>();
            subPerkComponents.add(subPerkComponent);
            SubAbilityHandler subAbilityHandler = new SubAbilityHandler(false, 2, new ArrayList<>(), new HashMap<>(), "Upgrade 1: +50 maximum magic for 2 xp points");
            SubPerk subPerk1 = new SubPerk(subAbilityHandler, subPerkComponents, "1");

            tree = new Tree<>();
            propertyHandler = new PropertyHandler<>("maximumMagic", false, null, ClassName.Hero, 100, new SelectingObjectsDetail<>(), tree);
            property = new Property<>(propertyHandler);
            properties = new ArrayList<>();
            properties.add(property);
            trieConditions = new Tree<>();
            trieConditions.getRoot().addChild(properties, new Condition());
            selectingObjectsDetailHandler = new SelectingObjectsDetailHandler<>(ClassName.Hero, false, true, false, null, 0, false, 0, false, 0, false, false, false);
            selectingObjectsDetail = new SelectingObjectsDetail<>(selectingObjectsDetailHandler);
            subAbilityComponentHandler = new SubAbilityComponentHandler<>(ClassName.Hero, trieConditions, selectingObjectsDetail, new ArrayList<>(), new HashMap<>());
            subPerkComponent = new SubPerkComponent<>(subAbilityComponentHandler);
            subPerkComponents = new ArrayList<>();
            subPerkComponents.add(subPerkComponent);
            subAbilityHandler = new SubAbilityHandler(false, 3, new ArrayList<>(), new HashMap<>(), "Upgrade 2: +50 maximum magic for 3 xp points");
            SubPerk subPerk2 = new SubPerk(subAbilityHandler, subPerkComponents, "2");

            tree = new Tree<>();
            propertyHandler = new PropertyHandler<>("maximumMagic", false, null, ClassName.Hero, 150, new SelectingObjectsDetail<>(), tree);
            property = new Property<>(propertyHandler);
            properties = new ArrayList<>();
            properties.add(property);
            trieConditions = new Tree<>();
            trieConditions.getRoot().addChild(properties, new Condition());
            selectingObjectsDetailHandler = new SelectingObjectsDetailHandler<>(ClassName.Hero, false, true, false, null, 0, false, 0, false, 0, false, false, false);
            selectingObjectsDetail = new SelectingObjectsDetail<>(selectingObjectsDetailHandler);
            subAbilityComponentHandler = new SubAbilityComponentHandler<>(ClassName.Hero, trieConditions, selectingObjectsDetail, new ArrayList<>(), new HashMap<>());
            subPerkComponent = new SubPerkComponent<>(subAbilityComponentHandler);
            subPerkComponents = new ArrayList<>();
            subPerkComponents.add(subPerkComponent);
            subAbilityHandler = new SubAbilityHandler(false, 4, new ArrayList<>(), new HashMap<>(), "Upgrade 3: +50 maximum magic for 4 xp points");
            SubPerk subPerk3 = new SubPerk(subAbilityHandler, subPerkComponents, "3");

            Tree<SubPerk> subPerkTree = new Tree<>();
            Tree.Node<SubPerk> node = subPerkTree.getRoot();
            node.addChild(subPerk1, new Condition());
            node = node.getChildren().get(0);
            node.addChild(subPerk2, new Condition());
            node = node.getChildren().get(0);
            node.addChild(subPerk3, new Condition());
            AbilityHandler abilityHandler = new AbilityHandler("Magic lessons", null ,3, "Permanently increases maximum magic");
            Perk perk = new Perk(abilityHandler, Perk.TimeOfCheck.eachActivity, subPerkTree);
            subPerk1.setRelatedPerk(perk);
            subPerk2.setRelatedPerk(perk);
            subPerk3.setRelatedPerk(perk);
            this.addNewPerk(perk);
        }
        {
            //Eley's Perk: Swirling attack

            Tree<ArrayList<Pair<String, Double>>> tree = new Tree<>();
            PropertyHandler<Object> propertyHandler = new PropertyHandler<>("attackPowerRatioOnNonTargetedEnemy", false, null, ClassName.Hero, 0.1, new SelectingObjectsDetail<>(), tree);
            Property<Hero, Object> property = new Property<>(propertyHandler);
            ArrayList<Property<Hero, ?>> properties = new ArrayList<>();
            properties.add(property);
            Tree<ArrayList<Property<Hero, ?>>> trieConditions = new Tree<>();
            trieConditions.getRoot().addChild(properties, new Condition());
            SelectingObjectsDetailHandler<Hero> selectingObjectsDetailHandler = new SelectingObjectsDetailHandler<>(ClassName.Hero, false, true, false, null, 0, false, 0, false, 0, false, false, false);
            SelectingObjectsDetail<Hero> selectingObjectsDetail = new SelectingObjectsDetail<>(selectingObjectsDetailHandler);
            SubAbilityComponentHandler<Hero> subAbilityComponentHandler = new SubAbilityComponentHandler<>(ClassName.Hero, trieConditions, selectingObjectsDetail, new ArrayList<>(), new HashMap<>());
            SubPerkComponent<Hero> subPerkComponent = new SubPerkComponent<>(subAbilityComponentHandler);
            ArrayList<SubPerkComponent<?>> subPerkComponents = new ArrayList<>();
            subPerkComponents.add(subPerkComponent);
            ArrayList<String> nameOfNecessaryAbilities = new ArrayList<>();
            Map<String, Integer> gradeOfNecessaryAbilities = new HashMap<>();
            nameOfNecessaryAbilities.add("Work out");
            gradeOfNecessaryAbilities.put("Work out", 1);
            SubAbilityHandler subAbilityHandler = new SubAbilityHandler(false, 2, nameOfNecessaryAbilities, gradeOfNecessaryAbilities, "Upgrade 1: P=10 for 2 xp points, needs Work out upgrade 1");
            SubPerk subPerk1 = new SubPerk(subAbilityHandler, subPerkComponents, "1");

            tree = new Tree<>();
            propertyHandler = new PropertyHandler<>("attackPowerRatioOnNonTargetedEnemy", false, null, ClassName.Hero, 0.2, new SelectingObjectsDetail<>(), tree);
            property = new Property<>(propertyHandler);
            properties = new ArrayList<>();
            properties.add(property);
            trieConditions = new Tree<>();
            trieConditions.getRoot().addChild(properties, new Condition());
            selectingObjectsDetailHandler = new SelectingObjectsDetailHandler<>(ClassName.Hero, false, true, false, null, 0, false, 0, false, 0, false, false, false);
            selectingObjectsDetail = new SelectingObjectsDetail<>(selectingObjectsDetailHandler);
            subAbilityComponentHandler = new SubAbilityComponentHandler<>(ClassName.Hero, trieConditions, selectingObjectsDetail, new ArrayList<>(), new HashMap<>());
            subPerkComponent = new SubPerkComponent<>(subAbilityComponentHandler);
            subPerkComponents = new ArrayList<>();
            subPerkComponents.add(subPerkComponent);
            subAbilityHandler = new SubAbilityHandler(false, 3, new ArrayList<>(), new HashMap<>(), "Upgrade 2: P=20 for 3 xp points");
            SubPerk subPerk2 = new SubPerk(subAbilityHandler, subPerkComponents, "2");

            tree = new Tree<>();
            propertyHandler = new PropertyHandler<>("attackPowerRatioOnNonTargetedEnemy", false, null, ClassName.Hero, 0.3, new SelectingObjectsDetail<>(), tree);
            property = new Property<>(propertyHandler);
            properties = new ArrayList<>();
            properties.add(property);
            trieConditions = new Tree<>();
            trieConditions.getRoot().addChild(properties, new Condition());
            selectingObjectsDetailHandler = new SelectingObjectsDetailHandler<>(ClassName.Hero, false, true, false, null, 0, false, 0, false, 0, false, false, false);
            selectingObjectsDetail = new SelectingObjectsDetail<>(selectingObjectsDetailHandler);
            subAbilityComponentHandler = new SubAbilityComponentHandler<>(ClassName.Hero, trieConditions, selectingObjectsDetail, new ArrayList<>(), new HashMap<>());
            subPerkComponent = new SubPerkComponent<>(subAbilityComponentHandler);
            subPerkComponents = new ArrayList<>();
            subPerkComponents.add(subPerkComponent);
            subAbilityHandler = new SubAbilityHandler(false, 4, new ArrayList<>(), new HashMap<>(), "Upgrade 3: P=30 for 4 xp points");
            SubPerk subPerk3 = new SubPerk(subAbilityHandler, subPerkComponents, "3");

            Tree<SubPerk> subPerkTree = new Tree<>();
            Tree.Node<SubPerk> node = subPerkTree.getRoot();
            node.addChild(subPerk1, new Condition());
            node = node.getChildren().get(0);
            node.addChild(subPerk2, new Condition());
            node = node.getChildren().get(0);
            node.addChild(subPerk3, new Condition());
            AbilityHandler abilityHandler = new AbilityHandler("Swirling attack", "Eley" ,3, "While attacking, non-targeted enemies also take P percent of its damage");
            Perk perk = new Perk(abilityHandler, Perk.TimeOfCheck.eachActivity, subPerkTree);
            subPerk1.setRelatedPerk(perk);
            subPerk2.setRelatedPerk(perk);
            subPerk3.setRelatedPerk(perk);
            this.addNewPerk(perk);
        }
        {
            //Chrome's Perk: Critical strike

            Tree<ArrayList<Pair<String, Double>>> tree = new Tree<>();
            PropertyHandler<Object> propertyHandler = new PropertyHandler<>("criticalHitChance",false, null, ClassName.Hero, 0.2, new SelectingObjectsDetail<>(), tree);
            Property<Hero, Object> property = new Property<>(propertyHandler);
            tree = new Tree<>();
            PropertyHandler<Object> propertyHandler2 = new PropertyHandler<>("criticalHitDamage",false, null, ClassName.Hero, 1, new SelectingObjectsDetail<>(), tree);
            Property<Hero, Object> property2 = new Property<>(propertyHandler2);
            ArrayList<Property<Hero, ?>> properties = new ArrayList<>();
            properties.add(property);
            properties.add(property2);
            Tree<ArrayList<Property<Hero, ?>>> trieConditions = new Tree<>();
            trieConditions.getRoot().addChild(properties, new Condition());
            SelectingObjectsDetailHandler<Hero> selectingObjectsDetailHandler = new SelectingObjectsDetailHandler<>(ClassName.Hero, false, true, false, null, 0, false, 0, false, 0, false, false, false);
            SelectingObjectsDetail<Hero> selectingObjectsDetail = new SelectingObjectsDetail<>(selectingObjectsDetailHandler);
            SubAbilityComponentHandler<Hero> subAbilityComponentHandler = new SubAbilityComponentHandler<>(ClassName.Hero, trieConditions, selectingObjectsDetail, new ArrayList<>(), new HashMap<>());
            SubPerkComponent<Hero> subPerkComponent = new SubPerkComponent<>(subAbilityComponentHandler);
            ArrayList<SubPerkComponent<?>> subPerkComponents = new ArrayList<>();
            subPerkComponents.add(subPerkComponent);
            ArrayList<String> nameOfNecessaryAbilities = new ArrayList<>();
            Map<String, Integer> gradeOfNecessaryAbilities = new HashMap<>();
            nameOfNecessaryAbilities.add("Fight training");
            gradeOfNecessaryAbilities.put("Fight training", 1);
            SubAbilityHandler subAbilityHandler = new SubAbilityHandler(false, 2, nameOfNecessaryAbilities, gradeOfNecessaryAbilities, "Upgrade 1: P=20 for 2 xp points, needs Fight training upgrade 1");
            SubPerk subPerk1 = new SubPerk(subAbilityHandler, subPerkComponents, "1");

            tree = new Tree<>();
            propertyHandler = new PropertyHandler<>("criticalHitChance", false, null, ClassName.Hero, 0.3, new SelectingObjectsDetail<>(), tree);
            property = new Property<>(propertyHandler);
            tree = new Tree<>();
            propertyHandler2 = new PropertyHandler<>("criticalHitDamage",false, null, ClassName.Hero, 2, new SelectingObjectsDetail<>(), tree);
            property2 = new Property<>(propertyHandler2);
            properties = new ArrayList<>();
            properties.add(property);
            properties.add(property2);
            trieConditions = new Tree<>();
            trieConditions.getRoot().addChild(properties, new Condition());
            selectingObjectsDetailHandler = new SelectingObjectsDetailHandler<>(ClassName.Hero, false, true, false, null, 0, false, 0, false, 0, false, false, false);
            selectingObjectsDetail = new SelectingObjectsDetail<>(selectingObjectsDetailHandler);
            subAbilityComponentHandler = new SubAbilityComponentHandler<>(ClassName.Hero, trieConditions, selectingObjectsDetail, new ArrayList<>(), new HashMap<>());
            subPerkComponent = new SubPerkComponent<>(subAbilityComponentHandler);
            subPerkComponents = new ArrayList<>();
            subPerkComponents.add(subPerkComponent);
            subAbilityHandler = new SubAbilityHandler(false, 3, new ArrayList<>(), new HashMap<>(), "Upgrade 2: P=30 for 3 xp points");
            SubPerk subPerk2 = new SubPerk(subAbilityHandler, subPerkComponents, "2");

            tree = new Tree<>();
            propertyHandler = new PropertyHandler<>("criticalHitChance", false, null, ClassName.Hero, 0.4, new SelectingObjectsDetail<>(), tree);
            property = new Property<>(propertyHandler);
            tree = new Tree<>();
            propertyHandler2 = new PropertyHandler<>("criticalHitDamage",false, null, ClassName.Hero, 2, new SelectingObjectsDetail<>(), tree);
            property2 = new Property<>(propertyHandler2);
            properties = new ArrayList<>();
            properties.add(property);
            properties.add(property2);
            trieConditions = new Tree<>();
            trieConditions.getRoot().addChild(properties, new Condition());
            selectingObjectsDetailHandler = new SelectingObjectsDetailHandler<>(ClassName.Hero, false, true, false, null, 0, false, 0, false, 0, false, false, false);
            selectingObjectsDetail = new SelectingObjectsDetail<>(selectingObjectsDetailHandler);
            subAbilityComponentHandler = new SubAbilityComponentHandler<>(ClassName.Hero, trieConditions, selectingObjectsDetail, new ArrayList<>(), new HashMap<>());
            subPerkComponent = new SubPerkComponent<>(subAbilityComponentHandler);
            subPerkComponents = new ArrayList<>();
            subPerkComponents.add(subPerkComponent);
            subAbilityHandler = new SubAbilityHandler(false, 4, new ArrayList<>(), new HashMap<>(), "Upgrade 3: P=40 for 4 xp points");
            SubPerk subPerk3 = new SubPerk(subAbilityHandler, subPerkComponents, "3");

            Tree<SubPerk> subPerkTree = new Tree<>();
            Tree.Node<SubPerk> node = subPerkTree.getRoot();
            node.addChild(subPerk1, new Condition());
            node = node.getChildren().get(0);
            node.addChild(subPerk2, new Condition());
            node = node.getChildren().get(0);
            node.addChild(subPerk3, new Condition());
            AbilityHandler abilityHandler = new AbilityHandler("Critical strike", "Chrome" ,3, "Has a permanent P percent chance of doing an attack with double power (does not affect other abilities)");
            Perk perk = new Perk(abilityHandler, Perk.TimeOfCheck.eachActivity, subPerkTree);
            subPerk1.setRelatedPerk(perk);
            subPerk2.setRelatedPerk(perk);
            subPerk3.setRelatedPerk(perk);
            this.addNewPerk(perk);
        }
    }

    private void creatingDefaultSkills() {
        {
            //Eley's Skill: Overpowered attack

            SelectingObjectsDetailHandler<Hero> selectingObjectsDetailHandler = new SelectingObjectsDetailHandler<>(ClassName.Hero, false, true, false, null, 0, false, 0, false, 0, false, false, false);
            SelectingObjectsDetail<Hero> selectingObjectsDetail = new SelectingObjectsDetail<>(selectingObjectsDetailHandler);
            Tree<ArrayList<Pair<String, Double>>> tree = new Tree<>();
            ArrayList<Pair<String, Double>> pairs = new ArrayList<>();
            pairs.add(new Pair<>("attackPower", -1.2));
            tree.getRoot().addChild(pairs, new Condition());
            PropertyHandler<Hero> propertyHandler = new PropertyHandler<>("currentHealth", true, ClassName.Hero, ClassName.Enemy, 0, selectingObjectsDetail, tree);
            Property<Enemy, Hero> property = new Property<>(propertyHandler);
            ArrayList<Property<Enemy, ?>> properties = new ArrayList<>();
            properties.add(property);
            Tree<ArrayList<Property<Enemy, ?>>> trieConditions = new Tree<>();
            trieConditions.getRoot().addChild(properties, new Condition());
            SelectingObjectsDetailHandler<Enemy> selectingObjectsDetailHandler2 = new SelectingObjectsDetailHandler<>(ClassName.Enemy, false, false, false, null, 0, false, 0, true, 1, false, false, false);
            SelectingObjectsDetail<Enemy> selectingObjectsDetail2 = new SelectingObjectsDetail<>(selectingObjectsDetailHandler2);
            SubAbilityComponentHandler<Enemy> subAbilityComponentHandler = new SubAbilityComponentHandler<>(ClassName.Enemy, trieConditions, selectingObjectsDetail2, new ArrayList<>(), new HashMap<>());
            SubSkillComponent<Enemy> subSkillComponent = new SubSkillComponent<>(subAbilityComponentHandler);
            ArrayList<SubSkillComponent<?>> subSkillComponents = new ArrayList<>();
            subSkillComponents.add(subSkillComponent);
            ArrayList<String> nameOfNecessaryAbilities = new ArrayList<>();
            Map<String, Integer> gradeOfNecessaryAbilities = new HashMap<>();
            nameOfNecessaryAbilities.add("Fight training");
            gradeOfNecessaryAbilities.put("Fight training", 1);
            SubAbilityHandler subAbilityHandler = new SubAbilityHandler(false, 2, nameOfNecessaryAbilities, gradeOfNecessaryAbilities, "Upgrade 1: N=1.2 for 2 xp points, needs Fight training upgrade 1");
            SubSkillHandler subSkillHandler = new SubSkillHandler(false, new Time(), 0, true, 2, 50, "1", subSkillComponents);
            SubSkill subSkill1 = new SubSkill(subSkillHandler, subAbilityHandler);

            selectingObjectsDetailHandler = new SelectingObjectsDetailHandler<>(ClassName.Hero, false, true, false, null, 0, false, 0, false, 0, false, false, false);
            selectingObjectsDetail = new SelectingObjectsDetail<>(selectingObjectsDetailHandler);
            tree = new Tree<>();
            pairs = new ArrayList<>();
            pairs.add(new Pair<>("attackPower", -1.4));
            tree.getRoot().addChild(pairs, new Condition());
            propertyHandler = new PropertyHandler<>("currentHealth", true, ClassName.Hero, ClassName.Enemy, 0, selectingObjectsDetail, tree);
            property = new Property<>(propertyHandler);
            properties = new ArrayList<>();
            properties.add(property);
            trieConditions = new Tree<>();
            trieConditions.getRoot().addChild(properties, new Condition());
            selectingObjectsDetailHandler2 = new SelectingObjectsDetailHandler<>(ClassName.Enemy, false, false, false, null, 0, false, 0, true, 1, false, false, false);
            selectingObjectsDetail2 = new SelectingObjectsDetail<>(selectingObjectsDetailHandler2);
            subAbilityComponentHandler = new SubAbilityComponentHandler<>(ClassName.Enemy, trieConditions, selectingObjectsDetail2, new ArrayList<>(), new HashMap<>());
            subSkillComponent = new SubSkillComponent<>(subAbilityComponentHandler);
            subSkillComponents = new ArrayList<>();
            subSkillComponents.add(subSkillComponent);
            nameOfNecessaryAbilities = new ArrayList<>();
            gradeOfNecessaryAbilities = new HashMap<>();
            nameOfNecessaryAbilities.add("Fight training");
            gradeOfNecessaryAbilities.put("Fight training", 2);
            subAbilityHandler = new SubAbilityHandler(false, 4, nameOfNecessaryAbilities, gradeOfNecessaryAbilities, "Upgrade 2: N=1.4 for 4 xp points, needs Fight training upgrade 2");
            subSkillHandler = new SubSkillHandler(false, new Time(), 0, true, 2, 50, "2", subSkillComponents);
            SubSkill subSkill2 = new SubSkill(subSkillHandler, subAbilityHandler);

            selectingObjectsDetailHandler = new SelectingObjectsDetailHandler<>(ClassName.Hero, false, true, false, null, 0, false, 0, false, 0, false, false, false);
            selectingObjectsDetail = new SelectingObjectsDetail<>(selectingObjectsDetailHandler);
            tree = new Tree<>();
            pairs = new ArrayList<>();
            pairs.add(new Pair<>("attackPower", -1.6));
            tree.getRoot().addChild(pairs, new Condition());
            propertyHandler = new PropertyHandler<>("currentHealth", true, ClassName.Hero, ClassName.Enemy, 0, selectingObjectsDetail, tree);
            property = new Property<>(propertyHandler);
            properties = new ArrayList<>();
            properties.add(property);
            trieConditions = new Tree<>();
            trieConditions.getRoot().addChild(properties, new Condition());
            selectingObjectsDetailHandler2 = new SelectingObjectsDetailHandler<>(ClassName.Enemy, false, false, false, null, 0, false, 0, true, 1, false, false, false);
            selectingObjectsDetail2 = new SelectingObjectsDetail<>(selectingObjectsDetailHandler2);
            subAbilityComponentHandler = new SubAbilityComponentHandler<>(ClassName.Enemy, trieConditions, selectingObjectsDetail2, new ArrayList<>(), new HashMap<>());
            subSkillComponent = new SubSkillComponent<>(subAbilityComponentHandler);
            subSkillComponents = new ArrayList<>();
            subSkillComponents.add(subSkillComponent);
            nameOfNecessaryAbilities = new ArrayList<>();
            gradeOfNecessaryAbilities = new HashMap<>();
            nameOfNecessaryAbilities.add("Fight training");
            gradeOfNecessaryAbilities.put("Fight training", 3);
            subAbilityHandler = new SubAbilityHandler(false, 6, nameOfNecessaryAbilities, gradeOfNecessaryAbilities, "Upgrade 3: N=1.6 for 6 xp points, needs Fight training upgrade 3");
            subSkillHandler = new SubSkillHandler(false, new Time(), 0, true, 2, 50, "3", subSkillComponents);
            SubSkill subSkill3 = new SubSkill(subSkillHandler, subAbilityHandler);

            Tree<SubSkill> subSkillTree = new Tree<>();
            Tree.Node<SubSkill> node = subSkillTree.getRoot();
            node.addChild(subSkill1, new Condition());
            node = node.getChildren().get(0);
            node.addChild(subSkill2, new Condition());
            node = node.getChildren().get(0);
            node.addChild(subSkill3, new Condition());
            AbilityHandler abilityHandler = new AbilityHandler("Overpowered attack", "Eley" ,3, "Attacks an enemy with N times power for 2 energy points and 50 magic points");
            Skill skill = new Skill(abilityHandler, subSkillTree);
            subSkill1.setRelatedSkill(skill);
            subSkill2.setRelatedSkill(skill);
            subSkill3.setRelatedSkill(skill);
            this.addNewSkill(skill);
        }
        {
            //Chrome's Skill: Sacrifice

            Tree<ArrayList<Pair<String, Double>>> tree = new Tree<>();
            PropertyHandler<Object> propertyHandler = new PropertyHandler<>("currentHealth", true, null, ClassName.Enemy, -120, new SelectingObjectsDetail<>(), tree);
            Property<Enemy, Object> property = new Property<>(propertyHandler);
            ArrayList<Property<Enemy, ?>> properties = new ArrayList<>();
            properties.add(property);
            Tree<ArrayList<Property<Enemy, ?>>> trieConditions = new Tree<>();
            trieConditions.getRoot().addChild(properties, new Condition());
            SelectingObjectsDetailHandler<Enemy> selectingObjectsDetailHandler = new SelectingObjectsDetailHandler<>(ClassName.Enemy, true, false, false, null, 0, false, 0, false, 0, false, false, false);
            SelectingObjectsDetail<Enemy> selectingObjectsDetail = new SelectingObjectsDetail<>(selectingObjectsDetailHandler);
            SubAbilityComponentHandler<Enemy> subAbilityComponentHandler = new SubAbilityComponentHandler<>(ClassName.Enemy, trieConditions, selectingObjectsDetail, new ArrayList<>(), new HashMap<>());
            SubSkillComponent<Enemy> subSkillComponent = new SubSkillComponent<>(subAbilityComponentHandler);
            propertyHandler = new PropertyHandler<>("currentHealth", true, null, ClassName.Hero, -40, null ,null);
            Property<Hero, Object> property2 = new Property<>(propertyHandler);
            ArrayList<Property<Hero, ?>> properties2 = new ArrayList<>();
            properties2.add(property2);
            Tree<ArrayList<Property<Hero, ?>>> trieConditions2 = new Tree<>();
            trieConditions2.getRoot().addChild(properties2, new Condition());
            SelectingObjectsDetailHandler<Hero> selectingObjectsDetailHandler2 = new SelectingObjectsDetailHandler<>(ClassName.Hero, false, true, false, null, 0, false, 0, false, 0, false, false, false);
            SelectingObjectsDetail<Hero> selectingObjectsDetail2 = new SelectingObjectsDetail<>(selectingObjectsDetailHandler2);
            SubAbilityComponentHandler<Hero> subAbilityComponentHandler2 = new SubAbilityComponentHandler<>(ClassName.Hero, trieConditions2, selectingObjectsDetail2, new ArrayList<>(), new HashMap<>());
            SubSkillComponent<Hero> subSkillComponent2 = new SubSkillComponent<>(subAbilityComponentHandler2);
            ArrayList<SubSkillComponent<?>> subSkillComponents = new ArrayList<>();
            subSkillComponents.add(subSkillComponent);
            subSkillComponents.add(subSkillComponent2);
            ArrayList<String> nameOfNecessaryAbilities = new ArrayList<>();
            Map<String, Integer> gradeOfNecessaryAbilities = new HashMap<>();
            nameOfNecessaryAbilities.add("Work out");
            gradeOfNecessaryAbilities.put("Work out", 1);
            SubAbilityHandler subAbilityHandler = new SubAbilityHandler(false, 2, nameOfNecessaryAbilities, gradeOfNecessaryAbilities, "Upgrade 1: H=40 for 2 xp points, needs Work out upgrade 1");
            SubSkillHandler subSkillHandler = new SubSkillHandler(false, new Time(), 1, true, 3, 60, "1", subSkillComponents);
            SubSkill subSkill1 = new SubSkill(subSkillHandler, subAbilityHandler);

            propertyHandler = new PropertyHandler<>("currentHealth", true, null, ClassName.Enemy, -150, null ,null);
            property = new Property<>(propertyHandler);
            properties = new ArrayList<>();
            properties.add(property);
            trieConditions = new Tree<>();
            trieConditions.getRoot().addChild(properties, new Condition());
            selectingObjectsDetailHandler = new SelectingObjectsDetailHandler<>(ClassName.Enemy, true, false, false, null, 0, false, 0, false, 0, false, false, false);
            selectingObjectsDetail = new SelectingObjectsDetail<>(selectingObjectsDetailHandler);
            subAbilityComponentHandler = new SubAbilityComponentHandler<>(ClassName.Enemy, trieConditions, selectingObjectsDetail, new ArrayList<>(), new HashMap<>());
            subSkillComponent = new SubSkillComponent<>(subAbilityComponentHandler);
            propertyHandler = new PropertyHandler<>("currentHealth", true, null, ClassName.Hero, -50, null ,null);
            property2 = new Property<>(propertyHandler);
            properties2 = new ArrayList<>();
            properties2.add(property2);
            trieConditions2 = new Tree<>();
            trieConditions2.getRoot().addChild(properties2, new Condition());
            selectingObjectsDetailHandler2 = new SelectingObjectsDetailHandler<>(ClassName.Hero, false, true, false, null, 0, false, 0, false, 0, false, false, false);
            selectingObjectsDetail2 = new SelectingObjectsDetail<>(selectingObjectsDetailHandler2);
            subAbilityComponentHandler2 = new SubAbilityComponentHandler<>(ClassName.Hero, trieConditions2, selectingObjectsDetail2, new ArrayList<>(), new HashMap<>());
            subSkillComponent2 = new SubSkillComponent<>(subAbilityComponentHandler2);
            subSkillComponents = new ArrayList<>();
            subSkillComponents.add(subSkillComponent);
            subSkillComponents.add(subSkillComponent2);
            nameOfNecessaryAbilities = new ArrayList<>();
            gradeOfNecessaryAbilities = new HashMap<>();
            nameOfNecessaryAbilities.add("Work out");
            gradeOfNecessaryAbilities.put("Work out", 2);
            subAbilityHandler = new SubAbilityHandler(false, 3, nameOfNecessaryAbilities, gradeOfNecessaryAbilities, "Upgrade 2: H=50 for 3 xp points, needs Work out upgrade 2");
            subSkillHandler = new SubSkillHandler(false, new Time(), 1, true, 3, 60, "2", subSkillComponents);
            SubSkill subSkill2 = new SubSkill(subSkillHandler, subAbilityHandler);

            propertyHandler = new PropertyHandler<>("currentHealth", true, null, ClassName.Enemy, -180, null ,null);
            property = new Property<>(propertyHandler);
            properties = new ArrayList<>();
            properties.add(property);
            trieConditions = new Tree<>();
            trieConditions.getRoot().addChild(properties, new Condition());
            selectingObjectsDetailHandler = new SelectingObjectsDetailHandler<>(ClassName.Enemy, true, false, false, null, 0, false, 0, false, 0, false, false, false);
            selectingObjectsDetail = new SelectingObjectsDetail<>(selectingObjectsDetailHandler);
            subAbilityComponentHandler = new SubAbilityComponentHandler<>(ClassName.Enemy, trieConditions, selectingObjectsDetail, new ArrayList<>(), new HashMap<>());
            subSkillComponent = new SubSkillComponent<>(subAbilityComponentHandler);
            propertyHandler = new PropertyHandler<>("currentHealth", true, null, ClassName.Hero, -60, null ,null);
            property2 = new Property<>(propertyHandler);
            properties2 = new ArrayList<>();
            properties2.add(property2);
            trieConditions2 = new Tree<>();
            trieConditions2.getRoot().addChild(properties2, new Condition());
            selectingObjectsDetailHandler2 = new SelectingObjectsDetailHandler<>(ClassName.Hero, false, true, false, null, 0, false, 0, false, 0, false, false, false);
            selectingObjectsDetail2 = new SelectingObjectsDetail<>(selectingObjectsDetailHandler2);
            subAbilityComponentHandler2 = new SubAbilityComponentHandler<>(ClassName.Hero, trieConditions2, selectingObjectsDetail2, new ArrayList<>(), new HashMap<>());
            subSkillComponent2 = new SubSkillComponent<>(subAbilityComponentHandler2);
            subSkillComponents = new ArrayList<>();
            subSkillComponents.add(subSkillComponent);
            subSkillComponents.add(subSkillComponent2);
            nameOfNecessaryAbilities = new ArrayList<>();
            gradeOfNecessaryAbilities = new HashMap<>();
            nameOfNecessaryAbilities.add("Work out");
            gradeOfNecessaryAbilities.put("Work out", 3);
            subAbilityHandler = new SubAbilityHandler(false, 4, nameOfNecessaryAbilities, gradeOfNecessaryAbilities, "Upgrade 3: H=60 for 4 xp points, needs Work out upgrade 3");
            subSkillHandler = new SubSkillHandler(false, new Time(), 1, true, 3, 60, "3", subSkillComponents);
            SubSkill subSkill3 = new SubSkill(subSkillHandler, subAbilityHandler);

            Tree<SubSkill> subSkillTree = new Tree<>();
            Tree.Node<SubSkill> node = subSkillTree.getRoot();
            node.addChild(subSkill1, new Condition());
            node = node.getChildren().get(0);
            node.addChild(subSkill2, new Condition());
            node = node.getChildren().get(0);
            node.addChild(subSkill3, new Condition());
            AbilityHandler abilityHandler = new AbilityHandler("Sacrifice", "Chrome", 3, "Damages all the enemies with 3H power at the cost of H of his own health, needs 3 energy points, 60 magic points and has a 1 turn cooldown");
            Skill skill = new Skill(abilityHandler, subSkillTree);
            subSkill1.setRelatedSkill(skill);
            subSkill2.setRelatedSkill(skill);
            subSkill3.setRelatedSkill(skill);
            this.addNewSkill(skill);
        }
        {
            //Meryl's Skill: Elixir

            Tree<ArrayList<Pair<String, Double>>> tree = new Tree<>();
            PropertyHandler<Object> propertyHandler = new PropertyHandler<>("currentHealth", true, null, ClassName.Hero, 100, new SelectingObjectsDetail<>() , tree);
            Property<Hero, Object> property = new Property<>(propertyHandler);
            ArrayList<Property<Hero, ?>> properties = new ArrayList<>();
            properties.add(property);
            Tree<ArrayList<Property<Hero, ?>>> trieConditions = new Tree<>();
            trieConditions.getRoot().addChild(properties, new Condition());
            SelectingObjectsDetailHandler<Hero> selectingObjectsDetailHandler = new SelectingObjectsDetailHandler<>(ClassName.Hero, false, false, false, null, 0, false, 0, true, 1, false, false, false);
            SelectingObjectsDetail<Hero> selectingObjectsDetail = new SelectingObjectsDetail<>(selectingObjectsDetailHandler);
            SubAbilityComponentHandler<Hero> subAbilityComponentHandler = new SubAbilityComponentHandler<>(ClassName.Hero, trieConditions, selectingObjectsDetail, new ArrayList<>(), new HashMap<>());
            SubSkillComponent<Hero> subSkillComponent = new SubSkillComponent<>(subAbilityComponentHandler);
            ArrayList<SubSkillComponent<?>> subSkillComponents = new ArrayList<>();
            subSkillComponents.add(subSkillComponent);
            SubAbilityHandler subAbilityHandler = new SubAbilityHandler(false, 2, new ArrayList<>(), new HashMap<>(), "Upgrade 1: H=100 for 2 xp points and takes 1 turn to cool down");
            SubSkillHandler subSkillHandler = new SubSkillHandler(false, new Time(), 1, true, 2, 60, "1", subSkillComponents);
            SubSkill subSkill1 = new SubSkill(subSkillHandler, subAbilityHandler);

            propertyHandler = new PropertyHandler<>("currentHealth", true, null, ClassName.Hero, 150, null ,null);
            property = new Property<>(propertyHandler);
            properties = new ArrayList<>();
            properties.add(property);
            trieConditions = new Tree<>();
            trieConditions.getRoot().addChild(properties, new Condition());
            selectingObjectsDetailHandler = new SelectingObjectsDetailHandler<>(ClassName.Hero, false, false, false, null, 0, false, 0, true, 1, false, false, false);
            selectingObjectsDetail = new SelectingObjectsDetail<>(selectingObjectsDetailHandler);
            subAbilityComponentHandler = new SubAbilityComponentHandler<>(ClassName.Hero, trieConditions, selectingObjectsDetail, new ArrayList<>(), new HashMap<>());
            subSkillComponent = new SubSkillComponent<>(subAbilityComponentHandler);
            subSkillComponents = new ArrayList<>();
            subSkillComponents.add(subSkillComponent);
            ArrayList<String> nameOfNecessaryAbilities = new ArrayList<>();
            Map<String, Integer> gradeOfNecessaryAbilities = new HashMap<>();
            nameOfNecessaryAbilities.add("Magic lessons");
            gradeOfNecessaryAbilities.put("Magic lessons", 1);
            subAbilityHandler = new SubAbilityHandler(false, 3, nameOfNecessaryAbilities, gradeOfNecessaryAbilities, "Upgrade 2: H=150 for 3 xp points, takes 1 turn to cool down and needs Magic lessons upgrade 1");
            subSkillHandler = new SubSkillHandler(false, new Time(), 1, true, 2, 60, "2", subSkillComponents);
            SubSkill subSkill2 = new SubSkill(subSkillHandler, subAbilityHandler);

            propertyHandler = new PropertyHandler<>("currentHealth", true, null, ClassName.Hero, 150, null ,null);
            property = new Property<>(propertyHandler);
            properties = new ArrayList<>();
            properties.add(property);
            trieConditions = new Tree<>();
            trieConditions.getRoot().addChild(properties, new Condition());
            selectingObjectsDetailHandler = new SelectingObjectsDetailHandler<>(ClassName.Hero, false, false, false, null, 0, false, 0, true, 1, false, false, false);
            selectingObjectsDetail = new SelectingObjectsDetail<>(selectingObjectsDetailHandler);
            subAbilityComponentHandler = new SubAbilityComponentHandler<>(ClassName.Hero, trieConditions, selectingObjectsDetail, new ArrayList<>(), new HashMap<>());
            subSkillComponent = new SubSkillComponent<>(subAbilityComponentHandler);
            subSkillComponents = new ArrayList<>();
            subSkillComponents.add(subSkillComponent);
            nameOfNecessaryAbilities = new ArrayList<>();
            gradeOfNecessaryAbilities = new HashMap<>();
            nameOfNecessaryAbilities.add("Magic lessons");
            gradeOfNecessaryAbilities.put("Magic lessons", 2);
            subAbilityHandler = new SubAbilityHandler(false, 5, nameOfNecessaryAbilities, gradeOfNecessaryAbilities, "Upgrade 3: H=150 for 5 xp points, cools down instantly and needs Magic lessons upgrade 2");
            subSkillHandler = new SubSkillHandler(false, new Time(), 0, true, 2, 60, "3", subSkillComponents);
            SubSkill subSkill3 = new SubSkill(subSkillHandler, subAbilityHandler);

            Tree<SubSkill> subSkillTree = new Tree<>();
            Tree.Node<SubSkill> node = subSkillTree.getRoot();
            node.addChild(subSkill1, new Condition());
            node = node.getChildren().get(0);
            node.addChild(subSkill2, new Condition());
            node = node.getChildren().get(0);
            node.addChild(subSkill3, new Condition());
            AbilityHandler abilityHandler = new AbilityHandler("Elixir", "Meryl", 3, "Refills H points of her own health or an ally’s, for 2 energy points and 60 magic points");
            Skill skill = new Skill(abilityHandler, subSkillTree);
            subSkill1.setRelatedSkill(skill);
            subSkill2.setRelatedSkill(skill);
            subSkill3.setRelatedSkill(skill);
            this.addNewSkill(skill);
        }
        {
            //Meryl's Skill: Caretaker

            Tree<ArrayList<Pair<String, Double>>> tree = new Tree<>();
            PropertyHandler<Object> propertyHandler = new PropertyHandler<>("currentEnergyPoint", false, null, ClassName.Hero, 1, new SelectingObjectsDetail<>(), tree);
            Property<Hero, Object> property = new Property<>(propertyHandler);
            ArrayList<Property<Hero, ?>> properties = new ArrayList<>();
            properties.add(property);
            Tree<ArrayList<Property<Hero, ?>>> trieConditions = new Tree<>();
            trieConditions.getRoot().addChild(properties, new Condition());
            SelectingObjectsDetailHandler<Hero> selectingObjectsDetailHandler = new SelectingObjectsDetailHandler<>(ClassName.Hero, false, false, false, null, 0, false, 0, true, 1, false, false, false);
            SelectingObjectsDetail<Hero> selectingObjectsDetail = new SelectingObjectsDetail<>(selectingObjectsDetailHandler);
            SubAbilityComponentHandler<Hero> subAbilityComponentHandler = new SubAbilityComponentHandler<>(ClassName.Hero, trieConditions, selectingObjectsDetail, new ArrayList<>(), new HashMap<>());
            SubSkillComponent<Hero> subSkillComponent = new SubSkillComponent<>(subAbilityComponentHandler);
            ArrayList<SubSkillComponent<?>> subSkillComponents = new ArrayList<>();
            subSkillComponents.add(subSkillComponent);
            ArrayList<String> nameOfNecessaryAbilities = new ArrayList<>();
            Map<String, Integer> gradeOfNecessaryAbilities = new HashMap<>();
            nameOfNecessaryAbilities.add("Quick as a bunny");
            gradeOfNecessaryAbilities.put("Quick as a bunny", 1);
            SubAbilityHandler subAbilityHandler = new SubAbilityHandler(false, 2, nameOfNecessaryAbilities, gradeOfNecessaryAbilities, "Upgrade 1: takes 2 energy points and has a 1 turn cooldown for 2 xp points, needs Quick as a bunny upgrade 1");
            SubSkillHandler subSkillHandler = new SubSkillHandler(false, new Time(0, 1, 0), 1, false, 2, 30, "1", subSkillComponents);
            SubSkill subSkill1 = new SubSkill(subSkillHandler, subAbilityHandler);

            propertyHandler = new PropertyHandler<>("currentEnergyPoint", false, null, ClassName.Hero, 1, null ,null);
            property = new Property<>(propertyHandler);
            properties = new ArrayList<>();
            properties.add(property);
            trieConditions = new Tree<>();
            trieConditions.getRoot().addChild(properties, new Condition());
            selectingObjectsDetailHandler = new SelectingObjectsDetailHandler<>(ClassName.Hero, false, false, false, null, 0, false, 0, true, 1, false, false, false);
            selectingObjectsDetail = new SelectingObjectsDetail<>(selectingObjectsDetailHandler);
            subAbilityComponentHandler = new SubAbilityComponentHandler<>(ClassName.Hero, trieConditions, selectingObjectsDetail, new ArrayList<>(), new HashMap<>());
            subSkillComponent = new SubSkillComponent<>(subAbilityComponentHandler);
            subSkillComponents = new ArrayList<>();
            subSkillComponents.add(subSkillComponent);
            nameOfNecessaryAbilities = new ArrayList<>();
            gradeOfNecessaryAbilities = new HashMap<>();
            nameOfNecessaryAbilities.add("Quick as a bunny");
            gradeOfNecessaryAbilities.put("Quick as a bunny", 2);
            subAbilityHandler = new SubAbilityHandler(false, 3, nameOfNecessaryAbilities, gradeOfNecessaryAbilities, "Upgrade 2: takes 2 energy points and cools down instantly for 3 xp points, needs Quick as a bunny upgrade 2");
            subSkillHandler = new SubSkillHandler(false, new Time(0, 1, 0), 0, false, 2, 30, "2", subSkillComponents);
            SubSkill subSkill2 = new SubSkill(subSkillHandler, subAbilityHandler);

            propertyHandler = new PropertyHandler<>("currentEnergyPoint", false, null, ClassName.Hero, 1, null ,null);
            property = new Property<>(propertyHandler);
            properties = new ArrayList<>();
            properties.add(property);
            trieConditions = new Tree<>();
            trieConditions.getRoot().addChild(properties, new Condition());
            selectingObjectsDetailHandler = new SelectingObjectsDetailHandler<>(ClassName.Hero, false, false, false, null, 0, false, 0, true, 1, false, false, false);
            selectingObjectsDetail = new SelectingObjectsDetail<>(selectingObjectsDetailHandler);
            subAbilityComponentHandler = new SubAbilityComponentHandler<>(ClassName.Hero, trieConditions, selectingObjectsDetail, new ArrayList<>(), new HashMap<>());
            subSkillComponent = new SubSkillComponent<>(subAbilityComponentHandler);
            subSkillComponents = new ArrayList<>();
            subSkillComponents.add(subSkillComponent);
            nameOfNecessaryAbilities = new ArrayList<>();
            gradeOfNecessaryAbilities = new HashMap<>();
            nameOfNecessaryAbilities.add("Quick as a bunny");
            gradeOfNecessaryAbilities.put("Quick as a bunny", 3);
            subAbilityHandler = new SubAbilityHandler(false, 5, nameOfNecessaryAbilities, gradeOfNecessaryAbilities, "Upgrade 3 takes 1 energy point and cools down instantly for 5 xp points, needs Quick as a bunny upgrade 3");
            subSkillHandler = new SubSkillHandler(false, new Time(0, 1, 0), 0, false, 1, 30, "3", subSkillComponents);
            SubSkill subSkill3 = new SubSkill(subSkillHandler, subAbilityHandler);

            Tree<SubSkill> subSkillTree = new Tree<>();
            Tree.Node<SubSkill> node = subSkillTree.getRoot();
            node.addChild(subSkill1, new Condition());
            node = node.getChildren().get(0);
            node.addChild(subSkill2, new Condition());
            node = node.getChildren().get(0);
            node.addChild(subSkill3, new Condition());
            AbilityHandler abilityHandler = new AbilityHandler("Caretaker", "Meryl", 3, "Gives 1 energy point to an ally for 30 magic points (this ep does not last until the end of the battle and is only usable during the current turn)");
            Skill skill = new Skill(abilityHandler, subSkillTree);
            subSkill1.setRelatedSkill(skill);
            subSkill2.setRelatedSkill(skill);
            subSkill3.setRelatedSkill(skill);
            this.addNewSkill(skill);
        }
        {
            //Bolti's Skill: Boost

            Tree<ArrayList<Pair<String, Double>>> tree = new Tree<>();
            PropertyHandler<Object> propertyHandler = new PropertyHandler<>("attackPowerRatioDuringAttack", false, null, ClassName.Hero, .20, new SelectingObjectsDetail<>(), tree);
            Property<Hero, Object> property = new Property<>(propertyHandler);
            ArrayList<Property<Hero, ?>> properties = new ArrayList<>();
            properties.add(property);
            Tree<ArrayList<Property<Hero, ?>>> trieConditions = new Tree<>();
            trieConditions.getRoot().addChild(properties, new Condition());
            SelectingObjectsDetailHandler<Hero> selectingObjectsDetailHandler = new SelectingObjectsDetailHandler<>(ClassName.Hero, false, false, false, null, 0, false, 0, true, 1, false, false, false);
            SelectingObjectsDetail<Hero> selectingObjectsDetail = new SelectingObjectsDetail<>(selectingObjectsDetailHandler);
            SubAbilityComponentHandler<Hero> subAbilityComponentHandler = new SubAbilityComponentHandler<>(ClassName.Hero, trieConditions, selectingObjectsDetail, new ArrayList<>(), new HashMap<>());
            SubSkillComponent<Hero> subSkillComponent = new SubSkillComponent<>(subAbilityComponentHandler);
            ArrayList<SubSkillComponent<?>> subSkillComponents = new ArrayList<>();
            subSkillComponents.add(subSkillComponent);
            SubAbilityHandler subAbilityHandler = new SubAbilityHandler(false, 2, new ArrayList<>(), new HashMap<>(), "Upgrade 1: A=20 for 2 xp points and takes 1 turn to cool down");
            SubSkillHandler subSkillHandler = new SubSkillHandler(false, new Time(0, 0, 1), 1, true, 2, 50, "1", subSkillComponents);
            SubSkill subSkill1 = new SubSkill(subSkillHandler, subAbilityHandler);

            propertyHandler = new PropertyHandler<>("attackPowerRatioDuringAttack", false, null, ClassName.Hero, 0.3, null ,null);
            property = new Property<>(propertyHandler);
            properties = new ArrayList<>();
            properties.add(property);
            trieConditions = new Tree<>();
            trieConditions.getRoot().addChild(properties, new Condition());
            selectingObjectsDetailHandler = new SelectingObjectsDetailHandler<>(ClassName.Hero, false, false, false, null, 0, false, 0, true, 1, false, false, false);
            selectingObjectsDetail = new SelectingObjectsDetail<>(selectingObjectsDetailHandler);
            subAbilityComponentHandler = new SubAbilityComponentHandler<>(ClassName.Hero, trieConditions, selectingObjectsDetail, new ArrayList<>(), new HashMap<>());
            subSkillComponent = new SubSkillComponent<>(subAbilityComponentHandler);
            subSkillComponents = new ArrayList<>();
            subSkillComponents.add(subSkillComponent);
            subAbilityHandler = new SubAbilityHandler(false, 3, new ArrayList<>(), new HashMap<>(), "Upgrade 2: A=30 for 3 xp points and takes 1 turn to cool down");
            subSkillHandler = new SubSkillHandler(false, new Time(0, 0, 1), 1, true, 2, 50, "2", subSkillComponents);
            SubSkill subSkill2 = new SubSkill(subSkillHandler, subAbilityHandler);

            propertyHandler = new PropertyHandler<>("attackPowerRatioDuringAttack", false, null, ClassName.Hero, 0.3, null ,null);
            property = new Property<>(propertyHandler);
            properties = new ArrayList<>();
            properties.add(property);
            trieConditions = new Tree<>();
            trieConditions.getRoot().addChild(properties, new Condition());
            selectingObjectsDetailHandler = new SelectingObjectsDetailHandler<>(ClassName.Hero, false, false, false, null, 0, false, 0, true, 1, false, false, false);
            selectingObjectsDetail = new SelectingObjectsDetail<>(selectingObjectsDetailHandler);
            subAbilityComponentHandler = new SubAbilityComponentHandler<>(ClassName.Hero, trieConditions, selectingObjectsDetail, new ArrayList<>(), new HashMap<>());
            subSkillComponent = new SubSkillComponent<>(subAbilityComponentHandler);
            subSkillComponents = new ArrayList<>();
            subSkillComponents.add(subSkillComponent);
            subAbilityHandler = new SubAbilityHandler(false, 5, new ArrayList<>(), new HashMap<>(), "Upgrade 3: A=30 for 5 xp points and cools down instantly");
            subSkillHandler = new SubSkillHandler(false, new Time(0, 0, 1), 0, true, 2, 50, "3", subSkillComponents);
            SubSkill subSkill3 = new SubSkill(subSkillHandler, subAbilityHandler);

            Tree<SubSkill> subSkillTree = new Tree<>();
            Tree.Node<SubSkill> node = subSkillTree.getRoot();
            node.addChild(subSkill1, new Condition());
            node = node.getChildren().get(0);
            node.addChild(subSkill2, new Condition());
            node = node.getChildren().get(0);
            node.addChild(subSkill3, new Condition());
            AbilityHandler abilityHandler = new AbilityHandler("Boost", "Bolti", 3, "Gives A bonus attack power to himself or an ally, which lasts till the end of the battle, for 2 energy points and 50 magic points (this bonus attack power can stack up)");
            Skill skill = new Skill(abilityHandler, subSkillTree);
            subSkill1.setRelatedSkill(skill);
            subSkill2.setRelatedSkill(skill);
            subSkill3.setRelatedSkill(skill);
            this.addNewSkill(skill);
        }
        {
            //Bolti's Skill: Mana beam

            Tree<ArrayList<Pair<String, Double>>> tree = new Tree<>();
            PropertyHandler<Object> propertyHandler = new PropertyHandler<>("currentMagic", true, null, ClassName.Hero, 50, new SelectingObjectsDetail<>(), tree);
            Property<Hero, Object> property = new Property<>(propertyHandler);
            ArrayList<Property<Hero, ?>> properties = new ArrayList<>();
            properties.add(property);
            Tree<ArrayList<Property<Hero, ?>>> trieConditions = new Tree<>();
            trieConditions.getRoot().addChild(properties, new Condition());
            SelectingObjectsDetailHandler<Hero> selectingObjectsDetailHandler = new SelectingObjectsDetailHandler<>(ClassName.Hero, false, false, false, null, 0, false, 0, true, 1, false, false, false);
            SelectingObjectsDetail<Hero> selectingObjectsDetail = new SelectingObjectsDetail<>(selectingObjectsDetailHandler);
            SubAbilityComponentHandler<Hero> subAbilityComponentHandler = new SubAbilityComponentHandler<>(ClassName.Hero, trieConditions, selectingObjectsDetail, new ArrayList<>(), new HashMap<>());
            SubSkillComponent<Hero> subSkillComponent = new SubSkillComponent<>(subAbilityComponentHandler);
            ArrayList<SubSkillComponent<?>> subSkillComponents = new ArrayList<>();
            subSkillComponents.add(subSkillComponent);
            ArrayList<String> nameOfNecessaryAbilities = new ArrayList<>();
            Map<String, Integer> gradeOfNecessaryAbilities = new HashMap<>();
            nameOfNecessaryAbilities.add("Magic lessons");
            gradeOfNecessaryAbilities.put("Magic lessons", 1);
            SubAbilityHandler subAbilityHandler = new SubAbilityHandler(false, 2, nameOfNecessaryAbilities, gradeOfNecessaryAbilities, "Upgrade 1: M=50 for 2 xp points and takes 1 turn to cool down, needs magic lessons upgrade 1");
            SubSkillHandler subSkillHandler = new SubSkillHandler(false, new Time(), 1, true, 1, 50, "1", subSkillComponents);
            SubSkill subSkill1 = new SubSkill(subSkillHandler, subAbilityHandler);

            propertyHandler = new PropertyHandler<>("currentMagic", true, null, ClassName.Hero, 80, null ,null);
            property = new Property<>(propertyHandler);
            properties = new ArrayList<>();
            properties.add(property);
            trieConditions = new Tree<>();
            trieConditions.getRoot().addChild(properties, new Condition());
            selectingObjectsDetailHandler = new SelectingObjectsDetailHandler<>(ClassName.Hero, false, false, false, null, 0, false, 0, true, 1, false, false, false);
            selectingObjectsDetail = new SelectingObjectsDetail<>(selectingObjectsDetailHandler);
            subAbilityComponentHandler = new SubAbilityComponentHandler<>(ClassName.Hero, trieConditions, selectingObjectsDetail, new ArrayList<>(), new HashMap<>());
            subSkillComponent = new SubSkillComponent<>(subAbilityComponentHandler);
            subSkillComponents = new ArrayList<>();
            subSkillComponents.add(subSkillComponent);
            nameOfNecessaryAbilities = new ArrayList<>();
            gradeOfNecessaryAbilities = new HashMap<>();
            nameOfNecessaryAbilities.add("Magic lessons");
            gradeOfNecessaryAbilities.put("Magic lessons", 2);
            subAbilityHandler = new SubAbilityHandler(false, 3, nameOfNecessaryAbilities, gradeOfNecessaryAbilities, "Upgrade 2: M=80 for 3 xp points and takes 1 turn to cool down, needs magic lessons upgrade 2");
            subSkillHandler = new SubSkillHandler(false, new Time(), 1, true, 1, 50, "2", subSkillComponents);
            SubSkill subSkill2 = new SubSkill(subSkillHandler, subAbilityHandler);

            propertyHandler = new PropertyHandler<>("currentMagic", true, null, ClassName.Hero, 80, null ,null);
            property = new Property<>(propertyHandler);
            properties = new ArrayList<>();
            properties.add(property);
            trieConditions = new Tree<>();
            trieConditions.getRoot().addChild(properties, new Condition());
            selectingObjectsDetailHandler = new SelectingObjectsDetailHandler<>(ClassName.Hero, false, false, false, null, 0, false, 0, true, 1, false, false, false);
            selectingObjectsDetail = new SelectingObjectsDetail<>(selectingObjectsDetailHandler);
            subAbilityComponentHandler = new SubAbilityComponentHandler<>(ClassName.Hero, trieConditions, selectingObjectsDetail, new ArrayList<>(), new HashMap<>());
            subSkillComponent = new SubSkillComponent<>(subAbilityComponentHandler);
            subSkillComponents = new ArrayList<>();
            subSkillComponents.add(subSkillComponent);
            nameOfNecessaryAbilities = new ArrayList<>();
            gradeOfNecessaryAbilities = new HashMap<>();
            nameOfNecessaryAbilities.add("Magic lessons");
            gradeOfNecessaryAbilities.put("Magic lessons", 3);
            subAbilityHandler = new SubAbilityHandler(false, 4, nameOfNecessaryAbilities, gradeOfNecessaryAbilities, "Upgrade 3: M=80 for 4 xp points and cools down instantly, needs magic lessons upgrade 3");
            subSkillHandler = new SubSkillHandler(false, new Time(), 0, true, 1, 50, "3", subSkillComponents);
            SubSkill subSkill3 = new SubSkill(subSkillHandler, subAbilityHandler);

            Tree<SubSkill> subSkillTree = new Tree<>();
            Tree.Node<SubSkill> node = subSkillTree.getRoot();
            node.addChild(subSkill1, new Condition());
            node = node.getChildren().get(0);
            node.addChild(subSkill2, new Condition());
            node = node.getChildren().get(0);
            node.addChild(subSkill3, new Condition());
            AbilityHandler abilityHandler = new AbilityHandler("Mana beam", "Bolti", 3, "Gives M magic points to himself or an ally for 1 energy point and 50 magic points");
            Skill skill = new Skill(abilityHandler, subSkillTree);
            subSkill1.setRelatedSkill(skill);
            subSkill2.setRelatedSkill(skill);
            subSkill3.setRelatedSkill(skill);
            this.addNewSkill(skill);
        }
    }

    public void doCustomGame(){
        this.setCustomGame(true);
        while (true){
            //Welcome To Our Adding Factory
            Hero newHero = new Hero();
            this.addNewHero(newHero);
        }
    }

    public void heroesAnnouncement(){
        this.showHeroTeamDescription();
        while(true) {
            Display.printInEachLine("Any Question?(If no type 'Next')");
            String command = Display.getString();
            if (command.equalsIgnoreCase("Next")) {
                return;
            }
            for (Hero hero : this.listOfHeroes) {      //(hero name) + “?”
                if (command.equalsIgnoreCase(hero.getName() + "?")) {
                    hero.showDescription();
                    break;
                }
            }
        }
    }

    public void enemiesAnnouncement(){
        this.showEnemyTeamDescription();
        while(true) {
            Display.printInEachLine("Any Question?(If no type 'Next')");
            String command = Display.getString();
            if (command.equalsIgnoreCase("next")) {
                return;
            }
            for (Enemy enemy : this.listOfEnemies) {
                if (command.equalsIgnoreCase(enemy.getName() + "?")) {
                    enemy.showDescription();
                    break;
                }
            }
        }
    }

    public void setEnemies(int numberOfBattle){
        if(numberOfBattle == 1){
            this.listOfEnemies.add(new Thug("Weak"));
            this.listOfEnemies.add(new Thug("Weak"));
            this.listOfEnemies.add(new Thug("Weak"));
            this.listOfEnemies.add(new Angel("Weak"));
            for (Enemy enemy: listOfEnemies) {
                Enemy.mapOfEnemies.put(enemy.getName(), enemy);
            }
        }
        else if(numberOfBattle == 2){
            this.listOfEnemies.add(new Thug("Able"));
            this.listOfEnemies.add(new Thug("Able"));
            this.listOfEnemies.add(new Angel("Weak"));
            this.listOfEnemies.add(new Tank("Weak"));
            for (Enemy enemy: listOfEnemies) {
                Enemy.mapOfEnemies.put(enemy.getName(), enemy);
            }
        }
        else if(numberOfBattle == 3){
            this.listOfEnemies.add(new Thug("Able"));
            this.listOfEnemies.add(new Thug("Mighty"));
            this.listOfEnemies.add(new Angel("Able"));
            this.listOfEnemies.add(new Tank("Weak"));
            for (Enemy enemy: listOfEnemies) {
                Enemy.mapOfEnemies.put(enemy.getName(), enemy);
            }
        }
        else if(numberOfBattle == 4){
            this.listOfEnemies.add(new Thug("Mighty"));
            this.listOfEnemies.add(new Thug("Mighty"));
            this.listOfEnemies.add(new Angel("Able"));
            this.listOfEnemies.add(new Tank("Able"));
            this.listOfEnemies.add(new Tank("Able"));
            for (Enemy enemy: listOfEnemies) {
                Enemy.mapOfEnemies.put(enemy.getName(), enemy);
            }
        }
        else if(numberOfBattle == 5) {
            this.listOfEnemies.add(new FinalBoss());
            Enemy.mapOfEnemies.put(listOfEnemies.get(0).getName(), listOfEnemies.get(0));
        }
    }

    public void updateAllPerks(){
        for (Hero hero: this.listOfHeroes) {
            for(Perk perk: hero.getPerks()){
                if (perk.isAcquire() == false)
                    continue;
                if (perk.getTimeOfCheck() == Perk.TimeOfCheck.duringAttackDefend)
                    continue;
                perk.updatePerkEffect(Hero.mapOfHeroes.get(perk.getOwnerName()));
            }
            hero.getSelfImprovement().checkCondition(this.player);
        }
        this.updateAllPerkItems();
    }

    public void updateAllSkills(String typeOfTime){
        for (Hero hero: this.listOfHeroes) {
            for (Skill skill: hero.getSkills()) {
                if (skill.isAcquire() == false)
                    continue;
                if (typeOfTime.equals("NumberOfTurns") == false)
                    skill.reduceTime(typeOfTime);
                skill.removeEffect();
                if (skill.getRemainingCooldown() != 0 && typeOfTime.equals("NumberOfCycles"))
                    skill.setRemainingCooldown(skill.getRemainingCooldown() - 1);
                if (typeOfTime.equals("NumberOfClashes"))
                    skill.setRemainingCooldown(0);
            }
        }
        this.updateAllSkillItems(typeOfTime);
    }

    public void updateAllPerkItems() {
        for (Hero hero: this.listOfHeroes) {
            for (PerkItem perkItem: hero.getListOfPerkItems()) {
                if (perkItem.getPerk().isAcquire() == false)
                    continue;
                if (perkItem.getPerk().getTimeOfCheck() == Perk.TimeOfCheck.duringAttackDefend)
                    continue;
                perkItem.updateItemEffect(Hero.mapOfHeroes.get(perkItem.getOwnerName()));
            }
        }
    }

    public void updateAllSkillItems(String typeOfTime) {
        for (Hero hero: this.listOfHeroes) {
            for (SkillItem skillItem: hero.getListOfSkillItems()) {
                if (skillItem.getSkill().isAcquire() == false)
                    continue;
                if (typeOfTime.equals("NumberOfTurns") == false)
                    skillItem.getSkill().reduceTime(typeOfTime);
                skillItem.removeEffect();
                if (skillItem.getRemainingCooldown() != 0 && typeOfTime.equals("NumberOfCycles"))
                    skillItem.getSkill().setRemainingCooldown(skillItem.getRemainingCooldown() - 1);
                if (typeOfTime.equals("NumberOfClashes"))
                    skillItem.getSkill().setRemainingCooldown(0);
            }
        }
        ArrayList<SkillItem> removedSkillItems = new ArrayList<>();
        for (SkillItem skillItem: GameEngine.listOfRemovedSkillItemsButHaveEffect) {
            if (typeOfTime.equals("NumberOfTurns") == false)
                skillItem.getSkill().reduceTime(typeOfTime);
            skillItem.removeEffect();
            if (skillItem.getSkill().numberOfEffectedObjects() == 0) {
                removedSkillItems.add(skillItem);
            }
        }
        GameEngine.listOfRemovedSkillItemsButHaveEffect.removeAll(removedSkillItems);
    }

    public void doCampaign(int battleNumber) {                                               // do Campaign Game (not Custom Game)
        setEnemies(battleNumber + 1);
        this.updateAllPerks();
        this.showBattleMessage(battleNumber + 1);
        while (true) {
            for(Hero hero : this.listOfHeroes){
                hero.setCurrentEnergyPoint(hero.getMaximumEnergyPoint());
                hero.setCurrentHealth(hero.getMaximumHealth());
                hero.setCurrentMagic(hero.getMaximumMagic());
            }
            Display.printInEachLine("#######################################");
            Display.printInEachLine("Choose What Do You Want To Do??");
            Display.printInEachLine("1 - Show Hero Team");
            Display.printInEachLine("2 - Show Enemy Team");
            Display.printInEachLine("3 - Show Shop items");
            Display.printInEachLine("4 - Aquire Or Update Abilities");
            Display.printInEachLine("5 - Go For Fight");
            Display.printInEachLine("6 - Help!");
            Display.printInEachLine("7 - Again!");
            int numberEntered = Display.getInteger();
            if (numberEntered == 1) {
                this.heroesAnnouncement();
                Display.printInEachLine("#######################################");
                continue;
            } else if (numberEntered == 2) {
                this.enemiesAnnouncement();
                Display.printInEachLine("#######################################");
                continue;
            } else if (numberEntered == 3) {
                this.shoppingCommands();
                Display.printInEachLine("#######################################");
                continue;
            } else if (numberEntered == 4) {
                this.abilityCastCommands();
                Display.printInEachLine("#######################################");
                continue;
            } else if (numberEntered == 5) {
                break;
            }
            else if(numberEntered == 6){
                Display.printInEachLine("(item name) + “?” ----> (item description)");
                Display.printInEachLine("(ability name) + “?” ----> (ability description)");
                Display.printInEachLine("(hero name) + “?” ----> (hero description)");
                Display.printInEachLine("(enemy name) + “?” ----> (enemy description)");
                Display.printInEachLine("(hero name) + “ cast “ + (ability name) + “ on “ + (hero name / enemy name and id) ----> cast hero's ability");
                Display.printInEachLine("(hero name) + “ use “ + (item name) + “ on “ + (hero name / enemy name and id) ----> uses item of hero");
                Display.printInEachLine("(hero name) + “ attack “ + (enemy name and id) ----> When You Want to Attack An Enemy");
                Display.printInEachLine("We Have Menu For You in All Activities And It's Very Very Simple!    (:D)");
                continue;
            }
            else if (numberEntered == 7) {
                this.showBattleMessage(getNumberOfBattle());
            }
            else {
                Display.printInEachLine("Wrong Number!Try Again!");
                continue;
            }
        }
        Display.printInEachLine("Welcome To The War!");
        while (true) {
            Display.printInEachLine("Choose What To Do?");
            Display.printInEachLine("1 - Show Hero Team Description");
            Display.printInEachLine("2 - Show Enemy Team Description");
            Display.printInEachLine("3 - Other Commands");
            Display.printInEachLine("4 - Are You Done?");
            Display.printInEachLine("Enter An Integer:");
            int numberEntered = Display.getInteger();
            if (numberEntered == 1) {
                this.heroesAnnouncement();
            }
            else if (numberEntered == 2) {
                this.enemiesAnnouncement();
            }
            else if(numberEntered == 3){
                Display.printInEachLine("Enter Your Command:");
                String command = Display.getString();
                if (command.equals("dead cheat")) {
                    for (Enemy enemy: listOfEnemies) {
                        enemy.setCurrentHealth(0);
                    }
                }
                if (command.equalsIgnoreCase("Again")) {
                    this.showBattleMessage(this.getNumberOfBattle());
                }
                for (Hero hero : this.listOfHeroes) {
                    if (command.equalsIgnoreCase(hero.getName() + "?")) {
                        hero.showDescription();
                        break;
                    }
                    if (command.contains(hero.getName())) {
                        for (Skill skill : hero.getSkills()) {
                            if (command.equalsIgnoreCase(hero.getName() + " " + skill.getName() + "?")) {//(hero name) + “ “ +(ability name) + "?"
                                skill.showDescription();
                                if (skill.getCurrentGrade() == 0) {
                                    Display.printInEachLine("This Ability is not acquires!");
                                }
                                else {
                                    Display.printInEachLine(skill.getThisGradeDescription());
                                }
                                if (skill.getCurrentGrade() != skill.getNumberOfGrades()) {
                                    Display.printInEachLine("Next grades are: ");
                                    for (SubSkill subSkill: skill.getNextGradeSubSkills()) {
                                        Display.printf(skill.getThisGradeDescription());
                                        int costOfUpgrade = subSkill.getCostOfUpgrade();
                                        Display.printf(" - You need " + costOfUpgrade + " experience points to acquire it.");
                                    }
                                }
                                break;
                            }
                        }
                        for (Enemy enemy : this.listOfEnemies) {
                            if (command.equalsIgnoreCase(hero.getName() + " attack " + enemy.getName())) {
                                if (hero.getCurrentEnergyPoint() < 2) {
                                    Display.printInEachLine(hero.getName() + "'s energy point isn't enough to attack.");
                                    break;
                                }
                                int attackPower = hero.attack(enemy);
                                hero.attackOnNonTargetedEnemies(enemy);
                                hero.setCurrentEnergyPoint(hero.getCurrentEnergyPoint() - 2);
                                Display.printInEachLine(hero.getName() + " has successfully attacked " + enemy.getName() + " with " + attackPower + " power");
                                Display.printInEachLine(enemy.getName() + "'s Current Health is: " + enemy.getCurrentHealth());
                                if (enemy.isDead()) {
                                    Display.printInEachLine(enemy.getName() + " has died");
                                    if (this.listOfEnemies.isEmpty()) {
                                        Display.printInEachLine("Victory! You’ve defeated all of your enemies");
                                        return;
                                    }
                                    this.listOfEnemies.remove(enemy);
                                    Enemy.mapOfEnemies.remove(enemy);
                                    break;
                                }
                                if (this.listOfEnemies.isEmpty()) {
                                    Display.printInEachLine("Victory! You’ve defeated all of your enemies");
                                    return;
                                }
                            }
                        }
                    }
                    if (command.contains(hero.getName() + " cast ")) {
                        for (Skill skill : hero.getSkills()) {
                            if (command.contains(hero.getName() + " cast " + skill.getName())) {
                                if (skill.isAcquire() == false) {
                                    Display.printInEachLine("This Skill isn't acquires!");
                                    break;
                                } else if (hero.getCurrentEnergyPoint() >= skill.getRequiredEnergyPoint() && hero.getCurrentMagic() < skill.getRequiredMagicPoint()) {
                                    Display.printInEachLine("You don’t have enough magic points");
                                    break;
                                } else if (hero.getCurrentEnergyPoint() < skill.getRequiredEnergyPoint() && hero.getCurrentMagic() >= skill.getRequiredMagicPoint()) {
                                    Display.printInEachLine("You don’t have enough energy points");
                                    break;
                                } else if (hero.getCurrentEnergyPoint() < skill.getRequiredEnergyPoint() && hero.getCurrentMagic() < skill.getRequiredMagicPoint()) {
                                    Display.printInEachLine("You don’t have enough energy points");
                                    Display.printInEachLine("You don’t have enough magic points");
                                    break;
                                } else if (skill.getRemainingCooldown() != 0) {
                                    Display.printInEachLine("Your desired ability is still in cooldown." + "remaining cooldown is " + skill.getRemainingCooldown());
                                    break;
                                } else if (hero.getCurrentEnergyPoint() >= skill.getRequiredEnergyPoint() && hero.getCurrentMagic() >= skill.getRequiredMagicPoint()) {
                                    hero.useSkill(skill.getName());
                                    Display.printInEachLine(hero.getName() + " casts Successfully " + skill.getName());
                                    this.updateAllSkills("NumberOfTurns");
                                    break;
                                }
                            }
                        }
                    }
                    if (command.contains(hero.getName() + " use ")) {
                        for (SkillItem skillItem: hero.getListOfSkillItems()) {
                            if (command.contains(hero.getName() + " use " + skillItem.getName() + " on ")) {
                                if (!hero.hasItem(skillItem)) {
                                    Display.printInEachLine("You don’t have this item");
                                    break;
                                }
//                                else if(itemProperties.getItem().getRemainingTime() > 0){
//                                    GUI.Display.printInEachLine("Your desired item is still in cooldown");
//                                    break;
//
                                else if (skillItem.getSkill().isAcquire() == false) {
                                    Display.printInEachLine("This Item isn't acquires!");
                                    break;
                                } else if (hero.getCurrentEnergyPoint() >= skillItem.getRequiredEnergyPoint() && hero.getCurrentMagic() < skillItem.getRequiredMagicPoint()) {
                                    Display.printInEachLine("You don’t have enough magic points");
                                    break;
                                } else if (hero.getCurrentEnergyPoint() < skillItem.getRequiredEnergyPoint() && hero.getCurrentMagic() >= skillItem.getRequiredMagicPoint()) {
                                    Display.printInEachLine("You don’t have enough energy points");
                                    break;
                                } else if (hero.getCurrentEnergyPoint() < skillItem.getRequiredEnergyPoint() && hero.getCurrentMagic() < skillItem.getRequiredMagicPoint()) {
                                    Display.printInEachLine("You don’t have enough energy points");
                                    Display.printInEachLine("You don’t have enough magic points");
                                    break;
                                } else if (skillItem.getRemainingCooldown() != 0) {
                                    Display.printInEachLine("Your desired Item is still in cooldown." + "remaining cooldown is " + skillItem.getRemainingCooldown());
                                    break;
                                } if (hero.getCurrentEnergyPoint() >= skillItem.getRequiredEnergyPoint() && hero.getCurrentMagic() >= skillItem.getRequiredMagicPoint()) {
                                    hero.useSkillItem(skillItem.getName());
                                    this.updateAllSkillItems("NumberOfTurns");
                                    Display.printInEachLine(hero.getName() + " used Successfully " + skillItem.getName());
                                    break;
                                }
                            }
                        }
                    }
                }
                for (Enemy enemy : this.listOfEnemies) {
                    if (command.equalsIgnoreCase(enemy.getName() + "?")) {
                        enemy.showDescription();
                        break;
                    }
                }
                for (ItemProperties<PerkItem> itemProperties : Shop.listOfPerkItems) {
                    if (command.equalsIgnoreCase(itemProperties.getItem().getName() + "?")) {
                        Display.printInEachLine(itemProperties.getItem().getDescription());
                        break;
                    }
                }

                for (ItemProperties<SkillItem> itemProperties : Shop.listOfSkillItems) {
                    if (command.equalsIgnoreCase(itemProperties.getItem().getName() + "?")) {
                        Display.printInEachLine(itemProperties.getItem().getDescription());
                        break;
                    }
                }

                for (Skill skill : this.listOfSkills) {
                    if (command.equalsIgnoreCase(skill.getName() + "?")) {
                        skill.showDescription();
                        break;
                    }
                }
                for (Perk perk : this.listOfPerks) {
                    if (command.equalsIgnoreCase(perk.getName() + "?")) {
                        perk.showDescription();
                        break;
                    }
                }
                ArrayList<Enemy> removedEnemies = new ArrayList<>();
                for (Enemy enemy: this.listOfEnemies) {
                    if (enemy.isDead()) {
                        removedEnemies.add(enemy);
                        Enemy.mapOfEnemies.remove(enemy);
                        Display.printInEachLine(enemy.getName() + " has died");
                    }
                }
                for (Enemy enemy: removedEnemies) {
                    this.listOfEnemies.remove(enemy);
                }
                if (this.listOfEnemies.isEmpty()) {
                    Display.printInEachLine("Victory! You’ve defeated all of your enemies");
                    return;
                }
            }
            else if(numberEntered == 4){
                for(Hero hero : this.listOfHeroes){
                    hero.setCurrentEnergyPoint(hero.getMaximumEnergyPoint());
                }
                for (Enemy enemy : GameEngine.listOfEnemies) {
                    enemy.doTurn();
                    for (Hero hero : GameEngine.listOfHeroes) {
                        if (hero.isDead()) {
                            if (Player.imortalityPotion > 0) {
                                Player.imortalityPotion--;
                                hero.setCurrentHealth(hero.getMaximumHealth());
                                Display.printInEachLine(hero.getName() + " is dying, immortality potion was used for reincarnation process, you now have " + Player.imortalityPotion + " immortality potions left");
                            } else {
                                this.listOfHeroes.remove(hero);
                                Display.printInEachLine(hero.getName() + " is dead and so is the spirit of this adventure, Game Over!");
                                System.exit(0);
                            }
                        }
                    }
                }
                if(this.listOfHeroes.isEmpty()){
                    Display.printInEachLine("You Defeat!");
                    System.exit(0);
                    return;
                }
                this.updateAllSkills("NumberOfCycles");
                for(Hero hero : this.listOfHeroes){
                    if((hero.getCurrentHealth() + hero.getMaximumHealth() * hero.getHealthRefillRate()) < hero.getMaximumHealth()) {
                        hero.setCurrentHealth(hero.getCurrentHealth() + hero.getMaximumHealth() * hero.getHealthRefillRate());
                    }
                    else{
                        hero.setCurrentHealth(hero.getMaximumHealth());
                    }
                    if ((hero.getCurrentMagic() + hero.getMaximumMagic() *  hero.getMagicRefillRate()) < hero.getMaximumMagic()) {
                        hero.setCurrentMagic(hero.getCurrentMagic() + (int) (hero.getMaximumMagic() * hero.getMagicRefillRate()));
                    }
                    else {
                        hero.setCurrentMagic(hero.getMaximumMagic());
                    }
                }
                continue;
            }
            else{
                Display.printInEachLine("Wrong Number! Try Again!");
                continue;
            }
        }
    }

    public void showHeroTeamDescription() {
        Display.printInEachLine("Hero Team:");
        for(int i = 0;i < this.listOfHeroes.size();i++){
            Display.printInEachLine(this.listOfHeroes.get(i).getName() + " (" + this.listOfHeroes.get(i).getClassName() + ")");
        }

    }

    public void showEnemyTeamDescription(){
        Display.printInEachLine("Enemy Team:");
        for(Enemy enemy: this.listOfEnemies){
            Display.printInEachLine(enemy.getName() + " , Health: " + enemy.getCurrentHealth() + " / " + enemy.getMaximumHealth());
        }
    }

    public void addNewHeroClass(HeroClass heroClass) {
        if(isCustomGame){
            //Creating A new Hero Class
            this.listOfHeroClasses.add(heroClass);
        }
        else{
            this.listOfHeroClasses.add(heroClass);
        }
    }

    public void addNewHero(Hero hero) {
        if(isCustomGame) {
            String name;
            Display.printf("Set Name For Your Hero:");
            name = Display.getString();
            //Choose A hero Class or make it!
            Display.printInEachLine("You Must Choose One OF HeroClasses For Your Own Hero!");
            Display.printInEachLine("0 - Make A New Hero Class!");
            for (int i = 0; i < GameEngine.listOfHeroClasses.size(); i++) {
                Display.printInEachLine((i + 1) + " - " + GameEngine.listOfHeroClasses.get(i).getClassName());
            }
            //Bug! (Commands) Soon Will Correct it!
            int choose = Display.getInteger();
            //if chooses one of hero classes
            if (choose > 0 && choose <= GameEngine.listOfHeroClasses.size()) {
                hero = new Hero(name, GameEngine.listOfHeroClasses.get(choose - 1));
            } else if (choose == 0) {
                this.addNewHeroClass(new HeroClass());
            }
            this.listOfHeroes.add(hero);
        }
        else{
            this.listOfHeroes.add(hero);
            Hero.mapOfHeroes.put(hero.getName(), hero);
        }
    }

    public void addNewPerk(Perk perk){
        if(isCustomGame){
            //...
            this.listOfPerks.add(perk);
        }
        else{
            this.listOfPerks.add(perk);
            Ability.listOfAbilities.put(perk.getName(), "perk");
            Perk.listOfPerks.put(perk.getName(), perk);
        }
    }

    public void addNewSkill(Skill skill) {
        if(isCustomGame){
            //...
            this.listOfSkills.add(skill);
        }
        else{
            this.listOfSkills.add(skill);
            Ability.listOfAbilities.put(skill.getName(), "skill");
            Skill.listOfSkills.put(skill.getName(), skill);
        }
    }

    public void addNewItem() {
        //TODO
    }

    public void addNewMap() {
        //TODO
    }

    public void reset() {
        //TODO
    }

    public void doBattle() {
        //TODO
    }

    public void play() {
        this.addDefaultAttributes();
        if(isCustomGame){
            this.doCustomGame();
        }
        else{
            for(int i = 0;i < 5;i++) {
                this.NumberOfBattle = i + 1;
                this.doCampaign(i);
                if (i != 4) {
                    player.setXp(player.getXp() + this.rewardedXP[this.getNumberOfBattle() - 1]);
                    player.setMoney(player.getMoney() + this.rewardedMoney[this.getNumberOfBattle() - 1]);
                }
                this.updateAllSkills("NumberOfClashes");
            }
        }
    }

    public void showBattleMessage(int numberOfBattle){
        switch(numberOfBattle){
            case 1:
                Display.printInEachLine("Welcome ,"+ this.player.getName());
                Display.printInEachLine("You’ve entered the castle, it takes a while for your eyes to get used to the darkness but the horrifying halo of your enemies is vaguely visible. Angel’s unsettling presence and the growling of thugs tell you that your first battle has BEGUN!");
                Display.printInEachLine("################################################################");
                Display.printInEachLine("Battle 1:");
                Display.printInEachLine("As you wander into the hall you realize the surrounding doors can lead your destiny to something far worse than you expected. You know what’s anticipating you behind the only open door but there’s no other choice.");
                break;
            case 2:
                Display.printInEachLine("Battle 2:");
                Display.printInEachLine("The door behind you is shut with a thunderous sound and you progress into the next hall holding the first key that you’ve found, hoping to seek the second one.");
                break;
            case 3:
                Display.printInEachLine("Battle 3:");
                Display.printInEachLine("Running with the second key in your hand, you unlock the door back to the first hall and use the first key to burst into your most terrifying nightmares.");
                break;
            case 4:
                Display.printInEachLine("Battle 4:");
                Display.printInEachLine("You feel hopeless and exhausted as you stalk to the final door. What’s behind that door makes your hearts pound and your spines shake with fear, but you came here to do one thing and backing down is not an option.");
                break;
            case 5:
                Display.printInEachLine("Battle 5:");
                Display.printInEachLine("The collector falls down on his knees, he’s strained and desperate but still tries to drag himself toward Epoch. He knows his era has come to an end. The ancient time machine calls you to end the disorder and bring unity under its glorious wings, now it’s your turn to be the MASTERS OF TIME!");
                break;
        }
    }

    public void chooseModeOfGame() {
        Display.printInEachLine("Choose Mode Of Game:");
        Display.printInEachLine("1 - Campaign");
        Display.printInEachLine("2 - Custom Game");
        int choose = Display.getInteger();
        while(true){
            if(choose == 1){
                this.setCustomGame(false);
                break;
            }
            else if(choose == 2){
                this.setCustomGame(true);
                break;
            }
            else{
                Display.printInEachLine("Wrong Number! Try Again!");
                choose = Display.getInteger();
            }
        }
    }

    public void clearScreen(){}
    //------------------------------------------ Getter && Setters

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public boolean isCustomGame() {
        return isCustomGame;
    }

    public void setCustomGame(boolean customGame) {
        isCustomGame = customGame;
    }

    public String getLevelOfGame() {
        return levelOfGame;
    }

    public void setLevelOfGame(String levelOfGame) {
        this.levelOfGame = levelOfGame;
    }

    public int getNumberOfBattle() {
        return NumberOfBattle;
    }

    public void setNumberOfBattle(int numberOfBattle) {
        NumberOfBattle = numberOfBattle;
    }



}
