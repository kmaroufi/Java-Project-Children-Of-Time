package GUI;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Future on 7/12/2016.
 */
public class StoryBookFrame extends JFrame {
    private JTextArea storyArea;
    private Font tahoma;
    private JButton okButton;
    private ArrayList<String> story;

    public StoryBookFrame() {
        this.storyArea = new JTextArea();
        this.okButton = new JButton("OK");
        this.tahoma = new Font("Tahoma", Font.PLAIN, 15);
        this.storyArea.setFont(tahoma);
        //---------------------------------------------------------
        this.setSize(700, 700);
        this.setLayout(null);
        this.storyArea.setBounds(0, 0, this.getWidth(), this.getHeight() - 150);
        this.okButton.setBounds(this.getWidth() / 3, this.getHeight() - 100, 150, 50);
        this.add(storyArea);
        this.add(okButton);
        this.setVisible(true);
    }

    public static void main(String[] args) {
        new StoryBookFrame();
    }

}
