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

    //------------------------------------------ Functions
    public void showHeroesTraits(){
        for(Hero hero : this.listOfHeroes){
            Display.printInEachLine(hero.getName());
            Display.printInEachLine("Health: " + hero.getCurrentHealth() + " / " + hero.getMaximumHealth());
            Display.printInEachLine("Magic: " + hero.getCurrentMagic() + " / " + hero.getMaximumMagic());
            Display.printInEachLine("Energy points: " + hero.getCurrentEnergyPoint());
            Display.printInEachLine("Attack power: " + hero.getAttackPower());
            for(Skill skill : hero.skills) {
                try {
                    Display.printInEachLine(skill.getDescription());
                    for (int i = 0; i < skill.getNumberOfGrades(); i++) {
                        try {
                            Display.printInEachLine("Update " + (i + 1) + ": " + skill.getUpgradeDescription()[i]);
                        } catch (Exception e) {
                            continue;
                        }
                    }
                } catch (Exception e) {
                    continue;
                }
            }
        }
    }
    public void shoppingCommands(){
        Shop.showItems();
        for(Hero hero : this.listOfHeroes){
            hero.showItems();
        }
        Display.printInEachLine("Your current wealth is:" + this.player.getMoney() + "dollars");
        Display.printf("Enter Your Command:(type 'Next' For Next Step!)");
        String command = Display.getString();
        for(ItemProperties itemProperties: Shop.listOfItems){
            if(command.equalsIgnoreCase(itemProperties.getItem().getName() + "?")){                 //(item name) + “?”
                itemProperties.getItem().getDescription();
                break;
            }
            if(command.contains("Buy " + itemProperties.getItem().getName() + " for ") || command.contains("Sell " + itemProperties.getItem().getName() + " of ")){
                for(Hero hero : this.listOfHeroes){
                    if(command.equals("Buy " + itemProperties.getItem().getName() + " for " + hero.getName())){ //“Buy “ + (item name) + “ for “ + (hero name)
                        if(hero.getInventorySize() < itemProperties.getItem().getSize()){
                            Display.printInEachLine(hero.getName() + "'s inventory is full");
                        }
                        else if(this.player.getMoney() >= itemProperties.getPrice() && hero.getInventorySize() >= itemProperties.getItem().getSize()){                                 //(item name) “ bought successfully, your current wealth is: ” + (current money)
                            this.player.setMoney(this.player.getMoney() - itemProperties.getPrice());
                            Display.printInEachLine(itemProperties.getItem().getName() + " bought successfully, your current wealth is: " + this.player.getMoney());
                        }
                        else if (this.player.getMoney() < itemProperties.getPrice() && hero.getInventorySize() >= itemProperties.getItem().getSize()){
                            Display.printInEachLine("You don’t have enough money");
                        }
                    }
                    else if(command.equals("Sell " + itemProperties.getItem().getName() + " of " + hero.getName())){//“Sell “ + (item name) + “ of” + (hero name)
                        if(hero.hasItem(itemProperties.getItem())) {
                            this.player.setMoney(this.player.getMoney() + (itemProperties.getPrice() / 2));
                            Display.printInEachLine(itemProperties.getItem().getName() + " successfully sold, your current wealth is: " + player.getMoney());//(item name) + “ successfully sold, your current wealth is: “ + (current money)
                        }
                        else{
                            Display.printInEachLine(hero.getName() + "hasn't this item");
                        }
                    }
                }
            }
        }

    }
    public void abilityCastCommands(){
        this.showHeroesTraits();
        while (true) {
            Display.printInEachLine("Any Commands :(Type 'Next' For skip)");
            String command = Display.getString();
            if (command.equalsIgnoreCase("Next")) {
                return;
            }
            if (command.contains("Acquire ")) {     //(ability name) (“acquired”/”upgraded”) + “ successfully, your current experience is: ” + (current xp)
                for (Perk perk : this.listOfPerks) {
                    if (command.contains("Acquire " + perk.getName() + " for ")) {
                        for (Hero hero : this.listOfHeroes) {
                            if (command.equalsIgnoreCase("Acquire " + perk.getName() + " for " + hero.getName())) {
                                if (hero.hasPerk(perk)) {
                                    //if getXP was less than perks
                                    if (perk.getCurrentGrade() == perk.getNumberOfGrades()) {
                                        Display.printInEachLine("This ability cannot be upgraded anymore");
                                        break;
                                    }
                                    if (perk.getCostOfUpgrade()[perk.getCurrentGrade()] > this.player.getXp()) {
                                        Display.printInEachLine("Your experience is insufficient");
                                        break;
                                    }
                                    hero.upgradeAbility(this.player, perk.getName());
                                    this.player.setXp(this.player.getXp() - perk.getCostOfUpgrade()[perk.getCurrentGrade()]);
                                    perk.setCurrentGrade(perk.getCurrentGrade() + 1);
                                    Display.printInEachLine(perk.getName() + " upgraded " + "successfully, your current experience is: " + player.getXp());
                                    break;
                                } else {
                                    hero.getPerks().add(perk);
                                    Display.printInEachLine(perk.getName() + " acquired " + "successfully, your current experience is: " + player.getXp());
                                    break;
                                }
                            }
                        }
                    }
                }
                for (Skill skill : this.listOfSkills) {
                    if (command.contains("Acquire " + skill.getName() + " for ")) {
                        for (Hero hero : this.listOfHeroes) {
                            if (command.equalsIgnoreCase("Acquire " + skill.getName() + " for " + hero.getName())) {
                                if (hero.hasSkill(skill)) {
                                    if (skill.getCurrentGrade() == skill.getNumberOfGrades()) {
                                        Display.printInEachLine("This ability cannot be upgraded anymore");
                                        break;
                                    }
                                    if (skill.getCostOfUpgrade()[skill.getCurrentGrade()] > this.player.getXp()) {
                                        Display.printInEachLine("Your experience is insufficient");
                                        break;
                                    }
                                    hero.upgradeAbility(this.player, skill.getName());
                                    this.player.setXp(this.player.getXp() - skill.getCostOfUpgrade()[skill.getCurrentGrade()]);
                                    skill.setCurrentGrade(skill.getCurrentGrade() + 1);
                                    Display.printInEachLine(skill.getName() + " upgraded " + "successfully, your current experience is: " + player.getXp());
                                    break;
                                } else {
                                    hero.getSkills().add(skill);
                                    Display.printInEachLine(skill.getName() + " acquired " + "successfully, your current experience is: " + player.getXp());
                                    break;
                                }
                            }
                        }
                    }
                }

            } else {
                Display.printInEachLine("Wrong Command!Try Again!");
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
        //Adding Abilities
        this.setNumberOfBattle(1);                                  //Number of battle

        this.creatingDefaultSkills();
        this.creatingDefaultPerks();

        //Adding Fighter Class
        HeroClassHandler fighterHandler = new HeroClassHandler("Fighter",200,120,120,6,2,0.1,0.05);
        fighterHandler.addPerk(Perk.listOfPerks.get("Fight training"));
        fighterHandler.addPerk(Perk.listOfPerks.get("Work out"));
        this.addNewHeroClass(new HeroClass(fighterHandler));
        //Adding Supporter Class
        HeroClassHandler supporterHandler = new HeroClassHandler("Supporter",220,80,200,5,3,0.05,0.1);
        supporterHandler.addPerk(Perk.listOfPerks.get("Quick as a bunny"));
        supporterHandler.addPerk(Perk.listOfPerks.get("Magic lessons"));
        this.addNewHeroClass(new HeroClass(supporterHandler));
        //Adding Eley
        Hero eley = new Hero("Eley",fighterHandler);
        eley.addSkill(Skill.listOfSkills.get("overPoweredAttack"));
        eley.addPerk(Perk.listOfPerks.get("Swirling attack"));
        this.addNewHero(eley);
        //Adding Chrome
        Hero chrome = new Hero("Chrome",fighterHandler);
        chrome.addSkill(Skill.listOfSkills.get("sacrifice"));
        chrome.addPerk(Perk.listOfPerks.get("Critical strike"));
        this.addNewHero(chrome);
        //Adding Meryl
        Hero meryl = new Hero("Meryl",supporterHandler);
        meryl.addSkill(Skill.listOfSkills.get("elixir"));
        meryl.addSkill(Skill.listOfSkills.get("careTaker"));
        this.addNewHero(meryl);
        //Adding Bolti
        Hero bolti = new Hero("Bolti",supporterHandler);
        bolti.addSkill(Skill.listOfSkills.get("boost"));
        bolti.addSkill(Skill.listOfSkills.get("manaBeam"));
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

        this.creatingDefaultItems(shop);
    }

    private void creatingDefaultItems(Shop shop) {
        {
            // toughen
            double[] tmp = {0,0,0};
            double[] arr = {20,0,0};
            PropertyHandler propertyHandler = new PropertyHandler("maximumHealth", 0, false, true, true, arr, tmp, tmp, tmp, tmp, tmp, tmp, tmp);
            Property<Hero> property = new Property<>(propertyHandler);
            ArrayList<Property<Hero>> properties = new ArrayList<>();
            properties.add(property);
            String description = "Items which alter a hero’s traits (don’t take up inventory’s space): +20 maximum health";
            Item item = new Item("thoughan", null, 0, true, 1, 0, 0, description, false, 1, false, properties, false, 0, false);
            ItemProperties itemProperties = new ItemProperties(item, 0, 2, 0, 4);
            shop.listOfItems.add(itemProperties);
        }
        {
            // Guide
            double[] tmp = {0,0,0};
            double[] arr = {20,0,0};
            PropertyHandler propertyHandler = new PropertyHandler("maximumMagic", 0, false, true, true, arr, tmp, tmp, tmp, tmp, tmp, tmp, tmp);
            Property<Hero> property = new Property<>(propertyHandler);
            ArrayList<Property<Hero>> properties = new ArrayList<>();
            properties.add(property);
            String description = "Items which alter a hero’s traits (don’t take up inventory’s space): +20 maximum magic";
            Item item = new Item("Guide", null, 0, true, 1, 0, 0, description, false, 1, false, properties, false, 0, false);
            ItemProperties itemProperties = new ItemProperties(item, 0, 2, 0, 4);
            shop.listOfItems.add(itemProperties);
        }
        {
            // Guide
            double[] tmp = {0,0,0};
            double[] arr = {8,0,0};
            PropertyHandler propertyHandler = new PropertyHandler("attackPower", 0, false, true, true, arr, tmp, tmp, tmp, tmp, tmp, tmp, tmp);
            Property<Hero> property = new Property<>(propertyHandler);
            ArrayList<Property<Hero>> properties = new ArrayList<>();
            properties.add(property);
            String description = "Items which alter a hero’s traits (don’t take up inventory’s space): +8 attack power";
            Item item = new Item("Defy", null, 0, true, 1, 0, 0, description, false, 1, false, properties, false, 0, false);
            ItemProperties itemProperties = new ItemProperties(item, 0, 2, 0, 4);
            shop.listOfItems.add(itemProperties);
        }
        {
            // Sword
            double[] tmp = {0,0,0};
            double[] arr = {80,0,0};
            PropertyHandler propertyHandler = new PropertyHandler("attackPower", 0, false, true, true, arr, tmp, tmp, tmp, tmp, tmp, tmp, tmp);
            Property<Hero> property = new Property<>(propertyHandler);
            ArrayList<Property<Hero>> properties = new ArrayList<>();
            properties.add(property);
            String description = "Items which have a permanent effect on a hero: +80 attack power, costs 25 dollars";
            Item item = new Item("Sword", null, 0, true, 1, 0, 0, description, false, 1, false, properties, true, 0, false);
            ItemProperties itemProperties = new ItemProperties(item, 0, 0, 0, 25);
            shop.listOfItems.add(itemProperties);
        }
        {
            // Energy Boots
            double[] tmp = {0,0,0};
            double[] arr = {1,0,0};
            PropertyHandler propertyHandler = new PropertyHandler("currentEnergyPoint", 0, false, true, true, arr, tmp, tmp, tmp, tmp, tmp, tmp, tmp);
            Property<Hero> property = new Property<>(propertyHandler);
            ArrayList<Property<Hero>> properties = new ArrayList<>();
            properties.add(property);
            String description = "Items which have a permanent effect on a hero: +1 energy point, costs 20 dollars";
            Item item = new Item("Energy Boots", null, 0, true, 1, 0, 0, description, false, 1, false, properties, true, 0, false);
            ItemProperties itemProperties = new ItemProperties(item, 0, 0, 0, 20);
            shop.listOfItems.add(itemProperties);
        }
        {
            // Armor Boots
            double[] tmp = {0,0,0};
            double[] arr = {200,0,0};
            PropertyHandler propertyHandler = new PropertyHandler("maximumHealth", 0, false, true, true, arr, tmp, tmp, tmp, tmp, tmp, tmp, tmp);
            Property<Hero> property = new Property<>(propertyHandler);
            ArrayList<Property<Hero>> properties = new ArrayList<>();
            properties.add(property);
            String description = "Items which have a permanent effect on a hero: +200 maximum health, costs 25 dollars";
            Item item = new Item("Armor", null, 0, true, 1, 0, 0, description, false, 1, false, properties, true, 0, false);
            ItemProperties itemProperties = new ItemProperties(item, 0, 0, 0, 25);
            shop.listOfItems.add(itemProperties);
        }
        {
            // Magic stick
            double[] tmp = {0,0,0};
            double[] arr = {150,0,0};
            PropertyHandler propertyHandler = new PropertyHandler("maximumMagic", 0, false, true, true, arr, tmp, tmp, tmp, tmp, tmp, tmp, tmp);
            Property<Hero> property = new Property<>(propertyHandler);
            ArrayList<Property<Hero>> properties = new ArrayList<>();
            properties.add(property);
            String description = "Items which have a permanent effect on a hero: +150 maximum magic, costs 28 dollars";
            Item item = new Item("Magic stick", null, 0, true, 1, 0, 0, description, false, 1, false, properties, true, 0, false);
            ItemProperties itemProperties = new ItemProperties(item, 0, 0, 0, 28);
            shop.listOfItems.add(itemProperties);
        }
        {
            // Health potion
            double[] tmp = {0,0,0};
            double[] arr = {100,0,0};
            PropertyHandler propertyHandler = new PropertyHandler("currentHealth", 0, false, true, true, arr, tmp, tmp, tmp, tmp, tmp, tmp, tmp);
            Property<Hero> property = new Property<>(propertyHandler);
            ArrayList<Property<Hero>> properties = new ArrayList<>();
            properties.add(property);
            String description = "Items which you can use during the battle up to 3 times (they free the inventory after 3 uses): +100 health points for the user or one of his/her allies, costs 15 dollars";
            Item item = new Item("Health potion", null, 1, false, 3, 0, 0, description, true, 1, false, properties, false, 0, true);
            ItemProperties itemProperties = new ItemProperties(item, 0, 0, 0, 15);
            shop.listOfItems.add(itemProperties);
        }
        {
            // Magic potion
            double[] tmp = {0,0,0};
            double[] arr = {50,0,0};
            PropertyHandler propertyHandler = new PropertyHandler("currentMagic", 0, false, true, true, arr, tmp, tmp, tmp, tmp, tmp, tmp, tmp);
            Property<Hero> property = new Property<>(propertyHandler);
            ArrayList<Property<Hero>> properties = new ArrayList<>();
            properties.add(property);
            String description = "Items which you can use during the battle up to 3 times (they free the inventory after 3 uses): +50 magic points for the user or one of his/her allies, costs 15 dollars";
            Item item = new Item("Magic potion", null, 1, false, 3, 0, 0, description, true, 1, false, properties, false, 0, true);
            ItemProperties itemProperties = new ItemProperties(item, 0, 0, 0, 15);
            shop.listOfItems.add(itemProperties);
        }
    }

    private void creatingDefaultPerks() {
        {
            //Fighter Class's Perk
            double[] tmp = {0,0,0};
            double[] arr = {30,30,30};
            PropertyHandler propertyHandler = new PropertyHandler("attackPower",3, false, true, true, arr, tmp, tmp, tmp, tmp, tmp, tmp, tmp);
            Property<Hero> property = new Property<>(propertyHandler);
            ArrayList<Property<Hero>> properties = new ArrayList<>();
            properties.add(property);
            PerkMode<Hero> perkMode = new PerkMode<>(properties, 0);
            ArrayList<PerkMode<Hero>> listOfModes = new ArrayList<>();
            listOfModes.add(perkMode);
            Condition condition = new Condition();
            ArrayList<Condition> listOfConditions = new ArrayList<>();
            listOfConditions.add(condition);
            Map<Condition, PerkMode<Hero>> mapOfCondition = new HashMap<>();
            mapOfCondition.put(condition, perkMode);
            int[] costOfUpgrade = {2,3,4};
//            Map<Integer, ArrayList<String>> nameOfNecessaryAbilities = new HashMap<>();
//            ArrayList<String> tmpArr = new ArrayList<>();
//            tmpArr.add("Fight Training");
//            nameOfNecessaryAbilities.put(1, tmpArr); nameOfNecessaryAbilities.put(2, tmpArr); nameOfNecessaryAbilities.put(3, tmpArr);
//            Map<Integer, Map<String, Integer>> gradeOfNecessaryAbilities = new HashMap<>();
//            Map<String, Integer> tmpMap = new HashMap<>();
//            tmpMap.put("Fight Training", 1);
//            gradeOfNecessaryAbilities.put(1, tmpMap);
//            tmpMap.put("Fight Training", 2);
//            gradeOfNecessaryAbilities.put(2, tmpMap);
//            tmpMap.put("Fight Training", 3);
//            gradeOfNecessaryAbilities.put(3, tmpMap);
            String[] upgradeDescription = new String[3];
            upgradeDescription[0] = "Upgrade1: +30 attack power for 2 xp points";
            upgradeDescription[1] = "Upgrade2: +30 attack power for 3 xp points";
            upgradeDescription[2] = "Upgrade3: +30 attack power for 4 xp points";
            String description = "Permanently increases attack power";
            AbilityHandler<Hero> abilityHandler = new AbilityHandler<>("Fight training", null, false, true, false, 1, 3, null, false, false, costOfUpgrade, null, null, upgradeDescription, description);
            Perk<Hero> FightTraining = new Perk<>(abilityHandler, listOfConditions, listOfModes, mapOfCondition, false, false, "JustForFirstTime");
            Perk.listOfPerks.put("FightTraining", FightTraining);
        }
        {
            //Fighter Class's Perk
            double[] tmp = {0,0,0};
            double[] arr = {50,50,50};
            PropertyHandler propertyHandler = new PropertyHandler("maximumHealth",3, false, true, true, arr, tmp, tmp, tmp, tmp, tmp, tmp, tmp);
            Property<Hero> property = new Property<>(propertyHandler);
            ArrayList<Property<Hero>> properties = new ArrayList<>();
            properties.add(property);
            PerkMode<Hero> perkMode = new PerkMode<>(properties, 0);
            ArrayList<PerkMode<Hero>> listOfModes = new ArrayList<>();
            listOfModes.add(perkMode);
            Condition condition = new Condition();
            ArrayList<Condition> listOfConditions = new ArrayList<>();
            listOfConditions.add(condition);
            Map<Condition, PerkMode<Hero>> mapOfCondition = new HashMap<>();
            mapOfCondition.put(condition, perkMode);
            int[] costOfUpgrade = {2,3,4};
//            Map<Integer, ArrayList<String>> nameOfNecessaryAbilities = new HashMap<>();
//            ArrayList<String> tmpArr = new ArrayList<>();
//            tmpArr.add("Fight Training");
//            nameOfNecessaryAbilities.put(1, tmpArr); nameOfNecessaryAbilities.put(2, tmpArr); nameOfNecessaryAbilities.put(3, tmpArr);
//            Map<Integer, Map<String, Integer>> gradeOfNecessaryAbilities = new HashMap<>();
//            Map<String, Integer> tmpMap = new HashMap<>();
//            tmpMap.put("Fight Training", 1);
//            gradeOfNecessaryAbilities.put(1, tmpMap);
//            tmpMap.put("Fight Training", 2);
//            gradeOfNecessaryAbilities.put(2, tmpMap);
//            tmpMap.put("Fight Training", 3);
//            gradeOfNecessaryAbilities.put(3, tmpMap);
            String[] upgradeDescription = new String[3];
            upgradeDescription[0] = "Upgrade 1: +50 maximum health for 2 xp points";
            upgradeDescription[1] = "Upgrade 2: +50 maximum health for 3 xp points";
            upgradeDescription[2] = "Upgrade 3: +50 maximum health for 4 xp points";
            String description = "Permanently increases maximum health";
            AbilityHandler<Hero> abilityHandler = new AbilityHandler<>("Work out", null, false, true, false, 1, 3, null, false, false, costOfUpgrade, null, null, upgradeDescription, description);
            Perk<Hero> WorkOut = new Perk<>(abilityHandler, listOfConditions, listOfModes, mapOfCondition, false, false, "JustForFirstTime");
            Perk.listOfPerks.put("Work out", WorkOut);
        }
        {
            //Supporter Class's Perk
            double[] tmp = {0,0,0};
            double[] arr = {1,1,1};
            PropertyHandler propertyHandler = new PropertyHandler("maximumEnergyPoint",3, false, true, true, arr, tmp, tmp, tmp, tmp, tmp, tmp, tmp);
            Property<Hero> property = new Property<>(propertyHandler);
            ArrayList<Property<Hero>> properties = new ArrayList<>();
            properties.add(property);
            PerkMode<Hero> perkMode = new PerkMode<>(properties, 0);
            ArrayList<PerkMode<Hero>> listOfModes = new ArrayList<>();
            listOfModes.add(perkMode);
            Condition condition = new Condition();
            ArrayList<Condition> listOfConditions = new ArrayList<>();
            listOfConditions.add(condition);
            Map<Condition, PerkMode<Hero>> mapOfCondition = new HashMap<>();
            mapOfCondition.put(condition, perkMode);
            int[] costOfUpgrade = {2,3,4};
//            Map<Integer, ArrayList<String>> nameOfNecessaryAbilities = new HashMap<>();
//            ArrayList<String> tmpArr = new ArrayList<>();
//            tmpArr.add("Fight Training");
//            nameOfNecessaryAbilities.put(1, tmpArr); nameOfNecessaryAbilities.put(2, tmpArr); nameOfNecessaryAbilities.put(3, tmpArr);
//            Map<Integer, Map<String, Integer>> gradeOfNecessaryAbilities = new HashMap<>();
//            Map<String, Integer> tmpMap = new HashMap<>();
//            tmpMap.put("Fight Training", 1);
//            gradeOfNecessaryAbilities.put(1, tmpMap);
//            tmpMap.put("Fight Training", 2);
//            gradeOfNecessaryAbilities.put(2, tmpMap);
//            tmpMap.put("Fight Training", 3);
//            gradeOfNecessaryAbilities.put(3, tmpMap);
            String[] upgradeDescription = new String[3];
            upgradeDescription[0] = "Upgrade1: +1 energy point for 2 xp points";
            upgradeDescription[1] = "Upgrade2: +1 energy point for 3 xp points";
            upgradeDescription[2] = "Upgrade3: +1 energy point for 4 xp points";
            String description = "Permanently increases energy points";
            AbilityHandler<Hero> abilityHandler = new AbilityHandler<>("Quick as a bunny", null, false, true, false, 1, 3, null, false, false, costOfUpgrade, null, null, upgradeDescription, description);
            Perk<Hero> Quickasabunny = new Perk<>(abilityHandler, listOfConditions, listOfModes, mapOfCondition, false, false, "JustForFirstTime");
            Perk.listOfPerks.put("Quick as a bunny", Quickasabunny);
        }
        {
            //Supporter Class's Perk
            double[] tmp = {0,0,0};
            double[] arr = {50,50,50};
            PropertyHandler propertyHandler = new PropertyHandler("maximumMagic",3, false, true, true, arr, tmp, tmp, tmp, tmp, tmp, tmp, tmp);
            Property<Hero> property = new Property<>(propertyHandler);
            ArrayList<Property<Hero>> properties = new ArrayList<>();
            properties.add(property);
            PerkMode<Hero> perkMode = new PerkMode<>(properties, 0);
            ArrayList<PerkMode<Hero>> listOfModes = new ArrayList<>();
            listOfModes.add(perkMode);
            Condition condition = new Condition();
            ArrayList<Condition> listOfConditions = new ArrayList<>();
            listOfConditions.add(condition);
            Map<Condition, PerkMode<Hero>> mapOfCondition = new HashMap<>();
            mapOfCondition.put(condition, perkMode);
            int[] costOfUpgrade = {2,3,4};
//            Map<Integer, ArrayList<String>> nameOfNecessaryAbilities = new HashMap<>();
//            ArrayList<String> tmpArr = new ArrayList<>();
//            tmpArr.add("Fight Training");
//            nameOfNecessaryAbilities.put(1, tmpArr); nameOfNecessaryAbilities.put(2, tmpArr); nameOfNecessaryAbilities.put(3, tmpArr);
//            Map<Integer, Map<String, Integer>> gradeOfNecessaryAbilities = new HashMap<>();
//            Map<String, Integer> tmpMap = new HashMap<>();
//            tmpMap.put("Fight Training", 1);
//            gradeOfNecessaryAbilities.put(1, tmpMap);
//            tmpMap.put("Fight Training", 2);
//            gradeOfNecessaryAbilities.put(2, tmpMap);
//            tmpMap.put("Fight Training", 3);
//            gradeOfNecessaryAbilities.put(3, tmpMap);
            String[] upgradeDescription = new String[3];
            upgradeDescription[0] = "Upgrade 1: +50 maximum magic for 2 xp points";
            upgradeDescription[1] = "Upgrade 2: +50 maximum magic for 3 xp points";
            upgradeDescription[2] = "Upgrade 3: +50 maximum magic for 4 xp points";
            String description = "Permanently increases maximum magic";
            AbilityHandler<Hero> abilityHandler = new AbilityHandler<>("Magic lessons", null, false, true, false, 1, 3, null, false, false, costOfUpgrade, null, null, upgradeDescription, description);
            Perk<Hero> Magiclessons = new Perk<>(abilityHandler, listOfConditions, listOfModes, mapOfCondition, false, false, "JustWhenUpgraded");
            Perk.listOfPerks.put("Magic lessons", Magiclessons);
        }
        {
            //Eley's Perk
            double[] tmp = {0,0,0};
            double[] arr = {10,20,30};
            PropertyHandler propertyHandler = new PropertyHandler("attackPowerRatioOnNonTargetedEnemy",3, false, true, true, arr, tmp, tmp, tmp, tmp, tmp, tmp, tmp);
            Property<Hero> property = new Property<>(propertyHandler);
            ArrayList<Property<Hero>> properties = new ArrayList<>();
            properties.add(property);
            PerkMode<Hero> perkMode = new PerkMode<>(properties, 0);
            ArrayList<PerkMode<Hero>> listOfModes = new ArrayList<>();
            listOfModes.add(perkMode);
            Condition condition = new Condition();
            ArrayList<Condition> listOfConditions = new ArrayList<>();
            listOfConditions.add(condition);
            Map<Condition, PerkMode<Hero>> mapOfCondition = new HashMap<>();
            mapOfCondition.put(condition, perkMode);
            int[] costOfUpgrade = {2,3,4};
            Map<Integer, ArrayList<String>> nameOfNecessaryAbilities = new HashMap<>();
            ArrayList<String> tmpArr = new ArrayList<>();
            tmpArr.add("Fight training");
            nameOfNecessaryAbilities.put(1, tmpArr); nameOfNecessaryAbilities.put(2, null); nameOfNecessaryAbilities.put(3, null);
            Map<Integer, Map<String, Integer>> gradeOfNecessaryAbilities = new HashMap<>();
            Map<String, Integer> tmpMap = new HashMap<>();
            tmpMap.put("Fight training", 1);
            gradeOfNecessaryAbilities.put(1, tmpMap);
            gradeOfNecessaryAbilities.put(2, null);
            gradeOfNecessaryAbilities.put(3, null);
            String[] upgradeDescription = new String[3];
            upgradeDescription[0] = "Upgrade 1: P=20 for 2 xp points, needs Fight training upgrade 1";
            upgradeDescription[1] = "Upgrade 2: P=30 for 3 xp points";
            upgradeDescription[2] = "Upgrade 3: P=40 for 4 xp points";
            String description = "Has a permanent P percent chance of doing an attack with double power (does not affect other abilities)";
            AbilityHandler<Hero> abilityHandler = new AbilityHandler<>("Critical strike", "Chrome", false, true, false, 1, 3, null, false, false, costOfUpgrade, nameOfNecessaryAbilities, gradeOfNecessaryAbilities, upgradeDescription, description);
            Perk<Hero> Criticalstrike = new Perk<>(abilityHandler, listOfConditions, listOfModes, mapOfCondition, false, false, "JustWhenUpgraded");
            Perk.listOfPerks.put("Critical strike", Criticalstrike);
        }
        {
            //Eley's Perk
            double[] tmp = {0,0,0};
            double[] arr = {20,30,40};
            PropertyHandler propertyHandler = new PropertyHandler("criticalHitChance",3, false, true, true, arr, tmp, tmp, tmp, tmp, tmp, tmp, tmp);
            Property<Hero> property = new Property<>(propertyHandler);
            double[] arr2 = {2,0,0};
            PropertyHandler propertyHandler2 = new PropertyHandler("criticalHitDamage",3, false, true, true, arr, tmp, tmp, tmp, tmp, tmp, tmp, tmp);
            Property<Hero> property2 = new Property<>(propertyHandler);
            ArrayList<Property<Hero>> properties = new ArrayList<>();
            properties.add(property);
            properties.add(property2);
            PerkMode<Hero> perkMode = new PerkMode<>(properties, 0);
            ArrayList<PerkMode<Hero>> listOfModes = new ArrayList<>();
            listOfModes.add(perkMode);
            Condition condition = new Condition();
            ArrayList<Condition> listOfConditions = new ArrayList<>();
            listOfConditions.add(condition);
            Map<Condition, PerkMode<Hero>> mapOfCondition = new HashMap<>();
            mapOfCondition.put(condition, perkMode);
            int[] costOfUpgrade = {2,3,4};
            Map<Integer, ArrayList<String>> nameOfNecessaryAbilities = new HashMap<>();
            ArrayList<String> tmpArr = new ArrayList<>();
            tmpArr.add("Work out");
            nameOfNecessaryAbilities.put(1, tmpArr); nameOfNecessaryAbilities.put(2, null); nameOfNecessaryAbilities.put(3, null);
            Map<Integer, Map<String, Integer>> gradeOfNecessaryAbilities = new HashMap<>();
            Map<String, Integer> tmpMap = new HashMap<>();
            tmpMap.put("Work out", 1);
            gradeOfNecessaryAbilities.put(1, tmpMap);
            gradeOfNecessaryAbilities.put(2, null);
            gradeOfNecessaryAbilities.put(3, null);
            String[] upgradeDescription = new String[3];
            upgradeDescription[0] = "Upgrade 1: P=10 for 2 xp points, needs Work out upgrade 1";
            upgradeDescription[1] = "Upgrade 2: P=20 for 3 xp points";
            upgradeDescription[2] = "Upgrade 3: P=30 for 4 xp points";
            String description = "While attacking, non-targeted enemies also take P percent of its damage";
            AbilityHandler<Hero> abilityHandler = new AbilityHandler<>("Swirling attack", null, false, true, false, 1, 3, null, false, false, costOfUpgrade, nameOfNecessaryAbilities, gradeOfNecessaryAbilities, upgradeDescription, description);
            Perk<Hero> Swirlingattack = new Perk<>(abilityHandler, listOfConditions, listOfModes, mapOfCondition, false, false, "JustWhenUpgraded");
            Perk.listOfPerks.put("Swirling attack", Swirlingattack);
        }
    }

    private void creatingDefaultSkills() {
        {
            //Eley's Skill
            double[] tmp = {0,0,0};
            double[] arr = {1.2,1.2,1.2};
            PropertyHandler propertyHandler = new PropertyHandler("currentHealth",3, false, true, true, tmp, arr, tmp, tmp, tmp, tmp, tmp, tmp);
            Property<Enemy> property = new Property<>(propertyHandler);
            ArrayList<Property<Enemy>> properties = new ArrayList<>();
            properties.add(property);
            int[] cooldown = {0,0,0}; int[] requiredEnergyPoint = {2,2,2}; int[] requiredMagicPoint = {50,50,50};
            SkillHandler<Enemy> skillHandler = new SkillHandler<Enemy>(properties, null, 0, false, new Time(), null, cooldown, true, false, requiredEnergyPoint, requiredEnergyPoint);
            int[] costOfUpgrade = {2,4,6};
            Map<Integer, ArrayList<String>> nameOfNecessaryAbilities = new HashMap<>();
            ArrayList<String> tmpArr = new ArrayList<>();
            tmpArr.add("Fight Training");
            nameOfNecessaryAbilities.put(1, tmpArr); nameOfNecessaryAbilities.put(2, tmpArr); nameOfNecessaryAbilities.put(3, tmpArr);
            Map<Integer, Map<String, Integer>> gradeOfNecessaryAbilities = new HashMap<>();
            Map<String, Integer> tmpMap = new HashMap<>();
            tmpMap.put("Fight Training", 1);
            gradeOfNecessaryAbilities.put(1, tmpMap);
            tmpMap.put("Fight Training", 2);
            gradeOfNecessaryAbilities.put(2, tmpMap);
            tmpMap.put("Fight Training", 3);
            gradeOfNecessaryAbilities.put(3, tmpMap);
            String[] upgradeDescription = new String[3];
            upgradeDescription[0] = "Upgrade 1: N=1.2 for 2 xp points, needs Fight training upgrade 1";
            upgradeDescription[1] = "Upgrade 2: N=1.4 for 4 xp points, needs Fight training upgrade 2";
            upgradeDescription[2] = "Upgrade 3: N=1.6 for 6 xp points, needs Fight training upgrade 3";
            String description = "Attacks an enemy with N times power for 2 energy points and 50 magic points";
            AbilityHandler<Enemy> abilityHandler = new AbilityHandler<Enemy>("overPoweredAttack", "Eley", false, true, false, 1, 3, null, false, false, costOfUpgrade, nameOfNecessaryAbilities, gradeOfNecessaryAbilities, upgradeDescription, description);
            Skill overPoweredAttack = new Skill(skillHandler, abilityHandler);
            Skill.listOfSkills.put("Fight Training", overPoweredAttack);
        }
        {
            //Chrome's Skill
            double[] tmp = {0,0,0};
            double[] arr1 = {40,50,60};
            double[] arr2 = {120,150,180};
            PropertyHandler propertyHandler1 = new PropertyHandler("currentHealth",3, false, true, true, arr2, tmp, tmp, tmp, tmp, tmp, tmp, tmp);
            Property<Enemy> property1 = new Property<>(propertyHandler1);
            PropertyHandler propertyHandler2 = new PropertyHandler("currentHealth",3, false, true, true, arr1, tmp, tmp, tmp, tmp, tmp, tmp, tmp);
            Property<Hero> property2 = new Property<>(propertyHandler2);
            ArrayList<Property<Enemy>> properties = new ArrayList<>();
            properties.add(property1);
            int[] cooldown = {1,1,1}; int[] requiredEnergyPoint = {3,3,3}; int[] requiredMagicPoint = {60,60,60};
            SkillHandler<Enemy> skillHandler = new SkillHandler<Enemy>(properties, property2, 0, false, new Time(), null, cooldown, false, false, requiredEnergyPoint, requiredEnergyPoint);
            int[] costOfUpgrade = {2,3,4};
            Map<Integer, ArrayList<String>> nameOfNecessaryAbilities = new HashMap<>();
            ArrayList<String> tmpArr = new ArrayList<>();
            tmpArr.add("Work out");
            nameOfNecessaryAbilities.put(1, tmpArr); nameOfNecessaryAbilities.put(2, tmpArr); nameOfNecessaryAbilities.put(3, tmpArr);
            Map<Integer, Map<String, Integer>> gradeOfNecessaryAbilities = new HashMap<>();
            Map<String, Integer> tmpMap = new HashMap<>();
            tmpMap.put("Work out", 1);
            gradeOfNecessaryAbilities.put(1, tmpMap);
            tmpMap.put("Work out", 2);
            gradeOfNecessaryAbilities.put(2, tmpMap);
            tmpMap.put("Work out", 3);
            gradeOfNecessaryAbilities.put(3, tmpMap);
            String[] upgradeDescription = new String[3];
            upgradeDescription[0] = "Upgrade 1: H=40 for 2 xp points, needs Work out upgrade 1";
            upgradeDescription[1] = "Upgrade 2: H=50 for 3 xp points, needs Work out upgrade 2";
            upgradeDescription[2] = "Upgrade 3: H=60 for 4 xp points, needs Work out upgrade 3";
            String description = "Damages all the enemies with 3H power at the cost of H of his own health, needs 3 energy points, 60 magic points and has a 1 turn cooldown";
            AbilityHandler<Enemy> abilityHandler = new AbilityHandler<Enemy>("Sacrifice", "Chrome", false, true, false, -5, 3, null, false, false, costOfUpgrade, nameOfNecessaryAbilities, gradeOfNecessaryAbilities, upgradeDescription, description);
            Skill WorkOut = new Skill(skillHandler, abilityHandler);
            Skill.listOfSkills.put("Work out", WorkOut);
        }
        {
            //Meryl's Skill
            double[] tmp = {0,0,0};
            double[] arr = {100,150,150};
            PropertyHandler propertyHandler = new PropertyHandler("currentHealth",3, false, true, true, arr, tmp, tmp, tmp, tmp, tmp, tmp, tmp);
            Property<Hero> property = new Property<>(propertyHandler);
            ArrayList<Property<Hero>> properties = new ArrayList<>();
            properties.add(property);
            int[] cooldown = {1,1,0}; int[] requiredEnergyPoint = {2,2,2}; int[] requiredMagicPoint = {60,60,60};
            SkillHandler<Hero> skillHandler = new SkillHandler<Hero>(properties, null, 0, false, new Time(), null, cooldown, true, false, requiredEnergyPoint, requiredEnergyPoint);
            int[] costOfUpgrade = {2,3,5};
            Map<Integer, ArrayList<String>> nameOfNecessaryAbilities = new HashMap<>();
            ArrayList<String> tmpArr = new ArrayList<>();
            tmpArr.add("Magic lessons");
            nameOfNecessaryAbilities.put(1, null); nameOfNecessaryAbilities.put(2, tmpArr); nameOfNecessaryAbilities.put(3, tmpArr);
            Map<Integer, Map<String, Integer>> gradeOfNecessaryAbilities = new HashMap<>();
            Map<String, Integer> tmpMap = new HashMap<>();
            gradeOfNecessaryAbilities.put(1, null);
            tmpMap.put("Magic lessons", 1);
            gradeOfNecessaryAbilities.put(2, tmpMap);
            tmpMap.put("Magic lessons", 2);
            gradeOfNecessaryAbilities.put(3, tmpMap);
            String[] upgradeDescription = new String[3];
            upgradeDescription[0] = "Upgrade 1: H=100 for 2 xp points and takes 1 turn to cool down";
            upgradeDescription[1] = "Upgrade 2: H=150 for 3 xp points, takes 1 turn to cool down and needs Magic lessons upgrade 1";
            upgradeDescription[2] = "Upgrade 3: H=150 for 5 xp points, cools down instantly and needs Magic lessons upgrade 2";
            String description = "Refills H points of her own health or an ally’s, for 2 energy points and 60 magic points";
            AbilityHandler<Enemy> abilityHandler = new AbilityHandler<Enemy>("Elixir", "Meryl", false, false, false, 1, 3, null, false, false, costOfUpgrade, nameOfNecessaryAbilities, gradeOfNecessaryAbilities, upgradeDescription, description);
            Skill MagicLessons = new Skill(skillHandler, abilityHandler);
            Skill.listOfSkills.put("MagicLessons", MagicLessons);
        }
        {
            //Meryl's Skill
            double[] tmp = {0,0,0};
            double[] arr = {1,1,1};
            PropertyHandler propertyHandler = new PropertyHandler("currentEnergyPoint",3, false, true, true, arr, tmp, tmp, tmp, tmp, tmp, tmp, tmp);
            Property<Hero> property = new Property<>(propertyHandler);
            ArrayList<Property<Hero>> properties = new ArrayList<>();
            properties.add(property);
            int[] cooldown = {1,0,0}; int[] requiredEnergyPoint = {2,2,1}; int[] requiredMagicPoint = {30,30,30};
            ArrayList<String> blackList = new ArrayList<>();
            blackList.add("Meryl");
            SkillHandler<Hero> skillHandler = new SkillHandler<Hero>(properties, null, 0, false, new Time(0, 1, 0), blackList, cooldown, true, false, requiredEnergyPoint, requiredEnergyPoint);
            int[] costOfUpgrade = {2,3,5};
            Map<Integer, ArrayList<String>> nameOfNecessaryAbilities = new HashMap<>();
            ArrayList<String> tmpArr = new ArrayList<>();
            tmpArr.add("Quick as a bunny");
            nameOfNecessaryAbilities.put(1, tmpArr); nameOfNecessaryAbilities.put(2, tmpArr); nameOfNecessaryAbilities.put(3, tmpArr);
            Map<Integer, Map<String, Integer>> gradeOfNecessaryAbilities = new HashMap<>();
            Map<String, Integer> tmpMap = new HashMap<>();
            tmpMap.put("Quick as a bunny", 1);
            gradeOfNecessaryAbilities.put(1, tmpMap);
            tmpMap.put("Quick as a bunny", 2);
            gradeOfNecessaryAbilities.put(2, tmpMap);
            tmpMap.put("Quick as a bunny", 3);
            gradeOfNecessaryAbilities.put(3, tmpMap);
            String[] upgradeDescription = new String[3];
            upgradeDescription[0] = "Upgrade 1: takes 2 energy points and has a 1 turn cooldown for 2 xp points, needs Quick as a bunny upgrade 1";
            upgradeDescription[1] = "Upgrade 2: takes 2 energy points and cools down instantly for 3 xp points, needs Quick as a bunny upgrade 2";
            upgradeDescription[2] = "Upgrade 3 takes 1 energy point and cools down instantly for 5 xp points, needs Quick as a bunny upgrade 3";
            String description = "Gives 1 energy point to an ally for 30 magic points (this ep does not last until the end of the battle and is only usable during the current turn)";
            AbilityHandler<Enemy> abilityHandler = new AbilityHandler<Enemy>("Caretaker", "Meryl", false, false, false, 1, 3, null, false, false, costOfUpgrade, nameOfNecessaryAbilities, gradeOfNecessaryAbilities, upgradeDescription, description);
            Skill Caretaker = new Skill(skillHandler, abilityHandler);
            Skill.listOfSkills.put("Caretaker", Caretaker);
        }
        {
            //Bolti's Skill
            double[] tmp = {0,0,0};
            double[] arr = {1.2,1.3,1.3};
            PropertyHandler propertyHandler = new PropertyHandler("attackPowerRatioDuringAttack",3, false, true, false, arr, tmp, tmp, tmp, tmp, tmp, tmp, tmp);
            Property<Hero> property = new Property<>(propertyHandler);
            ArrayList<Property<Hero>> properties = new ArrayList<>();
            properties.add(property);
            int[] cooldown = {1,1,0}; int[] requiredEnergyPoint = {2,2,2}; int[] requiredMagicPoint = {50,50,50};
            SkillHandler<Hero> skillHandler = new SkillHandler<Hero>(properties, null, 0, false, new Time(0, 0, 1), null, cooldown, true, true, requiredEnergyPoint, requiredEnergyPoint);
            int[] costOfUpgrade = {2,3,5};
//            Map<Integer, ArrayList<String>> nameOfNecessaryAbilities = new HashMap<>();
//            ArrayList<String> tmpArr = new ArrayList<>();
//            tmpArr.add("Quick as a bunny");
//            nameOfNecessaryAbilities.put(1, tmpArr); nameOfNecessaryAbilities.put(2, tmpArr); nameOfNecessaryAbilities.put(3, tmpArr);
//            Map<Integer, Map<String, Integer>> gradeOfNecessaryAbilities = new HashMap<>();
//            Map<String, Integer> tmpMap = new HashMap<>();
//            tmpMap.put("Quick as a bunny", 1);
//            gradeOfNecessaryAbilities.put(1, tmpMap);
//            tmpMap.put("Quick as a bunny", 2);
//            gradeOfNecessaryAbilities.put(2, tmpMap);
//            tmpMap.put("Quick as a bunny", 3);
//            gradeOfNecessaryAbilities.put(3, tmpMap);
            String[] upgradeDescription = new String[3];
            upgradeDescription[0] = "Upgrade 1: A=20 for 2 xp points and takes 1 turn to cool down";
            upgradeDescription[1] = "Upgrade 2: A=30 for 3 xp points and takes 1 turn to cool down";
            upgradeDescription[2] = "Upgrade 3: A=30 for 5 xp points and cools down instantly";
            String description = "Gives A bonus attack power to himself or an ally, which lasts till the end of the battle, for 2 energy points and 50 magic points (this bonus attack power can stack up)";
            AbilityHandler<Enemy> abilityHandler = new AbilityHandler<Enemy>("Boost", "Bolti", false, false, false, 1, 3, null, false, false, costOfUpgrade, null, null, upgradeDescription,description);
            Skill Boost = new Skill(skillHandler, abilityHandler);
            Skill.listOfSkills.put("Boost", Boost);
        }
        {
            //Bolti's Skill
            double[] tmp = {0,0,0};
            double[] arr = {50,80,80};
            PropertyHandler propertyHandler = new PropertyHandler("currentMagic",3, false, true, true, arr, tmp, tmp, tmp, tmp, tmp, tmp, tmp);
            Property<Hero> property = new Property<>(propertyHandler);
            ArrayList<Property<Hero>> properties = new ArrayList<>();
            properties.add(property);
            int[] cooldown = {1,1,0}; int[] requiredEnergyPoint = {1,1,1}; int[] requiredMagicPoint = {50,50,50};
            SkillHandler<Hero> skillHandler = new SkillHandler<Hero>(properties, null, 0, false, new Time(0, 0, 0), null, cooldown, true, false, requiredEnergyPoint, requiredEnergyPoint);
            int[] costOfUpgrade = {2,3,4};
            Map<Integer, ArrayList<String>> nameOfNecessaryAbilities = new HashMap<>();
            ArrayList<String> tmpArr = new ArrayList<>();
            tmpArr.add("magic lessons");
            nameOfNecessaryAbilities.put(1, tmpArr); nameOfNecessaryAbilities.put(2, tmpArr); nameOfNecessaryAbilities.put(3, tmpArr);
            Map<Integer, Map<String, Integer>> gradeOfNecessaryAbilities = new HashMap<>();
            Map<String, Integer> tmpMap = new HashMap<>();
            tmpMap.put("magic lessons", 1);
            gradeOfNecessaryAbilities.put(1, tmpMap);
            tmpMap.put("magic lessons", 2);
            gradeOfNecessaryAbilities.put(2, tmpMap);
            tmpMap.put("magic lessons", 3);
            gradeOfNecessaryAbilities.put(3, tmpMap);
            String[] upgradeDescription = new String[3];
            upgradeDescription[0] = "Upgrade 1: M=50 for 2 xp points and takes 1 turn to cool down, needs magic lessons upgrade 1";
            upgradeDescription[1] = "Upgrade 2: M=80 for 3 xp points and takes 1 turn to cool down, needs magic lessons upgrade 2";
            upgradeDescription[2] = "Upgrade 3: M=80 for 4 xp points and cools down instantly, needs magic lessons upgrade 3";
            String description = "Gives M magic points to himself or an ally for 1 energy point and 50 magic points";
            AbilityHandler<Enemy> abilityHandler = new AbilityHandler<Enemy>("Mana beam", "Bolti", false, false, false, 1, 3, null, false, false, costOfUpgrade, nameOfNecessaryAbilities, gradeOfNecessaryAbilities, upgradeDescription, description);
            Skill ManaBeam = new Skill(skillHandler, abilityHandler);
            Skill.listOfSkills.put("Mana beam", ManaBeam);
        }
    }

    public void doCustomGame(){
        this.addDefaultAttributes();                              // player's name
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
            Thug thug1 = new Thug("Weak");
            Thug thug2 = new Thug("Weak");
            Thug thug3 = new Thug("Weak");
            Angel angel1 = new Angel("Weak");
        }
        else if(numberOfBattle == 2){
            Thug thug1 = new Thug("Able");
            Thug thug2 = new Thug("Able");
            Angel angel1 = new Angel("Weak");
            Tank tank1 = new Tank("Weak");
        }
        else if(numberOfBattle == 3){
            Thug thug1 = new Thug("Able");
            Thug thug2 = new Thug("Mighty");
            Angel angel1 = new Angel("Able");
            Tank tank1 = new Tank("Weak");
        }
        else if(numberOfBattle == 4){
            Thug thug1 = new Thug("Mighty");
            Thug thug2 = new Thug("Mighty");
            Angel angel1 = new Angel("Able");
            Tank tank1 = new Tank("Able");
            Tank tank2 = new Tank("Able");
        }
    }

    public void doCampaign(int asb){                                               // do Campaign Game (not Custom Game)
        this.addDefaultAttributes();                                      // player's name
        for(int i = 0;i < 5;i++){
            setEnemies(i + 1);
            this.showBattleMessage(i + 1);
            Display.printInEachLine("#######################################");
            this.heroesAnnouncement();
            Display.printInEachLine("#######################################");
            this.enemiesAnnouncement();
            Display.printInEachLine("#######################################");
//            Display.printInEachLine("Your current experience is : " + this.player.getXp());
//            Display.printInEachLine("#######################################");
//            Shop.showItems();
//            for(Hero hero : this.listOfHeroes){
//                hero.showItems();
//            }
//            Display.printInEachLine("Your current wealth is: " + player.getMoney());
            this.abilityCastCommands();
            this.shoppingCommands();
            for(Hero hero : this.listOfHeroes){
                hero.showDescription();
            }
            for(Enemy enemy: this.listOfEnemies){
                enemy.showDescription();
            }
            while(true){
                Display.printInEachLine("Enter Your Command:");
                String command = Display.getString();
                for(Hero hero: this.listOfHeroes){
                    if(command.equalsIgnoreCase(hero.getName() + "?")){
                        hero.showDescription();
                        break;
                    }
                    if(command.contains(hero.getName())){
                        for(Skill skill:hero.getSkills()){
                            if(command.equalsIgnoreCase(hero.getName() + " " + skill.getName() + "?")){//(hero name) + “ “ +(ability name) + "?"
                                Display.printInEachLine(skill.getDescription());
                                Display.printInEachLine(skill.getUpgradeDescription()[skill.getCurrentGrade()]);
                                Display.printInEachLine("You need " + skill.getCostOfUpgrade()[skill.getCurrentGrade()] + " experience points");
                                break;
                            }
                        }
                    }
                    if(command.contains(hero.getName() + " cast ")){
                        for(Skill skill : this.listOfSkills){
                            if(command.contains(hero.getName() + " cast " + skill.getName())){
                                if(!hero.hasSkill(skill)){
                                    Display.printInEachLine(hero.getName() + "hasn't " + skill.getName());
                                    break;
                                }
                                else if(hero.getCurrentEnergyPoint() >= skill.getRequiredEnergyPoint()[skill.getCurrentGrade()] && hero.getCurrentMagic() >= skill.getRequiredMagicPoint()[skill.getCurrentGrade()]){
                                    hero.useSkill(skill.name);
                                    Display.printInEachLine(hero.getName() + "casts Successfully" + skill.getName());
                                    break;
                                }
                                else if(hero.getCurrentEnergyPoint() >= skill.getRequiredEnergyPoint()[skill.getCurrentGrade()] && hero.getCurrentMagic() < skill.getRequiredMagicPoint()[skill.getCurrentGrade()]){
                                    Display.printInEachLine("You don’t have enough magic points");
                                    break;
                                }
                                else if(hero.getCurrentEnergyPoint() < skill.getRequiredEnergyPoint()[skill.getCurrentGrade()] && hero.getCurrentMagic() >= skill.getRequiredMagicPoint()[skill.getCurrentGrade()]){
                                    Display.printInEachLine("You don’t have enough energy points");
                                    break;
                                }
                                else if(hero.getCurrentEnergyPoint() < skill.getRequiredEnergyPoint()[skill.getCurrentGrade()] && hero.getCurrentMagic() < skill.getRequiredMagicPoint()[skill.getCurrentGrade()]){
                                    Display.printInEachLine("You don’t have enough energy points");
                                    Display.printInEachLine("You don’t have enough magic points");
                                    break;
                                }
                            }
                        }
                    }
                    if(command.contains(hero.getName() + " use ")){
                        for(ItemProperties itemProperties: Shop.listOfItems){
                            if(command.contains(hero.getName() + " use " + itemProperties.getItem().getName() + " on ")){
                                if(!hero.hasItem(itemProperties.getItem())){
                                    Display.printInEachLine("You don’t have this item");
                                    break;
                                }
//                                else if(itemProperties.getItem().getRemainingTime() > 0){
//                                    Display.printInEachLine("Your desired item is still in cooldown");
//                                    break;
//                                }
                                if(hero.getCurrentEnergyPoint() >= itemProperties.getItem().getRequiredEnergyPoint() && hero.getCurrentMagic() >= itemProperties.getItem().getRequiredMagicPoint()){
                                    hero.setCurrentEnergyPoint(hero.getCurrentEnergyPoint() - itemProperties.getItem().getRequiredEnergyPoint());
                                    hero.setCurrentMagic(hero.getCurrentMagic() - itemProperties.getItem().getRequiredMagicPoint());
                                    Display.printInEachLine(hero.getName() + "used Successfully " + itemProperties.getItem().getName());
                                    break;
                                }
                                else if(hero.getCurrentEnergyPoint() >= itemProperties.getItem().getRequiredEnergyPoint() && hero.getCurrentMagic() < itemProperties.getItem().getRequiredMagicPoint()){
                                    Display.printInEachLine("You don’t have enough magic points");
                                    break;
                                }
                                else if(hero.getCurrentEnergyPoint() < itemProperties.getItem().getRequiredEnergyPoint() && hero.getCurrentMagic() >= itemProperties.getItem().getRequiredMagicPoint()){
                                    Display.printInEachLine("You don’t have enough energy points");
                                    break;
                                }
                                else if(hero.getCurrentEnergyPoint() < itemProperties.getItem().getRequiredEnergyPoint() && hero.getCurrentMagic() < itemProperties.getItem().getRequiredMagicPoint()){
                                    Display.printInEachLine("You don’t have enough energy points");
                                    Display.printInEachLine("You don’t have enough magic points");
                                    break;
                                }
                            }
                        }
                    }
                }
                for(Enemy enemy : this.listOfEnemies){
                    if(command.equalsIgnoreCase(enemy.getName() + "?")){
                        enemy.showDescription();
                        break;
                    }
                }
                for(ItemProperties itemProperties : Shop.listOfItems){
                    if(command.equalsIgnoreCase(itemProperties.getItem().getName() + "?")){
                        Display.printInEachLine(itemProperties.getItem().getDescription());
                        break;
                    }
                }
                for(Skill skill : this.listOfSkills) {
                    if (command.equalsIgnoreCase(skill.getName() + "?")) {
                        Display.printInEachLine(skill.getDescription());
                        break;
                    }
                }
                for(Perk perk : this.listOfPerks) {
                    if (command.equalsIgnoreCase(perk.getName() + "?")) {
                        Display.printInEachLine(perk.getDescription());
                        break;
                    }
                }

            }
        }
    }

    public void showHeroTeamDescription() {
        for(int i = 0;i < this.listOfHeroes.size();i++){
            Display.printInEachLine(this.listOfHeroes.get(i).getName() + " (" + this.listOfHeroes.get(i).getClassName() + ")");
        }

    }

    public void showEnemyTeamDescription(){
        for(Enemy enemy: this.listOfEnemies){
            Display.printInEachLine(enemy.getName());
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
                Display.printInEachLine((i + 1) + " - " + GameEngine.listOfHeroClasses.get(i).getName());
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
        }
    }

    public void addNewPerk(Perk perk){
        if(isCustomGame){
            //...
            this.listOfPerks.add(perk);
        }
        else{
            this.listOfPerks.add(perk);
        }
    }

    public void addNewSkill(Skill skill) {
        if(isCustomGame){
            //...
            this.listOfSkills.add(skill);
        }
        else{
            this.listOfSkills.add(skill);
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
        if(isCustomGame){
            this.doCustomGame();
        }
        else{
            for(int i = 0;i < 5;i++) {
                this.doCampaign(i);
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
            }
        }
    }
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
