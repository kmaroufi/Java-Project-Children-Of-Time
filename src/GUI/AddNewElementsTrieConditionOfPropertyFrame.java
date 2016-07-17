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
        this.newPropertyFrame = addNewPropertyFrame;
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
}
