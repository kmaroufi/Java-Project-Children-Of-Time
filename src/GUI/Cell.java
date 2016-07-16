package GUI;

import java.awt.*;
import java.io.Serializable;

/**
 * Created by Future on 7/6/2016.
 */
public class Cell implements Serializable{
    private Texture texture;
    private String mode;
    private Point center;
    private int x , y;
    private int width,height;

    //--------------------------------------------------------------------------- Constructors

    public Cell(String mode, int x, int y) {// ShopPackage(3) - Empty(1) - WarRoom(4) - Barrier(2) - SkillRoom(5) - Door(6)
        this.mode = mode;
        this.x = x;
        this.y = y;
        this.texture = new Texture(this.mode);
        this.width = 50;
        this.height = 50;
        this.center = new Point(this.x + (this.width / 2), this.y + (this.height / 2));
    }

    //--------------------------------------------------------------------------- Functions
    public void update(){
        this.texture = new Texture(this.mode);
        this.width = 50;
        this.height = 50;
        this.center = new Point(this.x + (this.width / 2), this.y + (this.height / 2));
    }

    public void render(Graphics graphics) {
        this.texture.render(graphics, this.x, this.y);
    }

    //--------------------------------------------------------------------------- Getter And Setters

    public Texture getTexture() {
        return texture;
    }

    public void setTexture(Texture texture) {
        this.texture = texture;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
        update();
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public Point getCenter() {
        return center;
    }

    public boolean containsPoint(double x, double y) {
        Rectangle cellRectangle = new Rectangle(this.x, this.y, this.width, this.height);
        return cellRectangle.contains(x, y);
    }
}
