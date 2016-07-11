package GUI;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Future on 7/12/2016.
 */
public class ShopFrame extends JFrame{
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


}
