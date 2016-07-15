package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Created by Future on 7/15/2016.
 */
public class AddNewItemFrame extends JFrame implements ActionListener, GameFrame {
    private JLabel nameLabel;
    private JLabel typeLabel;
    private JLabel worthLabel;
    private JLabel sizeLabel;
    private JLabel descriptionLabel;

    private JTextField nameField;
    private JTextField typeField;
    private JTextField worthField;
    private JTextField sizeField;
    private JTextField descriptionField;

    private JButton addButton;
    private JButton backButton;

    private ArrayList<JLabel> labels;
    private ArrayList<JTextField> fields;

    private Font tahoma = new Font("Tahoma", Font.PLAIN, 14);

    public AddNewItemFrame(){
        this.addButton = new JButton();
        this.backButton = new JButton();
        this.nameLabel = new JLabel("Name");
        this.typeLabel = new JLabel("Item Type");
        this.worthLabel = new JLabel("Worth");
        this.sizeLabel = new JLabel("Size");
        this.descriptionLabel = new JLabel("Description");
        this.nameField = new JTextField();
        this.typeField = new JTextField();
        this.worthField = new JTextField();
        this.sizeField = new JTextField();
        this.descriptionField = new JTextField();
        //-------------------------------------------------------
        this.setLabelsAndFields();
        this.addActionListeners();
        this.setButtons();
        this.showFrame();
    }

}
