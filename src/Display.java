import java.util.Scanner;

/**
 * Created by Future on 5/6/2016.
 */

public class Display {
    private static Scanner cin;

    //----------------------------------

    public static String getString(){
        return cin.nextLine();
    }

    public static int getInteger(){
        int i = 0;
        while (true) {
            try {
                i = cin.nextInt();
                break;
            } catch (Exception e) {
                System.out.println("Wrong Input!");
            }
        }
        return i;
    }

    public static double getDouble(){
        double i = 0;
        while (true) {
            try {
                i = cin.nextDouble();
                break;
            } catch (Exception e) {
                System.out.println("Wrong Input!");
            }
        }
        return i;    }

    public static void printInEachLine(String sentence){
        System.out.println(sentence);
    }

    public static Hero getHero(){                   // Choose The The Hero Class(if none of them make a hero class)
        Hero newHero = new Hero();
        String name;
        Display.printf("Set Name For Your Hero:");
        name = Display.getString();
        //Choose A hero Class or make it!
        Display.printInEachLine("You Must Choose One OF HeroClasses For Your Own Hero!(0 for make a new hero class!)");
        for(int i = 0;i < GameEngine.listOfHeroClasses.size();i++){
            Display.printInEachLine((i + 1) + " - " + GameEngine.listOfHeroClasses.get(i).getName());
        }
        //Bug! (Commands) Soon Will Correct it!
        int choose = Display.getInteger();
        //if chooses one of hero classes
        if(choose > 0 && choose <= GameEngine.listOfHeroClasses.size()){
            newHero = new Hero(name,GameEngine.listOfHeroClasses.get(choose - 1));
        }
        else if(choose == 0){
            HeroClassHandler heroClassHandler = Display.getHeroClass();
            GameEngine.listOfHeroClasses.add(new HeroClass(heroClassHandler));
            newHero = new Hero(name,heroClassHandler);
        }
        //else implement Later!

        return newHero;
    }

    public static HeroClassHandler getHeroClass(){
        HeroClassHandler heroClassHandler = new HeroClassHandler();
        //............!!!
        return heroClassHandler;
    }

    public static PropertyHandler getProperty() {
        PropertyHandler propertyHandler = new PropertyHandler();

        return propertyHandler;
    }

    public static AbilityHandler getAbility() {
        AbilityHandler abilityHandler = new AbilityHandler();

        return abilityHandler;
    }

    public static SkillHandler getSkill() {
        SkillHandler skillHandler = new SkillHandler();

        return skillHandler;
    }

    public static PerkHandler getPerk() {
        PerkHandler perkHandler = new PerkHandler();

        return perkHandler;
    }

    public static PerkModeHandler getPerkMode() {
        PerkModeHandler perkModeHandler = new PerkModeHandler();

        return perkModeHandler;
    }

    public static ConditionHandler getCondition() {
        ConditionHandler conditionHandler = new ConditionHandler();

        return conditionHandler;
    }

    public static void printf(String sentence){
        System.out.printf(sentence);
    }


}
