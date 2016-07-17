/**
 * Created by asus-pc on 5/3/2016.
 */
import Engine.*;
import PlayerPackage.*;
import GUI.Display;

import javax.swing.*;
import java.io.Serializable;

public class MainClass implements Serializable {
    public static GameEngine childrenOfTime = new GameEngine();
    public static void main(String[] args) {
//        Display.printInEachLine("Hi,Welcome!");
//        Display.printInEachLine("What's Your Name?");
//        String playerName = Display.getString();
        String name = JOptionPane.showInputDialog("What's Your Name?");
        childrenOfTime.setPlayer(new PlayerPackage.Player(name,15,40));
//        Display.printInEachLine("Hi " + childrenOfTime.getPlayer().getName());
        childrenOfTime.play();
    }
}
