/**
 * Created by asus-pc on 5/6/2016.
 */
public class ItemProperties {
    private Item item;
    private int timeOfBuyThisItem;
    private double increasePriceValue;
    private double coefficientOfPriceIncrease;
    private double price;

    public ItemProperties(Item item, int timeOfBuyThisItem, double increasePriceValue, double coefficientOfPriceIncrease, double price) {
        this.item = item;
        this.timeOfBuyThisItem = timeOfBuyThisItem;
        this.increasePriceValue = increasePriceValue;
        this.coefficientOfPriceIncrease = coefficientOfPriceIncrease;
        this.price = price;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public int getTimeOfBuyThisItem() {
        return timeOfBuyThisItem;
    }

    public void setTimeOfBuyThisItem(int timeOfBuyThisItem) {
        this.timeOfBuyThisItem = timeOfBuyThisItem;
    }

    public double getIncreasePriceValue() {
        return increasePriceValue;
    }

    public void setIncreasePriceValue(double increasePriceValue) {
        this.increasePriceValue = increasePriceValue;
    }

    public double getCoefficientOfPriceIncrease() {
        return coefficientOfPriceIncrease;
    }

    public void setCoefficientOfPriceIncrease(double coefficientOfPriceIncrease) {
        this.coefficientOfPriceIncrease = coefficientOfPriceIncrease;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void updatePrice() {
        if (this.coefficientOfPriceIncrease == 0)
            this.price += this.increasePriceValue;
        else {
            this.price *= this.coefficientOfPriceIncrease;
        }
    }
}
