package GUI;

import AbilityPackage.AbilityHandler;
import AbilityPackage.Perk;
import AbilityPackage.SubPerk;
import Engine.*;
import Engine.GameEngine;
import Structure.Condition;
import Structure.Tree;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Created by Future on 7/13/2016.
 */
public class AddNewPerkFrame extends JFrame implements ActionListener,GameFrame{

    //Getting An Perk
    private JButton addButton;
    private JButton addNewSubPerksButton;
    private JLabel perkNameLabel;
    private JLabel numberOfGradesLabel;
    private JLabel descriptionLabel;
//        //Getting A SubPerk
//        //set isGlobal = false;
//        private JLabel costOfUpgradeLabel;
//            // Getting All Of Necessary Abilities
//            private JComboBox nameOfNeccessaryAbilitiesLabel;      // Don't Think
//            private JLabel gradeOfNeccessaryAbilitiesLabel;
//        private JLabel upgradeDescriptionLabel;
//        private JLabel subPerkNameLabel;
//            //Getting A SubPerk Component
//            private JComboBox classOfEffectedObjectsBox;      // Hero or Enemy
//                private JComboBox classOfEffectingObjectsBox;
//                //3 getting Property
//                private JComboBox nameOfEffectedFieldBox;
//                private JCheckBox isPermenantly;
//                private JLabel constantPropertyLabel;
//                        //Getting Effecting Variable
//                        private JComboBox gettingVariableNameBox;
//                        private JLabel gettingVarableRatioLabel;
//                        //Getting Property's Condition
//                        private JComboBox fieldOfClassNameBox2;
//                        private JRadioButton greaterThan2,smallerThan2,equals2;
//                        private JLabel constantNumberLabel2;
//                    //SelectingObjectDetails
//                    private JLabel isAllRelatedObjectsInvolvedLabel;
//                    private JLabel isUserSelectedLabel;
//                    private JLabel isDependsOnConditionLabel;
//                        //Getting Selecting Object Detail Condition
//                        private JComboBox fieldOfClassNameBox3;
//                        private JRadioButton greaterThan3,smallerThan3,equals3;
//                        private JLabel constantNumberLabel3;
//                    private JLabel numberOfSelectedObjectsByConditionsLabel;
//                    private JCheckBox isRandomObjectsSelectingLabel;
//                    private JLabel numberOfRandomSelectedObjectsLabel;
//                    private JCheckBox isSelectedObjectsDependsOnPlayerLabel;
//                    private JLabel numberOfSelectedObjectsByPlayerLabel;
//                    private JCheckBox isRelatedToAttackDefendLabel;
//                    private JCheckBox isHeroSelectedLabel;
//                    private JCheckBox isEnemySelectedLabel;
//                //4 getting Condition's Property
//                private JComboBox fieldOfClassNameBox;
//                private JRadioButton greaterThan,smallerThan,equals;
//                private JLabel constantNumberLabel;
//            //SelectingObjectDetails
//            private JLabel isAllRelatedObjectsInvolvedLabel2;
//            private JLabel isUserSelectedLabel2;
//            private JLabel isDependsOnConditionLabel2;
//            //Getting Selecting Object Detail Condition
//            private JComboBox fieldOfClassNameBox4;
//            private JRadioButton greaterThan4,smallerThan4,equals4;
//            private JLabel constantNumberLabel4;
//            private JLabel numberOfSelectedObjectsByConditionsLabel2;
//            private JCheckBox isRandomObjectsSelectingLabel2;
//            private JLabel numberOfRandomSelectedObjectsLabel2;
//            private JCheckBox isSelectedObjectsDependsOnPlayerLabel2;
//            private JLabel numberOfSelectedObjectsByPlayerLabel2;
//            private JCheckBox isRelatedToAttackDefendLabel2;
//            private JCheckBox isHeroSelectedLabel2;
//            private JCheckBox isEnemySelectedLabel2;
    //finally!
    private JComboBox timeOfCheckBox;
    //---------------------------------------------------------------------------------------------------------
    private ArrayList<SubPerk> subPerks;
    //---------------------------------------------------------------------------------------------------------         // KAMYAR!
    private JTextField nameField;                                                                                       // I'll Find You
    private JTextField numberOfGradesField;                                                                             // And I'll Kill You!
    private JTextField descriptionField;
    public AddNewPerkFrame(){
        this.addButton = new JButton();
        this.addNewSubPerksButton = new JButton("Add New SubPerk");
        this.timeOfCheckBox = new JComboBox();
        this.subPerks = new ArrayList<>();
        this.nameField = new JTextField();
        this.perkNameLabel = new JLabel("Perk Name");
        this.numberOfGradesField = new JTextField();
        this.numberOfGradesLabel = new JLabel("Number Of Grades");
        this.descriptionLabel = new JLabel("Description");
        this.descriptionField = new JTextField();
        this.setBackgroundImage();

    }


