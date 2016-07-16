package GUI;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Created by Future on 7/6/2016.
 */
public class KeyInput implements KeyListener {
    public static final int NUM_OF_KEYS = 256;
    public static boolean Keys[] = new boolean[NUM_OF_KEYS];


    public static boolean wasPressed(int keyCode) {
        return Keys[keyCode];
    }


    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        Keys[e.getKeyCode()] = true;
    }


    @Override
    public void keyReleased(KeyEvent e) {
        Keys[e.getKeyCode()] = false;
    }
}
