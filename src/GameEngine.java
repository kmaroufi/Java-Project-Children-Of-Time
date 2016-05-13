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
    public static ArrayList<Enemy> listOfEnemies = new ArrayList<Enemy>();
    public static ArrayList<HeroClass> listOfHeroClasses = new ArrayList<HeroClass>();
    public static ArrayList<Hero> listOfHeroes = new ArrayList<Hero>();
    public static ArrayList<Skill> listOfSkills = new ArrayList<Skill>();
    public static ArrayList<Perk> listOfPerks = new ArrayList<Perk>();
    public static ArrayList<Shop> shops = new ArrayList<Shop>();
    private int NumberOfBattle;                     // which battle we are in(1-2-3-4-5)
    private String levelOfGame;                     // level of Game(Easy-Medium-Hard)

    //------------------------------------------ Functions
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

    public void addDefaultAttributes(String playerName){            // Adds All Datas in PDF (Fighter-Meryl-......)
        //Adding Abilities
        this.setNumberOfBattle(1);                                  //Number of battle
        Perk fightTraining = new Perk();                            //Fighter Class's Perk
        Perk workOut = new Perk();                                  //Fighter Class's Perk
        Perk quickAsBunny = new Perk();                             //Supporter Class's Perk
        Perk magicLessons = new Perk();                             //Supporter Class's Perk


        Perk swirlingAttack = new Perk();                           //Eley's Perk



        Perk criticalStrike = new Perk();                           //Chrome's Perk
        Skill elixir = new Skill();                                 //Meryl's Skill
        Perk careTaker = new Perk();                                //Meryl's Perk
        Skill boost = new Skill();                                  //Bolti's Skill
        Skill manaBeam = new Skill();                               //Bolti's Skill
        //Adding Fighter Class
        HeroClassHandler fighterHandler = new HeroClassHandler("Fighter",200,120,120,6,2,0.1,0.05);
        fighterHandler.addPerk(fightTraining);
        fighterHandler.addPerk(workOut);
        this.addNewHeroClass(new HeroClass(fighterHandler));
        //Adding Supporter Class
        HeroClassHandler supporterHandler = new HeroClassHandler("Supporter",220,80,200,5,3,0.05,0.1);
        supporterHandler.addPerk(quickAsBunny);
        supporterHandler.addPerk(magicLessons);
        this.addNewHeroClass(new HeroClass(supporterHandler));
        //Adding Eley
        Hero eley = new Hero("Eley",fighterHandler);
        eley.addSkill(overPoweredAttack);
        eley.addPerk(swirlingAttack);
        this.addNewHero(eley);
        //Adding Chrome
        Hero chrome = new Hero("Chrome",fighterHandler);
        chrome.addSkill(sacrifice);
        chrome.addPerk(criticalStrike);
        this.addNewHero(chrome);
        //Adding Meryl
        Hero meryl = new Hero("Meryl",supporterHandler);
        meryl.addSkill(elixir);
        meryl.addPerk(careTaker);
        this.addNewHero(meryl);
        //Adding Bolti
        Hero bolti = new Hero("Bolti",supporterHandler);
        bolti.addSkill(boost);
        bolti.addSkill(manaBeam);
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
        Item toughen = new Item();
        Item guide = new Item();
        Item defy = new Item();
        Item sword = new Item();
        Item energyBoots = new Item();
        Item armor = new Item();
        Item magicStick = new Item();
        Item healthPotion = new Item();
        Item magicPotion = new Item();
        // Add Shop
        Shop shop = new Shop();
        Shop.listOfItems.add(toughen);
        Shop.listOfItems.add(guide);
        Shop.listOfItems.add(defy);
        Shop.listOfItems.add(sword);
        Shop.listOfItems.add(energyBoots);
        Shop.listOfItems.add(armor);
        Shop.listOfItems.add(magicStick);
        Shop.listOfItems.add(healthPotion);
        Shop.listOfItems.add(magicPotion);

    }

    private void creatingDefaultSkills() {
        {
            //Eley's Skill
            double[] tmp = {0,0,0};
            double[] arr = {30,30,30};
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
            AbilityHandler<Enemy> abilityHandler = new AbilityHandler<Enemy>("overPoweredAttack", "Eley", false, true, false, 1, 3, null, false, false, costOfUpgrade, nameOfNecessaryAbilities, gradeOfNecessaryAbilities, upgradeDescription);
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
            int[] cooldown = {0,0,0}; int[] requiredEnergyPoint = {3,3,3}; int[] requiredMagicPoint = {60,60,60};
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
            AbilityHandler<Enemy> abilityHandler = new AbilityHandler<Enemy>("Sacrifice", "Chrome", false, true, false, -5, 3, null, false, false, costOfUpgrade, nameOfNecessaryAbilities, gradeOfNecessaryAbilities, upgradeDescription);
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
            AbilityHandler<Enemy> abilityHandler = new AbilityHandler<Enemy>("Elixir", "Meryl", false, false, false, 1, 3, null, false, false, costOfUpgrade, nameOfNecessaryAbilities, gradeOfNecessaryAbilities, upgradeDescription);
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
            AbilityHandler<Enemy> abilityHandler = new AbilityHandler<Enemy>("Caretaker", "Meryl", false, false, false, 1, 3, null, false, false, costOfUpgrade, nameOfNecessaryAbilities, gradeOfNecessaryAbilities, upgradeDescription);
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
            AbilityHandler<Enemy> abilityHandler = new AbilityHandler<Enemy>("Boost", "Bolti", false, false, false, 1, 3, null, false, false, costOfUpgrade, null, null, upgradeDescription);
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
            AbilityHandler<Enemy> abilityHandler = new AbilityHandler<Enemy>("Mana beam", "Bolti", false, false, false, 1, 3, null, false, false, costOfUpgrade, nameOfNecessaryAbilities, gradeOfNecessaryAbilities, upgradeDescription);
            Skill ManaBeam = new Skill(skillHandler, abilityHandler);
            Skill.listOfSkills.put("Mana beam", ManaBeam);
        }
    }

    public void doCustomGame(){
        this.addDefaultAttributes("");                              // player's name
        this.setCustomGame(true);
        while (true){
            //Welcome To Our Adding Factory
            Hero newHero = new Hero();
            this.addNewHero(newHero);
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
    public void doCampaign(){                                               // do Campaign Game (not Custom Game)
        this.addDefaultAttributes("");                                      // player's name
        for(int i = 0;i < 5;i++){
            setEnemies(i + 1);
            this.showBattleMessage(i + 1);                                  // Showing The Story Of Game
            Display.printInEachLine("#######################################");
            this.showHeroTeamDescription();                                 // Showing The Hero Team Description
            Display.printInEachLine("#######################################");
            this.showEnemyTeamDescription();                                // Showing The Enemy Team Description
            Display.printInEachLine("#######################################");
//            Display.printInEachLine("Your current experience is : " + this.player.getXp());
//            Display.printInEachLine("#######################################");
//            Shop.showItems();
//            for(Hero hero : this.listOfHeroes){
//                hero.showItems();
//            }
//            Display.printInEachLine("Your current wealth is: " + player.getMoney());
            while(true){
                Display.printf("Enter Your Command:");
                String command = Display.getString();
                for(Item item: Shop.listOfItems){
                    if(command.equalsIgnoreCase(item.getName() + "?")){                 //(item name) + “?”
                        item.getDescription();
                        break;
                    }
                    if(command.contains("Buy " + item.getName() + " for ") || command.contains("Sell " + item.getName() + " of ")){
                        for(Hero hero : this.listOfHeroes){
                            if(command.equals("Buy " + item.getName() + " for " + hero.getName())){ //“Buy “ + (item name) + “ for “ + (hero name)
                                if(hero.getInventorySize() < item.getSize()){
                                    Display.printInEachLine(hero.getName() + "'s inventory is full");
                                }
                                else if(this.player.getMoney() >= item.getRequiredMoney() && hero.getInventorySize() >= item.getSize()){                                 //(item name) “ bought successfully, your current wealth is: ” + (current money)
                                    this.player.setMoney(this.player.getMoney() - item.getRequiredMoney());
                                    Display.printInEachLine(item.getName() + " bought successfully, your current wealth is: " + this.player.getMoney());
                                }
                                else if (this.player.getMoney() < item.getRequiredMoney() && hero.getInventorySize() >= item.getSize()){
                                    Display.printInEachLine("You don’t have enough money");
                                }

                            }
                            else if(command.equals("Sell " + item.getName() + " of " + hero.getName())){//“Sell “ + (item name) + “ of” + (hero name)
                                if(hero.hasItem(item)) {
                                    this.player.setMoney(this.player.getMoney() + (item.getRequiredMoney() / 2));
                                    Display.printInEachLine(item.getName() + " successfully sold, your current wealth is: " + player.getMoney());//(item name) + “ successfully sold, your current wealth is: “ + (current money)
                                }
                                else{
                                    Display.printInEachLine(hero.getName() + "hasn't this item");
                                }
                            }
                        }
                    }
                }

                if(command.contains("Acquire ")){     //(ability name) (“acquired”/”upgraded”) + “ successfully, your current experience is: ” + (current xp)
                    for(Perk perk: this.listOfPerks){
                        if(command.contains("Acquire " + perk.getName() + " for ")){
                            for(Hero hero : this.listOfHeroes){
                                if(command.equalsIgnoreCase("Acquire " + perk.getName() + " for " + hero.getName())){
                                    if(hero.hasPerk(perk)){
                                        //if getXP was less than perks
                                        if(perk.getCurrentGrade() == perk.getNumberOfGrades()){
                                            Display.printInEachLine("This ability cannot be upgraded anymore");
                                            break;
                                        }
                                        if(perk.getCostOfUpgrade()[perk.getCurrentGrade()] > this.player.getXp()){
                                            Display.printInEachLine("Your experience is insufficient");
                                        }
                                        hero.upgradeAbility(this.player,perk.getName());
                                        this.player.setXp(this.player.getXp() - perk.getCostOfUpgrade()[perk.getCurrentGrade()]);
                                        perk.setCurrentGrade(perk.getCurrentGrade() + 1);
                                        Display.printInEachLine(perk.getName() + " upgraded " + "successfully, your current experience is: " + player.getXp());
                                        break;
                                    }
                                    else{
                                        hero.getPerks().add(perk);
                                        Display.printInEachLine(perk.getName() + " acquired " + "successfully, your current experience is: " + player.getXp());
                                        break;
                                    }
                                }
                            }
                        }
                    }
                }
                for(Hero hero: this.listOfHeroes){      //(hero name) + “?”
                    if(command.equalsIgnoreCase(hero.getName() + "?")){
                        hero.showDescription();
                        break;
                    }
                }
                for(Hero hero: this.listOfHeroes){
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
                }
                for(Enemy enemy : this.listOfEnemies){
                    if(command.equalsIgnoreCase(enemy.getName() + "?")){
                        enemy.showDescription();
                        break;
                    }
                }

            }
        }
    }

    private void showHeroTeamDescription() {
        for(int i = 0;i < this.listOfHeroes.size();i++){
            Display.printInEachLine(this.listOfHeroes.get(i).getName() + " (" + this.listOfHeroes.get(i).getClassName() + ")");
        }

    }

    public void showEnemyTeamDescription(){
        for(int i = 0;i < 4;i++){
            int count = 0;


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
            this.doCampaign();
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
            }
            else if(choose == 2){
                this.setCustomGame(true);
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
