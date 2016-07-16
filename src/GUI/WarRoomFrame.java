package GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Future on 7/12/2016.
 */
public class WarRoomFrame extends JFrame implements ActionListener {
    private JFrame attackFrame;
    private JButton attackButton;
    private JButton castButton;
    private JButton skipButton;
    private JButton useItemButton;
    private JLabel playerNameLabel;
    private JLabel playerEPLabel;
    private JLabel warRoomImage;
    public WarRoomFrame(){
        super("War Room");
        this.attackButton = new JButton("Attack");
        this.castButton = new JButton("Cast Ability");
        this.skipButton = new JButton("Skip");
        this.useItemButton = new JButton("Use Item");
        this.playerNameLabel = new JLabel("PLAYER NAME");
        this.playerEPLabel = new JLabel("PLAYER EP");
        this.warRoomImage = new JLabel(new ImageIcon("PATH"));
        this.showTest();

    }


    public void showTest(){
        JFrame frame = new JFrame("Test");
        frame.setSize(500, 500);
        JButton button = new JButton("Button");
    }
    public void showFrame(){
        this.setSize(1500, 800);
        this.setLayout(null);
        this.add(attackButton);
        this.add(castButton);
        this.add(skipButton);
        this.add(useItemButton);
        this.add(playerNameLabel);
        this.add(playerEPLabel);
        playerNameLabel.setBounds(1300, 0, 200, 20);
        playerEPLabel.setBounds(1300, 20, 200, 20);
        attackButton.setBounds(1300, 100, 200, 100);
        castButton.setBounds(1300, 200, 200, 100);
        useItemButton. setBounds(1300, 300, 200, 100);
        skipButton.setBounds(1300, 400, 200, 100);

        this.setVisible(true);
    }






















    public static void main(String[] args) {
        new WarRoomFrame();
    }
    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
