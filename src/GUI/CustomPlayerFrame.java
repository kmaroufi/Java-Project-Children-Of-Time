package GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Future on 7/12/2016.
 */
public class CustomPlayerFrame extends JFrame implements ActionListener,GameFrame {
    MenuFrame menuFrame;

    private JButton addNewItemButton;
    private JButton addNewHeroButton;
    private JButton addNewEnemyButton;
    private JButton addNewSkillButton;
    private JButton addNewPerkButton;
    private JButton addNewHeroClassButton;
    private JButton addNewMapButton;
    private JButton backButton;
    private JButton nextButton;

    public CustomPlayerFrame(MenuFrame menuFrame){
        this.menuFrame = menuFrame;
        this.addNewItemButton = new JButton();
        this.addNewHeroButton = new JButton();
        this.addNewEnemyButton = new JButton();
        this.addNewPerkButton = new JButton();
        this.addNewSkillButton = new JButton();
        this.addNewHeroClassButton = new JButton();
        this.addNewMapButton = new JButton();
        this.nextButton = new JButton();
        this.backButton = new JButton();
        //-------------------------------------------------------
        this.setBackgroundImage();
        this.addActionListeners();
        this.setButtons();
        this.showFrame();
    }

    @Override
    public void setBackgroundImage() {
        this.setContentPane(new JLabel(new ImageIcon("./resources/images/CustomPlayerBackground.png")));
    }

    @Override
    public void addActionListeners() {
        this.addNewEnemyButton.addActionListener(this);
        this.addNewPerkButton.addActionListener(this);
        this.addNewHeroButton.addActionListener(this);
        this.addNewHeroClassButton.addActionListener(this);
        this.addNewPerkButton.addActionListener(this);
        this.addNewSkillButton.addActionListener(this);
        this.addNewItemButton.addActionListener(this);
        this.nextButton.addActionListener(this);
        this.backButton.addActionListener(this);
    }

    @Override
    public void showFrame() {
        this.setTitle("Custom Game");
        this.setSize(700, 700);
        this.setLayout(null);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setResizable(false);
        //---------------------------------------------------
        this.addComponents();
        this.setComponentsBounds();
        this.setFonts();
        //----------------------------------------------------
        this.setVisible(true);
    }

    @Override
    public void setButtons() {
        this.addNewItemButton.setName("AddNewItemButton");
        this.addNewPerkButton.setName("AddNewPerkButton");
        this.addNewSkillButton.setName("AddNewSkillButton");
        this.addNewHeroClassButton.setName("AddNewHeroClassButton");
        this.addNewEnemyButton.setName("AddNewEnemyButton");
        this.addNewHeroButton.setName("AddNewHeroButton");
        this.addNewMapButton.setName("AddNewMapButton");
        this.nextButton.setName("NextButton");
        this.backButton.setName("BackButton");
        this.setButton(addNewItemButton);
        this.setButton(addNewPerkButton);
        this.setButton(addNewSkillButton);
        this.setButton(addNewHeroClassButton);
        this.setButton(addNewEnemyButton);
        this.setButton(addNewHeroButton);
        this.setButton(addNewMapButton);
        this.setButton(nextButton);
        this.setButton(backButton);
    }

    @Override
    public void addComponents() {
        this.add(addNewItemButton);
        this.add(addNewHeroButton);
        this.add(addNewEnemyButton);
        this.add(addNewPerkButton);
        this.add(addNewSkillButton);
        this.add(addNewHeroClassButton);
        this.add(addNewMapButton);
        this.add(nextButton);
        this.add(backButton);
    }

    @Override
    public void setComponentsBounds() {
        this.addNewItemButton.setBounds(100, 100, 200, 100);
        this.addNewMapButton.setBounds(100, 200, 200, 100);
        this.addNewHeroClassButton.setBounds(100, 300, 200, 100);
        this.addNewPerkButton.setBounds(400, 100, 200, 100);
        this.addNewSkillButton.setBounds(400, 200, 200, 100);
        this.addNewHeroButton.setBounds(400, 300, 200, 100);
        this.addNewEnemyButton.setBounds(250, 400, 200, 100);
        this.nextButton.setBounds(400, 500, 200, 100);
        this.backButton.setBounds(100, 500, 200, 100);
    }

    @Override
    public void setFonts() {

    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addNewMapButton) {

        } else if (e.getSource() == addNewHeroButton) {
            AddNewHeroFrame addNewHeroFrame = new AddNewHeroFrame();
        } else if (e.getSource() == addNewPerkButton) {
            AddNewPerkFrame addNewPerkFrame = new AddNewPerkFrame();
        } else if (e.getSource() == addNewHeroClassButton) {
            AddNewHeroClassFrame addNewHeroClassFrame = new AddNewHeroClassFrame();
        } else if (e.getSource() == addNewEnemyButton) {
            AddNewEnemyFrame addNewEnemyFrame = new AddNewEnemyFrame();
        } else if (e.getSource() == addNewItemButton) {
            AddNewItemFrame addNewItemFrame = new AddNewItemFrame();
        } else if (e.getSource() == nextButton) {

        } else if (e.getSource() == backButton) {
            this.setVisible(false);
            this.menuFrame.setVisible(true);
        } else if (e.getSource() == addNewSkillButton) {

        }
    }
}
