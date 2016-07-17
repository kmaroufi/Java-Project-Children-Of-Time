package GUI;

import AbilityPackage.SubAbilityComponent;
import AbilityPackage.SubAbilityComponentHandler;
import AbilityPackage.SubPerkComponent;
import Structure.ClassName;
import Structure.SelectingObjectsDetail;
import Structure.Tree;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Future on 7/16/2016.
 */
public class AddNewSubPerkComponentFrame extends JFrame implements ActionListener,GameFrame{
    private JButton addNewSelectingObjectDetailButton;
    private JButton addButton;
    private AddNewSubPerkFrame newSubPerkFrame;
    private JComboBox classOfEffectedObjectsBox;      // Hero or Enemy
    //-----------------------------------------------------
    private SelectingObjectsDetail selectingObjectsDetail;
    private ClassName classOfEffectedObjects;
    private Tree trieCondition;

    public AddNewSubPerkComponentFrame(AddNewSubPerkFrame newSubPerkFrame) throws HeadlessException {
        this.addNewSelectingObjectDetailButton = new JButton();
        this.addButton = new JButton();
        this.classOfEffectedObjectsBox = new JComboBox();
        this.newSubPerkFrame = newSubPerkFrame;
    }

    public void setClassOfEffectedObjectsBox(){
        for (ClassName className : ClassName.values()) {
            this.classOfEffectedObjectsBox.addItem(className.getName());
        }
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
        } else if (e.getSource() == addNewSelectingObjectDetailButton) {
            new AddNewSelectingObjectDetailFrame(this).showFrame();
        }
    }

    private void addData() {
        SubAbilityComponentHandler subAbilityComponentHandler = new SubAbilityComponentHandler(classOfEffectedObjects, trieCondition, selectingObjectsDetail, new ArrayList(), new HashMap());
        SubPerkComponent subPerkComponent = new SubPerkComponent(subAbilityComponentHandler);
        this.newSubPerkFrame.getSubPerkComponents().add(subPerkComponent);
    }

    //------------------------------------------------------------------------

    public Tree getTrieCondition() {
        return trieCondition;
    }

    public void setTrieCondition(Tree trieCondition) {
        this.trieCondition = trieCondition;
    }

    public ClassName getClassOfEffectedObjects() {
        return classOfEffectedObjects;
    }

    public void setClassOfEffectedObjects(ClassName classOfEffectedObjects) {
        this.classOfEffectedObjects = classOfEffectedObjects;
    }

    public JComboBox getclassOfEffectedObjectsBox(){
        return this.classOfEffectedObjectsBox;
    }

    public JButton getAddNewSelectingObjectDetailButton() {
        return addNewSelectingObjectDetailButton;
    }

    public void setAddNewSelectingObjectDetailButton(JButton addNewSelectingObjectDetailButton) {
        this.addNewSelectingObjectDetailButton = addNewSelectingObjectDetailButton;
    }

    public JButton getAddButton() {
        return addButton;
    }

    public void setAddButton(JButton addButton) {
        this.addButton = addButton;
    }

    public AddNewSubPerkFrame getNewSubPerkFrame() {
        return newSubPerkFrame;
    }

    public void setNewSubPerkFrame(AddNewSubPerkFrame newSubPerkFrame) {
        this.newSubPerkFrame = newSubPerkFrame;
    }

    public JComboBox getClassOfEffectedObjectsBox() {
        return classOfEffectedObjectsBox;
    }

    public void setClassOfEffectedObjectsBox(JComboBox classOfEffectedObjectsBox) {
        this.classOfEffectedObjectsBox = classOfEffectedObjectsBox;
    }

    public SelectingObjectsDetail getSelectingObjectsDetail() {
        return selectingObjectsDetail;
    }

    public void setSelectingObjectsDetail(SelectingObjectsDetail selectingObjectsDetail) {
        this.selectingObjectsDetail = selectingObjectsDetail;
    }
}
