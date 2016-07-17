package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Created by Future on 7/11/2016.
 */
public class ShopFrame extends JFrame implements ActionListener,GameFrame{
    private JButton backButton;
    private JButton buyButton;
    private JButton sellButton;
    private JButton showMyItemsButton;
    private JProgressBar loadingFrameProgressBar;
    private JLabel welcomeLabel;
    private JLabel playerNameLabel;
    private JLabel playerMoneyLabel;
    private JLabel playerCapacityLabel;
    private JLabel playerImage;
    private ArrayList<JLabel> playerItems;
    private Font tahoma = new Font("Tahoma", Font.PLAIN, 14);


    public ShopFrame(boolean isCustom) {
        this.welcomeLabel = new JLabel(new ImageIcon("./resources/images/WelcomeToTheShop.png"));
        this.tahoma = new Font("Tahoma", Font.PLAIN, 16);
        this.backButton = new JButton();
        this.playerNameLabel = new JLabel("PLAYER NAME");
        this.playerMoneyLabel = new JLabel("PLAYER MONEY");
        this.playerItems = new ArrayList<>();
        this.playerCapacityLabel = new JLabel("CAPACITY");
        this.playerImage = new JLabel(new ImageIcon("./resources/images/PlayerPackage(Down-1).png"));
        this.buyButton = new JButton();
        this.sellButton = new JButton();
        this.showMyItemsButton = new JButton();
        this.loadingFrameProgressBar = new JProgressBar(0, 100);
        //----------------------------------------------------
//        this.setPlayerInformation(new PlayerPackage());                          // CORRECT IT!
        this.addActionListeners();
        this.setBackgroundImage();
        this.showProgressFrame();
        this.setButtons();
        this.showFrame();
    }

    public void setPlayerInformation(Player player){
//         get player info from the GameEngine
//        this.playerCapacityLabel = player.getCapacity();
//        this.playerNameLabel.setText(player.getName());
//        this.playerMoneyLabel.setText(player.getMoney());
//        ArrayList<ItemPackage> playerItems = player.getItems();
//        for(int i = 0;i < this.playerItems.size();i++) {
//            this.playerItems.get(i).setFont(tahoma);
//            this.playerItems.get(i) = new JLabel((i + 1) + " - " + playerItems.get(i).getName());
//        }
    }


    public void setButtons(){
        this.buyButton.setName("BuyButton");
        this.showMyItemsButton.setName("ShowMyItemsButton");
        this.backButton.setName("BackButton");
        this.sellButton.setName("SellButton");
        this.setButton(buyButton);
        this.setButton(showMyItemsButton);
        this.setButton(backButton);
        this.setButton(sellButton);
    }

    @Override
    public void addComponents() {
        this.add(welcomeLabel);
        this.add(backButton);
        this.add(buyButton);
        this.add(sellButton);
        this.add(showMyItemsButton);
        this.add(playerNameLabel);
        this.add(playerCapacityLabel);
        this.add(playerMoneyLabel);
        this.add(playerImage);
    }

    @Override
    public void setComponentsBounds() {
        this.playerImage.setBounds(350, 150, 200, 200);
        this.playerNameLabel.setBounds(60, 170, 200, 20);
        this.playerMoneyLabel.setBounds(60, 190, 200, 20);
        this.playerCapacityLabel.setBounds(60, 210, 200, 20);
        this.backButton.setBounds(170, 480,200, 100);
        this.buyButton.setBounds(50, 300, 200, 100);
        this.sellButton.setBounds(300, 400, 200, 100);
        this.showMyItemsButton.setBounds(50, 400, 200, 100);
    }

    @Override
    public void setFonts() {
        this.loadingFrameProgressBar.setFont(tahoma);
        this.playerNameLabel.setFont(tahoma);
        this.playerMoneyLabel.setFont(tahoma);
        this.playerCapacityLabel.setFont(tahoma);
    }

    @Override
    public void setBackgroundImage() {

    }

    @Override
    public void addActionListeners() {
        this.buyButton.addActionListener(this);
        this.sellButton.addActionListener(this);
        this.backButton.addActionListener(this);
        this.showMyItemsButton.addActionListener(this);
    }

    public void showFrame(){
//        this.showProgressFrame();
//        this.setContentPane(new JLabel(new ImageIcon("C:\\Users\\Future\\Downloads\\WhYzqs1468271980.png")));
        this.getContentPane().setBackground(Color.WHITE);
        this.setSize(600, 600);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setLayout(null);
        //--------------------------------------------------------------------
        this.addComponents();
        this.setFonts();
        this.setComponentsBounds();
        //--------------------------------------------------------------------
        this.setVisible(true);
    }

    public void showProgressFrame(){
        JFrame frame = new JFrame("Progress");
        JLabel label = new JLabel("Please Wait While Loading ShopPackage...");
        frame.setSize(400, 200);
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frame.setLayout(null);
        frame.add(this.loadingFrameProgressBar);
        frame.add(label);
        label.setBounds(100, 50, 200, 20);
        this.loadingFrameProgressBar.setStringPainted(true);                 // set background image
        this.loadingFrameProgressBar.setFont(this.tahoma);
        this.loadingFrameProgressBar.setBounds(50, 80, 300, 20);            // Add Window Listener For debugging
        frame.setVisible(true);
        for(int i = 1;i <= 100;i++) {
            this.loadingFrameProgressBar.setString("Loading : " + i + "%");
            this.loadingFrameProgressBar.setValue(i);
            try {
                Thread.sleep(40);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        frame.dispose();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.buyButton) {
//            this.buy();
            System.out.println("BUY BUTTON");
        } else if (e.getSource() == this.sellButton) {
//            this.sellItem;
        } else if (e.getSource() == this.showMyItemsButton) {
            ShowMyItemFrame showMyItemFrame = new ShowMyItemFrame();
        }

    }


    public static void main(String[] args) {
        new ShopFrame(true);
    }
}
