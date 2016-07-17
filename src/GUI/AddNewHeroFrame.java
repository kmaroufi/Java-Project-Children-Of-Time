package GUI;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Future on 7/13/2016.
 */
public class AddNewHeroFrame extends JFrame implements ActionListener, GameFrame {
    private JButton addButton;
    private JButton backButton;
    private JButton addHeroClassButton;
    private JButton addSkillButton;
    private JButton addPerkButton;

    private JLabel nameLabel;
    private JTextField nameField;

    private JTable heroClassTable;
    private JTable skillsTable;
    private JTable perksTable;
    private JScrollPane heroClassScrollPane;
    private JScrollPane skillsScrollPane;
    private JScrollPane perksScrollPane;

    private Font tahoma = new Font("Tahoma", Font.PLAIN, 14);

    public AddNewHeroFrame() {
        this.addButton = new JButton();
        this.backButton = new JButton();
        this.addHeroClassButton = new JButton();
        this.addSkillButton = new JButton();
        this.addPerkButton = new JButton();
        this.nameLabel = new JLabel("Name");
        this.nameField = new JTextField("Your Hero's Name");
        this.heroClassTable = new JTable();
        this.skillsTable = new JTable();
        this.perksTable = new JTable();
        //--------------------------------------------------
        this.setButtons();
        this.setTables();
        this.showFrame();
    }

    private void setTables() {
        String[] columns = {"Select", "Name", "Health", "AttackPower", "MagicPoint", "Energy Point", "Skills", "Perks", "Description"};
        this.heroClassTable.setModel(this.setColumns(columns));
        String[] skillColumns = {"Select", "Name", "Upgrades", "Description"};
        this.skillsTable.setModel(this.setColumns(skillColumns));
        this.perksTable.setModel(this.setColumns(skillColumns));

    }

    private void addTablesData(){
        // ADD TABLES DATA HERE
    }

    private void setScrollPanes(){
        this.heroClassScrollPane = new JScrollPane(heroClassTable);
        this.skillsScrollPane = new JScrollPane(skillsTable);
        this.perksScrollPane = new JScrollPane(perksTable);
    }

    private DefaultTableModel setColumns(String[] columns) {
        DefaultTableModel model = new DefaultTableModel(){
            public Class<?> getColumnClass(int column) {
                switch (column) {
                    case 0:
                        return Boolean.class;
                    default:
                        return String.class;
                }
            }
        };
        for(int i = 0;i < columns.length;i++) {
            model.addColumn(columns[i]);
        }
        return model;
    }

    @Override
    public void setBackgroundImage() {

    }


    @Override
    public void addActionListeners() {
        this.addButton.addActionListener(this);
        this.backButton.addActionListener(this);
        this.addSkillButton.addActionListener(this);
        this.addPerkButton.addActionListener(this);
        this.addHeroClassButton.addActionListener(this);
    }

    @Override
    public void showFrame() {
        this.setSize(1500, 800);
        this.setLayout(null);
//        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        //--------------------------------------------------------------------
        this.setScrollPanes();
        this.addComponents();
        this.setComponentsBounds();
        this.setFonts();
        //--------------------------------------------------------------------
        this.setVisible(true);
    }

    @Override
    public void setButtons() {
        this.addButton.setName("AddButton");
        this.backButton.setName("BackButton");
        this.addSkillButton.setName("AddSkillButton");
        this.addPerkButton.setName("AddPerkButton");
        this.addHeroClassButton.setName("AddHeroClassButton");
        this.setButton(addButton);
        this.setButton(backButton);
        this.setButton(addSkillButton);
        this.setButton(addPerkButton);
        this.setButton(addHeroClassButton);
    }

    @Override
    public void addComponents() {
        this.add(nameLabel);
        this.add(nameField);
        this.add(addButton);
        this.add(backButton);
        this.add(addSkillButton);
        this.add(addPerkButton);
        this.add(addHeroClassButton);
        this.add(skillsScrollPane);
        this.add(perksScrollPane);
        this.add(heroClassScrollPane);
    }

    @Override
    public void setComponentsBounds() {
        this.nameLabel.setBounds(50, 100, 100, 20);
        this.nameField.setBounds(150, 100, 150, 20);
        this.heroClassScrollPane.setBounds(50, 150, 700, 200);
        this.skillsScrollPane.setBounds(50, 400, 700, 200);
        this.perksScrollPane.setBounds(800, 150, 650, 200);
        this.addButton.setBounds(1000, 480, 200, 100);
        this.backButton.setBounds(900, 580, 200, 100);
        this.addSkillButton.setBounds(800, 380, 200, 100);
        this.addPerkButton.setBounds(800, 480, 200, 100);
        this.addHeroClassButton.setBounds(1000, 380, 200 ,100);
    }

    @Override
    public void setFonts() {
        this.nameLabel.setFont(tahoma);
        this.nameField.setFont(tahoma);
        this.heroClassScrollPane.setFont(tahoma);
        this.skillsScrollPane.setFont(tahoma);
        this.perksScrollPane.setFont(tahoma);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addButton) {

        } else if (e.getSource() == backButton) {

        } else if (e.getSource() == addSkillButton) {

        } else if (e.getSource() == addPerkButton) {

        } else if (e.getSource() == addHeroClassButton) {

        }
    }

    public static void main(String[] args) {
        new AddNewHeroFrame();
    }
}