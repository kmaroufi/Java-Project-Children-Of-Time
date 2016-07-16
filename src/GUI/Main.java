package GUI;

import javax.swing.*;

/**
 * Created by Future on 7/6/2016.
 */
public class Main {
    public static void main(String[] args) {
        GameEngine gameEngine = new GameEngine();
        JFrame frame = new JFrame("Tile Map");
        frame.setSize(800, 800);
        frame.add(gameEngine);
        frame.setResizable(false);
        frame.setFocusable(true);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}
