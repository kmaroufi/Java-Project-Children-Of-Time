package GUI;

import AbilityPackage.*;

import Engine.*;
import Engine.GameEngine;
import SoldierPackage.Hero;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Created by Future on 7/12/2016.
 */
public class SkillRoomFrame extends JFrame implements ActionListener {
    private JLabel welcomeFrame;
    private JButton acquireButton;
    private JButton showPlayerHerosButton;
    private JComboBox skillBox;
    private JLabel playerNameLabel;
    private JLabel playerMoneyLabel;
    private JLabel playerEPLabel;
    //    private JLabel skillNameLabel;
//    private JLabel skillEpLabel;
//    private JLabel skillMoneyLabel;
//    private JLabel skillMagicPointsLabel;
    private JTextArea skillInformationArea;
    private ArrayList<JLabel> playerHerosLabels;
    private JProgressBar loadAbilityProgressBar;
    private Font tahoma;

    private Thread skillRoomThread = new Thread(new Runnable() {
        @Override
        public void run() {
            while (true) {
                if (skillBox != null && skillBox.getItemCount() != 0) {
                    process();
                }
                try {
                    Thread.sleep(30);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    });

    public void addActionListeners() {
        this.acquireButton.addActionListener(this);
        this.showPlayerHerosButton.addActionListener(this);
    }


    public SkillRoomFrame() {
        this.welcomeFrame = new JLabel(new ImageIcon("./resources/images/WelcomeToTheSkillRoom.png"));
        this.acquireButton = new JButton("Acquire");
        this.showPlayerHerosButton = new JButton("Show My Heroes");
        this.skillBox = new JComboBox();
        PlayerPackage.Player player = new PlayerPackage.Player("ali", 20, 30);
        Engine.GameEngine.player = player;
        this.playerNameLabel = new JLabel("PlayerName: " + Engine.GameEngine.player.getName());
        this.playerEPLabel = new JLabel("Player's XP: " + String.valueOf(Engine.GameEngine.player.getXp()));
        this.playerMoneyLabel = new JLabel("Player's Money: " + String.valueOf(Engine.GameEngine.player.getMoney()));
        this.skillInformationArea = new JTextArea();
        this.skillInformationArea.setEditable(false);
        this.tahoma = new Font("Tahoma", Font.PLAIN, 15);
        //-------------------------------------------------------------------
        this.setComponentsFont();
        this.addSkills();
        //-------------------------------------------------------------------
        this.addActionListeners();
        this.skillRoomThread.start();
        this.showFrame();
    }

    public void setPlayerInformation(Player player) {
//         get player info from the GameEngine
//        this.playerCapacityLabel = player.getCapacity();
//        this.playerNameLabel.setText(player.getName());
//        this.playerMoneyLabel.setText(player.getMoney());
//        ArrayList<ItemPackage> playerItems = player.getItems();
//        for(int i = 0;i < this.playerItems.size();i++) {
//            this.playerItems.get(i).setFont(tahoma);
//            this.playerItems.get(i) = new JLabel((i + 1) + " - " + playerItems.get(i).getName());
//        }
    }

    public void addSkills() {
        //Get All Skills From GameEngine
        for (Skill skill : GameEngine.listOfSkills) {
            this.skillBox.addItem(skill.getName());
        }
    }

    public void process() {
        this.setSkillInformation();
    }

    public void setSkillInformation() {                              // Hey mipare Ok kon!
        String skillName = (String) this.skillBox.getSelectedItem();
        Skill skill = Skill.listOfSkills.get(skillName);
        if (skill.isAcquire()) {
            String skillEP = String.valueOf(skill.getRequiredEnergyPoint());
            String skillMagicPoints = String.valueOf(skill.getRequiredMagicPoint());
            String skillMoney = String.valueOf(skill.getNextGradeSubSkills().get(0).getCostOfUpgrade());
            String skillDescription = skill.getDescription();
            this.skillInformationArea.setText("Name : " + skillName + "\n" + "Price : " + skillMoney + "\n");
            this.skillInformationArea.append("MagicPoints : " + skillMagicPoints + "\n" + "Energy Points : " + skillEP + "\n");
            this.skillInformationArea.append("Description : " + skillDescription);
        } else {
            String skillMoney = String.valueOf(skill.getNextGradeSubSkills().get(0).getCostOfUpgrade());
            String skillDescription = skill.getDescription();
            this.skillInformationArea.setText("Name : " + skillName + "\n" + "Price : " + skillMoney + "\n");
            this.skillInformationArea.append("Description : " + skillDescription + "\n");
            this.skillInformationArea.append("is not acquired");
        }
    }


    public void setComponentsFont() {
        this.acquireButton.setFont(tahoma);
        this.skillBox.setFont(tahoma);
        this.showPlayerHerosButton.setFont(tahoma);
        this.playerNameLabel.setFont(tahoma);
        this.playerMoneyLabel.setFont(tahoma);
        this.playerEPLabel.setFont(tahoma);
        this.skillInformationArea.setFont(tahoma);
    }

    public void showFrame() {
        this.setSize(650, 600);
//        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.getContentPane().setBackground(Color.WHITE);
        this.add(welcomeFrame);
        this.add(acquireButton);
        this.add(showPlayerHerosButton);
        this.add(playerNameLabel);
        this.add(playerEPLabel);
        this.add(playerMoneyLabel);
        this.add(skillBox);
        this.add(skillInformationArea);
        this.setVisible(true);
        //--------------------------------------------
        Dimension welcomeFramePreferredSize = welcomeFrame.getPreferredSize();
        welcomeFrame.setBounds(10, 10, welcomeFramePreferredSize.width, welcomeFramePreferredSize.height);

        Dimension acquireButtonPreferredSize = acquireButton.getPreferredSize();
        acquireButton.setBounds(70, 450, acquireButtonPreferredSize.width + 50, acquireButtonPreferredSize.height);

        Dimension playerNamePreferredSize = playerNameLabel.getPreferredSize();
        playerNameLabel.setBounds(50, 100, playerNamePreferredSize.width, playerNamePreferredSize.height);

        Dimension playerMoneyPreferredSize = playerMoneyLabel.getPreferredSize();
        playerMoneyLabel.setBounds(50, 120, playerMoneyPreferredSize.width, playerMoneyPreferredSize.height);

        Dimension playerEPPreferredSize = playerEPLabel.getPreferredSize();
        playerEPLabel.setBounds(50, 160, playerEPPreferredSize.width, playerEPPreferredSize.height);

        Dimension playerHerosButtonPreferredSize = showPlayerHerosButton.getPreferredSize();
        showPlayerHerosButton.setBounds(400, 450, playerHerosButtonPreferredSize.width, playerHerosButtonPreferredSize.height);

        Dimension skillBoxPreferredSize = skillBox.getPreferredSize();
        skillBox.setBounds(70, 200, skillBoxPreferredSize.width + 400, skillBoxPreferredSize.height);

        Dimension skillInformationPreferredSize = skillInformationArea.getPreferredSize();
        skillInformationArea.setBounds(70, 240, skillInformationPreferredSize.width + 400, skillInformationPreferredSize.height + 100);

    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == acquireButton) {
            String skillName = (String) this.skillBox.getSelectedItem();
            Skill skill = Skill.listOfSkills.get(skillName);
            if (skill.getCurrentGrade() == skill.getNumberOfGrades()) {
                Display.displayMessage("This ability cannot be upgraded anymore");
            } else {
                SubSkill subSkill = skill.getNextGradeSubSkills().get(0);
                Hero hero = Hero.mapOfHeroes.get(skill.getOwnerName());
                if (subSkill.getCostOfUpgrade() > GameEngine.player.getXp()) {
                    Display.displayMessage("Your experience is insufficient");
                } else if (skill.isAcquire() == false) {
                    if (hero.upgradeAbility(Engine.GameEngine.player, skill.getName(), subSkill)) {
                        Engine.GameEngine.player.setXp(Engine.GameEngine.player.getXp() - subSkill.getCostOfUpgrade());
                        Display.displayMessage(skill.getName() + " acquired " + "successfully, your current experience is: " + Engine.GameEngine.player.getXp());
                    } else {
                        Display.displayMessage("Required abilities aren't acquired");
                    }
                } else {
                    if (hero.upgradeAbility(Engine.GameEngine.player, skill.getName(), subSkill)) {
                        Engine.GameEngine.player.setXp(Engine.GameEngine.player.getXp() - subSkill.getCostOfUpgrade());
                        Display.displayMessage(skill.getName() + " upgraded " + "successfully, your current experience is: " + Engine.GameEngine.player.getXp());
                    } else {
                        Display.displayMessage("Required abilities aren't acquired");
                    }
                }
            }
        }
    }


    public static void main(String[] args) {
        new SkillRoomFrame();
    }
}
