package GUI;

import AbilityPackage.Perk;
import ItemPackage.*;
import ShopPackage.Shop;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Future on 7/11/2016.
 */
public class BuyItemFrame extends JFrame implements ActionListener,GameFrame{
    private JComboBox itemsBox;
    private JLabel itemImage;
    private JLabel itemNameLabel, itemPriceLabel, itemDescriptionLabel;
    private Font tahoma;
    private Shop shop;
    private Thread buyShopThread = new Thread(new Runnable() {
        @Override
        public void run() {
            while (true) {
                if (itemsBox != null && itemsBox.getItemCount() != 0) {
                    process();
                }
                try {
                    Thread.sleep(30);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    });
    private JButton buyButton;

    public BuyItemFrame(Shop shop){
        this.shop = shop;
        this.buyButton = new JButton();
        this.itemImage = new JLabel(new ImageIcon("./resources/images/Barrier.png"));
        this.itemNameLabel = new JLabel("ITEM INFORMATION"); // item's information ---->> name - price description
        this.itemPriceLabel = new JLabel("ItemPackage Price");
        this.itemDescriptionLabel = new JLabel("ItemPackage Description");
        this.itemsBox = new JComboBox();
        //-------------------------------------------------------
        this.setButtons();
        this.setBackgroundImage();
        this.addItemLists();
        this.showFrame();
        //-------------------------------------------------------
        this.buyShopThread.start();
    }

    public void addItemLists(/*ArrayList<ItemPackage>*/){
        //adding items from the GameEngine.items
        for (ItemProperties<SkillItem> itemProperties: shop.listOfSkillItems) {
            this.itemsBox.addItem(itemProperties.getItem().getName());
        }
        for (ItemProperties<PerkItem> itemProperties: shop.listOfPerkItems) {
            this.itemsBox.addItem(itemProperties.getItem().getName());
        }
    }

    private void setItemInformation(){
        String itemName = (String) this.itemsBox.getSelectedItem();
        ItemProperties<SkillItem> itemProperties = null;
        for (ItemProperties<SkillItem> itemProperties1: shop.listOfSkillItems) {
            if (itemProperties1.getItem().getName().equals(itemName)) {
                itemProperties = itemProperties1;
                System.out.println(itemProperties.getPrice());
                this.itemNameLabel.setText("ItemPackage Name : " + itemName);// item - 1
                this.itemPriceLabel.setText("ItemPackage Price : " + itemProperties.getPrice() + "$");
                this.itemDescriptionLabel.setText("Description : " + itemProperties.getItem().getDescription());
                break;
            }
        }
        ItemProperties<PerkItem> perkItemItemProperties;
        for (ItemProperties<PerkItem> itemProperties1: shop.listOfPerkItems) {
            if (itemProperties1.getItem().getName().equals(itemName)) {
                perkItemItemProperties = itemProperties1;
                System.out.println(perkItemItemProperties.getPrice());
                this.itemNameLabel.setText("ItemPackage Name : " + itemName);// item - 1
                this.itemPriceLabel.setText("ItemPackage Price : " + perkItemItemProperties.getPrice() + "$");
                this.itemDescriptionLabel.setText("Description : " + perkItemItemProperties.getItem().getDescription());
                break;
            }
        }

//        this.itemImage.setText("This is ItemPackage Image(!!!)");
    }

    public void process(){
        setItemInformation();
    }

    @Override
    public void setBackgroundImage() {

    }

    @Override
    public void addActionListeners() {
        this.buyButton.addActionListener(this);
    }

    @Override
    public void showFrame(){
        this.getContentPane().setBackground(Color.WHITE);
        this.setSize(600, 600);
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        this.setLayout(null);
        //----------------------------------------------------
        this.addComponents();
        this.setFonts();
        this.setComponentsBounds();
        //----------------------------------------------------
        setVisible(true);
    }

    @Override
    public void setButtons() {
        this.buyButton.setName("BuyButton");
        this.setButton(buyButton);
    }

    @Override
    public void addComponents() {
        this.add(buyButton);
        this.add(itemNameLabel);
        this.add(itemImage);
        this.add(itemDescriptionLabel);
        this.add(itemPriceLabel);
        this.add(itemsBox);
    }

    @Override
    public void setComponentsBounds() {
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

    @Override
    public void setFonts() {
        this.tahoma = new Font("Tahoma", Font.PLAIN, 14);
        this.itemNameLabel.setFont(tahoma);
        this.itemPriceLabel.setFont(tahoma);
        this.itemDescriptionLabel.setFont(tahoma);
        this.itemsBox.setFont(tahoma);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == buyButton) {
            //BUY PROCEDURE
        }
    }

}