    @Override
    public void setBackgroundImage() {

    }

    @Override
    public void addActionListeners() {
        this.addButton.addActionListener(this);
        this.addNewSubPerksButton.addActionListener(this);
    }

    @Override
    public void showFrame() {
        this.setSize(700, 700);
        this.setLayout(null);
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
        this.add(addNewSubPerksButton);
        this.add(timeOfCheckBox);
        this.add(perkNameLabel);
        this.add(nameField);
        this.add(descriptionField);
        this.add(descriptionLabel);
        this.add(numberOfGradesLabel);
        this.add(numberOfGradesField);
    }

    @Override
    public void setComponentsBounds() {
        this.addButton.setBounds(500, 500, 200, 100);
        this.addNewSubPerksButton.setBounds(100, 500, 200, 100);
        this.timeOfCheckBox.setBounds(50, 250, 400, 50);
        this.perkNameLabel.setBounds(50, 50, 100, 20);
        this.nameField.setBounds(200, 50, 100, 20);
        this.descriptionLabel.setBounds(50, 100, 150, 20);
        this.descriptionField.setBounds(200, 100, 150, 20);
        this.numberOfGradesLabel.setBounds(50, 150, 150, 20);
        this.numberOfGradesField.setBounds(200, 150, 150, 20);
    }

    @Override
    public void setFonts() {

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addButton) {
            this.addData();
            dispose();
        } else if (e.getSource() == addNewSubPerksButton) {
            new AddNewSubPerkFrame(this).showFrame();
        }
    }

    private void addData() {
        String perkName = this.nameField.getText();
        String perkDescription = this.descriptionField.getText();
        int numberOfGrades = Integer.parseInt(this.numberOfGradesField.getText());
        AbilityHandler abilityHandler = new AbilityHandler(perkName, null,numberOfGrades, perkDescription);
        Perk.TimeOfCheck timeOfCheck = Perk.TimeOfCheck.eachActivity;
        if (timeOfCheckBox.getSelectedItem().equals("duringAttack")) {
            timeOfCheck = Perk.TimeOfCheck.duringAttack;
        } else if (timeOfCheckBox.getSelectedItem().equals("duringDefend")) {
            timeOfCheck = Perk.TimeOfCheck.duringDefend;
        } else if (timeOfCheckBox.getSelectedItem().equals("duringAttackDefend")) {
            timeOfCheck = Perk.TimeOfCheck.duringAttackDefend;
        } else if (timeOfCheckBox.getSelectedItem().equals("eachActivity")) {
            timeOfCheck = Perk.TimeOfCheck.eachActivity;
        }
        Tree tree = new Tree();
        Tree.Node node = tree.getRoot();
        for (SubPerk subPerk : subPerks) {
            node.addChild(subPerk, new Condition());
            node = (Tree.Node) node.getChildren().get(0);
        }
        Perk perk = new Perk(abilityHandler, timeOfCheck, tree);
        GameEngine.addNewPerk(perk);
    }

    public static void main(String[] args) {
        new AddNewPerkFrame().showFrame();
    }

    //--------------------------------------------------------------------------Getter And Setters


    public ArrayList<SubPerk> getSubPerks() {
        return subPerks;
    }

    public void setSubPerks(ArrayList<SubPerk> subPerks) {
        this.subPerks = subPerks;
    }
}
