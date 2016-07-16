package GUI;

import javax.swing.*;

/**
 * Created by Future on 7/13/2016.
 */
public interface GameFrame {
    default void setButton(JButton button){
        ImageIcon buttonImage = new ImageIcon("./resources/images/" + button.getName() + ".png");
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        button.setFocusPainted(false);
        button.setOpaque(false);
        button.setIcon(buttonImage);
        button.setSize(buttonImage.getIconWidth(), buttonImage.getIconHeight());
    }
    void setBackgroundImage();
    void addActionListeners();
    void showFrame();
    void setButtons();
    void addComponents();
    void setComponentsBounds();
    void setFonts();
}
