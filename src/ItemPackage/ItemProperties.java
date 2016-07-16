package ItemPackage;

/**
 * Created by asus-pc on 5/6/2016.
 */
public class ItemProperties<T> {
    private T item;
    private int numberOfThisItem;
    private boolean countLess;
    private int timeOfBuyThisItem;
    private double increasePriceValue;
    private double coefficientOfPriceIncrease;
    private double price;
    private double coefficientOfDecreaseOfValueWhenReBuying;
    private double decreasePriceValueWhenReBuying;

    //--------------------------------------------------- Constructors

    public ItemProperties(T item, int numberOfThisItem, double increasePriceValue, double coefficientOfPriceIncrease, double price, double coefficientOfDecreaseOfValueWhenReBuying, double decreasePriceValueWhenReBuying) {
        this.item = item;
        this.numberOfThisItem = numberOfThisItem;
        this.countLess = false;
        this.timeOfBuyThisItem = 0;
        this.increasePriceValue = increasePriceValue;
        this.coefficientOfPriceIncrease = coefficientOfPriceIncrease;
        this.price = price;
        this.coefficientOfDecreaseOfValueWhenReBuying = coefficientOfDecreaseOfValueWhenReBuying;
        this.decreasePriceValueWhenReBuying = decreasePriceValueWhenReBuying;
    }

    public ItemProperties(T item, double increasePriceValue, double coefficientOfPriceIncrease, double price, double coefficientOfDecreaseOfValueWhenReBuying, double decreasePriceValueWhenReBuying) {
        this.item = item;
        this.numberOfThisItem = 9999999;
        this.countLess = true;
        this.timeOfBuyThisItem = 0;
        this.increasePriceValue = increasePriceValue;
        this.coefficientOfPriceIncrease = coefficientOfPriceIncrease;
        this.price = price;
        this.coefficientOfDecreaseOfValueWhenReBuying = coefficientOfDecreaseOfValueWhenReBuying;
        this.decreasePriceValueWhenReBuying = decreasePriceValueWhenReBuying;
    }

    //--------------------------------------------------- Functions

    public void update() {
        if (this.coefficientOfPriceIncrease == 0)
            this.price += this.increasePriceValue;
        else {
            this.price *= this.coefficientOfPriceIncrease;
        }
        this.timeOfBuyThisItem++;
        if (!this.countLess) {
            this.numberOfThisItem--;
        }
    }

    //--------------------------------------------------- Getter && Setters

    public T getItem() {
        return item;
    }

    public void setItem(T item) {
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

    public int getNumberOfThisItem() {
        return numberOfThisItem;
    }

    public void setNumberOfThisItem(int numberOfThisItem) {
        this.numberOfThisItem = numberOfThisItem;
    }

    public boolean isCountLess() {
        return countLess;
    }

    public void setCountLess(boolean countLess) {
        this.countLess = countLess;
    }

    public double getCoefficientOfDecreaseOfValueWhenReBuying() {
        return coefficientOfDecreaseOfValueWhenReBuying;
    }

    public void setCoefficientOfDecreaseOfValueWhenReBuying(double coefficientOfDecreaseOfValueWhenReBuying) {
        this.coefficientOfDecreaseOfValueWhenReBuying = coefficientOfDecreaseOfValueWhenReBuying;
    }

    public double getDecreasePriceValueWhenReBuying() {
        return decreasePriceValueWhenReBuying;
    }

    public void setDecreasePriceValueWhenReBuying(double decreasePriceValueWhenReBuying) {
        this.decreasePriceValueWhenReBuying = decreasePriceValueWhenReBuying;
    }
}
