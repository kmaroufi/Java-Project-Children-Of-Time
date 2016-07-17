package GUI;

import javafx.util.Pair;

import javax.swing.*;
import java.awt.*;
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

    public void setGettingVariableNameBox(){

    }

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
        this.add(gettingVarableRatioLabel);
        this.add(gettingVariableRatioField);
        this.add(gettingVariableNameBox);
    }

    @Override
    public void setComponentsBounds() {
        this.addButton.setBounds(300, 300, 200, 100);
        this.gettingVarableRatioLabel.setBounds(100, 50, 150, 20);
        this.gettingVariableRatioField.setBounds(250, 50, 100, 20);
        this.gettingVariableNameBox.setBounds(100, 100, 300, 50);
    }

    @Override
    public void setFonts() {
        this.gettingVarableRatioLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
        this.gettingVariableNameBox.setFont(new Font("Tahoma", Font.PLAIN, 14));
        this.gettingVariableNameBox.setFont(new Font("Tahoma", Font.PLAIN, 14));
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

    public JButton getAddButton() {
        return addButton;
    }

    public void setAddButton(JButton addButton) {
        this.addButton = addButton;
    }

    public JComboBox getGettingVariableNameBox() {
        return gettingVariableNameBox;
    }

    public void setGettingVariableNameBox(JComboBox gettingVariableNameBox) {
        this.gettingVariableNameBox = gettingVariableNameBox;
    }

    public JLabel getGettingVarableRatioLabel() {
        return gettingVarableRatioLabel;
    }

    public void setGettingVarableRatioLabel(JLabel gettingVarableRatioLabel) {
        this.gettingVarableRatioLabel = gettingVarableRatioLabel;
    }

    public JTextField getGettingVariableRatioField() {
        return gettingVariableRatioField;
    }

    public void setGettingVariableRatioField(JTextField gettingVariableRatioField) {
        this.gettingVariableRatioField = gettingVariableRatioField;
    }

    public AddNewElementsTrieConditionOfPropertyFrame getNewElementsTrieConditionOfPropertyFrame() {
        return newElementsTrieConditionOfPropertyFrame;
    }

    public void setNewElementsTrieConditionOfPropertyFrame(AddNewElementsTrieConditionOfPropertyFrame newElementsTrieConditionOfPropertyFrame) {
        this.newElementsTrieConditionOfPropertyFrame = newElementsTrieConditionOfPropertyFrame;
    }
}
