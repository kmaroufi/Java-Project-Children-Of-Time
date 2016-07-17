package GUI;

import Engine.*;
import Engine.GameEngine;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Future on 7/16/2016.
 */
public class AddNewNecessaryAbilityFrame extends JFrame implements ActionListener,GameFrame{
    private JButton addButton;
    private AddNewSubPerkFrame newSubPerkFrame;
    private JComboBox nameOfNeccessaryAbilityBox;
    private JLabel gradeOfNeccessaryAbilityLabel;
    private JTextField gradeOfNeccessaryAbilityField;
    //----------------------------------------------------------
    private String nameOfNecessaryAbility;
    private Integer gradeOfNecessaryAbility;

    public AddNewNecessaryAbilityFrame(AddNewSubPerkFrame newSubPerkFrame) throws HeadlessException {
        this.addButton = new JButton();
        this.nameOfNeccessaryAbilityBox = new JComboBox();
        this.gradeOfNeccessaryAbilityField = new JTextField();
        this.gradeOfNeccessaryAbilityLabel = new JLabel("Grade Of Necessary Ability");
        this.newSubPerkFrame = newSubPerkFrame;
        this.setBox();
        this.setBackgroundImage();
        this.setButtons();
        this.addActionListeners();
    }

    public void setBox() {
        for(int i = 0;i < GameEngine.listOfPerks.size();i++) {
            this.nameOfNeccessaryAbilityBox.addItem(GameEngine.listOfPerks.get(i).getName());
        }
        for(int i = 0;i < GameEngine.listOfSkills.size();i++) {
            this.nameOfNeccessaryAbilityBox.addItem(GameEngine.listOfPerks.get(i).getName());
        }
    }

    @Override
    public void setBackgroundImage() {

    }

    @Override
    public void addActionListeners() {
        this.addButton.addActionListener(this);
    }

    @Override
    public void showFrame() {
        this.setSize(700, 700);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setResizable(false);
        this.addComponents();
        this.setComponentsBounds();
        this.setFonts();
        this.setVisible(true);
    }

    @Override
    public void setButtons() {
        this.addButton.setName("AddButton");
        this.setButton(addButton);
    }

    @Override
    public void addComponents() {
        this.add(addButton);
        this.add(nameOfNeccessaryAbilityBox);
        this.add(gradeOfNeccessaryAbilityLabel);
        this.add(gradeOfNeccessaryAbilityField);
    }

    @Override
    public void setComponentsBounds() {
        this.addButton.setBounds(500, 500, 200, 100);
        this.nameOfNeccessaryAbilityBox.setBounds(100, 300, 500, 50);
        this.gradeOfNeccessaryAbilityLabel.setBounds(100, 100, 200, 50);
        this.gradeOfNeccessaryAbilityField.setBounds(300, 100, 200, 50);
    }

    @Override
    public void setFonts() {

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addButton) {
            this.addData();
        }
    }

    private void addData() {
        this.nameOfNecessaryAbility = (String) this.nameOfNeccessaryAbilityBox.getSelectedItem();
        this.gradeOfNecessaryAbility = Integer.parseInt(this.gradeOfNeccessaryAbilityField.getText());
        this.newSubPerkFrame.getNameNecessaryAbilities().add(nameOfNecessaryAbility);
        this.newSubPerkFrame.getGradeOfNecessaryAbilities().put(nameOfNecessaryAbility, gradeOfNecessaryAbility);
    }
}
