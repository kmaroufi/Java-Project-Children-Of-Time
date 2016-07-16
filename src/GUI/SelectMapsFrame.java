package GUI;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Future on 7/13/2016.
 */
public class SelectMapsFrame extends JFrame implements ActionListener,GameFrame{
    private JButton OKButton;
    private JButton backButton;
    private JLabel titleLabel;
    private JTable table;
    private DefaultTableModel tableModel = new DefaultTableModel();
    private JScrollPane heroScrollPane;
    private Font tahoma = new Font("Tahoma", Font.PLAIN, 14);

    public SelectMapsFrame(){
        String[] columnNames = {"Select", "Name", "Class", "Health", "Magic Point", "Attack Power", "Abilities", "Description"};
        this.OKButton = new JButton();
        this.backButton = new JButton();
        this.titleLabel = new JLabel(new ImageIcon("./resources/images/PleaseSelectGameMap.png"));
        this.table = new JTable();
        this.tableModel = new DefaultTableModel(){
            public Class<?> getColumnClass(int column) {
                switch (column) {
                    case 0:
                        return Boolean.class;
                    default:
                        return String.class;
                }
            }
        };
        //----------------------------------------------------
        this.table.setModel(tableModel);
        addActionListeners();
        setDataColumns();
        //----------------------------------------------------
        this.setDataTable();
        this.setButtons();
        this.setScrollPane();
        this.showFrame();
    }

    private void setScrollPane() {
        this.heroScrollPane = new JScrollPane(table);
        heroScrollPane.setBounds(100, 200, 1300, 200);
    }


    public void setButtons(){
        this.OKButton.setName("OKButton");
        this.backButton.setName("BackButton");
        this.setButton(OKButton);
        this.setButton(backButton);
    }

    @Override
    public void addComponents() {
        this.add(titleLabel);
        this.add(heroScrollPane);
        this.add(OKButton);
        this.add(backButton);
    }

    @Override
    public void setComponentsBounds() {
        this.OKButton.setBounds(500, 600, OKButton.getWidth(), OKButton.getHeight());
        this.backButton.setBounds(50, 600, backButton.getWidth(), backButton.getHeight());
        this.titleLabel.setBounds(400, 50, 700, 100);
    }

    @Override
    public void setFonts() {
        this.heroScrollPane.setFont(tahoma);
    }


    @Override
    public void setBackgroundImage() {

    }

    public void addActionListeners(){
        this.OKButton.addActionListener(this);
        this.backButton.addActionListener(this);
    }

    public void showFrame() {
        this.setSize(1500, 700);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setLayout(null);
        //--------------------------------------------------
        this.addComponents();
        this.setComponentsBounds();
        this.setFonts();
        //--------------------------------------------------
        this.setVisible(true);

    }

    public void setDataColumns(){
        this.tableModel.addColumn("Select");
        this.tableModel.addColumn("Name");
        this.tableModel.addColumn("Class");
        this.tableModel.addColumn("Health");
        this.tableModel.addColumn("Magic Point");
        this.tableModel.addColumn("Attack Power");
        this.tableModel.addColumn("Abilities");
        this.tableModel.addColumn("Description");
        //-----------------------------------------------
        JScrollPane scrollPane = new JScrollPane(table);
        this.table.setFillsViewportHeight(true);
    }

    private void setDataTable() {
        //Adding Hero Data in table
//        this.tableModel.addRow(new Object[0]);
//        this.tableModel.setValueAt("Mohsen", 0, 1);
        for (int i = 0; i <= 11; i++) {
            tableModel.addRow(new Object[9]);
            tableModel.setValueAt(false, i, 0);
            tableModel.setValueAt("Row"+ (i+1), i, 1);
            tableModel.setValueAt("Data Col 2", i, 2);
            tableModel.setValueAt("Data Col 3", i, 3);
            tableModel.setValueAt("Data Col 4", i, 4);
            tableModel.setValueAt("Data Col 5", i, 5);
            tableModel.setValueAt("Data Col 6", i, 6);
            tableModel.setValueAt("Data Col 7", i, 7);
        }
    }

    private void setPlayerHeroes(){
        for(int i = 0;i < this.table.getRowCount();i++) {
            boolean isChecked = (Boolean) table.getValueAt(i, 0);
            if (isChecked) {
                //MAKING A HERO OBJECT ADD IT TO ARRAYLIST OF SELECTED HEROES AND GET IT TO KAMYAR (:D)
            }
        }
        //CHECK THE COUNT OF SELECTED HEROES (0 or more)
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == OKButton) {
            setPlayerHeroes();
            this.dispose();
        } else if (e.getSource() == backButton) {
            this.dispose();
        }
    }

    public static void main(String[] args) {
        SelectMapsFrame selction = new SelectMapsFrame();
    }
}
