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
}
