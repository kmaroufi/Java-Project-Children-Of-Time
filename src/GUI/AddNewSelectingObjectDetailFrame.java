package GUI;

import Structure.ClassName;
import Structure.SelectingObjectsDetail;
import Structure.SelectingObjectsDetailHandler;
import Structure.Tree;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Future on 7/17/2016.
 */
public class AddNewSelectingObjectDetailFrame extends JFrame implements ActionListener,GameFrame{
    private JButton addButton;
    private JButton addNewElementTrieConditionButton;
    private JCheckBox isAllRelatedObjectsInvolvedLabel;
    private JCheckBox isUserSelectedLabel;
    private JCheckBox isDependsOnConditionLabel;
    private JLabel numberOfSelectedObjectsByConditionsLabel;
    private JCheckBox isRandomObjectsSelectingLabel;
    private JLabel numberOfRandomSelectedObjectsLabel;
    private JCheckBox isSelectedObjectsDependsOnPlayerLabel;
    private JLabel numberOfSelectedObjectsByPlayerLabel;
    private JCheckBox isRelatedToAttackDefendLabel;
    private JCheckBox isHeroSelectedLabel;
    private JCheckBox isEnemySelectedLabel;
    private JTextField numberOfSelectedObjectsByConditionsField;
    private JTextField numberOfRandomSelectedObjectsField;
    private JTextField numberOfSelectedObjectsByPlayerField;
    //------------------------------------------------------------------
    private Tree trieCondition;
    private Object fatherFrame;


    public AddNewSelectingObjectDetailFrame(Object fatherFrame) throws HeadlessException {
        this.addButton = new JButton();
        this.addNewElementTrieConditionButton = new JButton();
        this.fatherFrame = fatherFrame;
        this.numberOfSelectedObjectsByConditionsLabel = new JLabel();
        this.isSelectedObjectsDependsOnPlayerLabel = new JCheckBox();
        this.numberOfSelectedObjectsByPlayerLabel = new JLabel();
        this.isAllRelatedObjectsInvolvedLabel = new JCheckBox();
        this.isUserSelectedLabel = new JCheckBox();
        this.isDependsOnConditionLabel = new JCheckBox();
        this.numberOfRandomSelectedObjectsField = new JTextField();
        this.numberOfRandomSelectedObjectsLabel = new JLabel();
        this.numberOfSelectedObjectsByConditionsField = new JTextField();
        this.isRandomObjectsSelectingLabel = new JCheckBox();
        this.isRelatedToAttackDefendLabel = new JCheckBox();
        this.isHeroSelectedLabel = new JCheckBox();
        this.isEnemySelectedLabel = new JCheckBox();
        this.numberOfSelectedObjectsByConditionsField = new JTextField();
        this.numberOfSelectedObjectsByPlayerField = new JTextField();
    }

    @Override
    public void setBackgroundImage() {

    }

    @Override
    public void addActionListeners() {
        this.addButton.addActionListener(this);
        this.addNewElementTrieConditionButton.addActionListener(this);
    }

    @Override
    public void showFrame() {
        this.setSize(700, 700);
        this.setLayout(null);
        this.setResizable(false);
        this.addComponents();
        this.setComponentsBounds();
        this.setFonts();
        this.setVisible(true);
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
        } else if (e.getSource() == addNewElementTrieConditionButton) {
            new AddNewElementsTrieConditionOfSelectingObjectDetail(this).showFrame();
        }
    }

    private void addData() {
        if (fatherFrame instanceof AddNewPropertyFrame) {
            AddNewPropertyFrame newPropertyFrame = (AddNewPropertyFrame) fatherFrame;
            String classOfEffectedObject = (String) newPropertyFrame.getClassOfEffectingObjects().getSelectedItem();
            ClassName className = ClassName.valueOf(classOfEffectedObject);
            SelectingObjectsDetailHandler selectingObjectsDetailHandler = new SelectingObjectsDetailHandler(className, isAllRelatedObjectsInvolvedLabel.isSelected(), isUserSelectedLabel.isSelected(), isDependsOnConditionLabel.isSelected(), this.trieCondition, Integer.parseInt(numberOfSelectedObjectsByConditionsField.getText()), isRandomObjectsSelectingLabel.isSelected(), Integer.parseInt(numberOfRandomSelectedObjectsField.getText()), isSelectedObjectsDependsOnPlayerLabel.isSelected(), Integer.parseInt(numberOfSelectedObjectsByPlayerField.getText()), isRelatedToAttackDefendLabel.isSelected(), isHeroSelectedLabel.isSelected(), isEnemySelectedLabel.isSelected());
            newPropertyFrame.setSelectingObjectsDetail(new SelectingObjectsDetail(selectingObjectsDetailHandler));
        }
    }

    //-----------------------------------------------------------------------------------------

    public Tree getTrieCondition() {
        return trieCondition;
    }

    public void setTrieCondition(Tree trieCondition) {
        this.trieCondition = trieCondition;
    }
}
