import java.lang.reflect.Field;
import java.lang.reflect.Parameter;
import java.lang.reflect.Type;

/**
 * Created by asus-pc on 5/3/2016.
 */
public class MainClass {
    public static GameEngine childrenOfTime = new GameEngine();
    public static void main(String[] args) {
        Display.printInEachLine("Hi,Welcome!");
        Display.printInEachLine("What's Your Name?");
        String playerName = Display.getString();
        childrenOfTime.setPlayer(new Player(playerName,15,40));
        Display.printInEachLine("Hi " + childrenOfTime.getPlayer().getName());
        childrenOfTime.chooseModeOfGame();
        childrenOfTime.chooseLevelOfGame();
        childrenOfTime.play();
    }
}
