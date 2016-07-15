package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Created by Future on 7/15/2016.
 */
public class AddNewHeroClassFrame extends JFrame implements ActionListener,GameFrame{
    private JButton addButton;
    private JButton backButton;
    private JButton addPerkButton;
    private JButton addSkillButton;

    private JTextField nameField;
    private JTextField attackPowerField;
    private JTextField magicPointsField;
    private JTextField maximumEnergyPointField;
    private JTextField maximumHealthField;
    private JTextField inventorySizeField;
    private JTextField healthRefillRateField;
    private JTextField magicRefillRateField;
    private JTextField criticalHitChanceField;
    private JTextField criticalHitDamageField;
    private JTextField attackPowerOnNonTargetedEnemyField;
    private JTextField attackPowerRatioDuringAttackField;
    private JTextField attackPowerRatioOnNonTargetedEnemyField;
    private JLabel nameLabel;
    private JLabel attackPowerLabel;
    private JLabel magicPointsLabel;
    private JLabel maximumEnergyPointLabel;
    private JLabel maximumHealthLabel;
    private JLabel inventorySizeLabel;
    private JLabel healthRefillRateLabel;
    private JLabel magicRefillRateLabel;
    private JLabel criticalHitChanceLabel;
    private JLabel criticalHitDamageLabel;
    private JLabel attackPowerOnNonTargetedEnemyLabel;
    private JLabel attackPowerRatioDuringAttackLabel;
    private JLabel attackPowerRatioOnNonTargetedEnemyLabel;

    private JTable skillsTable;
    private JTable perksTable;
    private JScrollPane skillsScrollPane;
    private JScrollPane perksScrollPane;

    private ArrayList<JLabel> labels = new ArrayList<>();
    private ArrayList<JTextField> textFields = new ArrayList<>();
    private Font tahoma = new Font("Tahoma", Font.PLAIN, 15);


    public AddNewHeroClassFrame() {
        this.addButton = new JButton();
        this.backButton = new JButton();
        this.addSkillButton = new JButton();
        this.addPerkButton = new JButton();
        //------------------------------------------------
        this.attackPowerField = new JTextField();
        this.attackPowerLabel = new JLabel("Attack Power");
        this.attackPowerOnNonTargetedEnemyField = new JTextField();
        this.attackPowerOnNonTargetedEnemyLabel = new JLabel("AttackPowerOnNonTargetedEnemy");
        this.attackPowerRatioDuringAttackField = new JTextField();
        this.attackPowerRatioDuringAttackLabel = new JLabel("AttackPowerRatioDuringAttack");
        this.attackPowerRatioOnNonTargetedEnemyField = new JTextField();
        this.attackPowerRatioOnNonTargetedEnemyLabel = new JLabel("AttackPowerRatioOnNonTargetedEnemy");
        this.criticalHitChanceField = new JTextField();
        this.criticalHitChanceLabel = new JLabel("CriticalHitChance");
        this.criticalHitDamageField = new JTextField();
        this.criticalHitDamageLabel = new JLabel("CriticalHitDamage");
        this.healthRefillRateField = new JTextField();
        this.healthRefillRateLabel = new JLabel("Health Refill Rate (0 to 1)");
        this.inventorySizeField = new JTextField();
        this.inventorySizeLabel = new JLabel("Inventory Size");
        this.magicPointsField = new JTextField();
        this.magicPointsLabel = new JLabel("Magic Points");
        this.magicRefillRateField = new JTextField();
        this.magicRefillRateLabel = new JLabel("Magic Refill Rate (0 to 1)");
        this.maximumEnergyPointField = new JTextField();
        this.maximumEnergyPointLabel = new JLabel("Maximum Energy Points");
        this.maximumHealthField = new JTextField();
        this.maximumHealthLabel = new JLabel("Maximum Health");
        this.nameField = new JTextField();
        this.nameLabel = new JLabel("Name");
        //----------------------------------------------
        this.perksTable = new JTable();
        this.skillsTable = new JTable();
        //----------------------------------------------
        this.setButtons();
        this.setLabelsAndFields();
        this.setFonts();
        this.setTables();
        this.setScrollPanes();
        this.showFrame();
    }

    public void setLabelsAndFields() {
        labels.add(nameLabel);
        labels.add(attackPowerLabel);
        labels.add(magicPointsLabel);
        labels.add(healthRefillRateLabel);
        labels.add(magicRefillRateLabel);
        labels.add(maximumHealthLabel);
        labels.add(maximumEnergyPointLabel);
        labels.add(inventorySizeLabel);
        labels.add(criticalHitChanceLabel);
        labels.add(criticalHitDamageLabel);
        labels.add(attackPowerOnNonTargetedEnemyLabel);
        labels.add(attackPowerRatioDuringAttackLabel);
        labels.add(attackPowerRatioOnNonTargetedEnemyLabel);
        textFields.add(nameField);
        textFields.add(attackPowerField);
        textFields.add(magicPointsField);
        textFields.add(healthRefillRateField);
        textFields.add(magicRefillRateField);
        textFields.add(maximumHealthField);
        textFields.add(maximumEnergyPointField);
        textFields.add(inventorySizeField);
        textFields.add(criticalHitChanceField);
        textFields.add(criticalHitDamageField);
        textFields.add(attackPowerOnNonTargetedEnemyField);
        textFields.add(attackPowerRatioDuringAttackField);
        textFields.add(attackPowerRatioOnNonTargetedEnemyField);
    }

    public void setScrollPanes(){
        this.skillsScrollPane = new JScrollPane(skillsTable);
        this.perksScrollPane = new JScrollPane(perksTable);
    }

}
