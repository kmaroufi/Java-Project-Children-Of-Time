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
        this.properties = new ArrayList<>();
        this.conditions = new ArrayList<>();
        this.newSubPerkComponentFrame = subPerkComponentFrame;
    }

    @Override
    public void setBackgroundImage() {

    }

    @Override
    public void addActionListeners() {

    }

    @Override
    public void showFrame() {

    }

    @Override
    public void setButtons() {

    }

    @Override
    public void addComponents() {

    }

    @Override
    public void setComponentsBounds() {

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
