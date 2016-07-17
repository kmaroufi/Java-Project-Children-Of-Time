package GUI;

import javafx.util.Pair;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Arc2D;

/**
 * Created by Future on 7/16/2016.
 */
public class AddNewEffectingVariableFrame extends JFrame implements ActionListener,GameFrame {
    private JButton addButton;
    private JComboBox gettingVariableNameBox;
    private JLabel gettingVarableRatioLabel;
    private JTextField gettingVariableRatioField;
    //---------------------------------------
    private AddNewElementsTrieConditionOfPropertyFrame newElementsTrieConditionOfPropertyFrame;


    public AddNewEffectingVariableFrame(AddNewElementsTrieConditionOfPropertyFrame newElementsTrieConditionOfPropertyFrame) {
        this.addButton = new JButton();
        this.gettingVarableRatioLabel = new JLabel("Variable Ratio");
        this.gettingVariableRatioField = new JTextField();
        this.gettingVariableNameBox = new JComboBox();
        this.newElementsTrieConditionOfPropertyFrame = newElementsTrieConditionOfPropertyFrame;
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
        }
    }

    private void addData() {
        String variableName = (String) this.gettingVariableNameBox.getSelectedItem();
        double varialeRatio = Double.parseDouble(this.gettingVariableRatioField.getText());
        this.newElementsTrieConditionOfPropertyFrame.getVariables().add(new Pair<>(variableName,varialeRatio));
    }
}
