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
