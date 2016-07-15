package GUI;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Created by Future on 7/15/2016.
 */
public class AddNewHeroFrame extends JFrame, implements GameFrame,ActionListener{
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


}
