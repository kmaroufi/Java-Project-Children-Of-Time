import java.util.Scanner;

/**
 * Created by Future on 5/6/2016.
 */

public class Console {
    private static Scanner cin;

    //----------------------------------

    public static String getString(){
        return cin.nextLine();
    }

    public static int getInteger(){
        return cin.nextInt();
    }

    public static double getDouble(){
        return cin.nextDouble();
    }

    public static void printInEachLine(String sentence){
        System.out.println(sentence);
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

    public static void printf(String sentence){
        System.out.printf(sentence);
    }


}
