package GUI;

import AbilityPackage.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Map;

/**
 * Created by Future on 7/16/2016.
 */
public class AddNewSubPerkFrame extends JFrame implements ActionListener, GameFrame {
    private JButton addButton;
    private JButton addNewNecessaryAbilityButton;
    private JLabel costOfUpgradeLabel;
    private JLabel upgradeDescriptionLabel;
    private JLabel subPerkNameLabel;
    private JTextField costOfUpgradeField;
    private JTextField upgradeDescriptionField;
    private JTextField subPerkNameField;
    //---------------------------------------------------------------------
    private boolean isGlobal = false;
    private AddNewPerkFrame newPerkFrame;
    private ArrayList<SubPerkComponent<?>> subPerkComponents;
    private Map<String, Integer> gradeOfNecessaryAbilities;
    private ArrayList<String> nameNecessaryAbilities;
    public AddNewSubPerkFrame(AddNewPerkFrame newPerkFrame) throws HeadlessException {
        this.newPerkFrame = newPerkFrame;
        this.addButton = new JButton();
        this.addNewNecessaryAbilityButton = new JButton("Add Necessary Ability");
        this.costOfUpgradeField = new JTextField();
        this.subPerkNameLabel = new JLabel("SubPerk Name");
        this.costOfUpgradeLabel = new JLabel("Cost Of Upgrade");
        this.upgradeDescriptionField = new JTextField();
        this.subPerkNameField = new JTextField();
        this.upgradeDescriptionLabel = new JLabel("Upgrade Desctiption");
        this.addActionListeners();
        this.setButtons();
    }

    @Override
    public void setBackgroundImage() {

    }

    @Override
    public void addActionListeners() {
        this.addButton.addActionListener(this);
        this.addNewNecessaryAbilityButton.addActionListener(this);
    }

    @Override
    public void showFrame() {
        this.setSize(700, 700);
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
        this.add(addNewNecessaryAbilityButton);
        this.add(costOfUpgradeField);
        this.add(costOfUpgradeLabel);
        this.add(upgradeDescriptionField);
        this.add(upgradeDescriptionLabel);
        this.add(subPerkNameField);
        this.add(subPerkNameLabel);
    }

    @Override
    public void setComponentsBounds() {
        this.addButton.setBounds(500, 500, 200, 100);
        this.addNewNecessaryAbilityButton.setBounds(100, 500, 200, 100);
        this.costOfUpgradeLabel.setBounds(100, 200, 200, 20);
        this.costOfUpgradeField.setBounds(300, 200, 200, 20);
        this.subPerkNameLabel.setBounds(100, 100 , 200, 20);
        this.subPerkNameField.setBounds(300, 100, 200, 20);
        this.upgradeDescriptionLabel.setBounds(100, 300, 200, 20);
        this.upgradeDescriptionField.setBounds(300, 300, 200, 20);
    }

    @Override
    public void setFonts() {

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addNewNecessaryAbilityButton) {
            System.out.println("HEH");
            new AddNewNecessaryAbilityFrame(this).showFrame();
        } else if (e.getSource() == addButton) {
            this.addData();
            dispose();
        }
    }

    private void addData() {
        int costOfUpgrade = Integer.parseInt(costOfUpgradeField.getText());
        String upgradeDescription = upgradeDescriptionField.getText();
        SubAbilityHandler subAbilityHandler = new SubAbilityHandler(false, costOfUpgrade, nameNecessaryAbilities, gradeOfNecessaryAbilities, upgradeDescription);
        String perkName = subPerkNameField.getName();
        SubPerk subPerk = new SubPerk(subAbilityHandler, subPerkComponents, perkName);
        for (SubPerkComponent subPerkComponent : this.subPerkComponents) {
            subPerkComponent.setRelatedSubPerk(subPerk);
        }
        this.newPerkFrame.getSubPerks().add(subPerk);
    }


    //------------------------------------------------------------------------------ Getter And Setter

    public ArrayList<SubPerkComponent<?>> getSubPerkComponents() {
        return subPerkComponents;
    }

    public void setSubPerkComponents(ArrayList<SubPerkComponent<?>> subPerkComponents) {
        this.subPerkComponents = subPerkComponents;
    }

    public Map<String, Integer> getGradeOfNecessaryAbilities() {
        return gradeOfNecessaryAbilities;
    }

    public void setGradeOfNecessaryAbilities(Map<String, Integer> gradeOfNecessaryAbilities) {
        this.gradeOfNecessaryAbilities = gradeOfNecessaryAbilities;
    }

    public ArrayList<String> getNameNecessaryAbilities() {
        return nameNecessaryAbilities;
    }

    public void setNameNecessaryAbilities(ArrayList<String> nameNecessaryAbilities) {
        this.nameNecessaryAbilities = nameNecessaryAbilities;
    }
}
