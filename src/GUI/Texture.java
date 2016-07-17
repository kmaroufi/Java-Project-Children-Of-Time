package GUI;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;

/**
 * Created by Future on 7/6/2016.
 */
public class Texture implements Serializable{
    private transient BufferedImage image;
    private int width;
    private int height;

    //------------------------------------------------------------------- Constructors

    public Texture() {
        this.width = 75;
        this.height = 75;
    }

    public Texture(String imageName) {
        try {
            this.image = ImageIO.read(new File("./resources/images/" + imageName + ".png"));
        } catch (IOException e) {
            return;
        }
        this.width  = 45;
        this.height = 45;
    }

    //-------------------------------------------------------------------- Functions

    public void render(Graphics graphics, int x, int y) {
        graphics.drawImage(this.image, x ,  y, this.width, this.height, null);
    }

    //-------------------------------------------------------------------- Getter And Setters
    public BufferedImage getImage() {
        return image;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
