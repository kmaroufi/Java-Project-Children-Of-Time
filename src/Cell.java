import java.awt.*;

/**
 * Created by Future on 7/10/2016.
 */
public class Cell {
    private Texture texture;
    private String mode;
    private Point center;
    private int x , y;
    private int width,height;

    public Cell(String mode, int x, int y) {// Shop(3) - Empty(1) - WarRoom(4) - Barrier(2) - SkillRoom(5) - Door(6)
        this.mode = mode;
        this.x = x;
        this.y = y;
        this.texture = new Texture(this.mode);
        this.width = 50;
        this.height = 50;
        this.center = new Point(this.x + (this.width / 2), this.y + (this.height / 2));
    }


    public void update(){
        this.texture = new Texture(this.mode);
        this.width = 75;
        this.height = 75;
        this.center = new Point(this.x + (this.width / 2), this.y + (this.height / 2));
    }


}
