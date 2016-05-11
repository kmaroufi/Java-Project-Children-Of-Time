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
        Console.printInEachLine("Choose Level Of Game:");
        Console.printInEachLine("1 - Easy");
        Console.printInEachLine("2 - Medium");
        Console.printInEachLine("3 - Hard");
        int choose = Console.getInteger();
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
                Console.printInEachLine("Wrong Number! Try Again!");
            }
        }
    }

    public void addDefaultAttributes(){                             // Adds All Datas in PDF (Fighter-Meryl-......)
        //Adding Fighter Class
        HeroClassHandler fighterHandler = new HeroClassHandler("Fighter",200,120,120,6,2,0.1,0.05);
        this.listOfHeroClasses.add(new HeroClass(fighterHandler));
        //Adding Supporter Class
        HeroClassHandler supporterHandler = new HeroClassHandler("Supporter",220,80,200,5,3,0.05,0.1);
        this.listOfHeroClasses.add(new HeroClass(supporterHandler));
        //Adding Eley
        Hero Eley = new Hero("Eley",fighterHandler);
        //Adding Chrome
        Hero Chrome = new Hero("Chrome",fighterHandler);
        //Adding Meryl
        Hero Meryl = new Hero("Meryl",supporterHandler);
        //Adding Bolti
        Hero Bolti = new Hero("Bolty",supporterHandler);
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

    }

    public void doCampaign(){                                       // do Campaign Game (not Custom Game)
    }

    public void addNewHeroClass() {
        HeroClassHandler heroClassHandler = Console.getHeroClass();
        this.listOfHeroClasses.add(new HeroClass(heroClassHandler));
    }

    public void addNewHero() {
        //TODO
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
                Console.printInEachLine("You’ve entered the castle, it takes a while for your eyes to get used to the darkness but the horrifying halo of your enemies is vaguely visible. Angel’s unsettling presence and the growling of thugs tell you that your first battle has BEGUN!");
                break;
            case 2:
                Console.printInEachLine("As you wander into the hall you realize the surrounding doors can lead your destiny to something far worse than you expected. You know what’s anticipating you behind the only open door but there’s no other choice.");
                break;
            case 3:
                Console.printInEachLine("The door behind you is shut with a thunderous sound and you progress into the next hall holding the first key that you’ve found, hoping to seek the second one.");
                break;
            case 4:
                Console.printInEachLine("Running with the second key in your hand, you unlock the door back to the first hall and use the first key to burst into your most terrifying nightmares.");
                break;
            case 5:
                Console.printInEachLine("You feel hopeless and exhausted as you stalk to the final door. What’s behind that door makes your hearts pound and your spines shake with fear, but you came here to do one thing and backing down is not an option.");
                break;
            case 6:
                Console.printInEachLine("The collector falls down on his knees, he’s strained and desperate but still tries to drag himself toward Epoch. He knows his era has come to an end. The ancient time machine calls you to end the disorder and bring unity under its glorious wings, now it’s your turn to be the MASTERS OF TIME!");
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
