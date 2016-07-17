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
        this.addButton = new JButton();
        this.addNewConditionButton = new JButton();
        this.conditions = new ArrayList<>();
        this.newSelectingObjectDetailFrame = addNewSelectingObjectDetailFrame;
    }

    @Override
    public void setBackgroundImage() {

    }

    @Override
    public void addActionListeners() {
        this.addButton.addActionListener(this);
        this.addNewConditionButton.addActionListener(this);
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
        this.addNewConditionButton.setName("AddConditionButton");
        this.setButton(addButton);
        this.setButton(addNewConditionButton);
    }

    @Override
    public void addComponents() {
        this.add(addButton);
        this.add(addNewConditionButton);
    }

    @Override
    public void setComponentsBounds() {
        this.addNewConditionButton.setBounds(300, 200, 200, 100);
        this.addButton.setBounds(100, 200, 200, 100);
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


    public JButton getAddButton() {
        return addButton;
    }

    public void setAddButton(JButton addButton) {
        this.addButton = addButton;
    }

    public JButton getAddNewConditionButton() {
        return addNewConditionButton;
    }

    public void setAddNewConditionButton(JButton addNewConditionButton) {
        this.addNewConditionButton = addNewConditionButton;
    }

    public AddNewSelectingObjectDetailFrame getNewSelectingObjectDetailFrame() {
        return newSelectingObjectDetailFrame;
    }

    public void setNewSelectingObjectDetailFrame(AddNewSelectingObjectDetailFrame newSelectingObjectDetailFrame) {
        this.newSelectingObjectDetailFrame = newSelectingObjectDetailFrame;
    }
}
