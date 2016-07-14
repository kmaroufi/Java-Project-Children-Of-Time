/**
 * Created by asus-pc on 7/14/2016.
 */
public class CraftingRequirement {
    private int water;
    private int air;
    private int aether;
    private int fire;

    public CraftingRequirement(int water, int air, int aether, int fire) {
        this.water = water;
        this.air = air;
        this.aether = aether;
        this.fire = fire;
    }

    public int getWater() {
        return water;
    }

    public void setWater(int water) {
        this.water = water;
    }

    public int getAir() {
        return air;
    }

    public void setAir(int air) {
        this.air = air;
    }

    public int getAether() {
        return aether;
    }

    public void setAether(int aether) {
        this.aether = aether;
    }

    public int getFire() {
        return fire;
    }

    public void setFire(int fire) {
        this.fire = fire;
    }
}
