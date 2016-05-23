import java.util.ArrayList;
import java.util.Map;

/**
 * Created by asus-pc on 5/6/2016.
 */
public class Shop {
    public static ArrayList<ItemProperties> listOfItems = new ArrayList<>();

    public static void showItems(){
        Display.printInEachLine("This shop offers you ");
        for(ItemProperties itemProperties : listOfItems){
            Display.printInEachLine(itemProperties.getItem().getName() + " for " + itemProperties.getPrice() + " dollars ");
        }
    }

    public void sellItemToHero(String itemName) {                                       // hero buys an item
        for(int i = 0;i < this.listOfItems.size();i++){
            if(this.listOfItems.get(i).getItem().getName().equals(itemName)){
                this.listOfItems.remove(this.listOfItems.get(i));
                return;
            }
        }
        // if this item doesn't exist
        Display.printInEachLine( itemName + " doesn't exist in Shop!" );
    }

    public void byeItemFromHero(Hero hero,String itemName) {                      // hero sells an item
        for(int i = 0;i < hero.getListOfItems().size();i++){
            if(hero.getListOfItems().get(i).getName().equals(itemName)){
                hero.getListOfItems().remove(hero.getListOfItems().get(i));
                return;
            }
        }
        // if hero hasn't this item
        Display.printInEachLine(hero.getName() + " hasn't " + itemName);
    }
}
