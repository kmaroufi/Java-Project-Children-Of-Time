package GUI;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Future on 7/12/2016.
 */
public class BuyItemFrame extends JFrame {
    private JComboBox itemsBox;
    private JLabel itemImage;
    private JLabel itemNameLabel, itemPriceLabel, itemDescriptionLabel;
    private Font tahoma;
    private Thread buyShopThread = new Thread(new Runnable() {
        @Override
        public void run() {
            while (true) {
                if (itemsBox != null && itemsBox.getItemCount() != 0) {
                    process();
                }
            }
        }
    });
    private JButton buyButton;

    public BuyItemFrame(){
        this.buyButton = new JButton(new ImageIcon("./resources/images/BuyButton.png"));
        this.itemImage = new JLabel(new ImageIcon("./resources/images/Barrier.png"));
        this.itemNameLabel = new JLabel("ITEM INFORMATION"); // item's information ---->> name - price description
        this.itemPriceLabel = new JLabel("Hello Fred");
        this.itemDescriptionLabel = new JLabel("Hello Mary");
        this.itemsBox = new JComboBox();
        //-------------------------------------------------------
        this.buyButtonSetting();
        this.addItemLists();
        this.setComponentsFont();
        this.showFrame();
        //-------------------------------------------------------
        this.buyShopThread.start();
    }

}
