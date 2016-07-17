package GUI;

import SoldierPackage.Hero;
import Structure.ClassName;
import Structure.Condition;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Field;

/**
 * Created by Future on 7/16/2016.
 */
public class AddNewConditionFrame extends JFrame implements ActionListener,GameFrame{
    private JButton addButton;
    private JComboBox fieldOfClassNameBox;
    private JComboBox conditionBox;
    private JLabel constantNumberLabel;
    private JTextField constantNumberField;
    private JLabel classOfEffectedObjectsLabel;
    private JTextField classOfEffectedObjectsField;
    //---------------------------
    private Object fatherFrame;


    public AddNewConditionFrame(Object fatherFrame) throws HeadlessException {
        this.addButton = new JButton();
        this.conditionBox = new JComboBox();
        this.fieldOfClassNameBox = new JComboBox();
        this.constantNumberField = new JTextField();
        this.constantNumberLabel = new JLabel("Constant Number");
        this.classOfEffectedObjectsField = new JTextField();
        this.classOfEffectedObjectsLabel = new JLabel("Class Of Effected Objects");
        this.fatherFrame = fatherFrame;
        this.addBoxItems();
    }

    public void addBoxItems(){
        this.conditionBox.addItem("Greater Than");
        this.conditionBox.addItem("Smaller Than");
        this.conditionBox.addItem("Equals");
   }

    @Override
    public void setBackgroundImage() {

    }

    @Override
    public void addActionListeners() {
        this.addButton.addActionListener(this);
    }

    @Override
    public void showFrame() {
        this.setSize(500, 500);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
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
        this.add(fieldOfClassNameBox);
        this.add(conditionBox);
        this.add(constantNumberLabel);
        this.add(constantNumberField);
        this.add(classOfEffectedObjectsLabel);
        this.add(classOfEffectedObjectsField);
    }

    @Override
    public void setComponentsBounds() {
        this.addButton.setBounds(300, 300, 200, 100);
        this.classOfEffectedObjectsLabel.setBounds(50, 50, 200, 20);
        this.classOfEffectedObjectsField.setBounds(250, 50, 100, 20);
        this.constantNumberLabel.setBounds(50, 100, 100, 20);
        this.constantNumberField.setBounds(150, 100, 100, 20);
        this.fieldOfClassNameBox.setBounds(100, 200, 300, 30);
        this.conditionBox.setBounds(100, 150, 300, 30);
    }

    @Override
    public void setFonts() {

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addButton) {
            this.addData();
            dispose();
        }

    }

    private void addData() {
        String fieldName = (String) this.fieldOfClassNameBox.getSelectedItem();
        Object valueOfField = null;
        try {
            valueOfField = Double.parseDouble(this.constantNumberField.getText());
        } catch (NumberFormatException e) {
            valueOfField = this.constantNumberField.getText();
        }
        int status = 0;
        if (conditionBox.getSelectedItem().equals("Greater Than")) {
            status = 1;
        } else if (conditionBox.getSelectedItem().equals("Equals")) {
            status = 0;
        } else if (conditionBox.getSelectedItem().equals("Smaller Than")) {
            status = -1;
        }

        Condition condition = new Condition(fieldName, valueOfField, status, false);
        if (this.fatherFrame instanceof AddNewElementsTrieConditionOfPropertyFrame) {
            AddNewElementsTrieConditionOfPropertyFrame newElementsTrieConditionOfPropertyFrame = (AddNewElementsTrieConditionOfPropertyFrame) this.fatherFrame;
            newElementsTrieConditionOfPropertyFrame.getConditions().add(condition);
        } else if (this.fatherFrame instanceof AddNewElementsTrieConditionOfSelectingObjectDetail) {
            AddNewElementsTrieConditionOfSelectingObjectDetail newSelectingObjectDetailFrame = (AddNewElementsTrieConditionOfSelectingObjectDetail) this.fatherFrame;
            newSelectingObjectDetailFrame.getConditions().add(condition);
        } else if (this.fatherFrame instanceof AddNewElementsTrieCondition) {
            AddNewElementsTrieCondition addNewElementsTrieCondition = (AddNewElementsTrieCondition) this.fatherFrame;
            addNewElementsTrieCondition.getConditions().add(condition);
        }

    }
    //----------------------------------------------------
}
