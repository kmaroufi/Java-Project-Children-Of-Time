package GUI;

import Structure.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Future on 7/16/2016.
 */
public class AddNewPropertyFrame extends JFrame implements ActionListener,GameFrame{
    private JButton addButton;
    private JButton addNewElementsTrieConditionButton;
    private JButton addNewSelectingEffectingObjectsDetailButton;
    private JComboBox classOfEffectingObjects;
    private JComboBox nameOfEffectedFieldBox;
    private JCheckBox isPermenantly;
    private JLabel constantPropertyLabel;
    private JTextField constantPropertyField;
    //--------------------------------------------------------------------------------
    private AddNewElementsTrieCondition newElementsTrieCondition;
    private SelectingObjectsDetail selectingObjectsDetail;
    private Tree trieCondition;

    public AddNewPropertyFrame(AddNewElementsTrieCondition newElementsTrieCondition) throws HeadlessException {
        this.trieCondition = new Tree();
        this.selectingObjectsDetail = new SelectingObjectsDetail();
        this.addButton = new JButton();
        this.addNewElementsTrieConditionButton = new JButton();
        this.addNewSelectingEffectingObjectsDetailButton = new JButton();
        this.newElementsTrieCondition = newElementsTrieCondition;
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
        if (e.getSource() == addButton) {
            this.addData();
            dispose();
        } else if (e.getSource() == addNewSelectingEffectingObjectsDetailButton) {
            new AddNewSelectingObjectDetailFrame(this).showFrame();
        } else if (e.getSource() == addNewElementsTrieConditionButton) {
            new AddNewElementsTrieConditionOfPropertyFrame(this).showFrame();
        }
    }

    private void addData() {
        String name = (String) nameOfEffectedFieldBox.getSelectedItem();
        ClassName className = (ClassName) classOfEffectingObjects.getSelectedItem();
        double constantProperty = Double.parseDouble(constantPropertyField.getText());
        PropertyHandler propertyHandler = new PropertyHandler(name, isPermenantly.isSelected(), className , null, constantProperty, selectingObjectsDetail, trieCondition);
        Property property = new Property(propertyHandler);
        this.newElementsTrieCondition.getProperties().add(property);
    }
    //--------------------------------------------------------------------------------------

    public SelectingObjectsDetail getSelectingObjectsDetail() {
        return selectingObjectsDetail;
    }

    public JComboBox getClassOfEffectingObjects() {
        return classOfEffectingObjects;
    }

    public void setClassOfEffectingObjects(JComboBox classOfEffectingObjects) {
        this.classOfEffectingObjects = classOfEffectingObjects;
    }

    public void setSelectingObjectsDetail(SelectingObjectsDetail selectingObjectsDetail) {
        this.selectingObjectsDetail = selectingObjectsDetail;
    }

    public Tree getTrieCondition() {
        return trieCondition;
    }

    public void setTrieCondition(Tree trieCondition) {
        this.trieCondition = trieCondition;
    }
}
