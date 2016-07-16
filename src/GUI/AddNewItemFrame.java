package GUI;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Created by Future on 7/12/2016.
 */
public class AddNewItemFrame extends JFrame implements ActionListener, GameFrame {
    private JLabel nameLabel;
    private JLabel typeLabel;
    private JLabel worthLabel;
    private JLabel sizeLabel;
    private JLabel descriptionLabel;

    private JTextField nameField;
    private JTextField typeField;
    private JTextField worthField;
    private JTextField sizeField;
    private JTextField descriptionField;

    private JScrollPane abilityScrollPane;
    private JTable abilityTable;

    private JButton addButton;
    private JButton addNewAbilityButton;
    private JButton backButton;

    private ArrayList<JLabel> labels;
    private ArrayList<JTextField> fields;

    private Font tahoma = new Font("Tahoma", Font.PLAIN, 14);

    private Thread newItemThread = new Thread(new Runnable() {
        @Override
        public void run() {
            while (true) {
                if (typeField != null && typeField.getText() != null && typeField.getText().equals("SkillItem")) {
                    addNewAbilityButton.setName("AddNewSkillButton");
                    setButton(addNewAbilityButton);
                    add(addNewAbilityButton);
                    setAbilityTable();
                    repaint();
                    return;
                }
                else if (typeField != null && typeField.getText() != null && typeField.getText().equals("PerkItem")) {
                    addNewAbilityButton.setName("AddNewPerkButton");
                    setButton(addNewAbilityButton);
                    add(addNewAbilityButton);
                    setAbilityTable();
                    repaint();
                    return;
                }
            }
        }
    });

    public AddNewItemFrame(){
        this.addButton = new JButton();
        this.addNewAbilityButton = new JButton();
        this.backButton = new JButton();
        this.nameLabel = new JLabel("Name");
        this.typeLabel = new JLabel("ItemPackage Type");
        this.worthLabel = new JLabel("Worth");
        this.sizeLabel = new JLabel("Size");
        this.descriptionLabel = new JLabel("Description");
        this.abilityTable = new JTable();
        this.nameField = new JTextField();
        this.typeField = new JTextField();
        this.worthField = new JTextField();
        this.sizeField = new JTextField();
        this.descriptionField = new JTextField();
        //-------------------------------------------------------
        this.setLabelsAndFields();
        this.addActionListeners();
//        this.setAbilityTable();
        this.setButtons();
        this.showFrame();
        //-------------------------------------------------------
        this.newItemThread.start();
    }

    public void setAbilityTable(){
        DefaultTableModel model = this.setTableModel();
        this.abilityTable.setModel(model);
        this.abilityScrollPane = new JScrollPane(abilityTable);
        this.abilityScrollPane.setBounds(50, 175, 600, 200);
        this.add(abilityScrollPane);
    }

    public DefaultTableModel setTableModel(){
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Name");
        model.addColumn("Price");
        model.addColumn("Upgrades");
        model.addColumn("Description");
        return model;
    }

    public void setLabelsAndFields(){
        labels = new ArrayList<>();
        labels.add(nameLabel);
        labels.add(typeLabel);
        labels.add(worthLabel);
        labels.add(sizeLabel);
        labels.add(descriptionLabel);
        fields = new ArrayList<>();
        fields.add(nameField);
        fields.add(typeField);
        fields.add(worthField);
        fields.add(sizeField);
        fields.add(descriptionField);
    }

    @Override
    public void setBackgroundImage() {

    }

    @Override
    public void addActionListeners() {
        this.addButton.addActionListener(this);
        this.addNewAbilityButton.addActionListener(this);
        this.backButton.addActionListener(this);
    }

    @Override
    public void showFrame() {
        this.setSize(700, 700);
        this.setLayout(null);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setResizable(false);
        //------------------------------------------------------------------
        this.addComponents();
        this.setFonts();
        this.setComponentsBounds();
        //------------------------------------------------------------------
        this.setVisible(true);

    }

    @Override
    public void setButtons() {
        this.addButton.setName("AddButton");
//        this.addNewAbilityButton.setName("AddNewPerkItem");
        this.backButton.setName("BackButton");
        this.setButton(addButton);
//        this.setButton(addNewAbilityButton);
        this.setButton(backButton);
    }

    @Override
    public void addComponents() {
        this.add(addButton);
        this.add(backButton);
        for (int i = 0; i < this.labels.size(); i++) {
            this.add(labels.get(i));
            this.add(fields.get(i));
        }
    }

    @Override
    public void setComponentsBounds() {
        int labelSize = 200;
        int fieldSize = 250;
        for(int i = 0;i < this.labels.size();i++) {
            this.labels.get(i).setBounds(50, 50 + 25 * i, labelSize, 20);
            this.fields.get(i).setBounds(labelSize, 50 + 25 * i, fieldSize, 20);
        }
        this.addButton.setBounds(500, 500, 200, 100);
        this.addNewAbilityButton.setBounds(250, 400, 200, 100);
        this.backButton.setBounds(50, 500, 200, 100);
    }

    @Override
    public void setFonts() {
        this.addButton.setFont(tahoma);
        this.backButton.setFont(tahoma);
        for(int i = 0;i < this.labels.size();i++) {
            this.labels.get(i).setFont(tahoma);
            this.fields.get(i).setFont(tahoma);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addButton) {

        } else if (e.getSource() == backButton) {

        }
    }

    public static void main(String[] args) {
        new AddNewItemFrame();
    }
}
