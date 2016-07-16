package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Created by Future on 7/12/2016.
 */
public class SkillRoomFrame extends JFrame implements ActionListener {
    private JLabel welcomeFrame;
    private JButton acquireButton;
    private JButton showPlayerHerosButton;
    private JComboBox skillBox;
    private JLabel playerNameLabel;
    private JLabel playerMagicPointsLabel;
    private JLabel playerMoneyLabel;
    private JLabel playerEPLabel;
//    private JLabel skillNameLabel;
//    private JLabel skillEpLabel;
//    private JLabel skillMoneyLabel;
//    private JLabel skillMagicPointsLabel;
    private JTextArea skillInformationArea;
    private ArrayList<JLabel> playerHerosLabels;
    private JProgressBar loadAbilityProgressBar;
    private Font tahoma;

    private Thread skillRoomThread = new Thread(new Runnable() {
        @Override
        public void run() {
            while (true) {
                if (skillBox != null && skillBox.getItemCount() != 0) {
                    process();
                }
            }
        }
    });


    public SkillRoomFrame(){
        this.welcomeFrame = new JLabel(new ImageIcon("./resources/images/WelcomeToTheSkillRoom.png"));
        this.acquireButton = new JButton("Acquire");
        this.showPlayerHerosButton = new JButton("Show My Heroes");
        this.skillBox = new JComboBox();
        this.playerNameLabel = new JLabel("PLAYER NAME ");
        this.playerEPLabel = new JLabel("PLAYER EP");
        this.playerMoneyLabel = new JLabel("PLAYER MONEY");
        this.playerMagicPointsLabel = new JLabel("PLAYER MAGIC POINT");
        this.skillInformationArea = new JTextArea();
        this.skillInformationArea.setEditable(false);
        this.tahoma = new Font("Tahoma", Font.PLAIN, 15);
        //-------------------------------------------------------------------
        this.setComponentsFont();
        this.addSkills();
        //-------------------------------------------------------------------
        this.skillRoomThread.start();
        this.showFrame();
    }
    public void setPlayerInformation(Player player){
//         get player info from the GameEngine
//        this.playerCapacityLabel = player.getCapacity();
//        this.playerNameLabel.setText(player.getName());
//        this.playerMoneyLabel.setText(player.getMoney());
//        ArrayList<Item> playerItems = player.getItems();
//        for(int i = 0;i < this.playerItems.size();i++) {
//            this.playerItems.get(i).setFont(tahoma);
//            this.playerItems.get(i) = new JLabel((i + 1) + " - " + playerItems.get(i).getName());
//        }
    }

    public void addSkills(){
        //Get All Skills From GameEngine
        for(int i = 1;i <= 10;i++) {
            this.skillBox.addItem("Skill - " + i);
        }
    }

    public void process(){
        this.setSkillInformation();
    }

    public void setSkillInformation(){                              // Hey mipare Ok kon!
        String skillName = (String) this.skillBox.getSelectedItem();
        String skillEP = "SKILL EP";
        String skillMagicPoints = "SKILL MAGIC POINTS";
        String skillMoney = "SKILL MONEY";
        String skillDescription = "SKILL MONEY";
        this.skillInformationArea.setText("Name : " + skillName + "\n" + "Price : " + skillMoney + "\n");
        this.skillInformationArea.append("MagicPoints : " + skillMagicPoints + "\n" + "Energy Points : " + skillEP + "\n");
        this.skillInformationArea.append("Description : " + skillDescription);

    }


    public void setComponentsFont(){
        this.acquireButton.setFont(tahoma);
        this.skillBox.setFont(tahoma);
        this.showPlayerHerosButton.setFont(tahoma);
        this.playerNameLabel.setFont(tahoma);
        this.playerMoneyLabel.setFont(tahoma);
        this.playerEPLabel.setFont(tahoma);
        this.playerMagicPointsLabel.setFont(tahoma);
        this.skillInformationArea.setFont(tahoma);
    }

    public void showFrame(){
        this.setSize(650, 600);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.getContentPane().setBackground(Color.WHITE);
        this.add(welcomeFrame);
        this.add(acquireButton);
        this.add(showPlayerHerosButton);
        this.add(playerNameLabel);
        this.add(playerEPLabel);
        this.add(playerMagicPointsLabel);
        this.add(playerMoneyLabel);
        this.add(skillBox);
        this.add(skillInformationArea);
        this.setVisible(true);
        //--------------------------------------------
        Dimension welcomeFramePreferredSize = welcomeFrame.getPreferredSize();
        welcomeFrame.setBounds(10, 10, welcomeFramePreferredSize.width , welcomeFramePreferredSize.height);

        Dimension acquireButtonPreferredSize = acquireButton.getPreferredSize();
        acquireButton.setBounds(70, 450 , acquireButtonPreferredSize.width + 50, acquireButtonPreferredSize.height);

        Dimension playerNamePreferredSize = playerNameLabel.getPreferredSize();
        playerNameLabel.setBounds(50, 100, playerNamePreferredSize.width, playerNamePreferredSize.height);

        Dimension playerMoneyPreferredSize = playerMoneyLabel.getPreferredSize();
        playerMoneyLabel.setBounds(50, 120, playerMoneyPreferredSize.width, playerMoneyPreferredSize.height);

        Dimension playerMagicPointsPreferredSize = playerMagicPointsLabel.getPreferredSize();
        playerMagicPointsLabel.setBounds(50, 140, playerMagicPointsPreferredSize.width, playerMagicPointsPreferredSize.height);

        Dimension playerEPPreferredSize = playerEPLabel.getPreferredSize();
        playerEPLabel.setBounds(50, 160, playerEPPreferredSize.width, playerEPPreferredSize.height);

        Dimension playerHerosButtonPreferredSize = showPlayerHerosButton.getPreferredSize();
        showPlayerHerosButton.setBounds(400 , 450, playerHerosButtonPreferredSize.width, playerHerosButtonPreferredSize.height);

        Dimension skillBoxPreferredSize = skillBox.getPreferredSize();
        skillBox.setBounds(70, 200, skillBoxPreferredSize.width + 400, skillBoxPreferredSize.height);

        Dimension skillInformationPreferredSize = skillInformationArea.getPreferredSize();
        skillInformationArea.setBounds(70, 240, skillInformationPreferredSize.width + 400, skillInformationPreferredSize.height + 100);

    }













    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == acquireButton) {

        }
    }


    public static void main(String[] args) {
        new SkillRoomFrame();
    }
}
