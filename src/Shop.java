import GUI.Display;

import java.util.ArrayList;

/**
 * Created by asus-pc on 5/6/2016.
 */
public class Shop {
    public static ArrayList<ItemProperties<PerkItem>> listOfPerkItems = new ArrayList<>();
    public static ArrayList<ItemProperties<SkillItem>> listOfSkillItems = new ArrayList<>();

    private int budget;

    public static void showItems(){
        Display.printInEachLine("This shop offers you ");
        for (ItemProperties<PerkItem> itemProperties: Shop.listOfPerkItems) {
            Display.printInEachLine(itemProperties.getItem().getName() + " for " + itemProperties.getPrice() + " dollars ");
        }
        for (ItemProperties<SkillItem> itemProperties: Shop.listOfSkillItems) {
            Display.printInEachLine(itemProperties.getItem().getName() + " for " + itemProperties.getPrice() + " dollars ");
        }
    }

    public void sellItemToHero(String itemName) {                                       // hero buys an item
//        for(int i = 0;i < this.listOfItems.size();i++){
//            if(this.listOfItems.get(i).getItem().getName().equals(itemName)){
//                this.listOfItems.remove(this.listOfItems.get(i));
//                return;
//            }
//        }
        for (ItemProperties<PerkItem> itemProperties: Shop.listOfPerkItems) {
            if (itemProperties.getItem().getName().equals(itemName)) {

            }
        }
        // if this item doesn't exist
        Display.printInEachLine( itemName + " doesn't exist in Shop!" );
    }

    public void byeItemFromHero(Hero hero,String itemName) {                      // hero sells an item
        SkillItem skillItem = hero.getMapOfSkillItems().get(itemName);
        if (skillItem != null) {
            hero.getListOfSkillItems().remove(skillItem);
            hero.getMapOfSkillItems().remove(itemName);
            return;
        }
        PerkItem perkItem = hero.getMapOfPerkItems().get(itemName);
        if (perkItem != null) {
            hero.getListOfPerkItems().remove(perkItem);
            hero.getMapOfPerkItems().remove(itemName);
            return;
        }
        // if hero hasn't this item
        Display.printInEachLine(hero.getName() + " hasn't " + itemName);
    }

    public void sellPerkItemToHero(ItemProperties<PerkItem> itemProperties, Player player, Hero hero) {
        PerkItem perkItem = itemProperties.getItem().clone();
        perkItem.setWorth(itemProperties.getPrice());
        perkItem.setOwnerName(hero.getName());
        hero.addItem(perkItem);
        player.setMoney(player.getMoney() - itemProperties.getPrice());
        itemProperties.update();
        if (itemProperties.getNumberOfThisItem() == 0) {
            Shop.listOfPerkItems.remove(itemProperties);
            Display.printInEachLine("This item is no longer Available in this shop");
        }
    }

    public void sellSkillItemToHero(ItemProperties<SkillItem> itemProperties, Player player, Hero hero) {
        SkillItem skillItem = itemProperties.getItem().clone();
        skillItem.setWorth(itemProperties.getPrice());
        skillItem.setOwnerName(hero.getName());
        hero.addItem(skillItem);
        player.setMoney(player.getMoney() - itemProperties.getPrice());
        itemProperties.update();
        if (itemProperties.getNumberOfThisItem() == 0) {
            Shop.listOfSkillItems.remove(itemProperties);
            Display.printInEachLine("This item is no longer Available in this shop");
        }
    }

    public void buyPerkItemFromHero(ItemProperties<PerkItem> itemProperties,Player player, Hero hero) {
        PerkItem perkItem = itemProperties.getItem();
        hero.removeItem(perkItem);
        if (itemProperties.getCoefficientOfDecreaseOfValueWhenReBuying() == 0) {
            player.setMoney(player.getMoney() + perkItem.getWorth() - itemProperties.getDecreasePriceValueWhenReBuying());
        }
        else {
            player.setMoney(player.getMoney() + perkItem.getWorth() * itemProperties.getCoefficientOfDecreaseOfValueWhenReBuying());
        }
    }

    public void buySkillItemFromHero(ItemProperties<SkillItem> itemProperties, Player player, Hero hero) {
        SkillItem skillItem = itemProperties.getItem();
        hero.removeItem(skillItem);
        if (itemProperties.getCoefficientOfDecreaseOfValueWhenReBuying() == 0) {
            player.setMoney(player.getMoney() + skillItem.getWorth() - itemProperties.getDecreasePriceValueWhenReBuying());
        }
        else {
            player.setMoney(player.getMoney() + skillItem.getWorth() * itemProperties.getCoefficientOfDecreaseOfValueWhenReBuying());
        }
    }
}
