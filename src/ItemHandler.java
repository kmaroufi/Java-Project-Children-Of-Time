/**
 * Created by asus-pc on 7/12/2016.
 */
public class ItemHandler {
    protected String name;
    protected Item.Type type;
    protected int size;
    protected String Description;
    protected CraftingRequirement craftingRequirement;

    //--------------------------------------------------- Constructors

    public ItemHandler(String name, Item.Type type, int size, String description, CraftingRequirement craftingRequirement) {
        this.name = name;
        this.type = type;
        this.size = size;
        Description = description;
        this.craftingRequirement = craftingRequirement;
    }

    //--------------------------------------------------- Getter && Setters


    public String getName() {
        return name;
    }

    public int getSize() {
        return size;
    }

    public String getDescription() {
        return Description;
    }

    public Item.Type getType() {
        return type;
    }

    public CraftingRequirement getCraftingRequirement() {
        return craftingRequirement;
    }
}
