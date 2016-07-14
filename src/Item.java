import GUI.Display;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by asus-pc on 5/6/2016.
 */
public class Item implements Cloneable{

    protected String name;
    protected Type type;
    protected String ownerName;
    protected int size;
    protected String Description;
    protected double worth;
    protected CraftingRequirement craftingRequirement;

    public static enum Type {
        SkillItem, PerkItem
    }

    //--------------------------------------------------- Constructors
    public Item(){}

    public Item(ItemHandler itemHandler) {
        setName(itemHandler.getName());
        setType(itemHandler.getType());
        setOwnerName(null);
        setSize(itemHandler.getSize());
        setDescription(itemHandler.getDescription());
        setWorth(0);
        setCraftingRequirement(itemHandler.getCraftingRequirement());
    }
    //--------------------------------------------------- Functions

    public void showDescription(){
        Display.printInEachLine(this.getDescription());
    }

    public boolean equals(Item item){
        if(this.name.equals(item.getName())){
            return true;
        }
        return false;
    }


    //--------------------------------------------------- Getter && Setters


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public double getWorth() {
        return worth;
    }

    public void setWorth(double worth) {
        this.worth = worth;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public CraftingRequirement getCraftingRequirement() {
        return craftingRequirement;
    }

    public void setCraftingRequirement(CraftingRequirement craftingRequirement) {
        this.craftingRequirement = craftingRequirement;
    }
}
