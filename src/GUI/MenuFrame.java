package GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Future on 7/12/2016.
 */
public class MenuFrame extends JFrame implements ActionListener{
    private JProgressBar loadingGameProgress;
    private JButton singlePlayerButton;
    private JButton customPlayerButton;
    private JButton PVPPlayerButton;
    private JButton exitButton;
    private JButton optionButton;


    public MenuFrame(){
        this.loadGame();
        //-------------------------------------------------
        this.singlePlayerButton = new JButton();
        this.customPlayerButton = new JButton();
        this.PVPPlayerButton = new JButton("PVP Player");
        this.optionButton = new JButton("Option");
        this.exitButton = new JButton("Exit");
        //-------------------------------------------------
        this.setButtons();
        this.showFrame();
    }

    private void setButtons(){
        this.setSinglePlayerButton();
        this.setCustomPlayerButton();
    }

    private void setSinglePlayerButton(){
        ImageIcon singlePlayerButtonImage = new ImageIcon("./resources/images/SinglePlayerButton.png");
//        this.singlePlayerButton.setBorderPainted(false);
//        this.singlePlayerButton.setContentAreaFilled(false);
//        this.singlePlayerButton.setFocusPainted(false);
//        this.singlePlayerButton.setOpaque(false);
        this.singlePlayerButton.setIcon(singlePlayerButtonImage);
        this.singlePlayerButton.setSize(singlePlayerButtonImage.getIconWidth(), singlePlayerButtonImage.getIconHeight());
        this.singlePlayerButton.addActionListener(this);
    }

    private void setCustomPlayerButton(){
        ImageIcon customPlayerButtonImage = new ImageIcon("./resources/images/CustomPlayerButton.png");
        this.customPlayerButton.setBorderPainted(false);
        this.customPlayerButton.setContentAreaFilled(false);
        this.customPlayerButton.setFocusPainted(false);
        this.customPlayerButton.setOpaque(false);
        this.customPlayerButton.setIcon(customPlayerButtonImage);
        this.customPlayerButton.setSize(customPlayerButtonImage.getIconWidth(), customPlayerButtonImage.getIconHeight());
        this.customPlayerButton.addActionListener(this);
    }

    private void showFrame() {
        this.setContentPane(new JLabel(new ImageIcon("./resources/images/MenuFrameBackground.png")));
        this.setLayout(null);
        this.setSize(700, 700);
        this.add(singlePlayerButton);
        this.add(customPlayerButton);
        this.add(PVPPlayerButton);
        this.add(optionButton);
        this.add(exitButton);
        //-----------------------------------------
        this.singlePlayerButton.setBounds(100, 100, 200, 75);
        this.customPlayerButton.setBounds(100, 200, 200, 75);
        this.PVPPlayerButton.setBounds(100, 300, 200, 75);
        this.optionButton.setBounds(100, 400, 200, 75);
        this.exitButton.setBounds(100, 500, 200, 75);
        //-----------------------------------------
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    private void loadGame() {
        this.loadingGameProgress = new JProgressBar(0, 100);
        this.loadingGameProgress.setStringPainted(true);
        JLabel message = new JLabel("Please Wait While The Game is loading...");
        JFrame loadingFrame = new JFrame("Loading Game...");
        loadingFrame.setLayout(null);
        loadingFrame.setSize(500, 200);
        loadingFrame.add(this.loadingGameProgress);
        loadingFrame.add(message);
        loadingFrame.setVisible(true);
        message.setBounds(130, 75, 250, 20);
        this.loadingGameProgress.setBounds(50, 100, 400, 30);
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
            //set Mode of Game to Single
        } else if (e.getSource() == customPlayerButton) {

        } else if (e.getSource() == PVPPlayerButton) {

        } else if (e.getSource() == optionButton) {

        } else if (e.getSource() == exitButton) {

        }
    }
}
