package GUI;

import Structure.Condition;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Future on 7/16/2016.
 */
public class AddNewConditionFrame extends JFrame implements ActionListener,GameFrame{
    private JButton addButton;
    private JComboBox fieldOfClassNameBox;
    private JRadioButton greaterThan,smallerThan,equals;
    private JLabel constantNumberLabel;
    private JTextField constantNumberField;
    //---------------------------
    private Object fatherFrame;


    public AddNewConditionFrame(Object fatherFrame) throws HeadlessException {
        this.addButton = new JButton();
        this.fieldOfClassNameBox = new JComboBox();
        this.greaterThan = new JRadioButton("Greater Than");
        this.equals = new JRadioButton("Equals");
        this.smallerThan = new JRadioButton("Smaller Than");
        this.constantNumberField = new JTextField();
        this.constantNumberLabel = new JLabel("Constant Number");
        this.fatherFrame = fatherFrame;
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
        String fieldName = (String) this.fieldOfClassNameBox.getSelectedItem();
        Object valueOfField = null;
        try {
            valueOfField = Double.parseDouble(this.constantNumberField.getText());
        } catch (NumberFormatException e) {
            valueOfField = this.constantNumberField.getText();
        }
        int status = 0;
        if (greaterThan.isSelected()) {
            status = 1;
        } else if (equals.isSelected()) {
            status = 0;
        } else if (smallerThan.isSelected()) {
            status = -1;
        }

        Condition condition = new Condition(fieldName, valueOfField, status, false);
        if (this.fatherFrame instanceof AddNewElementsTrieConditionOfPropertyFrame) {
            AddNewElementsTrieConditionOfPropertyFrame newElementsTrieConditionOfPropertyFrame = (AddNewElementsTrieConditionOfPropertyFrame) this.fatherFrame;
            newElementsTrieConditionOfPropertyFrame.getConditions().add(condition);
        } else if (this.fatherFrame instanceof AddNewElementsTrieConditionOfSelectingObjectDetail) {
            AddNewElementsTrieConditionOfSelectingObjectDetail newSelectingObjectDetailFrame = (AddNewElementsTrieConditionOfSelectingObjectDetail) this.fatherFrame;
            newSelectingObjectDetailFrame.getConditions().add(condition);
        }

    }
}
