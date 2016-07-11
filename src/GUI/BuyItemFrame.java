package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Future on 7/12/2016.
 */
public class BuyItemFrame extends JFrame implements ActionListener{
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
    //------------------------------------------------------------------------------- Constructors
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

    //-------------------------------------------------------------------------------- Functions
    public void setComponentsFont(){
        this.tahoma = new Font("Tahoma", Font.PLAIN, 14);
        this.itemNameLabel.setFont(tahoma);
        this.itemPriceLabel.setFont(tahoma);
        this.itemDescriptionLabel.setFont(tahoma);
        this.itemsBox.setFont(tahoma);
    }

    public void addItemLists(/*ArrayList<Item>*/){
        //adding items from the GameEngine.items
        for(int i = 1;i <= 10;i++) {
            this.itemsBox.addItem("Item - " + i);
        }
    }

    private void setItemInformation(){
        String itemName = (String) this.itemsBox.getSelectedItem();
        this.itemNameLabel.setText("Item Name : " + itemName);// item - 1
        this.itemPriceLabel.setText("Item Price : " + itemName.charAt(7) + "$");
        this.itemDescriptionLabel.setText("Description : " + "heheheh");

//        this.itemImage.setText("This is Item Image(!!!)");
    }

    public void process(){
        setItemInformation();
    }

    private void buyButtonSetting(){
        this.buyButton.setBorderPainted(false);
        this.buyButton.setContentAreaFilled(false);
        this.buyButton.setFocusPainted(false);
        this.buyButton.setOpaque(false);
        this.buyButton.addActionListener(this);
    }


    private void showFrame(){
        this.getContentPane().setBackground(Color.WHITE);
        this.setSize(600, 600);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.add(buyButton);
        this.add(itemNameLabel);
        this.add(itemImage);
        this.add(itemDescriptionLabel);
        this.add(itemPriceLabel);
        this.add(itemsBox);
        setVisible(true);
        //----------------------------------------------------
        Insets insets = this.getInsets();
        Dimension itemBoxPreferredSize = this.itemsBox.getPreferredSize();
        this.itemsBox.setBounds(50 + insets.left , 50 + insets.top, itemBoxPreferredSize.width + 400, itemBoxPreferredSize.height);
        Dimension itemNamePreferredSize = this.itemNameLabel.getPreferredSize();
        this.itemNameLabel.setBounds(50 + insets.left, 100 + insets.top, itemNamePreferredSize.width + 100, itemNamePreferredSize.height);
        Dimension itemPricePreferrredSize = this.itemPriceLabel.getPreferredSize();
        this.itemPriceLabel.setBounds(50 + insets.left, 120 + insets.top, itemPricePreferrredSize.width + 100, itemPricePreferrredSize.height);
        Dimension itemDescriptionPreferredSize = this.itemDescriptionLabel.getPreferredSize();
        this.itemDescriptionLabel.setBounds(50 + insets.left, 140 + insets.top, itemDescriptionPreferredSize.width + 200, itemDescriptionPreferredSize.height);
        Dimension itemImagePreferredSize = this.itemImage.getPreferredSize();
        this.itemImage.setBounds(400 + insets.left, 50 + insets.top, itemImagePreferredSize.width+100, itemImagePreferredSize.height + 100);
        Dimension buyButtonPreferredSize = this.buyButton.getPreferredSize();
        this.buyButton.setBounds(200 + insets.left , 200 + insets.top, buyButtonPreferredSize.width, buyButtonPreferredSize.height);

    }

    public static void main(String[] args) {
        new BuyItemFrame();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == buyButton) {
            //BUY PROCEDURE
        }
    }
}
