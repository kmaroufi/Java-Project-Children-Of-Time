package GUI;

import Structure.Condition;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Created by Future on 7/17/2016.
 */
public class AddNewElementsTrieConditionOfSelectingObjectDetail extends JFrame implements ActionListener,GameFrame{
    private JButton addButton;
    private JButton addNewConditionButton;
    private AddNewSelectingObjectDetailFrame newSelectingObjectDetailFrame;
    private ArrayList<Condition> conditions;

    public AddNewElementsTrieConditionOfSelectingObjectDetail(AddNewSelectingObjectDetailFrame addNewSelectingObjectDetailFrame) {
        this.newSelectingObjectDetailFrame = addNewSelectingObjectDetailFrame;
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
        } else if (e.getSource() == addNewConditionButton) {
            new AddNewConditionFrame(this).showFrame();
        }
    }

    private void addData() {
        this.newSelectingObjectDetailFrame.getTrieCondition().add(this.conditions, true);
    }

    //--------------------------------------------------------


    public ArrayList<Condition> getConditions() {
        return conditions;
    }

    public void setConditions(ArrayList<Condition> conditions) {
        this.conditions = conditions;
    }
}
