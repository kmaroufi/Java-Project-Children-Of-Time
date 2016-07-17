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
        this.addNewNecessaryAbilityButton = new JButton();
        this.costOfUpgradeField = new JTextField();
        this.subPerkNameLabel = new JLabel();
        this.costOfUpgradeLabel = new JLabel();
        this.upgradeDescriptionField = new JTextField();
        this.subPerkNameField = new JTextField();
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

    }

    @Override
    public void addComponents() {

    }

    @Override
    public void setComponentsBounds() {

    }

    @Override
    public void setFonts() {

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addNewNecessaryAbilityButton) {
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
