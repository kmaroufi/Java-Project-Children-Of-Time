package GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Future on 7/12/2016.
 */
public class MenuFrame extends JFrame implements ActionListener,GameFrame{
    private JProgressBar loadingGameProgress;
    private JButton singlePlayerButton;
    private JButton customPlayerButton;
    private JButton PVPBattleButton;
    private JButton exitButton;
    private JButton optionButton;


    public MenuFrame(){
        this.loadGame();
        //-------------------------------------------------
        this.singlePlayerButton = new JButton();
        this.customPlayerButton = new JButton();
        this.PVPBattleButton = new JButton();
        this.optionButton = new JButton();
        this.exitButton = new JButton();
        //-------------------------------------------------
        this.setButtons();
        this.showFrame();
    }

    public void setButtons(){
        this.singlePlayerButton.setName("SinglePlayerButton");
        this.customPlayerButton.setName("CustomPlayerButton");
        this.PVPBattleButton.setName("PVPBattleButton");
        this.optionButton.setName("OptionButton");
        this.exitButton.setName("ExitButton");
        this.setButton(singlePlayerButton);
        this.setButton(customPlayerButton);
        this.setButton(PVPBattleButton);
        this.setButton(optionButton);
        this.setButton(exitButton);
        this.addActionListeners();

    }

    @Override
    public void addComponents() {
        this.add(singlePlayerButton);
        this.add(customPlayerButton);
        this.add(PVPBattleButton);
        this.add(optionButton);
        this.add(exitButton);
    }

    @Override
    public void setComponentsBounds() {
        this.singlePlayerButton.setBounds(100, 100, 200, 100);
        this.customPlayerButton.setBounds(100, 200, 200, 100);
        this.PVPBattleButton.setBounds(100, 300, 200, 100);
        this.optionButton.setBounds(100, 400, 200, 100);
        this.exitButton.setBounds(100, 500, 200, 100);
    }

    @Override
    public void setFonts() {

    }

    @Override
    public void setBackgroundImage() {
        this.setContentPane(new JLabel(new ImageIcon("./resources/images/MenuFrameBackground.png")));

    }

    @Override
    public void addActionListeners() {
        this.singlePlayerButton.addActionListener(this);
        this.customPlayerButton.addActionListener(this);
        this.PVPBattleButton.addActionListener(this);
        this.exitButton.addActionListener(this);
    }
    @Override
    public void showFrame() {
        this.setBackgroundImage();
        this.setLayout(null);
        this.setSize(700, 700);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setResizable(false);
        //-----------------------------------------
        this.addComponents();
        this.setComponentsBounds();
        this.setFonts();
        //-----------------------------------------
        this.setVisible(true);
    }

    private void loadGame() {
        this.loadingGameProgress = new JProgressBar(0, 100);
        this.loadingGameProgress.setStringPainted(true);
        JLabel title = new JLabel(new ImageIcon("./resources/images/WelcomeToChildrenOfTime.png"));
        JLabel message = new JLabel("Please Wait While The Game is loading...");
        JFrame loadingFrame = new JFrame("Loading Game...");
        loadingFrame.setContentPane(new JLabel(new ImageIcon("./resources/images/WelcomeFrame.png")));
        loadingFrame.setLayout(null);
        loadingFrame.setSize(700, 700);
        loadingFrame.add(this.loadingGameProgress);
        loadingFrame.add(message);
        loadingFrame.add(title);
        loadingFrame.setVisible(true);
        message.setBounds(130, 330, 250, 20);
        title.setBounds(50, 20, 600, 200);
        this.loadingGameProgress.setBounds(50, 350, 500, 50);
        for(int i = 0;i <= 100;i++) {
            this.loadingGameProgress.setString("Loading " + i + "%");
            this.loadingGameProgress.setValue(i);
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        loadingFrame.dispose();
    }

    public static void main(String[] args) {
        new MenuFrame();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == singlePlayerButton) {
            SinglePlayerFrame singlePlayerFrame = new SinglePlayerFrame();
            singlePlayerFrame.showFrame();

        } else if (e.getSource() == customPlayerButton) {
            this.setVisible(false);
            CustomPlayerFrame customPlayerFrame = new CustomPlayerFrame();
            customPlayerFrame.showFrame();
        } else if (e.getSource() == PVPBattleButton) {

        } else if (e.getSource() == optionButton) {
            OptionFrame optionFrame = new OptionFrame();
            optionFrame.showFrame();
        } else if (e.getSource() == exitButton) {
            System.exit(0);
        }
    }
}
