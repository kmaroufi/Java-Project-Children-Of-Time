/**
 * Created by asus-pc on 5/3/2016.
 */
import Engine.*;
import PlayerPackage.*;
import GUI.Display;

import java.io.Serializable;

public class MainClass implements Serializable {
    public static GameEngine childrenOfTime = new GameEngine();
    public static void main(String[] args) {
        Display.printInEachLine("Hi,Welcome!");
        Display.printInEachLine("What's Your Name?");
        String playerName = Display.getString();
        childrenOfTime.setPlayer(new PlayerPackage.Player(playerName,15,40));
        Display.printInEachLine("Hi " + childrenOfTime.getPlayer().getName());
        childrenOfTime.chooseModeOfGame();
        childrenOfTime.play();
    }
}
