package GUI;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Created by Future on 7/13/2016.
 */
public class AddNewEnemyFrame extends JFrame implements ActionListener,GameFrame{
    private JButton addButton;
    private JButton backButton;
    private JButton addSkillButton;

    private JTextField nameField;
    private JTextField versionField;
    private JTextField attackPowerField;
    private JTextField magicPointsField;
    private JTextField maximumEnergyPointField;
    private JTextField maximumHealthField;
    private JTextField healthRefillRateField;
    private JTextField magicRefillRateField;
    private JTextField attackPowerOnNonTargetedEnemyField;
    private JTextField attackPowerRatioDuringAttackField;
    private JTextField attackPowerRatioOnNonTargetedEnemyField;
    private JLabel nameLabel;
    private JLabel versionLabel;
    private JLabel attackPowerLabel;
    private JLabel magicPointsLabel;
    private JLabel maximumEnergyPointLabel;
    private JLabel maximumHealthLabel;
    private JLabel healthRefillRateLabel;
    private JLabel magicRefillRateLabel;
    private JLabel attackPowerOnNonTargetedEnemyLabel;
    private JLabel attackPowerRatioDuringAttackLabel;
    private JLabel attackPowerRatioOnNonTargetedEnemyLabel;

    private JTable skillsTable;
    private JScrollPane skillsScrollPane;

    private ArrayList<JLabel> labels = new ArrayList<>();
    private ArrayList<JTextField> textFields = new ArrayList<>();
    private Font tahoma = new Font("Tahoma", Font.PLAIN, 15);


    public AddNewEnemyFrame() {
        this.addButton = new JButton();
        this.backButton = new JButton();
        this.addSkillButton = new JButton();
        //------------------------------------------------
        this.attackPowerField = new JTextField();
        this.attackPowerLabel = new JLabel("Attack Power");
        this.attackPowerOnNonTargetedEnemyField = new JTextField();
        this.attackPowerOnNonTargetedEnemyLabel = new JLabel("AttackPowerOnNonTargetedEnemy");
        this.attackPowerRatioDuringAttackField = new JTextField();
        this.attackPowerRatioDuringAttackLabel = new JLabel("AttackPowerRatioDuringAttack");
        this.attackPowerRatioOnNonTargetedEnemyField = new JTextField();
        this.attackPowerRatioOnNonTargetedEnemyLabel = new JLabel("AttackPowerRatioOnNonTargetedEnemy");
        this.healthRefillRateField = new JTextField();
        this.healthRefillRateLabel = new JLabel("Health Refill Rate (0 to 1)");
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
        this.versionField = new JTextField();
        this.versionLabel = new JLabel("Version");
        //----------------------------------------------
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
        labels.add(versionLabel);
        labels.add(attackPowerLabel);
        labels.add(magicPointsLabel);
        labels.add(healthRefillRateLabel);
        labels.add(magicRefillRateLabel);
        labels.add(maximumHealthLabel);
        labels.add(maximumEnergyPointLabel);
        labels.add(attackPowerOnNonTargetedEnemyLabel);
        labels.add(attackPowerRatioDuringAttackLabel);
        labels.add(attackPowerRatioOnNonTargetedEnemyLabel);
        textFields.add(nameField);
        textFields.add(versionField);
        textFields.add(attackPowerField);
        textFields.add(magicPointsField);
        textFields.add(healthRefillRateField);
        textFields.add(magicRefillRateField);
        textFields.add(maximumHealthField);
        textFields.add(maximumEnergyPointField);
        textFields.add(attackPowerOnNonTargetedEnemyField);
        textFields.add(attackPowerRatioDuringAttackField);
        textFields.add(attackPowerRatioOnNonTargetedEnemyField);
    }

    public void setScrollPanes(){
        this.skillsScrollPane = new JScrollPane(skillsTable);
    }

    @Override
    public void setComponentsBounds() {
        int labelLength = 350;
        int textFieldLength = 150;
        for(int i = 0 ;i < this.labels.size();i++) {
            this.labels.get(i).setBounds(50, 50 + 25 * i, labelLength, 20);
        }
        for(int i = 0;i < this.textFields.size();i++) {
            this.textFields.get(i).setBounds(labelLength, 50 + 25 * i,textFieldLength,20);
        }
        this.skillsScrollPane.setBounds(600, 50, 500, 150);
        this.addButton.setBounds(1200, 500, 200, 100);
        this.backButton.setBounds(50, 500, 200, 100);
        this.addSkillButton.setBounds(1200, 100, 200, 100);
    }

    @Override
    public void setFonts(){
        for(int i = 0;i < this.labels.size();i++) {
            this.labels.get(i).setFont(tahoma);
        }
        for(int i = 0;i < this.textFields.size();i++) {
            this.textFields.get(i).setFont(tahoma);
        }
    }

    @Override
    public void addComponents() {
        for(int i = 0;i < this.labels.size();i++) {
            this.add(labels.get(i));
        }
        for(int i = 0;i < this.textFields.size();i++) {
            this.add(textFields.get(i));
        }
        this.add(skillsScrollPane);
        this.add(addSkillButton);
        this.add(addButton);
        this.add(backButton);
    }

    private void setTables() {
        this.setskillsTable();
    }


    public DefaultTableModel setTableModel(){
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Name");
        model.addColumn("Price");
        model.addColumn("Upgrades");
        model.addColumn("Description");
        return model;
    }

    private void setskillsTable() {
        DefaultTableModel model = this.setTableModel();
        this.skillsTable.setModel(model);
        this.setSkillsTableData();
        this.skillsScrollPane = new JScrollPane(this.skillsTable);
    }

    private void setSkillsTableData() {

    }


    private void setPerksTableData() {

    }

    @Override
    public void setBackgroundImage() {

    }

    @Override
    public void addActionListeners() {
        this.addButton.addActionListener(this);
        this.backButton.addActionListener(this);
        this.addSkillButton.addActionListener(this);
    }

    @Override
    public void showFrame() {
        this.setSize(1500, 800);
        this.setLayout(null);
        //-------------------------------
        this.addComponents();
        this.setComponentsBounds();
        //-------------------------------
        this.setVisible(true);
    }

    @Override
    public void setButtons() {
        //ADD IMAGES
        this.addButton.setName("AddButton");
        this.backButton.setName("BackButton");
        this.addSkillButton.setName("AddNewSkillButton");
        this.setButton(addButton);
        this.setButton(backButton);
        this.setButton(addSkillButton);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addButton) {

        } else if (e.getSource() == backButton) {

        } else if (e.getSource() == addSkillButton) {

        }
    }

    public static void main(String[] args) {
        new AddNewEnemyFrame();
    }

}
