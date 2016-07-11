package Input;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by Future on 7/10/2016.
 */
public class MouseInput extends MouseAdapter{
    private static final int NUM_KEYS = 10;
    private static boolean[] Keys = new boolean[NUM_KEYS];
    public static double x,y;


    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        this.Keys[e.getButton()] = true;
        x = e.getX();
        y = e.getY();    }

    @Override
    public void mouseReleased(MouseEvent e) {
        this.Keys[e.getButton()] = false;
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    public static boolean wasPressed(int buttonCode) {
        return Keys[buttonCode];
    }

    public static double getX() {
        return x;
    }

    public static double getY() {
        return y;
    }

    public static boolean wasClicked() {
        for(int i = 0;i < NUM_KEYS;i++) {
            if(Keys[i]){
                return true;
            }
        }
        return false;
    }
}
