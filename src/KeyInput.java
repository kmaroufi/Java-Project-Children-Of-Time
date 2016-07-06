import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Created by Future on 7/6/2016.
 */
public class KeyInput implements KeyListener {
    public static boolean RIGHT = false;
    public static boolean LEFT = false;
    public static boolean DOWN = false;
    public static boolean UP = false;
    public static boolean ENTER = false;


    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int codePressed = e.getKeyCode ();
        switch (codePressed) {
            case KeyEvent.VK_RIGHT:
                this.rightClicked ();
                break;
            case KeyEvent.VK_LEFT:
                break;
            case KeyEvent.VK_UP:
                break;
            case KeyEvent.VK_DOWN:
                break;
            case KeyEvent.VK_ENTER:
                break;
        }
    }

    private void rightClicked() {
        RIGHT = true;
        LEFT = false;
        DOWN = false;
        UP = false;
        ENTER = false;
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
