package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Future on 7/16/2016.
 */
public class AddNewNecessaryAbilityFrame extends JFrame implements ActionListener,GameFrame{
    private JButton addButton;
    private AddNewSubPerkFrame newSubPerkFrame;
    private JComboBox nameOfNeccessaryAbilityLabel;
    private JLabel gradeOfNeccessaryAbilityLabel;
    private JTextField gradeOfNeccessaryAbilityField;
    //----------------------------------------------------------
    private String nameOfNecessaryAbility;
    private Integer gradeOfNecessaryAbility;

    public AddNewNecessaryAbilityFrame(AddNewSubPerkFrame newSubPerkFrame) throws HeadlessException {
        this.addButton = new JButton();
        this.newSubPerkFrame = newSubPerkFrame;
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
        }
    }

    private void addData() {
        this.nameOfNecessaryAbility = (String) this.nameOfNeccessaryAbilityLabel.getSelectedItem();
        this.gradeOfNecessaryAbility = Integer.parseInt(this.gradeOfNeccessaryAbilityField.getText());
        this.newSubPerkFrame.getNameNecessaryAbilities().add(nameOfNecessaryAbility);
        this.newSubPerkFrame.getGradeOfNecessaryAbilities().put(nameOfNecessaryAbility, gradeOfNecessaryAbility);
    }
}
