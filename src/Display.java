import javax.swing.*;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Future on 5/6/2016.
 */

public class Display {
    private static Scanner cin = new Scanner(System.in);
    private static String lastGet = new String();

    //----------------------------------

    public static String getString(){
        if (Display.lastGet.equals("Integer") || Display.lastGet.equals("Double"))
            cin.nextLine();
        Display.lastGet = "String";
        return cin.nextLine();
    }

    public static int getInteger(){
        int i = 0;
        int cond = 0;
        while (true) {
            try {
                if (cond == 1)
                    cin.nextLine();
                i = cin.nextInt();
                break;
            } catch (Exception e) {
                System.out.println("Wrong Input!");
                cond = 1;
            }
        }
        Display.lastGet = "Integer";
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
        Display.lastGet = "Double";
        return i;    }

    public static void printInEachLine(String sentence){
        System.out.println(sentence);
    }

    public static void displayMessage(String message) {
        JOptionPane.showMessageDialog(null, message);
    }

    public static void printf(String sentence){
        System.out.printf(sentence);
    }

    public static ArrayList<String> getAbilityDetailsBeforeUsing(Object o) {
        ArrayList<String> inputs = new ArrayList<String>();
        inputs.add(Display.getString());
        return  inputs;
    }


}
