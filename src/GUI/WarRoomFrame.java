package GUI;

import Engine.GameEngine;
import com.sun.xml.internal.ws.api.pipe.Engine;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Future on 7/12/2016.
 */
public class WarRoomFrame extends JFrame implements ActionListener,GameFrame {
    private JButton attackButton;
    private JButton castButton;
    private JButton skipButton;
    private JButton useItemButton;
    private JLabel playerNameLabel;
    private JLabel playerXPLabel;
    private Font tahoma = new Font("Tahoma", Font.PLAIN, 14);
    public WarRoomFrame(){
        super("War Room");
        this.attackButton = new JButton("Attack");
        this.castButton = new JButton("Cast");
        this.skipButton = new JButton("Skip");
        this.useItemButton = new JButton("Use Item");
        this.playerNameLabel = new JLabel(Engine.GameEngine.player.getName());
        this.playerXPLabel = new JLabel(GameEngine.player.getXp());
        this.setBackgroundImage();
    }


    @Override
    public void setBackgroundImage() {
        this.setContentPane(new JLabel(new ImageIcon("./resources/images/WarRoomBackground.png")));
    }

    @Override
    public void addActionListeners() {
        this.attackButton.addActionListener(this);
        this.castButton.addActionListener(this);
        this.skipButton.addActionListener(this);
        this.useItemButton.addActionListener(this);
    }

    public void showFrame(){
        this.setSize(1500, 800);
        this.setLayout(null);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.addComponents();
        this.setComponentsBounds();
        this.setFonts();
        this.setVisible(true);
    }

    @Override
    public void setButtons() {
        this.attackButton.setName("AttackButton");
        this.castButton.setName("CastButton");
        this.skipButton.setName("SkipButton");
        this.useItemButton.setName("UseItemButton");
        this.setButton(attackButton);
        this.setButton(castButton);
        this.setButton(useItemButton);
        this.setButton(skipButton);
    }

    @Override
    public void paintComponents(Graphics g) {
        super.paintComponents(g);
        for(int i = 0;i < GameEngine.listOfHeroes.size();i++) {
            GameEngine.listOfHeroes.get(i).getTexture().render(g, (int)(600 * Math.random()), (int)(500 * Math.random()));
        }
        for(int i = 0;i < GameEngine.listOfHeroes.size();i++) {
            GameEngine.listOfEnemies.get(i).getTexture().render(g, (int)(750 + 600 * Math.random()), (int)(500 * Math.random()));
        }

    }

    @Override
    public void addComponents() {
        this.add(attackButton);
        this.add(castButton);
        this.add(skipButton);
        this.add(useItemButton);
        this.add(playerNameLabel);
        this.add(playerXPLabel);
    }

    @Override
    public void setComponentsBounds() {
        playerNameLabel.setBounds(1300, 0, 200, 20);
        playerXPLabel.setBounds(1300, 20, 200, 20);
        attackButton.setBounds(1300, 100, 200, 100);
        castButton.setBounds(1300, 200, 200, 100);
        useItemButton. setBounds(1300, 300, 200, 100);
        skipButton.setBounds(1300, 400, 200, 100);
    }

    @Override
    public void setFonts() {
        this.playerXPLabel.setFont(tahoma);
        this.playerNameLabel.setFont(tahoma);
    }


    public static void main(String[] args) {
        new WarRoomFrame().showFrame();
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == attackButton) {

        } else if (e.getSource() == castButton) {

        } else if (e.getSource() == skipButton) {

        }else if(e.getSource() == useItemButton)

    }
}
