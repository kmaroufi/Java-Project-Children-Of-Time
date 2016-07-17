package GUI;

import SoldierPackage.Hero;
import Structure.Condition;
import Structure.Property;
import Structure.PropertyHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Created by Future on 7/17/2016.
 */
public class AddNewElementsTrieCondition extends JFrame implements ActionListener,GameFrame {
    private JButton addProperty;
    private JButton addCondition;
    private JButton finishButton;
    private AddNewSubPerkComponentFrame newSubPerkComponentFrame;
    //------------------------------------------------------------------
    ArrayList<Condition> conditions;
    ArrayList<Property> properties;


    public AddNewElementsTrieCondition(AddNewSubPerkComponentFrame subPerkComponentFrame) throws HeadlessException {
        this.addProperty = new JButton();
        this.addCondition = new JButton();
        this.finishButton = new JButton();
        this.newSubPerkComponentFrame = subPerkComponentFrame;
        this.conditions = new ArrayList<>();
        this.properties = new ArrayList<>();
    }

    @Override
    public void setBackgroundImage() {

    }

    @Override
    public void addActionListeners() {
        this.addProperty.addActionListener(this);
        this.addCondition.addActionListener(this);
        this.finishButton.addActionListener(this);
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
        this.addProperty.setName("AddPropertyButton");
        this.addCondition.setName("AddConditionButton");
        this.finishButton.setName("FinishButton");
        this.setButton(addProperty);
        this.setButton(addCondition);
        this.setButton(finishButton);
    }

    @Override
    public void addComponents() {
        this.add(addProperty);
        this.add(addCondition);
        this.add(finishButton);
    }

    @Override
    public void setComponentsBounds() {
        this.addProperty.setBounds(300, 200, 200, 100);
        this.addCondition.setBounds(100, 200, 200, 100);
        this.finishButton.setBounds(200, 350, 200, 100);
    }

    @Override
    public void setFonts() {

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addProperty) {

            new AddNewPropertyFrame(this).showFrame();
        } else if (e.getSource() == addCondition) {
            new AddNewConditionFrame(this).showFrame();
        } else if (e.getSource() == finishButton) {
            this.addData();
            dispose();
        }
    }

    private void addData() {
        this.newSubPerkComponentFrame.getTrieCondition().add(this.conditions, this.properties);
    }

    //-------------------------------------------------------------- Getter And Setter


    public ArrayList<Condition> getConditions() {
        return conditions;
    }

    public void setConditions(ArrayList<Condition> conditions) {
        this.conditions = conditions;
    }

    public ArrayList<Property> getProperties() {
        return properties;
    }

    public void setProperties(ArrayList<Property> properties) {
        this.properties = properties;
    }

    public JButton getAddProperty() {
        return addProperty;
    }

    public void setAddProperty(JButton addProperty) {
        this.addProperty = addProperty;
    }

    public JButton getAddCondition() {
        return addCondition;
    }

    public void setAddCondition(JButton addCondition) {
        this.addCondition = addCondition;
    }

    public JButton getFinishButton() {
        return finishButton;
    }

    public void setFinishButton(JButton finishButton) {
        this.finishButton = finishButton;
    }

    public AddNewSubPerkComponentFrame getNewSubPerkComponentFrame() {
        return newSubPerkComponentFrame;
    }

    public void setNewSubPerkComponentFrame(AddNewSubPerkComponentFrame newSubPerkComponentFrame) {
        this.newSubPerkComponentFrame = newSubPerkComponentFrame;
    }
}
