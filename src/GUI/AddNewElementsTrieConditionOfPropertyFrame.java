package GUI;

import Structure.Condition;
import javafx.util.Pair;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Created by Future on 7/17/2016.
 */
public class AddNewElementsTrieConditionOfPropertyFrame extends JFrame implements ActionListener,GameFrame{
    private JButton addButton;
    private JButton addNewConditionButton;
    private JButton addNewEffectingVariablesButton;
    private AddNewPropertyFrame newPropertyFrame;
    private ArrayList<Pair<String, Double>> variables;
    private ArrayList<Condition> conditions;



    public AddNewElementsTrieConditionOfPropertyFrame(AddNewPropertyFrame addNewPropertyFrame) {
        this.addButton = new JButton();
        this.addNewConditionButton = new JButton();
        this.addNewEffectingVariablesButton = new JButton("Add New Effecting Variable");
        this.variables = new ArrayList<>();
        this.conditions = new ArrayList<>();
        this.newPropertyFrame = addNewPropertyFrame;
    }

    @Override
    public void setBackgroundImage() {

    }

    @Override
    public void addActionListeners() {
        this.addButton.addActionListener(this);
        this.addNewEffectingVariablesButton.addActionListener(this);
        this.addNewEffectingVariablesButton.addActionListener(this);
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
        this.add(addNewEffectingVariablesButton);
    }

    @Override
    public void setComponentsBounds() {
        this.addButton.setBounds(300, 300, 200, 100);
        this.addNewConditionButton.setBounds(200, 200, 200, 100);
        this.addNewEffectingVariablesButton.setBounds(100, 100, 200, 100);
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
        } else if (e.getSource() == addNewEffectingVariablesButton) {
            new AddNewEffectingVariableFrame(this).showFrame();
        }
    }

    private void addData() {
        this.newPropertyFrame.getTrieCondition().add(this.conditions, this.variables);
    }
    //-------------------------------------------------------------------------------

    public ArrayList<Condition> getConditions() {
        return conditions;
    }

    public void setConditions(ArrayList<Condition> conditions) {
        this.conditions = conditions;
    }

    public ArrayList<Pair<String, Double>> getVariables() {
        return variables;
    }

    public void setVariables(ArrayList<Pair<String, Double>> variables) {
        this.variables = variables;
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

    public JButton getAddNewEffectingVariablesButton() {
        return addNewEffectingVariablesButton;
    }

    public void setAddNewEffectingVariablesButton(JButton addNewEffectingVariablesButton) {
        this.addNewEffectingVariablesButton = addNewEffectingVariablesButton;
    }

    public AddNewPropertyFrame getNewPropertyFrame() {
        return newPropertyFrame;
    }

    public void setNewPropertyFrame(AddNewPropertyFrame newPropertyFrame) {
        this.newPropertyFrame = newPropertyFrame;
    }
}
