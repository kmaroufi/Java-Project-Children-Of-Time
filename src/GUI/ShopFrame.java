package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Created by Future on 7/12/2016.
 */
public class ShopFrame extends JFrame implements ActionListener{
    private JButton buyButton;
    private JButton sellButton;
    private JButton showMyItemsButton;
    private JButton addNewItemButton;
    private JProgressBar loadingFrameProgressBar;
    private JLabel playerNameLabel;
    private JLabel playerMoneyLabel;
    private JLabel playerCapacityLabel;
    private JLabel playerImage;
    private ArrayList<JLabel> playerItems;
    private Font tahoma;
    private boolean isCustom;


    public ShopFrame(boolean isCustom) {

        this.tahoma = new Font("Tahoma", Font.PLAIN, 16);
        this.playerNameLabel = new JLabel("PLAYER NAME");
        this.playerMoneyLabel = new JLabel("PLAYER MONEY");
        this.playerItems = new ArrayList<>();
        this.playerCapacityLabel = new JLabel("CAPACITY");
        this.playerImage = new JLabel(new ImageIcon("./resources/images/Player(Down-1).png"));
        this.buyButton = new JButton();
        this.sellButton = new JButton(("Sell"));
        this.showMyItemsButton = new JButton("Show My Items");
        this.isCustom = isCustom;
        this.loadingFrameProgressBar = new JProgressBar(0, 100);
        this.customInitialization();
        //----------------------------------------------------
//        this.setPlayerInformation(new Player());                          // CORRECT IT!
        this.setComponentsFont();
        this.showFrame();
        this.setButtons();
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

    public void setButtons(){
        setBuyButtonImage();
        setShowMyItemsButtonImage();
        setAddNewItemButtonImage();
        setSellButtonImage();
    }

    private void setSellButtonImage() {
        ImageIcon buyButtonImage = new ImageIcon("./resources/images/SellButton.png");
        this.sellButton.setBorderPainted(false);
        this.sellButton.setContentAreaFilled(false);
        this.sellButton.setFocusPainted(false);
        this.sellButton.setOpaque(false);
        this.sellButton.setIcon(buyButtonImage);
        this.sellButton.setSize(buyButtonImage.getIconWidth(), buyButtonImage.getIconHeight());
        this.sellButton.addActionListener(this);
    }

    private void setAddNewItemButtonImage() {
        ImageIcon buyButtonImage = new ImageIcon("./resources/images/AddNewItemButton.png");
        this.addNewItemButton.setBorderPainted(false);
        this.addNewItemButton.setContentAreaFilled(false);
        this.addNewItemButton.setFocusPainted(false);
        this.addNewItemButton.setOpaque(false);
        this.addNewItemButton.setIcon(buyButtonImage);
        this.addNewItemButton.setSize(buyButtonImage.getIconWidth(), buyButtonImage.getIconHeight());
        this.addNewItemButton.addActionListener(this);
    }

    private void setShowMyItemsButtonImage() {
        ImageIcon buyButtonImage = new ImageIcon("./resources/images/ShowMyItemsButton.png");
        this.showMyItemsButton.setBorderPainted(false);
        this.showMyItemsButton.setContentAreaFilled(false);
        this.showMyItemsButton.setFocusPainted(false);
        this.showMyItemsButton.setOpaque(false);
        this.showMyItemsButton.setIcon(buyButtonImage);
        this.showMyItemsButton.setSize(buyButtonImage.getIconWidth(), buyButtonImage.getIconHeight());
        this.showMyItemsButton.addActionListener(this);
    }

    public void setBuyButtonImage() {
        ImageIcon buyButtonImage = new ImageIcon("./resources/images/BuyButton.png");
        this.buyButton.setBorderPainted(false);
        this.buyButton.setContentAreaFilled(false);
        this.buyButton.setFocusPainted(false);
        this.buyButton.setOpaque(false);
        this.buyButton.setIcon(buyButtonImage);
        this.buyButton.setSize(buyButtonImage.getIconWidth(), buyButtonImage.getIconHeight());
        this.buyButton.addActionListener(this);
    }

    public void setComponentsFont(){
        this.buyButton.setFont(tahoma);
        this.sellButton.setFont(tahoma);
        this.showMyItemsButton.setFont(tahoma);
        this.playerCapacityLabel.setFont(tahoma);
        this.playerMoneyLabel.setFont(tahoma);
        this.playerNameLabel.setFont(tahoma);
        this.addNewItemButton.setFont(tahoma);
    }

    public void showFrame(){
//        this.showProgressFrame();
        this.getContentPane().setBackground(Color.WHITE);
        this.setSize(475, 400);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.add(buyButton);
        this.add(sellButton);
        this.add(showMyItemsButton);
        this.add(playerNameLabel);
        this.add(playerCapacityLabel);
        this.add(playerMoneyLabel);
        this.add(playerImage);
//        this.add(playerItems);
        Dimension playerImagePreferredSize = playerImage.getPreferredSize();
        this.playerImage.setBounds(200, 50, playerImagePreferredSize.width, playerImagePreferredSize.height);

        Dimension playerNamePreferredSize = playerNameLabel.getPreferredSize();
        this.playerNameLabel.setBounds(50, 50, playerNamePreferredSize.width + 100, playerNamePreferredSize.height);


        Dimension playerMoneyPreferredSize = playerMoneyLabel.getPreferredSize();
        this.playerMoneyLabel.setBounds(50, 70, playerMoneyPreferredSize.width + 100, playerMoneyPreferredSize.height);

        Dimension playerCapacityPreferredSize = playerMoneyLabel.getPreferredSize();
        this.playerCapacityLabel.setBounds(50, 90, playerCapacityPreferredSize.width + 100, playerCapacityPreferredSize.height);

        Insets insets = this.getInsets();
        Dimension buyButtonPreferredSize = this.buyButton.getPreferredSize();
        this.buyButton.setBounds(50 + insets.left, 200 + insets.top, buyButtonPreferredSize.width + 100, buyButtonPreferredSize.height);
        System.out.println(buyButtonPreferredSize.width);
        System.out.println(buyButtonPreferredSize.height);
        Dimension sellButtonPreferredSize = this.sellButton.getPreferredSize();
        this.sellButton.setBounds(250 + insets.left, 250 + insets.top, sellButtonPreferredSize.width + 100, sellButtonPreferredSize.height);
        Dimension showMyItemsButtonPreferredSize = this.showMyItemsButton.getPreferredSize();
        this.showMyItemsButton.setBounds(50 + insets.left, 300 + insets.top, showMyItemsButtonPreferredSize.width + 17, showMyItemsButtonPreferredSize.height);
        this.add(addNewItemButton);
        Dimension addNewItemPreferredSize = this.addNewItemButton.getPreferredSize();
        this.addNewItemButton.setBounds(220 + insets.left, 300 + insets.top, addNewItemPreferredSize.width + 28, addNewItemPreferredSize.height);
        this.setVisible(true);
    }

    private void customInitialization(){
        this.addNewItemButton = new JButton("Add New Item");
        this.addNewItemButton.setFont(tahoma);
    }

    public void showProgressFrame(){
        JFrame frame = new JFrame("Progress");
        JLabel label = new JLabel("Please Wait While Loading Shop...");
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



}
