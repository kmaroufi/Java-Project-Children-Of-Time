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

        double[] tmp = {0,0,0};
        double[] arr = {30,30,30};
        PropertyHandler propertyHandler = new PropertyHandler("currentHealth", 3, false, true, true, tmp, arr, tmp, tmp, tmp, tmp, tmp, tmp);
        Property<Enemy> property = new Property<>(propertyHandler);
        ArrayList<Property<Enemy>> properties = new ArrayList<>();
        properties.add(property);
        ArrayList<String> blackList = new ArrayList<>();
        blackList.add("Eley");
        int[] cooldown = {0,0,0}; int[] requiredEnergyPoint = {2,2,2}; int[] requiredMagicPoint = {50,50,50};
        SkillHandler<Enemy> skillHandler = new SkillHandler<Enemy>(properties, null, 0, false, new Time(), blackList, cooldown, true, false, requiredEnergyPoint, requiredEnergyPoint);
        int[] costOfUpgrade = {2,4,6};
        Map<Integer, ArrayList<String>> nameOfNecessaryAbilities = new HashMap<>();
        ArrayList<String> tmpArr = new ArrayList<>();
        tmpArr.add("Fight Training");
        nameOfNecessaryAbilities.put(0, tmpArr); nameOfNecessaryAbilities.put(1, tmpArr); nameOfNecessaryAbilities.put(2, tmpArr);
        Map<Integer, Map<String, Integer>> gradeOfNecessaryAbilities = new HashMap<>();
        Map<String, Integer> tmpMap = new HashMap<>();
        tmpMap.put("Fight Training", 1);
        gradeOfNecessaryAbilities.put(1, tmpMap);
        tmpMap.put("Fight Training", 2);
        gradeOfNecessaryAbilities.put(2, tmpMap);
        tmpMap.put("Fight Training", 3);
        gradeOfNecessaryAbilities.put(3, tmpMap);
        AbilityHandler<Enemy> abilityHandler = new AbilityHandler<Enemy>("overPoweredAttack", "Eley", false, true, false, 1, 3, null, true, false, false, costOfUpgrade, nameOfNecessaryAbilities, gradeOfNecessaryAbilities)
        Skill overPoweredAttack = new Skill(skillHandler, abilityHandler);                     //Eley's Skill
        Perk swirlingAttack = new Perk();                           //Eley's Perk
        Skill sacrifice = new Skill();                              //Chrome's Skill
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
        if(this.getLevelOfGame().equals("Easy")){
            Thug thug = new Thug("Weak");
            Angel angel = new Angel("Weak");
            Tank tank = new Tank("Weak");
            FinalBoss finalBoss = new FinalBoss();
            this.listOfEnemies.add(thug);
            this.listOfEnemies.add(angel);
            this.listOfEnemies.add(tank);
            this.listOfEnemies.add(finalBoss);
        }
        else if(this.getLevelOfGame().equals("Medium")){
            Thug thug = new Thug("Able");
            Angel angel = new Angel("Able");
            Tank tank = new Tank("Able");
            FinalBoss finalBoss = new FinalBoss();
            this.listOfEnemies.add(thug);
            this.listOfEnemies.add(angel);
            this.listOfEnemies.add(tank);
            this.listOfEnemies.add(finalBoss);
        }
        else if(this.getLevelOfGame().equals("Hard")){
            Thug thug = new Thug("Mighty");
            Angel angel = new Angel("Able");
            Tank tank = new Tank("Able");
            FinalBoss finalBoss = new FinalBoss();
            this.listOfEnemies.add(thug);
            this.listOfEnemies.add(angel);
            this.listOfEnemies.add(tank);
            this.listOfEnemies.add(finalBoss);
        }
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

    public void doCustomGame(){
        this.addDefaultAttributes("");                              // player's name
        this.setCustomGame(true);
        while (true){
            //Add A new Hero!
            Hero newHero = new Hero();
            this.addNewHero(newHero);
        }
    }

    public void doCampaign(){                                               // do Campaign Game (not Custom Game)
        this.addDefaultAttributes("");                                      // player's name
        for(int i = 0;i < 5;i++){
            boolean hasBoughtInShop = false;
            this.showBattleMessage(i + 1);                                  // Showing The Story Of Game
            Display.printInEachLine("#######################################");
            this.showEnemyTeamDescription();                                // Showing The Enemy Team Description
            Display.printInEachLine("#######################################");
            this.showHeroTeamDescription();
            Display.printInEachLine("#######################################");
            Display.printInEachLine("Your current experience is : " + this.player.getXp());
            Display.printInEachLine("#######################################");
            Shop.showItems();
            for(Hero hero : this.listOfHeroes){
                hero.showItems();
            }
            Display.printInEachLine("Your current wealth is: " + player.getMoney());
            while(true){
                String command = Display.getString();
                for(Item item: Shop.listOfItems){
                    if(command.equalsIgnoreCase(item.getName() + "?")){
                        item.getDescription();
                        break;
                    }
                    if(command.contains("Buy " + item.getName() + " for ") || command.contains("Sell " + item.getName() + " of ")){
                        for(Hero hero : this.listOfHeroes){
                            if(command.equals("Buy " + item.getName() + " for " + hero.getName())){
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
                            else if(command.equals("Sell " + item.getName() + " of " + hero.getName())){    //(item name) + “ successfully sold, your current wealth is: “ + (current money)
                                if(hero.hasItem(item)) {
                                    this.player.setMoney(this.player.getMoney() + (item.getRequiredMoney() / 2));
                                    Display.printInEachLine(item.getName() + " successfully sold, your current wealth is: " + player.getMoney());
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
                                        if(/*perk's xp >> player's xp*/){                            //Kamyar
                                            Display.printInEachLine("Your experience is insufficient");
                                        }
                                        hero.upgradeAbility(perk.getName());                        // kamyar Decrease XP!!!!!!!!!!!!!!!!!!!!!!
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
                if(command.equals("Shop!")){                // Correct it!
                    // buy from shop
                    //if buy or sell                    hasBoughtInShop = true;
                }

                for(Hero hero: this.listOfHeroes){
                    if(command.equalsIgnoreCase(hero.getName() + "?")){
                        hero.showDescription();
                        break;
                    }
                }
                for(Hero hero: this.listOfHeroes){
                    if(command.contains(hero.getName())){
                        for(Skill skill:hero.getSkills()){
                            if(command.equalsIgnoreCase(hero.getName() + " " + skill.getName())){
                                skill.getDescription();
                                Display.printInEachLine("You need " + skill.getRequiredExperience() + " experience points");
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
            Display.printInEachLine(this.listOfHeroes.get(i).getName());
            Display.printInEachLine("Health: " + this.listOfHeroes.get(i).getCurrentHealth() + " / " + this.listOfHeroes.get(i).getMaximumHealth());
            Display.printInEachLine("Magic: " + this.listOfHeroes.get(i).getCurrentMagic() + " / " + this.listOfHeroes.get(i).getMaximumMagic());
            Display.printInEachLine("Energy points: " + this.listOfHeroes.get(i).getCurrentEnergyPoint());
            Display.printInEachLine("Attack power: " + this.listOfHeroes.get(i).getAttackPower());
            for(int j = 0;j < this.listOfHeroes.get(i).getSkills().size();j++){
                Display.printInEachLine("");
            }
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
                Display.printInEachLine("You’ve entered the castle, it takes a while for your eyes to get used to the darkness but the horrifying halo of your enemies is vaguely visible. Angel’s unsettling presence and the growling of thugs tell you that your first battle has BEGUN!");
                break;
            case 2:
                Display.printInEachLine("As you wander into the hall you realize the surrounding doors can lead your destiny to something far worse than you expected. You know what’s anticipating you behind the only open door but there’s no other choice.");
                break;
            case 3:
                Display.printInEachLine("The door behind you is shut with a thunderous sound and you progress into the next hall holding the first key that you’ve found, hoping to seek the second one.");
                break;
            case 4:
                Display.printInEachLine("Running with the second key in your hand, you unlock the door back to the first hall and use the first key to burst into your most terrifying nightmares.");
                break;
            case 5:
                Display.printInEachLine("You feel hopeless and exhausted as you stalk to the final door. What’s behind that door makes your hearts pound and your spines shake with fear, but you came here to do one thing and backing down is not an option.");
                break;
            case 6:
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
