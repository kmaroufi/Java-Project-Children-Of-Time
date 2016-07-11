import java.awt.*;

/**
 * Created by Future on 7/5/2016.
 */
public class Tile {
    private String mode; // Barrier - StoryBook - WarRoom - Shop - SkillRoom - Door - FinalWar
    private Image icon;
    private Hero heroInHere;    // if There is No Hero There it's null
    //--------------------------------------------------------------------- Constructor

    public Tile(String mode, Image icon, Hero heroInHere) {
        this.mode = mode;
        this.icon = icon;
        this.heroInHere = heroInHere;
    }

    //--------------------------------------------------------------------- Fucntions

    //--------------------------------------------------------------------- Getter && Setter

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public Image getIcon() {
        return icon;
    }

    public void setIcon(Image icon) {
        this.icon = icon;
    }

    public Hero getHeroInHere() {
        return heroInHere;
    }

    public void setHeroInHere(Hero heroInHere) {
        this.heroInHere = heroInHere;
    }
}
