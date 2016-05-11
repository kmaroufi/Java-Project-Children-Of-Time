import java.util.ArrayList;

/**
 * Created by asus-pc on 5/6/2016.
 */
public class GameEngine {
    private Player player;
    private boolean isCustomGame = false;
    public static ArrayList<Enemy> listOfEnemies = new ArrayList<Enemy>();
    public static ArrayList<HeroClass> listOfHeroClasses = new ArrayList<HeroClass>();
    public static ArrayList<Hero> listOfHeroes = new ArrayList<Hero>();

    private String level;                   // level of Game(Easy-Medium-Hard)

    //------------------------------------------ Functions
    public void chooseLevelOfGame(){
        Display.printInEachLine("Choose Level Of Game:");
        Display.printInEachLine("1 - Easy");
        Display.printInEachLine("2 - Medium");
        Display.printInEachLine("3 - Hard");
        int choose = Display.getInteger();
        while(true){
            if(choose == 1) {
                this.setLevel("Easy");
                break;
            }
            else if(choose == 2) {
                this.setLevel("Medium");
                break;
            }
            else if(choose == 3) {
                this.setLevel("Hard");
                break;
            }
            else {
                Display.printInEachLine("Wrong Number! Try Again!");
            }
        }
    }

    public void addDefaultAttributes(){                             // Adds All Datas in PDF (Fighter-Meryl-......)
        //Adding Fighter Class
        HeroClassHandler fighterHandler = new HeroClassHandler("Fighter",200,120,120,6,2,0.1,0.05);
        this.addNewHeroClass(new HeroClass(fighterHandler));
        //Adding Supporter Class
        HeroClassHandler supporterHandler = new HeroClassHandler("Supporter",220,80,200,5,3,0.05,0.1);
        this.addNewHeroClass(new HeroClass(supporterHandler));
        //Adding Eley
        this.addNewHero(new Hero("Eley",fighterHandler));
        //Adding Chrome
        this.addNewHero(new Hero("Chrome",fighterHandler));
        //Adding Meryl
        this.addNewHero(new Hero("Meryl",supporterHandler));
        //Adding Bolti
        this.addNewHero(new Hero("Bolti",supporterHandler));
        //Adding Enemies
        if(this.getLevel().equals("Easy")){
            Thug thug = new Thug("Weak");
            Angel angel = new Angel("Weak");
            Tank tank = new Tank("Weak");
            FinalBoss finalBoss = new FinalBoss();
        }
        else if(this.getLevel().equals("Medium")){
            Thug thug = new Thug("Able");
            Angel angel = new Angel("Able");
            Tank tank = new Tank("Able");
            FinalBoss finalBoss = new FinalBoss();
        }
        else if(this.getLevel().equals("Hard")){
            Thug thug = new Thug("Mighty");
            Angel angel = new Angel("Able");
            Tank tank = new Tank("Able");
            FinalBoss finalBoss = new FinalBoss();
        }
    }

    public void doCustomGame(){
        this.addDefaultAttributes();
        this.setCustomGame(true);
        while (true){
            //Add A new Hero!
            Hero newHero = new Hero();
            this.addNewHero(newHero);
        }
    }

    public void doCampaign(){                                       // do Campaign Game (not Custom Game)
        this.addDefaultAttributes();
        for(int i = 0;i < 5;i++){
            this.showBattleMessage(i + 1);
            //Do Battle - move - ...
        }
    }

    public void addNewHeroClass(HeroClass heroClass) {
        if(isCustomGame){
            //Creating A new Hero Class
            this.getListOfHeroClasses().add(heroClass);
        }
        else{
            this.getListOfHeroClasses().add(heroClass);
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
            this.getListOfHeroes().add(hero);
        }
        else{
            this.getListOfHeroes().add(hero);
        }
    }

    public void addNewAbility() {
        //TODO
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
        //TODO
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


    //------------------------------------------ Getter && Setters

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public ArrayList<HeroClass> getListOfHeroClasses() {
        return listOfHeroClasses;
    }

    public void setListOfHeroClasses(ArrayList<HeroClass> listOfHeroClasses) {
        this.listOfHeroClasses = listOfHeroClasses;
    }

    public ArrayList<Hero> getListOfHeroes() {
        return listOfHeroes;
    }

    public void setListOfHeroes(ArrayList<Hero> listOfHeroes) {
        this.listOfHeroes = listOfHeroes;
    }

    public boolean isCustomGame() {
        return isCustomGame;
    }

    public void setCustomGame(boolean customGame) {
        isCustomGame = customGame;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }


}
