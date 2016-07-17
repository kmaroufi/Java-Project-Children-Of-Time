package ItemPackage;
import SoldierPackage.*;
import AbilityPackage.*;

import java.io.Serializable;

/**
 * Created by asus-pc on 7/12/2016.
 */
public class PerkItem extends Item implements Serializable{
    private Perk perk;
    private boolean isEffectJustOneTimeInstantlyAfterPerching;

    //--------------------------------------------------- Constructors

    public PerkItem(ItemHandler itemHandler, Perk perk, boolean isEffectJustOneTimeInstantlyAfterPerching) {
        super(itemHandler);
        this.perk = perk;
        this.isEffectJustOneTimeInstantlyAfterPerching = isEffectJustOneTimeInstantlyAfterPerching;
    }


    //--------------------------------------------------- Functions

    public PerkItem clone(){
        PerkItem perkItem = null;
        try {
            perkItem = (PerkItem) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        perkItem.setPerk(this.perk.clone());
        return perkItem;
    }

    public <T extends Soldier> void updateItemEffect(T user) {
        updateItemEffect(null, null, user);
    }

    public <T extends Soldier> void updateItemEffect(Enemy enemy, Hero hero, T user) {
        this.perk.updatePerkEffect(enemy, hero, user);
        if (isEffectJustOneTimeInstantlyAfterPerching) {
            Hero.mapOfHeroes.get(this.ownerName).removeItem(this);
        }
    }

    public void removeEffect() {
        this.perk.removeEffect();
    }



    //--------------------------------------------------- Getter && Setters


    public Perk getPerk() {
        return perk;
    }

    public void setPerk(Perk perk) {
        this.perk = perk;
    }

    public boolean isEffectJustOneTimeInstantlyAfterPerching() {
        return isEffectJustOneTimeInstantlyAfterPerching;
    }

    public void setEffectJustOneTimeInstantlyAfterPerching(boolean effectJustOneTimeInstantlyAfterPerching) {
        isEffectJustOneTimeInstantlyAfterPerching = effectJustOneTimeInstantlyAfterPerching;
    }
}
