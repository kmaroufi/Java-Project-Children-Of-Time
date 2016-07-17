package Engine;

import GUI.GameFrame;

import javax.swing.*;
import java.util.Map;

/**
 * Created by asus-pc on 7/17/2016.
 */
public class StroryBook extends JFrame implements GameFrame{
    private String stroty;
    private JTextArea stroy;

    public StroryBook(String stroty) {
        this.stroty = stroty;
    }

    @Override
    public void setBackgroundImage() {

    }

    @Override
    public void addActionListeners() {

    }

    @Override
    public void showFrame() {
        this.setSize(700, 700);
        this.setLayout(null);
//        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setResizable(false);
        //------------------------------------------------------------------
        this.addComponents();
        this.setFonts();
        this.setComponentsBounds();
        //------------------------------------------------------------------
        this.setVisible(true);
    }

    @Override
    public void setButtons() {

    }

    @Override
    public void addComponents() {
        this.add(this.stroy);
    }

    @Override
    public void setComponentsBounds() {

    }

    @Override
    public void setFonts() {

    }

    public static void main(String[] args) {
    }
}
